package com.booknetworkapi.controller;

import com.booknetworkapi.common.PageResponse;
import com.booknetworkapi.dto.BookDTO;
import com.booknetworkapi.dto.BookResponse;
import com.booknetworkapi.dto.BorrowedBookResponse;
import com.booknetworkapi.entity.user.SecurityUser;
import com.booknetworkapi.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
@Tag(name = "Book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Long> saveBook(@RequestBody BookDTO bookDTO,
                                         @AuthenticationPrincipal SecurityUser user){

        return ResponseEntity.ok(bookService.save(bookDTO, user));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable(name = "bookId") Long id){

        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<BookResponse>> findAllBook(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int pageSize,
            @AuthenticationPrincipal SecurityUser user
    ){
        PageResponse<BookResponse> allBooks = bookService.findAllBooks(page, pageSize, user);
        return ResponseEntity.ok(allBooks);

    }

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<BookResponse>> findAllBooksByOwner(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int pageSize,
            @AuthenticationPrincipal SecurityUser user
    ){
        return ResponseEntity.ok(bookService.findAllBooksByOwner(page, pageSize, user));
    }

    @GetMapping("/borrowed")
    public ResponseEntity<PageResponse<BorrowedBookResponse>> findAllBorrowedBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int pageSize,
            @AuthenticationPrincipal SecurityUser user
    ){
        return ResponseEntity.ok(bookService.findAllBorrowedBooks(page, pageSize, user));
    }

    @GetMapping("/returned")
    public ResponseEntity<PageResponse<BorrowedBookResponse>> findAllReturnedBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int pageSize,
            @AuthenticationPrincipal SecurityUser user
    ){
        return ResponseEntity.ok(bookService.findAllReturnedBooks(page, pageSize, user));
    }

    @PatchMapping("/sharable/{bookId}") //Update sharable status of book
    public ResponseEntity<Long> updateSharableStatus(
            @PathVariable Long bookId,
            @AuthenticationPrincipal SecurityUser user){

        return ResponseEntity.ok(bookService.updateSharableStatus(bookId, user));
    }

    @PatchMapping("/archived/{bookId}") //Update sharable status of book
    public ResponseEntity<Long> updateArchivedStatus(
            @PathVariable Long bookId,
            @AuthenticationPrincipal SecurityUser user){

        return ResponseEntity.ok(bookService.updateArchivedStatus(bookId, user));
    }

    @PostMapping("/borrow/{bookId}")
    public ResponseEntity<Long> borrowBook(
            @PathVariable Long bookId,
            @AuthenticationPrincipal SecurityUser user
    ){
        return ResponseEntity.ok(bookService.borrowBook(bookId, user));
    }

    @PostMapping("/borrow/return/{bookId}")
    public ResponseEntity<Long> returnBorrowedBook(
            @PathVariable Long bookId,
            @AuthenticationPrincipal SecurityUser user
    ){
        return ResponseEntity.ok(bookService.returnBorrowedBook(bookId, user));
    }

    @PostMapping("/borrow/return/approve/{bookId}")
    public ResponseEntity<Long> approveReturnOfBorrowedBook(
            @PathVariable Long bookId,
            @AuthenticationPrincipal SecurityUser user
    ){
        return ResponseEntity.ok(bookService.approveReturnOfBorrowedBook(bookId, user));
    }

    @PostMapping(value = "/cover/{bookId}", consumes = "multipart/form-date")
    public ResponseEntity<?> uploadBookCover(
            @PathVariable Long bookId,
            @AuthenticationPrincipal SecurityUser user,
            @RequestPart("file") MultipartFile file
            //todo swagger
            ){
        bookService.uploadBookCoverPicture(file, user, bookId);
        return ResponseEntity.accepted().build();
    }

}