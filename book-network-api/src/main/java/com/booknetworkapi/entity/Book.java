package com.booknetworkapi.entity;

import com.booknetworkapi.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends BaseEntity{

    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String bookCover;
    private Boolean archived;
    private Boolean sharable;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "book")
    private List<BookTransactionHistory> histories;

    //We set up bidirectional relationship because we want to access all feedback from book entity
    @OneToMany(mappedBy = "book")
    private List<FeedBack> feedBacks;

    @Transient //Specifies that the property of field is not persisted
    public double getRate(){
        if(feedBacks == null || feedBacks.isEmpty()){
            return 0.0;
        }

        var rate = feedBacks.stream()
                .mapToDouble(FeedBack::getNote)
                .average()
                .orElse(0.0);

        double roundedRate = Math.round(rate * 10) / 10.0;
        return roundedRate;
    }

}
