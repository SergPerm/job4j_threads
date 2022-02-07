package ru.job4j.nonblocking;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        Integer cnt;
        int cntIncr;
        do {
            cnt = count.get();
            cntIncr = cnt + 1;
        } while (!count.compareAndSet(cnt, cntIncr));
    }

    public int get() {
        return count.get();
    }
}
