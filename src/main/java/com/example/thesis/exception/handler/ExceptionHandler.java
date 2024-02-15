package com.example.thesis.exception.handler;

import com.example.thesis.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler( value = {NotExistObjectException.class})
    public ResponseEntity<?> handleNotExistObjectException(NotExistObjectException e) {
        return buildException(Collections.singletonList(e.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException e) {
        return buildException(Collections.singletonList(e.getMessage()),HttpStatus.UNAUTHORIZED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ForbiddenActionException.class)
    public ResponseEntity<?> handleForbiddenActionException(ForbiddenActionException e) {
        return buildException(Collections.singletonList(e.getMessage()),HttpStatus.FORBIDDEN);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException e) {
        return buildException(Collections.singletonList(e.getMessage()),HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ExistException.class)
    public ResponseEntity<?> handleExistExceptionException(ExistException e) {
        return buildException(Collections.singletonList(e.getMessage()),HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({ JwtException.class })
    public ResponseEntity<?> handleAuthenticationException(JwtException ex) {
        return buildException(ex.getMessage(),HttpStatus.UNAUTHORIZED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler( value = {UnAvailableDeleteException.class})
    public ResponseEntity<?> handleNotExistObjectException(UnAvailableDeleteException e) {
        return buildException(Collections.singletonList(e.getMessage()),HttpStatus.CONFLICT);
    }

    private ResponseEntity<Object> buildException(List<String> message, HttpStatus httpStatus){
        var exceptionDetails = new ExceptionDetails();

        exceptionDetails.setMessages(message);
        exceptionDetails.setStatus(httpStatus);

        return new ResponseEntity<>(exceptionDetails, httpStatus);
    }
    private ResponseEntity<Object> buildException(String message, HttpStatus httpStatus){
        var exceptionDetails = new ExceptionDetails();

        exceptionDetails.setMessages(Collections.singletonList(message));
        exceptionDetails.setStatus(httpStatus);

        return new ResponseEntity<>(exceptionDetails, httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        return buildException(errors,HttpStatus.valueOf(status.value()));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal (Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return buildException(ex.getMessage(),HttpStatus.valueOf(statusCode.value()));
    }

    @Override
    protected ResponseEntity<Object> createResponseEntity(Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return buildException(body.toString(),HttpStatus.valueOf( statusCode.value()));
    }

}
