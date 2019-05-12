package com.test.thread.logger.output;

/**
 * Created by apoorv on 09/09/17.
 */
public interface Output {

    enum OutputType {
        CONSOLE, FILE
    }

    void printMessage(String message);

}
