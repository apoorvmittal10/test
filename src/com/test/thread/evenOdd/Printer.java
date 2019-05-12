package com.test.thread.evenOdd;

/**
 * Created by apoorv on 12/05/19.
 */
public class Printer {

    private boolean isOdd = true;
    private int limit = 100;
    private int count = 1;

    public void printOdd() {
        while (true) {
            synchronized (this) {
                while (!isOdd) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                isOdd = false;
                if (count > limit) {
                    notify();
                    break;
                }

                System.out.println("Odd thread: " + count);
                count++;
                notify();
            }
        }
    }

    public void printEven() {
        while (true) {
            synchronized (this) {
                while (isOdd) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                isOdd = true;
                if (count > limit) {
                    notify();
                    break;
                }

                System.out.println("Even thread: " + count);
                count++;
                notify();
            }
        }
    }
}
