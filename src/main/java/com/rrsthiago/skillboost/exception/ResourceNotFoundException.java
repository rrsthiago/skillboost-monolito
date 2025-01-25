package com.rrsthiago.skillboost.exception;

import java.math.BigInteger;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(BigInteger resourceId) {
        super("Resource not found with id " + resourceId);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
