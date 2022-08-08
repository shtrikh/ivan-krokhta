package org.example.internetshopspring.exception.handler;

import lombok.extern.log4j.Log4j2;
import org.example.internetshopspring.dto.ErrorDto;
import org.example.internetshopspring.enums.ErrorType;
import org.example.internetshopspring.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Log4j2
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        log.error("handleMethodArgumentNotValidException: message: {}", ex.getMessage(), ex);
        return ex.getBindingResult().getAllErrors()
                .stream()
                .map(err -> new ErrorDto(err.getDefaultMessage(), ErrorType.VALIDATION_ERROR_TYPE))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleServiceException(EntityNotFoundException ex, HandlerMethod hm) {
        log.error("handleServiceException: message: {}, method: {}", ex.getMessage(),
                hm.getMethod().getName(), ex);
        return new ErrorDto(ex.getMessage(), ex.getErrorType());
    }
}
