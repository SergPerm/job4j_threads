package ru.job4j.wait;

public class ParallelSearch {

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(10);
        final Thread consumer = new Thread(
                () -> {
                    while (!sbq.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(sbq.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "Consumer"
        );

        final Thread producer = new Thread(
                () -> {
                    for (int index = 0; index < 5; index++) {
                        try {
                            sbq.offer(index);
                            System.out.println("producer " + index);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "Producer"
        );

        producer.start();
        Thread.sleep(500);
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
    }
}
