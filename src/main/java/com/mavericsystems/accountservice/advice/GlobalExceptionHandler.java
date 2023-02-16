package com.mavericsystems.accountservice.advice;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mavericsystems.accountservice.Dto.ErrorDto;
import com.mavericsystems.accountservice.Dto.ErrorReponseDto;
import com.mavericsystems.accountservice.exception.AccountIDNotfoundException;
import com.mavericsystems.accountservice.exception.AccountNotFoundException;
import com.mavericsystems.accountservice.exception.CustomerIDNotFoundExistsException;
import com.mavericsystems.accountservice.exception.CustomerIdMissmatch;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static ch.qos.logback.core.util.AggregationType.NOT_FOUND;
import static com.mavericsystems.accountservice.constants.Constants.BAD_REQUEST_CODE;
import static com.mavericsystems.accountservice.constants.Constants.METHOD_NOT_ALLOWED_CODE;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorReponseDto> handllingException(AccountNotFoundException ex){
        ErrorReponseDto response = new ErrorReponseDto();
        response.setCode("404");
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(AccountIDNotfoundException.class)
    public ResponseEntity<ErrorReponseDto> handllingException(AccountIDNotfoundException ex){
        ErrorReponseDto response = new ErrorReponseDto();
        response.setCode("400");
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler({CustomerIDNotFoundExistsException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorDto handleCustomerIDNotFoundExistsException(CustomerIDNotFoundExistsException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode("NOT_FOUND");
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ErrorDto handleMessageNotReadableException() {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(String.valueOf(HttpStatus.BAD_REQUEST));
        errorDto.setMessage("Type is mandatory - 'SAVINGS' or 'CURRENT'");
        return errorDto;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(BAD_REQUEST_CODE);
        errorDto.setMessage(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return errorDto;
    }
    @ExceptionHandler(CustomerIdMissmatch.class)
    public ResponseEntity<ErrorReponseDto> handllingException (CustomerIdMissmatch ex){
        ErrorReponseDto response = new ErrorReponseDto();
        response.setCode("400");
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler //for enum
    public ResponseEntity<ErrorReponseDto> invalidValueException (InvalidFormatException ex){

        //HttpMessageNotReadableException.
        ErrorReponseDto responseDto = new ErrorReponseDto();
        responseDto.setCode("400");
        if (ex.getMessage().contains("enum")) {
            responseDto.setMessage("Cannot have values other than enum [SAVINGS,CURRENT]");
        } else
            responseDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorDto handleHttpRequestMethodNotSupportedException (
            HttpRequestMethodNotSupportedException ex){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(METHOD_NOT_ALLOWED_CODE);
        errorDto.setMessage("customerId should be either should be mandotory");
        return errorDto;
    }

}
