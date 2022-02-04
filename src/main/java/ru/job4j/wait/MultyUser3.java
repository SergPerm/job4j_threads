package ru.job4j.wait;

public class MultyUser3 {
    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(2);
        Thread producer1 = new Thread(
                () -> {
                    sbq.offer(1);
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Producer1"
        );
        Thread producer2 = new Thread(
                () -> {
                    sbq.offer(2);
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Producer2"
        );
        Thread producer3 = new Thread(
                () -> {
                    sbq.offer(3);
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Producer3"
        );
        Thread producer4 = new Thread(
                () -> {
                    sbq.offer(4);
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Producer4"
        );
        Thread consumer1 = new Thread(
                () -> {
                    sbq.poll();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Consumer1"
        );
        Thread consumer2 = new Thread(
                () -> {
                    sbq.poll();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Consumer2"
        );
        producer1.start();
        producer2.start();
        Thread.sleep(1000);
        producer3.start();
        producer4.start();
        consumer1.start();
        Thread.sleep(1000);
        consumer2.start();
    }
}
