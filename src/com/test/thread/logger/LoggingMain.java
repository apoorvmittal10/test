package com.test.thread.logger;

/**
 * Created by apoorv on 09/09/17.
 */
public class LoggingMain {

    private static MyLogger myLogger = MyLoggerFactory.getDefaultLogger();

    public static void main (String args[]) {
        myLogger.logMessage("test", "1", MyLogger.Level.DEBUG);
        myLogger.logMessage("test1", "2", MyLogger.Level.INFO);
        myLogger.logMessage("test2", "3", MyLogger.Level.INFO);
        myLogger.logMessage("test3", "4", MyLogger.Level.DEBUG);
        myLogger.logMessage("test4", "5", MyLogger.Level.INFO);
        myLogger.logMessage("test5", "6", MyLogger.Level.INFO);
        myLogger.logMessage("test6", "7", MyLogger.Level.INFO);
        myLogger.logMessage("test7", "8", MyLogger.Level.INFO);
        myLogger.logMessage("test8", "9", MyLogger.Level.ERROR);

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            final String message = String.format("test%s", i);
            final String source = String.valueOf(i);
            Thread t = new Thread(() -> myLogger.logMessage(message, source, MyLogger.Level.DEBUG));
            threads[i] = t;
        }

        for (Thread thread : threads) {
            thread.start();
        }

    }
}
