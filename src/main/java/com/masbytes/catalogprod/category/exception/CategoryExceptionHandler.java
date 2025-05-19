package com.masbytes.catalogprod.category.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.masbytes.catalogprod.category.exception.database.CategoryAlreadyExistsException;
import com.masbytes.catalogprod.category.exception.database.CategoryAlreadyIsActiveException;
import com.masbytes.catalogprod.category.exception.database.CategoryAlreadyIsInactiveException;
import com.masbytes.catalogprod.category.exception.database.CategoryNotFoundException;
import com.masbytes.catalogprod.category.exception.validation.CategoryInvalidDataException;
import com.masbytes.catalogprod.common.exception.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Handles all exceptions related to Category operations and maps them
 * to appropriate HTTP responses.
 */

@RestControllerAdvice(basePackages = "com.masbytes.catalogprod.category")
public class CategoryExceptionHandler {

    /**
     * Handles CategoryAlreadyExistsException and returns a 409 Conflict response.
     * This exception is thrown when an attempt is made to create a category
     * that already exists.
     * 
     * @param ex      message
     * @param request request
     * @return ResponseEntity<ErrorResponse>
     * @throws CategoryAlreadyExistsException
     */

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCategoryAlreadyExists(CategoryAlreadyExistsException ex,
            HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), request.getRequestURI());
    }

    /**
     * Handles CategoryAlreadyIsActiveException and returns a 400 Bad Request
     * response.
     * This exception is thrown when an attempt is made to activate a category
     * that is already active.
     * 
     * @param ex      message
     * @param request request
     * @return ResponseEntity<ErrorResponse>
     * @throws CategoryAlreadyIsActiveException
     */

    @ExceptionHandler(CategoryAlreadyIsActiveException.class)
    public ResponseEntity<ErrorResponse> handleCategoryAlreadyIsActive(CategoryAlreadyIsActiveException ex,
            HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI());
    }

    /**
     * Handles CategoryAlreadyIsInactiveException and returns a 400 Bad Request
     * response.
     * This exception is thrown when an attempt is made to deactivate a category
     * that is already inactive.
     * 
     * @param ex      message
     * @param request request
     * @return ResponseEntity<ErrorResponse>
     * @throws CategoryAlreadyIsInactiveException
     */

    @ExceptionHandler(CategoryAlreadyIsInactiveException.class)
    public ResponseEntity<ErrorResponse> handleCategoryAlreadyIsInactive(CategoryAlreadyIsInactiveException ex,
            HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI());
    }

    /**
     * Handles CategoryNotFoundException and returns a 404 Not Found response.
     * This exception is thrown when a requested category is not found.
     * This can occur during retrieval or deletion operations.
     * 
     * @param ex      message
     * @param request request
     * @return ResponseEntity<ErrorResponse>
     * @throws CategoryNotFoundException
     */

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFound(CategoryNotFoundException ex,
            HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
    }

    /**
     * Handles CategoryInvalidDataException and returns a 422 Unprocessable Entity
     * response.
     * This exception is thrown when the provided data for a category
     * is invalid or does not meet the required criteria.
     * 
     * @param ex      message
     * @param request request
     * @return ResponseEntity<ErrorResponse>
     * @throws CategoryInvalidDataException
     */

    @ExceptionHandler(CategoryInvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleCategoryInvalidData(CategoryInvalidDataException ex,
            HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), request.getRequestURI());
    }

    /**
     * Handles MethodArgumentNotValidException and returns a 400 Bad Request
     * 
     * @param ex      message
     * @param request request
     * @return ResponseEntity<ErrorResponse>
     * @throws MethodArgumentNotValidException
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        // Extraer el primer error (podés adaptar para todos si querés)
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String message = (fieldError != null)
                ? String.format("Field '%s' %s", fieldError.getField(), fieldError.getDefaultMessage())
                : "Validation failed for the request.";

        return buildErrorResponse(HttpStatus.BAD_REQUEST, message, request.getRequestURI());
    }

    /**
     * Handles HttpMessageNotReadableException and returns a 400 Bad Request
     * response.
     * This exception is thrown when the request body cannot be read
     * or is malformed.
     * 
     * @param ex      message
     * @param request request
     * @return ResponseEntity<ErrorResponse>
     * @throws HttpMessageNotReadableException
     */
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidJson(HttpMessageNotReadableException ex,
            HttpServletRequest request) {
        String message = "Malformed JSON request or incompatible data types.";

        if (ex.getCause() instanceof InvalidFormatException ife) {
            message = String.format("Invalid value for field: %s", ife.getPath().get(0).getFieldName());
        }

        return buildErrorResponse(HttpStatus.BAD_REQUEST, message, request.getRequestURI());
    }

    /**
     * Handles generic exceptions and returns a 500 Internal Server Error
     * response.
     * This is a fallback method for any unhandled exceptions that may occur
     * during the processing of requests.
     * 
     * @param ex      message
     * @param request request
     * @return ResponseEntity<ErrorResponse>
     * @throws Exception
     */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred. Please contact support.",
                request.getRequestURI());
    }

    /**
     * Helper method to build a standardized error response.
     * 
     * @param status  HTTP status code
     * @param message error message
     * @param path    request path
     * @return ResponseEntity<ErrorResponse>
     */

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message, String path) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path);
        return new ResponseEntity<>(error, status);
    }

}
