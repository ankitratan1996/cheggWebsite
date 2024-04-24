package com.CheggWebsite.exception;

import com.CheggWebsite.model.enums.StatusCode;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import org.springframework.context.event.EventPublicationInterceptor;

@NoArgsConstructor
public class UserInvalidException extends BaseException{

    public UserInvalidException(StatusCode statusCode)
    {
        super(statusCode);
    }
}
