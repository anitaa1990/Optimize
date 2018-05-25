package com.an.optimize.rest;

public class OptimizeException extends RuntimeException {

    public OptimizeException(String s) {
        super(s);
    }

    public OptimizeException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
