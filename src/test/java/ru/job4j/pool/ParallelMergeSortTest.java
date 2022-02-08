package ru.job4j.pool;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParallelMergeSortTest {

    @Test
    public void sort() {
        int[] array = new int[] {2, 10, 48, 95, 7, 12, 7, 68, 4, 100, 1, -4, 8, -8};
        int[] result = new int[] {-8, -4, 1, 2, 4, 7, 7, 8, 10, 12, 48, 68, 95, 100};
        assertArrayEquals(result, ParallelMergeSort.sort(array));
    }
}