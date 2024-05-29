package com.booknetworkapi.repository;

import com.booknetworkapi.entity.user.Token;
import com.booknetworkapi.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("""
SELECT t FROM Token t
    INNER JOIN User u
        ON t.user.id = u.id
    WHERE u.id = :userId
""")
    List<Token> findAllUserTokens(Long userId);
}
