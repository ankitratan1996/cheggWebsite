package com.CheggWebsite.exception;

import com.CheggWebsite.model.enums.StatusCode;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthorNotFoundException extends BaseException{
  public AuthorNotFoundException(StatusCode statusCode)
  {
      super(statusCode);
  }
}
