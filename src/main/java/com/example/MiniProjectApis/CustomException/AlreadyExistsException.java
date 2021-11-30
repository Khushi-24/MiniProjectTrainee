package com.example.MiniProjectApis.CustomException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(HttpStatus conflict, String message){
        super(message);
    }
}
