package com.CheggWebsite.utilities;

import com.CheggWebsite.model.enums.StatusCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseInfo<T> {

    private T data;
    private String errorMessage;
    private HttpStatus errorCode;
    private String traceId;
    public ResponseInfo(StatusCode statusCode)
    {
        this.errorCode=statusCode.getHttpStatus();
        this.errorMessage=statusCode.getErrorMessage();
        this.traceId= MDC.get("traceId");

    }
}
