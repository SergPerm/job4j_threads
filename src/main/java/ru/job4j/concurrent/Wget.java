package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

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
        String nameRes = null;
        try {
            nameRes = Paths.get(new URI(url).getPath()).getFileName().toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (nameRes == null) {
            nameRes = "unknownName.dat";
        }
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(nameRes)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long byteWrite = 0;
            Instant start = Instant.now();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                byteWrite += bytesRead;
                if (byteWrite >= speed) {
                    Instant finish = Instant.now();
                    long duration = Duration.between(start, finish).toMillis();
                    System.out.println(duration);
                    byteWrite = 0;
                    if (duration < 1000) {
                        System.out.println("delay");
                        Thread.sleep(1000 - duration);
                    }
                    start = Instant.now();
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
        if (argsLength != 2) {
            throw new IllegalArgumentException("");
        }
    }
}
