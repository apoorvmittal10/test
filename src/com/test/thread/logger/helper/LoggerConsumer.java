package com.test.thread.logger.helper;

import com.test.thread.logger.output.Output;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by apoorv on 09/09/17.
 */
public class LoggerConsumer {

    private ConcurrentLinkedQueue<String> linkedQueue;

    private Output output;

    public LoggerConsumer(ConcurrentLinkedQueue<String> linkedQueue, Output output) {
        this.linkedQueue = linkedQueue;
        this.output = output;
    }

    public void printMessage() {
        while (true) {
            String message = linkedQueue.poll();
            if (message != null) {
                output.printMessage(message);
            }
        }
    }
}
