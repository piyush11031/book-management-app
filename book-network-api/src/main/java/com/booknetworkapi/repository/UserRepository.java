package com.booknetworkapi.repository;

import com.booknetworkapi.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(User user);

    Optional<User> findByEmail(String username);
}
