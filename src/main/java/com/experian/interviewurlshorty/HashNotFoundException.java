package com.experian.interviewurlshorty;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HashNotFoundException extends RuntimeException {
    public HashNotFoundException(String message) {
        super(message);
    }
}
