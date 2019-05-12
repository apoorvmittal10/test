package com.test.thread.queue.persistence;

import java.io.Serializable;

public interface IPersistMessage {

    boolean persistData(Serializable message);
}
