package com.test.thread.logger.helper;

import com.test.thread.logger.MyLogger;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by apoorv on 09/09/17.
 */
public class LoggerProducer {

    private ConcurrentLinkedQueue<String> linkedQueue;

    public LoggerProducer(ConcurrentLinkedQueue<String> linkedQueue) {
        this.linkedQueue = linkedQueue;
    }

    public void addMessage(String message, String source, MyLogger.Level level) {
        String messageToLog = String.format("Source: %s, Level: %s, Message: %s", source, level.name(), message);
        linkedQueue.offer(messageToLog);
    }
}
