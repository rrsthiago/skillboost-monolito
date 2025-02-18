package com.rrsthiago.skillboost.exception;

import java.math.BigInteger;

public class UserDoesntExistException extends RuntimeException {

    public UserDoesntExistException(BigInteger resourceId) {
        super("User account not found with id " + resourceId);
    }

}
