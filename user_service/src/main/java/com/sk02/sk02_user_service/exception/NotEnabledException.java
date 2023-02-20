package com.sk02.sk02_user_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotEnabledException extends HttpResponseException{

    public NotEnabledException(String message) {
        super(message);
    }
}
