package com.CheggWebsite.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum StatusCode {
    AUTHOR_NOT_FOUND("Author is not register",HttpStatus.NOT_FOUND),
    USER_ALREADY_EXIST("User already exist in the database",HttpStatus.ALREADY_REPORTED),
    USER_DOES_NOT_EXIST("User is not available in the database",HttpStatus.BAD_REQUEST),
    INVALID_INPUT_EXCEPTION("Invalid provided data",HttpStatus.BAD_REQUEST),
    BOOK_NOT_AVAILABLE("Book is not available",HttpStatus.BAD_REQUEST);
    private String errorMessage;
    private HttpStatus httpStatus;

}
