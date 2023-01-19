package com.kuzmich.solution.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchRecordException.class)
    public ResponseEntity<Object> handleWeatherbitCallException(
            NoSuchRecordException ex, WebRequest request) {
        return new ResponseEntity<>(createBody(ex), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(UnformatableDateException.class)
    public ResponseEntity<Object> handleWeatherbitCallException(
            UnformatableDateException ex, WebRequest request) {
        return new ResponseEntity<>(createBody(ex), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidFileFormatException.class)
    public ResponseEntity<Object> handleWeatherbitCallException(
            InvalidFileFormatException ex, WebRequest request) {
        return new ResponseEntity<>(createBody(ex), HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> createBody(RuntimeException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return body;
    }
}
