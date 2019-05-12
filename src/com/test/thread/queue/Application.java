package com.test.thread.queue;

import com.test.thread.queue.broker.BrokerFactory;
import com.test.thread.queue.broker.IBroker;
import com.test.thread.queue.exception.BrokerException;
import com.test.thread.queue.producer.Producer;
import com.test.thread.queue.subscriber.Consumer;

public class Application {

    public String message = "A";

    public static void main(String args[]) throws BrokerException {
        IBroker broker = BrokerFactory.getBroker();

        Producer p1 = new Producer("q1", broker);
        Producer p2 = new Producer("q1", broker);
        Producer p3 = new Producer("q1", broker);

        Producer p4 = new Producer("q2", broker);
        Producer p5 = new Producer("q2", broker);
        Producer p6 = new Producer("q2", broker);

        Consumer s1 = new Consumer("s1");
        Consumer s2 = new Consumer("s2", 2);
        Consumer s3 = new Consumer("s3", 5);
        Consumer s4 = new Consumer("s4", 2);
        Consumer s5 = new Consumer("s5");

        broker.registerSubscriber("q1", s1);
        broker.registerSubscriber("q1", s2);

        broker.registerSubscriber("q2", s3);
        broker.registerSubscriber("q2", s4);
        broker.registerSubscriber("q2", s5);

        p1.addMessage("test");
        p2.addMessage("test1");

        p4.addMessage("Atest1");
        p5.addMessage("Atest2");
        p6.addMessage("Atest3");

//        Application application = new Application();

//        Message original = application.message;

//        Runnable r = () -> {
//            application.test();};
//
//        Thread t1 = new Thread(r);
//        t1.setName("1");
//        t1.start();
//
//        Thread t2 = new Thread(r);
//        t2.setName("2");
//        t2.start();
//
//        Thread t3 = new Thread(r);
//        t3.setName("3");
//        t3.start();
//
//        Thread t4 = new Thread(r);
//        t4.setName("4");
//        t4.start();
//
//        Thread t5 = new Thread(r);
//        t5.setName("5");
//        t5.start();
//
//        Thread t6 = new Thread(r);
//        t6.setName("6");
//        t6.start();

//        application.test1();

    }

//    public void test() {
//        String id = Thread.currentThread().getName();
//        System.out.println(id + "-" + "waiting");
//        synchronized (message) {
//            System.out.println(id + "-" + "test");
//            message = "B";
//            System.out.println(id + "-" + "test1");
//        }
//        System.out.println(id + "-" + "completed");
//    }
//
//    public void test1() {
//        String[] dpColumns = "abc".split(",");
//        List<String> dpColumnsList = Arrays.asList(dpColumns);
//
//        for (String column : dpColumnsList) {
//            System.out.println(column);
//        }
//
//        dpColumns = "abc,def".split(",");
//        dpColumnsList = Arrays.asList(dpColumns);
//
//        for (String column : dpColumnsList) {
//            System.out.println(column);
//        }
//    }
}
