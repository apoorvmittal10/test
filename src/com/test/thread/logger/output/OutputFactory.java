package com.test.thread.logger.output;

import com.test.thread.logger.output.impl.ConsoleOutputImpl;
import com.test.thread.logger.output.impl.FileOutputImpl;

/**
 * Created by apoorv on 09/09/17.
 */
public class OutputFactory {

    private static final Output CONSOLE_OUTPUT = new ConsoleOutputImpl();
    private static final Output FILE_OUTPUT = new FileOutputImpl();

    public static Output getOutputByType(Output.OutputType outputType) {
        if (outputType == null) {
            return null;
        }

        switch (outputType) {
            case FILE:
                return FILE_OUTPUT;
            case CONSOLE:
                return CONSOLE_OUTPUT;
            default:
                return null;
        }
    }
}
