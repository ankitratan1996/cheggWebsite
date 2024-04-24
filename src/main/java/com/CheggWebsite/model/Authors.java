package com.CheggWebsite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "AUTHOR")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long authorId;
    String name;
    @Email
    String email;
    @CreationTimestamp
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;
    @OneToMany(mappedBy = "associatedAuthor")
    Set<Books> associatedBooks;

    /**
     *      1 Author ---------> N Book
     *      1 Author <--------- 1 Book
     *      -------------------------------
     *      1 Author ----------- N Book
     *      A1[B1,B2,b3]
     *      [B1,A1]
     *      authur has relation with book
     * */




}
