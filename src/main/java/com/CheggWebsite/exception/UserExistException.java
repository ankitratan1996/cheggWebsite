package com.CheggWebsite.exception;

import com.CheggWebsite.model.enums.StatusCode;

public class UserExistException extends BaseException{

    public UserExistException(StatusCode statusCode)
    {
        super(statusCode);
    }

}
