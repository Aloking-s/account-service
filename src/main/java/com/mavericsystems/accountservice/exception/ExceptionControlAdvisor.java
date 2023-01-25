package com.mavericsystems.accountservice.exception;

import com.mavericsystems.accountservice.Account.Accounts;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import com.mavericsystems.accountservice.Dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static com.mavericsystems.accountservice.constants.Constants.*;

@RestControllerAdvice
public class ExceptionControlAdvisor {

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorDto handleAccountNotFoundException (AccountNotFoundException exception){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(ACCOUNT_NOT_FOUND_CODE);
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }
    @ExceptionHandler(CustomerIdMissmatch.class)
    public final ErrorDto handleCustomerIdMismatchException(CustomerIdMissmatch customerIdMissmatch) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorDto.setMessage(customerIdMissmatch.getMessage());
        return errorDto;
    }

}
