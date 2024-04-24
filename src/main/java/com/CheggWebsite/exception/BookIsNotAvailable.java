package com.CheggWebsite.exception;

import com.CheggWebsite.model.enums.StatusCode;

public class BookIsNotAvailable extends BaseException{
    public BookIsNotAvailable(StatusCode statusCode)
    {
        super(statusCode);
    }
}
