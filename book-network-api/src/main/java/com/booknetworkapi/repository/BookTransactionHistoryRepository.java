package com.booknetworkapi.repository;

import com.booknetworkapi.entity.BookTransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, Long> {

    @Query("""
SELECT history FROM BookTransactionHistory history
    WHERE history.user.id = :userId
""")
    Page<BookTransactionHistory> findAllBorrowedBooks(Pageable pageable, Long userId);

    @Query("""
SELECT history FROM BookTransactionHistory history
    WHERE history.book.owner.id = :userId
""")
    Page<BookTransactionHistory> findAllReturnedBooks(Pageable pageable, Long userId);

    //If there is even a single row which satisfies the 3 conditions, this "isBorrowed" variable will return true
    //A book's returnApproved status has to be updated by the owner to true for it to be borrowed, even if it's "returned" status
    //is true
    @Query("""
          SELECT (COUNT (*) > 0) AS isBorrowed
                FROM BookTransactionHistory AS history
                    WHERE history.user.id = :userId
                    AND history.book.id = :bookId
                    AND history.returnApproved = false
            """)
    boolean isAlreadyBorrowedByUser(Long bookId, Long userId);


    @Query("""
SELECT history FROM BookTransactionHistory history
    WHERE history.book.id = :bookId
    AND history.user.id = :userId
    AND history.returned = false
    AND history.returnApproved = false
""")
    Optional<BookTransactionHistory> findByBookIdAndUserId(Long bookId, Long userId);

    @Query("""
SELECT history FROM BookTransactionHistory history
    WHERE history.book.id = :bookId
    AND  history.book.owner.id = :userId
    AND history.returned = true
    AND history.returnApproved = false
""")
    Optional<BookTransactionHistory> findByBookIdAndOwnerId(Long bookId, Long userId);
}
