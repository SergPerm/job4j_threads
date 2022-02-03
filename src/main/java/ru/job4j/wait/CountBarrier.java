package ru.job4j.wait;

public class CountBarrier {

    private final int total;
    private int count;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (this) {
            count++;
            this.notifyAll();
        }
    }

    public void await() {
        synchronized (this) {
            while (count < total) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
