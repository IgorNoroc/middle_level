package ru.job4j.nonblockingcache;

/**
 * Runtime exception for class Cache.
 */
public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}
