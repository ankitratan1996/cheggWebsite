package com.CheggWebsite.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;


@Entity(name = "USERS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    String name;
    String email;
    @UpdateTimestamp
    LocalDateTime createdAt;
    @CreationTimestamp
    LocalDateTime updatedAt;
    /**
     *  1 user --------------> n order
     *   1 user <------------  1 order
     * ------------------------------------
     * 1 user ------------------- n book
     *
     * u1[o1,o2]
     * [o1-u1]
     * [o2-u2]
     *
     */

    @OneToMany(mappedBy ="usersList")
    private List<Orders> ordersList;

}
