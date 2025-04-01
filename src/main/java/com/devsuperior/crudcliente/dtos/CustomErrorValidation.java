package com.devsuperior.crudcliente.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CustomErrorValidation extends CustomError {
    private final List<FieldMessage> errors = new ArrayList<>();

    public CustomErrorValidation(Instant timestamp, String message, String path, Integer status) {
        super(timestamp, message, path, status);
    }

    public void setErrors(FieldMessage fieldError) {
        this.errors.add(fieldError);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }
}
