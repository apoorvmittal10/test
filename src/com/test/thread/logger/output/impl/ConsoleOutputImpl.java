package com.test.thread.logger.output.impl;

import com.test.thread.logger.output.Output;

/**
 * Created by apoorv on 09/09/17.
 */
public class ConsoleOutputImpl implements Output {

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }
}
