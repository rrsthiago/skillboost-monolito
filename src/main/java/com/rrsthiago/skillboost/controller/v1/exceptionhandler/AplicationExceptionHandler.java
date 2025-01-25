package com.rrsthiago.skillboost.controller.v1.exceptionhandler;

import com.rrsthiago.skillboost.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

import static java.util.Optional.ofNullable;

@Slf4j
@ControllerAdvice
public class AplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String ERROR_5XX = "Erro interno no servidor";

    @ExceptionHandler({
            ResourceNotFoundException.class
    })
    private ResponseEntity<?> handleResourceNotFoundException(RuntimeException e, WebRequest webRequest) {
        var httpStatus = HttpStatus.NOT_FOUND;
        var problem = createProblemRfc7807(httpStatus, httpStatus.getReasonPhrase(), e.getMessage());

        return ResponseEntity.status(httpStatus).body(problem);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception e, WebRequest webRequest) {
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var problem = createProblemRfc7807(httpStatus, ERROR_5XX, ofNullable(e.getMessage()).orElse(ERROR_5XX));

        return super.handleExceptionInternal(e, problem, new HttpHeaders(), httpStatus, webRequest);
    }

    private ProblemRfc7807 createProblemRfc7807(HttpStatus httpStatus, String title, String detail) {
        return ProblemRfc7807.builder()
                .timestamp(LocalDateTime.now())
                .status(httpStatus.value())
                .title(title)
                .detail(detail)
                .build();
    }

}
