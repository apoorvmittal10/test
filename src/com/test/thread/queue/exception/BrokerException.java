package com.test.thread.queue.exception;

public class BrokerException extends Exception {

    public BrokerException(String message) {
        super(message);
    }

    public BrokerException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BrokerException(Throwable throwable) {
        super(throwable);
    }
}
