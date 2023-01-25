package com.mavericsystems.accountservice.Controller;

import com.mavericsystems.accountservice.Dto.ErrorDto;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import static com.mavericsystems.accountservice.constants.Constants.*;

public class AccountErrorController implements ErrorController {

    @GetMapping("/error")
    public ResponseEntity<ErrorDto> errorHandler (HttpServletRequest req){
        ErrorDto error = new ErrorDto();
        error.setCode(INCORRECT_URL_CODE);
        error.setMessage(INCORRECT_URL_MESSAGE);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

}
