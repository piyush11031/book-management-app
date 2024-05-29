package com.booknetworkapi.entity.user;

import com.booknetworkapi.entity.BaseEntity;
import com.booknetworkapi.entity.Book;
import com.booknetworkapi.entity.BookTransactionHistory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    @Column(unique = true)
    private String email;

    private String password;

    private Boolean accountLocked;

    private Boolean enabled;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> authorities;

    public User(){
        this.authorities = new HashSet<>();
    }

    @OneToMany(mappedBy = "user")
    private List<BookTransactionHistory> histories;

    @Transient
    public String getFullName(){
        return firstName + " " + lastName;
    }

}
