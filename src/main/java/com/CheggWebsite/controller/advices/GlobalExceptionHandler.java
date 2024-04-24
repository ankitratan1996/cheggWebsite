package com.CheggWebsite.controller.advices;

import com.CheggWebsite.exception.BaseException;
import com.CheggWebsite.exception.InvalidInputException;
import com.CheggWebsite.exception.UserInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {InvalidInputException.class, UserInvalidException.class})
    public ResponseEntity<String> HandlerInvalidInputExceptionResponse(BaseException baseException)
    {
        log.info("exception received {} ",baseException.getStatusCode().getErrorMessage());
        return new ResponseEntity<>(baseException.getStatusCode().getErrorMessage(),
                baseException.getStatusCode().getHttpStatus());
    }
}
