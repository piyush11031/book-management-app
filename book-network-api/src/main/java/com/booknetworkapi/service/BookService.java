package com.booknetworkapi.service;

import com.booknetworkapi.common.PageResponse;
import com.booknetworkapi.dto.BookDTO;
import com.booknetworkapi.dto.BookResponse;
import com.booknetworkapi.dto.BorrowedBookResponse;
import com.booknetworkapi.entity.Book;
import com.booknetworkapi.entity.BookTransactionHistory;
import com.booknetworkapi.entity.user.SecurityUser;
import com.booknetworkapi.common.BookMapper;
import com.booknetworkapi.exception.OperationNotPermittedException;
import com.booknetworkapi.file.FileStorageService;
import com.booknetworkapi.repository.BookRepository;
import com.booknetworkapi.repository.BookSpecification;
import com.booknetworkapi.repository.BookTransactionHistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final BookTransactionHistoryRepository transactionHistoryRepository;
    private final FileStorageService fileStorageService;

    public Long save(BookDTO bookDTO, SecurityUser user) {

        Book book = bookMapper.toBook(bookDTO);
        book.setOwner(user.getUser());

        Book savedBook = bookRepository.save(book);
        return savedBook.getId();
    }

    public BookResponse findById(Long id) {

         return bookRepository.findById(id)
                .map(bookMapper::toBookResponse)
                .orElseThrow(() -> new EntityNotFoundException("No book found with id " + id));
    }

    public PageResponse<BookResponse> findAllBooks(int page, int pageSize, SecurityUser user) {

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("createdDate").descending());

        //We need to find all the books of all users except the current user
        Page<Book> books = bookRepository.findALlDisplayableBooks(pageable, user.getId());

        return getBookResponsePageResponse(books);
    }

    private PageResponse<BookResponse> getBookResponsePageResponse(Page<Book> books) {
        List<BookResponse> bookResponses = books.stream()
                                                .map(bookMapper::toBookResponse)
                                                .toList();

        return new PageResponse<>(
                bookResponses,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isFirst(),
                books.isLast()
        );
    }

    public PageResponse<BookResponse> findAllBooksByOwner(int page, int pageSize, SecurityUser user) {

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("createdDate").descending());

        //The specification enables us to fetch all the book with user id, from the book repository
        Page<Book> books = bookRepository.findAll(BookSpecification.withUserId(user.getId()), pageable);

        return getBookResponsePageResponse(books);

    }

    public PageResponse<BorrowedBookResponse> findAllBorrowedBooks(int page, int pageSize, SecurityUser user) {

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("createdDate").descending());

        Page<BookTransactionHistory> allBorrowedBooks =
                transactionHistoryRepository.findAllBorrowedBooks(pageable, user.getId());

        List<BorrowedBookResponse> bookResponses = allBorrowedBooks.stream()
                .map(bookMapper::toBorrowedBookResponse)
                .toList();

        return new PageResponse<>(
                bookResponses,
                allBorrowedBooks.getNumber(),
                allBorrowedBooks.getSize(),
                allBorrowedBooks.getTotalElements(),
                allBorrowedBooks.getTotalPages(),
                allBorrowedBooks.isFirst(),
                allBorrowedBooks.isLast()
        );
    }

    public PageResponse<BorrowedBookResponse> findAllReturnedBooks(int page, int pageSize, SecurityUser user) {

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("createdDate").descending());

        Page<BookTransactionHistory> allReturnedBooks =
                transactionHistoryRepository.findAllReturnedBooks(pageable, user.getId());

        List<BorrowedBookResponse> bookResponses = allReturnedBooks.stream()
                .map(bookMapper::toBorrowedBookResponse)
                .toList();

        return new PageResponse<>(
                bookResponses,
                allReturnedBooks.getNumber(),
                allReturnedBooks.getSize(),
                allReturnedBooks.getTotalElements(),
                allReturnedBooks.getTotalPages(),
                allReturnedBooks.isFirst(),
                allReturnedBooks.isLast()
        );
    }

    public Long updateSharableStatus(Long bookId, SecurityUser user) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("No book found with id " + bookId));

        //Only the owner of the book can update the book
        if (!Objects.equals(user.getId(), book.getOwner().getId())){
            throw new OperationNotPermittedException("You cannot update book's sharable status");
        }

        book.setSharable(!book.getSharable());
        bookRepository.save(book);
        return bookId;
    }

    public Long updateArchivedStatus(Long bookId, SecurityUser user) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("No book found with id " + bookId));

        //Only the owner of the book can update the book
        if (!Objects.equals(user.getId(), book.getOwner().getId())){
            throw new OperationNotPermittedException("You cannot update book's archive status");
        }

        book.setArchived(!book.getSharable());
        bookRepository.save(book);
        return bookId;
    }

    public Long borrowBook(Long bookId, SecurityUser user) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("No book found with id " + bookId));

        if(book.getArchived() == true || !book.getSharable()){
            throw new OperationNotPermittedException("The requested book cannot be borrowed because it is archived or not sharable");
        }

        if (Objects.equals(user.getId(), book.getOwner().getId())){
            throw new OperationNotPermittedException("User cannot borrow his own book");
        }

        final boolean isAlreadyBorrowed = transactionHistoryRepository.isAlreadyBorrowedByUser(bookId, user.getId());

        if(isAlreadyBorrowed){
            throw new OperationNotPermittedException("The requested book is already borrowed");
        }

        //Finally after all the checks we can borrow the book
        BookTransactionHistory history = BookTransactionHistory.builder()
                .user(user.getUser())
                .book(book)
                .returned(false)
                .returnApproved(false)
                .build();
        return transactionHistoryRepository.save(history).getId();
    }

    public Long returnBorrowedBook(Long bookId, SecurityUser user) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("No book found with id " + bookId));

        if(book.getArchived() == true || !book.getSharable()){
            throw new OperationNotPermittedException("The requested book cannot be returned because it is archived or not sharable");
        }

        if (Objects.equals(user.getId(), book.getOwner().getId())){
            throw new OperationNotPermittedException("User cannot return his own book");
        }

        //If the user has borrowed the book, it will have an entry in the BookTransactionHistory repository
        var transactionHistory = transactionHistoryRepository.findByBookIdAndUserId(bookId, user.getId())
                .orElseThrow(() -> new OperationNotPermittedException("User has not borrowed this book"));

        //After all the checks, user can return the book
        transactionHistory.setReturned(true);

        return transactionHistoryRepository.save(transactionHistory).getId();

    }

    public Long approveReturnOfBorrowedBook(Long bookId, SecurityUser user) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("No book found with id " + bookId));

        if(book.getArchived() == true || !book.getSharable()){
            throw new OperationNotPermittedException("The requested book cannot be returned because it is archived or not sharable");
        }

        if (Objects.equals(user.getId(), book.getOwner().getId())){
            throw new OperationNotPermittedException("User cannot return his own book");
        }

        //Finds the entry in transaction table, where the connected user is the owner of the borrowed book
        var transactionHistory = transactionHistoryRepository.findByBookIdAndOwnerId(bookId, user.getId())
                .orElseThrow(() -> new OperationNotPermittedException("The books is not returned yet, so you cannot approve its return"));

        transactionHistory.setReturnApproved(true);
        return transactionHistoryRepository.save(transactionHistory).getId();


    }

    public void uploadBookCoverPicture(MultipartFile file, SecurityUser user, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("No book found with id " + bookId));

        //saveFile method uploads the image for the specific user
        //bookCover variable contains the file path of the uploaded image
        var bookCover = fileStorageService.saveFile(file, user.getId());
        book.setBookCover(bookCover);
        bookRepository.save(book);
    }
}
