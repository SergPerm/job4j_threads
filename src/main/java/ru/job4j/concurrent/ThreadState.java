package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread thread1 = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread thread2 = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        thread1.start();
        thread2.start();
        while (thread1.getState() != Thread.State.TERMINATED
                && thread2.getState() != Thread.State.TERMINATED) {
            System.out.println("\rThreads is work");
        }
        System.out.println("" + thread1.getName() + " is " + thread1.getState()
                + ", " + thread2.getName() + " is " + thread2.getState());
    }

}
