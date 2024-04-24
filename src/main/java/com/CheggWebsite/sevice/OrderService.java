package com.CheggWebsite.sevice;

import com.CheggWebsite.exception.BookIsNotAvailable;
import com.CheggWebsite.model.Books;
import com.CheggWebsite.model.Orders;
import com.CheggWebsite.model.Users;
import com.CheggWebsite.model.enums.OrderStatus;
import com.CheggWebsite.model.enums.StatusCode;
import com.CheggWebsite.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserService  userService;
    @Autowired
    BookService bookService;

    public Orders orderPlace(String email,String isbn)
    {
        Users user =userService.findExistingUserByEmail(email);
        Books book = bookService.findBooksByIsbn(isbn);
        if(!book.getBookStatus().isAvailable())
        {
            throw new BookIsNotAvailable(StatusCode.BOOK_NOT_AVAILABLE);
        }
        List<Books> booksList=new ArrayList<>();
        booksList.add(book);

        Orders order =Orders.builder()
                .amount(book.getAmount())
                .orderStatus(OrderStatus.PENDING)
                .booksList(booksList).user(user)
                .build();
        try {
            saveOrUpdate(order);
            bookService.issueBookAndPersist(booksList);
            makeOrderSuccess(order);
        }
        catch (Exception exception)
        {
            bookService.makeBooksAvailable(booksList);
            makeOrderFailed(order);
            throw new BookIsNotAvailable(StatusCode.BOOK_NOT_AVAILABLE);
        }
        return order;
    }

    private void makeOrderFailed(Orders order) {
        order.setOrderStatus(OrderStatus.FAILED);
    }

    private void makeOrderSuccess(Orders orders) {
        orders.setOrderStatus(OrderStatus.SUCCESS);
    }

    private Orders saveOrUpdate(Orders orders) {
        return orderRepository.save(orders);
    }

}
