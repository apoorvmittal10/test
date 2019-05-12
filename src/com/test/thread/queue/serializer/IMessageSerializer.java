package com.test.thread.queue.serializer;

import com.test.thread.queue.bo.Message;

import java.io.Serializable;

public interface IMessageSerializer {

    Serializable serializeMessage(Message message);
}
