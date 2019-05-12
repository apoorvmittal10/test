package com.test.thread.evenOdd;

/**
 * Created by apoorv on 12/05/19.
 */

// Print number in sequence when 2 threads print the numbers, first thread printing even and other odd.
public class EvenOdd {

    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread t1 = new Thread(() -> {
            printer.printOdd();
        });

        Thread t2 = new Thread(() -> {
            printer.printEven();
        });

        t1.start();
        t2.start();
    }
}
