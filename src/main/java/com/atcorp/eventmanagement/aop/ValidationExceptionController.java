package com.atcorp.eventmanagement.aop;


import com.atcorp.eventmanagement.dto.FailureResponse;
import com.atcorp.eventmanagement.dto.Response;
import com.atcorp.eventmanagement.util.enums.FailureEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationExceptionController extends ResponseEntityExceptionHandler  {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Response response = new Response();
        FailureResponse failureResponse = new FailureResponse(ex.getMessage(), FailureEnum.VALIDATION_ERROR);
        response.setFailureResponse(failureResponse);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
