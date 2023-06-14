package com.back.end.shoppingapi.advice;

import com.back.end.java.dto.ErrorDTO;
import com.back.end.java.exception.ProductNotFoundException;
import com.back.end.java.exception.UserNotFoundException;
import org.springframework.cglib.core.Local;
import	org.springframework.http.HttpStatus;
import	org.springframework.web.bind.annotation.ControllerAdvice;
import	org.springframework.web.bind.annotation.ExceptionHandler;
import	org.springframework.web.bind.annotation.ResponseBody;
import	org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice(basePackages = "com.back.end.shoppingapi.controller")
public class ShoppingControllerAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)

    public ErrorDTO handleUserNotFound(ProductNotFoundException userNotFoundException){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setMessage("Produto não encontrado.");
        errorDTO.setTimestamp(LocalDateTime.now());

        return errorDTO;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDTO handleUserNotFound(UserNotFoundException userNotFoundException){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setMessage("Usuário não encontrado.");
        errorDTO.setTimestamp(LocalDateTime.now());

        return errorDTO;
    }

}
