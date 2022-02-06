package ru.job4j.nonblocking;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        Integer cnt;
        Integer cntIncr;
        do {
            cnt = count.get();
            cntIncr = cnt++;
        } while (!count.compareAndSet(cnt, cntIncr));
    }

    public int get() {
        Integer cnt = count.get();
        if (cnt == null) {
            return 0;
        }
        return cnt;
    }
}
