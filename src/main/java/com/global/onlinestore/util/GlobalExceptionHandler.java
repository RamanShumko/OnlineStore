package com.global.onlinestore.util;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.TreeMap;

/**
 * GlobalExceptionHandler provides centralized exception handling for controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles EntityNotFoundException and returns a ResponseEntity with an appropriate error message.
     *
     * @param ex      The EntityNotFoundException that was thrown.
     * @param request The HttpServletRequest associated with the request.
     * @return A ResponseEntity with the error details and HTTP status code 404.
     */
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Map<String, Object>> entityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        final Map<String, Object> body = new TreeMap<>();

        body.put("status", HttpStatus.NOT_FOUND);
        body.put("error", "EntityNotFoundException");
        body.put("message", ex.getMessage());
        body.put("details", "Entity not found");
        body.put("path", request.getServletPath());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles IllegalArgumentException and returns a ResponseEntity with an appropriate error message.
     *
     * @param ex      The IllegalArgumentException that was thrown.
     * @param request The HttpServletRequest associated with the request.
     * @return A ResponseEntity with the error details and HTTP status code 400.
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Map<String, Object>> entityExistsException(IllegalArgumentException ex, HttpServletRequest request) {
        final Map<String, Object> body = new TreeMap<>();

        body.put("status", HttpStatus.BAD_REQUEST);
        body.put("error", "IllegalArgumentException");
        body.put("message", ex.getMessage());
        body.put("details", "Change the data in the request body");
        body.put("path", request.getServletPath());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
