package com.medilabosolutions.msgestionhistorique.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler for the application.
 * This class provides centralized exception handling across all methods and endpoints.
 * It captures specific exceptions and returns appropriate HTTP status codes and messages.
 *
 * @author Ivano
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles the custom exception {@link PatientNoteNotFoundException}.
     * Returns a NOT_FOUND HTTP status code along with the exception message.
     *
     * @param pnnfe The exception instance.
     * @return ResponseEntity containing the exception message and the HTTP status code.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PatientNoteNotFoundException.class)
    public ResponseEntity<String> handlePatientNoteNotFoundException(PatientNoteNotFoundException pnnfe) {
        return new ResponseEntity<>(pnnfe.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles validation exceptions that occur when method arguments are not valid.
     * Returns a BAD_REQUEST HTTP status code along with the validation error messages.
     *
     * @param manve The exception instance.
     * @return ResponseEntity containing the list of validation error messages and the HTTP status code.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException manve) {
        List<String> errors = manve.getBindingResult()
                .getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
    }
}
