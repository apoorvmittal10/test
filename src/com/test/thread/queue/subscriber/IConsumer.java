package com.test.thread.queue.subscriber;

import com.test.thread.queue.bo.Message;

public interface IConsumer {

    boolean consumeMessage(Message message);
}
