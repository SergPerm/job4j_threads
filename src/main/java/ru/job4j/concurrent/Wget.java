package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        for (int i = 1; i < 100; i++) {
                            System.out.print("\rLoading " + i + " %");
                            Thread.sleep(200);

                        }
                        System.out.print("\rLoaded");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
    }
}