package com.example.thesis.exception.handler;

import com.example.thesis.exception.ExistException;
import com.example.thesis.exception.ForbiddenActionException;
import com.example.thesis.exception.NotExistObjectException;
import com.example.thesis.exception.UnAvailableDeleteException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ExistException.class)
    public ResponseEntity<?> handleExistExceptionException(ExistException e) {
        return buildException(Collections.singletonList(e.getMessage()),HttpStatus.CONFLICT);
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
    protected ProblemDetail createProblemDetail (Exception ex, HttpStatusCode status, String defaultDetail, String detailMessageCode, Object[] detailMessageArguments, WebRequest request) {
        return super.createProblemDetail(ex, status, defaultDetail, detailMessageCode, detailMessageArguments, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal (Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return buildException(List.of(ex.getMessage()),HttpStatus.valueOf(statusCode.value()));
    }

}
