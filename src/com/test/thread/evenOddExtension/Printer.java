package com.test.thread.evenOddExtension;

/**
 * Created by apoorv on 12/05/19.
 */
public class Printer {

    // The printState defines the thread number which has to print the next number.
    // Starts with 0 i.e. for 3 threads 0,1,2.
    private int printState = 1;
    private int limit = 100;
    private int count = 1;
    private boolean isFinished = false;

    public void print() {
        while (true) {
            synchronized (this) {
                // Other thread which are not supposed to be in a state to print shall wait.
                while (count % 3 != printState && isFinished) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (count > limit) {
                    isFinished = true;
                    notifyAll();
                    break;
                }

                System.out.println(printState + " thread: " + count);

                printState++;
                printState %= 3;

                count++;
                notifyAll();
            }
        }
    }
}
