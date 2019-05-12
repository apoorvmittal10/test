package com.test.thread.logger;

/**
 * Created by apoorv on 09/09/17.
 */
public interface MyLogger {

    enum Level {
        DEBUG (0), INFO (1), ERROR (2);

        private int levelKey;

        Level(int key) {
            this.levelKey = key;
        }

        public  int getLevelKey() {
            return this.levelKey;
        }
    }

    void logMessage(String message, String source, Level level);

}
