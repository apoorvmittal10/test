package com.test.thread.queue.broker;

public class BrokerFactory {

    private static final IBroker DEFAULT_BROKER = new BrokerImpl();

    // TODO in-future add enum to support
    public static IBroker getBroker() {
        return DEFAULT_BROKER;
    }
}
