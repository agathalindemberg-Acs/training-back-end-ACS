//Essa classe é um "controller advice" em Spring, o que significa que ela fornece tratamento global para exceções lançadas pelos controladores da API.
//1. O método handleUserNotFound é chamado quando ocorre a exceção ProductNotFoundException, que provavelmente é lançada quando um produto não é encontrado. Esse método retorna um objeto ErrorDTO (um objeto de transferência de dados) com informações sobre o erro. O status HTTP retornado é 404 (NOT FOUND), indicando que o produto não foi encontrado.
//2. O método handleCategoryNotFound trata a exceção CategoryNotFoundException, que provavelmente é lançada quando uma categoria de produto não é encontrada. Ele retorna um objeto ErrorDTO com informações sobre o erro e também retorna o status HTTP 404.
//3. O método handleCategoryNotFound trata a exceção CategoryNotFoundException, que provavelmente é lançada quando uma categoria de produto não é encontrada. Ele retorna um objeto ErrorDTO com informações sobre o erro e também retorna o status HTTP 404.

package com.back.end.productapi.advice;

import com.back.end.java.dto.ErrorDTO;
import com.back.end.java.exception.CategoryNotFoundException;
import com.back.end.java.exception.UserNotFoundException;

import com.back.end.java.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice(basePackages = "com.back.end.productapi.controller")

public class ProductControllerAdvice {
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
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorDTO handleCategoryNotFound(CategoryNotFoundException categoryNotFoundException){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setMessage("Categoria não encontrada.");
        errorDTO.setTimestamp(LocalDateTime.now());
        return errorDTO;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDTO processValidationError(MethodArgumentNotValidException ex){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.BAD_REQUEST.value());
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder sb = new StringBuilder("Valor inválido para o(s) campo(s): ");

        for (FieldError fieldError : fieldErrors){
            sb.append(" ");
            sb.append(fieldError.getField());
        }
        errorDTO.setMessage(sb.toString());
        errorDTO.setTimestamp(LocalDateTime.now());
        return errorDTO;
    }

}
