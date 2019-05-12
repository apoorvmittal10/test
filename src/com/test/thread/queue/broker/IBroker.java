package com.test.thread.queue.broker;

import com.test.thread.queue.bo.Message;
import com.test.thread.queue.exception.BrokerException;
import com.test.thread.queue.subscriber.Consumer;

public interface IBroker {

    boolean addMessage(String qId, Message message) throws BrokerException;

    void registerSubscriber(String qId, Consumer consumer) throws BrokerException;

    void deregisterSubscriber(String qId, Consumer consumer);
}
