package com.example.proyectofinaldaniel.services;

import com.example.proyectofinaldaniel.entities.ErrorResponse;
import com.example.proyectofinaldaniel.entities.NotFoundException;
import com.example.proyectofinaldaniel.entities.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ErrorHandlerService {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleException(NotFoundException notFoundException) {
        return new ResponseEntity(new ErrorResponse("NOT_FOUND", notFoundException.getMessage()), HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity handleException(ValidationException exception) {
        return new ResponseEntity(new ErrorResponse(exception.getExceptionCause(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception exception) {
        return new ResponseEntity(new ErrorResponse("GENERIC_ERROR", "Something went wrong"), HttpStatus.BAD_REQUEST
        );
    }
}
