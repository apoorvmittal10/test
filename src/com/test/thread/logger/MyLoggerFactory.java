package com.test.thread.logger;

import com.test.thread.logger.impl.MyLoggerImpl;
import com.test.thread.logger.output.Output;

/**
 * Created by apoorv on 09/09/17.
 */
public class MyLoggerFactory {

    private static final MyLogger DEFAULT_LOGGER_IMPL = new MyLoggerImpl();

    public static MyLogger getDefaultLogger() {
        return DEFAULT_LOGGER_IMPL;
    }

    public static MyLogger getLogger(MyLogger.Level level, Output output) {
        return new MyLoggerImpl(level, output);
    }
}
