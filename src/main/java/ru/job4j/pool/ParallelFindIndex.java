package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelFindIndex<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final int from;
    private final int to;
    private final T value;

    public ParallelFindIndex(T value, T[] array, int from, int to) {
        this.value = value;
        this.array = array;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if ((to - from) <= 10) {
            return findIndexSmallArray(value, array);
        }
        int mid = (from + to) / 2;
        ParallelFindIndex<T> left = new ParallelFindIndex<>(value, array, from, mid);
        ParallelFindIndex<T> right = new ParallelFindIndex<>(value, array, mid + 1, to);
        left.fork();
        right.fork();
        Integer aLeft = left.join();
        Integer aRight = right.join();
        return aLeft != -1 ? aLeft : aRight;
    }

    public static <T> Integer findIndex(T value, T[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelFindIndex<>(value, array, 0, array.length - 1));
    }

    private Integer findIndexSmallArray(T value, T[] array) {
        for (int i = from; i <= to; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }
}
