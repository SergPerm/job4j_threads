package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Count {

    @GuardedBy("this")
    private int count;

    public synchronized int getCount() {
        return this.count;
    }

    public synchronized void increment() {
        this.count++;
    }
}
