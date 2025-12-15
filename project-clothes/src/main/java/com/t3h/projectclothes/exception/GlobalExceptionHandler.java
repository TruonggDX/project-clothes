package com.t3h.projectclothes.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> map = new HashMap<>();
    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
      map.put(fieldError.getField(), fieldError.getDefaultMessage());
    }
    return ResponseEntity.badRequest().body(map);
  }

}
