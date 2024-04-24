package com.CheggWebsite.controller.restApi;

import com.CheggWebsite.sevice.OrderService;
import com.CheggWebsite.utilities.ResponseGenerator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ResponseGenerator responseGenerator;

    @PostMapping(value = "/v1/order", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createABook(@RequestParam(value = "userEmail") String email,
                                              @RequestParam(value = "isbn") String isbn) {
        log.info("Request Received {}  {} ", email, isbn);
        Thread.currentThread().setName(MDC.get("traceId"));
        return responseGenerator.generateResponse(orderService.orderPlace(email, isbn), HttpStatus.CREATED);
    }
}
