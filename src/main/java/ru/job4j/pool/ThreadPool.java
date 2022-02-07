package ru.job4j.pool;

import ru.job4j.wait.SimpleBlockingQueue;
import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public ThreadPool() {
        init();
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            Thread tmp = new Thread(() -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        tasks.poll().run();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            tmp.start();
            threads.add(tmp);
        }
    }

    public void work(Runnable job) throws InterruptedException {
        if (job != null) {
            tasks.offer(job);
        }
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    public static void main(String[] args) {
        ThreadPool tp = new ThreadPool();
        System.out.println(tp.size);
    }
}