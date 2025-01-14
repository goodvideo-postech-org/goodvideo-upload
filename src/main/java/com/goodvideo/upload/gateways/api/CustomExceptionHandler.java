package com.goodvideo.upload.gateways.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.goodvideo.upload.usecase.TokenException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(TokenException.class)
    public HttpEntity<Object> handleNotFoundException(final TokenException ex) {
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(ex.getMessage(), httpHeaders, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public HttpEntity<Object> handleIllegalArgumentException(final IllegalArgumentException ex) {
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(ex.getMessage(), httpHeaders, HttpStatus.BAD_REQUEST);
    }

}
