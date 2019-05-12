package com.test.thread.evenOddExtension;

/**
 * Created by apoorv on 12/05/19.
 */

// Print number in sequence when 3 threads print the numbers, first thread printing 1 with a gap of 3, second thread
// printing 2 with a gap of 3, similarly third thread starting from 3 and printing with a gap of 3.
public class EvenOddExtension {

    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread t1 = new Thread(() -> {
            printer.print();
        });

        Thread t2 = new Thread(() -> {
            printer.print();
        });

        Thread t3 = new Thread(() -> {
            printer.print();
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
