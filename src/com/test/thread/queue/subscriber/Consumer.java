package com.test.thread.queue.subscriber;

import com.test.thread.queue.bo.Message;

public class Consumer implements IConsumer {

    private String sId;

    private int concurrency;

    public Consumer(String sId) {
        this(sId, 1);
    }

    public Consumer(String sId, int concurrency) {
        this.sId = sId;
        this.concurrency = concurrency;
    }

    public String getSId() {
        return sId;
    }

    public int getConcurrency() {
        return concurrency;
    }

    @Override
    public int hashCode() {
        return sId.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return sId.equals(other);
    }

    @Override
    public boolean consumeMessage(Message message) {
        System.out.println(sId + "-" + message.getData());
        return true;
    }
}
