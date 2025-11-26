package com.gitbaby.happy_back.domain.common.exception;

import com.gitbaby.happy_back.domain.common.dto.ApiResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(HappyException.class)
  public ResponseEntity<ApiResponce<?>> handleHappyException(HappyException ex) {
    return ResponseEntity
      .status(ex.getStatus())
      .body(ApiResponce.fail(ex.getErrorCode(), ex.getMessage()));
  }

  // @Valid
  @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponce<?>> handleValidation(MethodArgumentNotValidException ex) {
    String message = ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(ApiResponce.fail("VALIDATION_ERROR", message));
  }
}