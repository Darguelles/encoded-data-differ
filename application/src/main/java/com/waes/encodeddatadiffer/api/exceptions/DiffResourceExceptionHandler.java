package com.waes.encodeddatadiffer.api.exceptions;

import com.waes.encodeddatadiffer.core.binaryelement.exceptions.ElementNotFoundException;
import com.waes.encodeddatadiffer.core.binaryelement.exceptions.InvalidDataEncryptionException;
import com.waes.encodeddatadiffer.core.binaryelement.exceptions.MissingElementSideException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DiffResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ MissingElementSideException.class })
    protected ResponseEntity<Object> handleMissingElement(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Cannot get differences for the desired object.";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler({ ElementNotFoundException.class })
    protected ResponseEntity<Object> handleElementNotFound(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Unable to find the desired resource.";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ InvalidDataEncryptionException.class, NumberFormatException.class })
    protected ResponseEntity<Object> handleInvalidRequest(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Cannot process the request. Verify the data.";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
