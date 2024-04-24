package com.CheggWebsite.exception;

import com.CheggWebsite.model.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidInputException extends  BaseException{

    public InvalidInputException(StatusCode statusCode)
    {
        super(statusCode);
    }
}
