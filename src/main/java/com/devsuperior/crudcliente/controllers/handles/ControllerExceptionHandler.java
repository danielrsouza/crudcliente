package com.devsuperior.crudcliente.controllers.handles;

import com.devsuperior.crudcliente.dtos.CustomError;
import com.devsuperior.crudcliente.dtos.CustomErrorValidation;
import com.devsuperior.crudcliente.dtos.FieldMessage;
import com.devsuperior.crudcliente.services.exceptions.ResourceNotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<CustomError> handleResourceNotFound(ResourceNotFound ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomError(Instant.now(), ex.getMessage(), request.getRequestURI(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorValidation> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        CustomErrorValidation customErrorValidation = new CustomErrorValidation(Instant.now(), "Ocorreu um erro de validação", request.getRequestURI(), HttpStatus.UNPROCESSABLE_ENTITY.value());

        if (ex.getBindingResult().hasErrors()) {
            ex.getBindingResult()
                    .getFieldErrors()
                    .forEach(fieldError -> customErrorValidation.setErrors(new FieldMessage(fieldError.getField(), fieldError.getDefaultMessage())));
        }

        return ResponseEntity.status(customErrorValidation.getStatus()).body(customErrorValidation);
    }
}
