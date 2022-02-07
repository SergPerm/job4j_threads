package ru.job4j.nonblocking;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void whenMultiThreadSaveIncrement() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        CASCount counter = new CASCount();

        IntStream.range(0, 1000)
                .forEach(count -> service.submit(counter::increment));
        service.awaitTermination(100, TimeUnit.MILLISECONDS);

        assertEquals(1000, counter.get());
    }

    @Test
    public void whenOneIncrementThenOne() {
        CASCount counter = new CASCount();
        counter.increment();
        assertEquals(1, counter.get());
    }

    @Test
    public void whenWithoutIncrementThenZero() {
        CASCount counter = new CASCount();
        assertEquals(0, counter.get());
    }
}