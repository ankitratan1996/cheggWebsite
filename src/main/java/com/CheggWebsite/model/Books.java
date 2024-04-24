package com.CheggWebsite.model;

import com.CheggWebsite.model.enums.BookStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity(name = "BOOKS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Version
    Long version;
    private String name;
    private String isbn;
    @Enumerated(value = EnumType.STRING)
    private BookStatus bookStatus;
    private Double amount;
    @CreationTimestamp
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @JoinColumn
    @ManyToOne
    private Authors associatedAuthor;

    @ManyToOne
    private Orders associatedOrder;

    @PrePersist
    private void markStatusAvailable()
    {
        this.bookStatus=BookStatus.AVAILABLE;
    }
    /****
     *  book has relationship with order and author
     *
     *
     * ****/
}
