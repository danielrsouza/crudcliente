package com.devsuperior.crudcliente.services.exceptions;

public class ResourceNotFound extends RuntimeException {
   public ResourceNotFound(String message) {
       super(message);
   }
}
