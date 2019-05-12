package com.test.thread.queue.producer;

import com.test.thread.queue.bo.Message;
import com.test.thread.queue.broker.IBroker;
import com.test.thread.queue.exception.BrokerException;

public class Producer {

    private IBroker broker;

    private String qId;

    public Producer(String qId, IBroker broker) {
        this.qId = qId;
        this.broker = broker;
    }

    public void addMessage(String message) {
        boolean isSuccessful = false;
        try {
            isSuccessful = broker.addMessage(qId, new Message(message));
        } catch (BrokerException e) {
            e.printStackTrace();
        }

        // TODO Handle non-successful scenario
    }
}
