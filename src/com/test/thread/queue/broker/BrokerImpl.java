package com.test.thread.queue.broker;

import com.test.thread.queue.bo.Message;
import com.test.thread.queue.exception.BrokerException;
import com.test.thread.queue.persistence.FilePersistMessageImpl;
import com.test.thread.queue.persistence.IPersistMessage;
import com.test.thread.queue.serializer.DefaultMessageSerializer;
import com.test.thread.queue.serializer.IMessageSerializer;
import com.test.thread.queue.subscriber.Consumer;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.*;

public class BrokerImpl implements IBroker {

    private ConcurrentHashMap<String, ConcurrentLinkedQueue<Message>> masterQueueMap = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String, ConcurrentLinkedQueue<Message>> subscriberQueueMap = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String, HashSet<Consumer>> queueIdSubscriberMap = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String, Consumer> sIdConsumerMap = new ConcurrentHashMap<>();

    private ExecutorService executorService = Executors.newFixedThreadPool(20);

    private IPersistMessage persistMessage = new FilePersistMessageImpl();

    private IMessageSerializer messageSerializer = new DefaultMessageSerializer();

    // TODO Make class singleton
    public BrokerImpl() {
//        Runnable backgroundConsumption = () -> { messageConsumption(); };
//        new Thread(backgroundConsumption).start();
        CompletableFuture.supplyAsync(() -> messageConsumption(), executorService);
    }

    public boolean addMessage(String qId, Message message) throws BrokerException {
        if (qId == null || message == null || message.getData() == null) {
            throw new BrokerException("Invalid qId or message - null data");
        }

        if (!masterQueueMap.containsKey(qId)) {
            masterQueueMap.put(qId, new ConcurrentLinkedQueue<>());
        }

        Serializable data = messageSerializer.serializeMessage(message);
        boolean isPersistSuccessful = persistMessage.persistData(data);
        if (isPersistSuccessful) {
            if (!masterQueueMap.containsKey(qId)) {
                masterQueueMap.put(qId, new ConcurrentLinkedQueue<>());
            }
            // Data can be added in separate thread too
            masterQueueMap.get(qId).offer(message);
            CompletableFuture.supplyAsync(() -> publishMessageToSubscribers(qId, message), executorService);
            return true;
        }
        return false;
    }

    public void registerSubscriber(String qId, Consumer consumer) throws BrokerException {
        if (qId == null || consumer == null || consumer.getSId() == null) {
            throw new BrokerException("Invalid qId or consumer - null data");
        }

        if (!queueIdSubscriberMap.containsKey(qId)) {
            queueIdSubscriberMap.put(qId, new HashSet<>());
        }

        if (queueIdSubscriberMap.get(qId).contains(consumer)) {
            throw new BrokerException("Consumer already registered");
        }

        queueIdSubscriberMap.get(qId).add(consumer);
        sIdConsumerMap.put(consumer.getSId(), consumer);
    }

    public void deregisterSubscriber(String qId, Consumer consumer) {
        // Same like above implementation
    }

    private boolean publishMessageToSubscribers(String qId, Message data) {
        if (!queueIdSubscriberMap.containsKey(qId)) {
            return false;
        }

        for (Consumer consumer : queueIdSubscriberMap.get(qId)) {
            if (!subscriberQueueMap.containsKey(consumer.getSId())) {
                subscriberQueueMap.put(consumer.getSId(), new ConcurrentLinkedQueue<>());
            }

            subscriberQueueMap.get(consumer.getSId()).offer(data);
        }

        return true;
    }

    private boolean messageConsumption() {
        // TODO handle concurrency
        while (true) {
            for (Map.Entry<String, ConcurrentLinkedQueue<Message>> entry : subscriberQueueMap.entrySet()) {
                Message message = entry.getValue().poll();
                if (message != null) {
                    boolean isSuccessful = sIdConsumerMap.get(entry.getKey()).consumeMessage(message);
                    if (!isSuccessful) {
                        entry.getValue().offer(message);
                    }
                }
            }
        }
    }
}
