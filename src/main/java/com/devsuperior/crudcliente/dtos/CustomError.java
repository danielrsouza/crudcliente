package com.devsuperior.crudcliente.dtos;

import java.time.Instant;

public class CustomError {
    private Instant timestamp;
    private String message;
    private String path;
    private Integer status;

    public CustomError(Instant timestamp, String message, String path, Integer status) {
        this.timestamp = timestamp;
        this.message = message;
        this.path = path;
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public Integer getStatus() {
        return status;
    }
}
