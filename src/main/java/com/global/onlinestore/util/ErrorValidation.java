package com.global.onlinestore.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for handling validation errors.
 */
public class ErrorValidation {

    /**
     * Generates an error message based on the validation errors in the BindingResult.
     * If the BindingResult contains errors, it constructs a message concatenating field names
     * and their respective error messages.
     *
     * @param bindingResult The BindingResult containing validation errors.
     * @throws IllegalArgumentException If there are validation errors, an IllegalArgumentException
     *                                  is thrown with a concatenated error message.
     */
    public static void message(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = new ArrayList<>(bindingResult.getFieldErrors());

            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }
}
