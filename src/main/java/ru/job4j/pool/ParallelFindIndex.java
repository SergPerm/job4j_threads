package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelFindIndex extends RecursiveTask<Integer> {
    private final int[] array;
    private final int from;
    private final int to;
    private final int value;

    public ParallelFindIndex(int value, int[] array, int from, int to) {
        this.value = value;
        this.array = array;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        int mid = (from + to) / 2;
        if (array[mid] == value) {
            return mid;
        }
        if (from == to) {
            return -1;
        }
        ParallelFindIndex left = new ParallelFindIndex(value, array, from, mid);
        ParallelFindIndex right = new ParallelFindIndex(value, array, mid + 1, to);
        left.fork();
        right.fork();
        Integer aLeft = left.join();
        Integer aRight = right.join();
        return aLeft != -1 ? aLeft : aRight;
    }

    public static Integer findIndex(int value, int[] array) {
        if (array.length <= 10) {
            return ParallelFindIndex.findIndexSmallArray(value, array);
        }
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelFindIndex(value, array, 0, array.length - 1));
    }

    private static Integer findIndexSmallArray(int value, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
