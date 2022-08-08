package org.example.internetshopspring.exception;

import org.example.internetshopspring.enums.ErrorType;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public ErrorType getErrorType(){
        return ErrorType.DATABASE_ERROR_TYPE;
    }
}
