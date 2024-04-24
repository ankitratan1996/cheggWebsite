package com.CheggWebsite.exception;

import com.CheggWebsite.model.enums.StatusCode;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BaseException extends RuntimeException{
    private StatusCode statusCode;
    public BaseException(StatusCode statusCode)
    {
        super(statusCode.getErrorMessage());
        this.statusCode=statusCode;
    }
}
