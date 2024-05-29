package com.booknetworkapi.repository;

import com.booknetworkapi.entity.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> withUserId(Long userId){

        return (root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("user").get("id"), userId);
    }
}
