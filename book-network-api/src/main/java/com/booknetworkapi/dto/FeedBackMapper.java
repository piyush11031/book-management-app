package com.booknetworkapi.dto;

import com.booknetworkapi.entity.Book;
import com.booknetworkapi.entity.FeedBack;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FeedBackMapper {
    public FeedBack tofeedBack(FeedbackRequest request) {

        return FeedBack.builder()
                .note(request.note())
                .comment(request.comment())
                .book(Book.builder()
                        .id(request.bookId())
                        .archived(false) //No required and has not impact :: just to satisfy lombok
                        .sharable(false)
                        .build())
                .build();
    }

    public FeedBackResponse tofeedBackResponse(FeedBack feedBack, Long userId) {

        return FeedBackResponse.builder()
                .note(feedBack.getNote())
                .comment(feedBack.getComment())
                .ownFeedback(Objects.equals(feedBack.getCreatedBy(), userId)) //true if feedback is created by the same connected user
                .build();
    }
}
