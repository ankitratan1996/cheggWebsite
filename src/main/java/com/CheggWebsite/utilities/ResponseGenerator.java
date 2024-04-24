package com.CheggWebsite.utilities;

import com.CheggWebsite.model.enums.StatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseGenerator {

    private static ObjectMapper mapper;
    static {
        mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
    }

    @SneakyThrows
    public ResponseEntity<String> generateResponse(StatusCode statusCode)
    {
           ResponseInfo<String> responseInfo = new ResponseInfo<>();
           responseInfo.setErrorMessage(statusCode.getErrorMessage());
           responseInfo.setErrorCode(statusCode.getHttpStatus());
           responseInfo.setTraceId(MDC.get("TraceId"));
           return new ResponseEntity<>(mapper.writeValueAsString(responseInfo),statusCode.getHttpStatus());
    }

    @SneakyThrows
    public <T> ResponseEntity<String>generateResponse(T data, HttpStatus statusCode)
    {
        ResponseInfo<T> responseInfo = new ResponseInfo<>();
        responseInfo.setTraceId(MDC.get("TraceId"));
        responseInfo.setData(data);
        return new ResponseEntity<>(mapper.writeValueAsString(responseInfo),statusCode);
    }
}
