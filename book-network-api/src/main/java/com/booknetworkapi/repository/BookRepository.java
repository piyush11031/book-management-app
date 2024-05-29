package com.booknetworkapi.repository;

import com.booknetworkapi.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long>
                                        , JpaSpecificationExecutor<Book> //Gives us support for BookSpecification class

{
    @Query("""
SELECT b FROM Book b
    WHERE b.archived = false
    AND b.sharable = true
    AND b.owner.id != :userId
""")
    Page<Book> findALlDisplayableBooks(Pageable pageable, Long userId);
}
