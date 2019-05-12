package com.test.thread.queue.serializer;

import com.test.thread.queue.bo.Message;

import java.io.Serializable;

public class DefaultMessageSerializer implements IMessageSerializer {

    public Serializable serializeMessage(Message message) {
        return message;
    }
}
