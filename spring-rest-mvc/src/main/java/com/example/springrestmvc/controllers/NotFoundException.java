package com.example.springrestmvc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
public class NotFoundException extends RuntimeException {
  public NotFoundException() {
    super();

  }

  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotFoundException(Throwable cause) {
    super(cause);
  }

  protected NotFoundException(String message, Throwable cause, boolean var3, boolean var4) {
    super(message, cause, var3, var4);
  }


}
