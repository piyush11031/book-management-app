package com.booknetworkapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BookResponse {
    private Long id;
    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String owner;
    private byte[] cover; //contains picture of the book
    private double rate; //contains average rating of the book
    private boolean archived;
    private boolean sharable;
}
