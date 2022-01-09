package ru.job4j.concurrent;

import javax.swing.plaf.TableHeaderUI;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread thread2 = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("" + thread1.getName() + " is " + thread1.getState()
                + ", " + thread2.getName() + " is " + thread2.getState());
    }

}
