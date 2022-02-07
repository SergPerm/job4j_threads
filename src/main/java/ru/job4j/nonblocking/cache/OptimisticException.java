package ru.job4j.nonblocking.cache;

public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}
