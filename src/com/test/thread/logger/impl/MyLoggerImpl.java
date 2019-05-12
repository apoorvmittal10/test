package com.test.thread.logger.impl;

import com.test.thread.logger.MyLogger;
import com.test.thread.logger.helper.LoggerConsumer;
import com.test.thread.logger.helper.LoggerProducer;
import com.test.thread.logger.output.Output;
import com.test.thread.logger.output.OutputFactory;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by apoorv on 09/09/17.
 */
public class MyLoggerImpl implements MyLogger {

    private Level level;

    private ConcurrentLinkedQueue<String> linkedQueue = new ConcurrentLinkedQueue<>();

    private LoggerProducer loggerProducer;

    private LoggerConsumer loggerConsumer;

    public MyLoggerImpl() {
        this(Level.INFO, OutputFactory.getOutputByType(Output.OutputType.CONSOLE));
    }

    public MyLoggerImpl(Level level) {
        this(level, OutputFactory.getOutputByType(Output.OutputType.CONSOLE));
    }

    public MyLoggerImpl(Level level, Output output) {
        this.level = level;
        this.loggerProducer = new LoggerProducer(linkedQueue);
        this.loggerConsumer = new LoggerConsumer(linkedQueue, output);
        startLogger(loggerConsumer, 4);
    }

    public void logMessage(String message, String source, Level level) {
        // Reject message if not in up hierarchy
        if (level.getLevelKey() < this.level.getLevelKey()) {
            return;
        }

        // save message
        loggerProducer.addMessage(message, source, level);
    }

    private void startLogger(LoggerConsumer loggerConsumer, int threadCount) {
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            Thread t = new Thread(() -> loggerConsumer.printMessage());
            threads[i] = t;
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}
