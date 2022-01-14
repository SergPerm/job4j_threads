package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

/**
 * int speed - количество Mb в секунду.
 */

public class Wget implements Runnable {

    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed * 1024 * 1024;
    }

    @Override
    public void run() {
        Instant startAll = Instant.now();
        String nameRes = Arrays.stream(url.split("/")).reduce((first, second) -> second).get();
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(nameRes)) {
            byte[] dataBuffer = new byte[2048];
            int bytesRead;
            long byteWrite = 0;
            Instant start = Instant.now();
            while ((bytesRead = in.read(dataBuffer, 0, 2048)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                byteWrite += bytesRead;
                if (byteWrite >= speed) {
                    Instant finish = Instant.now();
                    long delay = 5000L - Duration.between(start, finish).toMillis();
                    System.out.println(delay);
                    start = finish;
                    byteWrite = 0;
                    if (delay > 0) {
                        System.out.println("delay");
                        Thread.sleep(delay);
                    }
                }
            }
            Instant finishAll = Instant.now();
            System.out.println(Duration.between(startAll, finishAll).toSeconds());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validate(args.length);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }

    private static void validate(int argsLength) {
        if (argsLength < 2) {
            throw new IllegalArgumentException("");
        }
    }
}
