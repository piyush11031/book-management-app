package com.booknetworkapi.common;

import com.booknetworkapi.dto.BookDTO;
import com.booknetworkapi.dto.BookResponse;
import com.booknetworkapi.dto.BorrowedBookResponse;
import com.booknetworkapi.entity.Book;
import com.booknetworkapi.entity.BookTransactionHistory;
import com.booknetworkapi.file.FileUtils;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public Book toBook(BookDTO bookDTO){

        return Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .authorName(bookDTO.getAuthorName())
                .synopsis(bookDTO.getSynopsis())
                .sharable(bookDTO.getSharable())
                .isbn(bookDTO.getIsbn())
                .archived(false)
                .build();
    }

    public BookResponse toBookResponse(Book book) {

        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorName(book.getAuthorName())
                .isbn(book.getIsbn())
                .synopsis(book.getSynopsis())
                .rate(book.getRate())
                .archived(book.getArchived())
                .owner(book.getOwner().getEmail())
                .cover(FileUtils.readFileFromLocation(book.getBookCover()))
                .build();
    }

    public BorrowedBookResponse toBorrowedBookResponse(BookTransactionHistory history) {

        return BorrowedBookResponse.builder()
                .id(history.getBook().getId())
                .title(history.getBook().getTitle())
                .authorName(history.getBook().getAuthorName())
                .isbn(history.getBook().getIsbn())
                .rate(history.getBook().getRate())
                .returned(history.getReturned())
                .returnApproved(history.getReturnApproved())
                .build();
    }
}
