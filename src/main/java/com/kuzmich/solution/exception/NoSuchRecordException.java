package com.kuzmich.solution.exception;

public class NoSuchRecordException extends RuntimeException {
    public NoSuchRecordException(String message) {
        super(message);
    }
}