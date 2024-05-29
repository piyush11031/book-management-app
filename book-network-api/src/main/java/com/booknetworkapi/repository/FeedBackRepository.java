package com.booknetworkapi.repository;


import com.booknetworkapi.entity.FeedBack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedBackRepository extends JpaRepository<FeedBack, Long> {

    @Query("""
    SELECT f FROM FeedBack f
        WHERE f.book.id = :bookId
""")
    Page<FeedBack> findAllByBookId(Long bookId, Pageable pageable);
}
