package ru.job4j.wait;

public class MultyUser2 {
    public static void main(String[] args) throws InterruptedException {
        CountBarrier barrier = new CountBarrier(2);
        Thread master = new Thread(
                () -> {
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Master"
        );
        Thread slave = new Thread(
                () -> {
                    barrier.count();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Slave"
        );
        Thread slave2 = new Thread(
                () -> {
                    barrier.count();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Slave2"
        );
        slave.start();
        master.start();
        slave2.start();
    }

}
