package ru.job4j.wait;

public class Barrier {

    private boolean flag = false;

    public void on() {
        synchronized (this) {
            flag = true;
            this.notifyAll();
        }
    }

    public void off() {
        synchronized (this) {
            flag = false;
            this.notifyAll();
        }
    }

    public void check() {
        synchronized (this) {
            while (!flag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
