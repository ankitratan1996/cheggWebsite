package com.CheggWebsite.model;

import com.CheggWebsite.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity(name = "ORDERS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderID;
    Double amount;
    OrderStatus orderStatus;
    @CreationTimestamp
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn
    Users user;

    /***
     *
     *  1 order -------> n book
     *  1 order <----------1 book
     *  ------------------------------
     *  1 order -------------- n book
     *  []
     *  order has relationship with user and books
     **/
    @OneToMany(mappedBy = "associatedOrder")
    List<Books>booksList;


}
