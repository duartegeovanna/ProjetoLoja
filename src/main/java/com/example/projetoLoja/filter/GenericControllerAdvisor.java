package com.example.projetoLoja.filter;

import com.example.projetoLoja.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GenericControllerAdvisor {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundErrors(Exception ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
