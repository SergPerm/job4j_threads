package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        char[] process = new char[]{'-', '\\', '|', '/'};
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\r load: " + process[count]);
            count++;
            if (count == process.length) {
                count = 0;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(6000);
        progress.interrupt();
    }
}
