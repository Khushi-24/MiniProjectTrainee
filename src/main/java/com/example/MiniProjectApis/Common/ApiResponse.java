package com.example.MiniProjectApis.Common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse {

    public static ResponseEntity<?> sendOkResponse(String message, Object data) {
        if (data == null)
            return new ResponseEntity<>(new Response(message), HttpStatus.OK);
        else
            return new ResponseEntity<>(new Response(message,200, data), HttpStatus.OK);
    }

}
