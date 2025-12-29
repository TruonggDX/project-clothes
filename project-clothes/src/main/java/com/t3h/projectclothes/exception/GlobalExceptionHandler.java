package com.t3h.projectclothes.exception;

import com.t3h.projectclothes.dto.common.BaseResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Xử lý BusinessException - lỗi nghiệp vụ
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponse<Void>> handleBusinessException(BusinessException ex) {
        BaseResponse<Void> response = BaseResponse.error(ex.getCode(), ex.getMessage());
        HttpStatus status = HttpStatus.valueOf(ex.getCode());
        return ResponseEntity.status(status).body(response);
    }

    /**
     * Xử lý lỗi validation
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Map<String, String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        BaseResponse<Map<String, String>> response = BaseResponse.<Map<String, String>>builder()
                .code(400)
                .message("Validation failed")
                .data(errors)
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Xử lý các exception khác (fallback)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleException(Exception ex) {
        ex.printStackTrace(); // Log lỗi ra console để debug
        BaseResponse<Void> response = BaseResponse.error(500, "Internal server error: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
