package ru.job4j.pool;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class RolColSumTest {

    @Test
    public void sum() {
        int[][] matrix = new int[][]{{2, 4, 6}, {7, 5, 8}, {3, 9, 4}};
        RolColSum.Sums sums1 = new RolColSum.Sums();
        sums1.setRowSum(12);
        sums1.setColSum(12);
        RolColSum.Sums sums2 = new RolColSum.Sums();
        sums2.setRowSum(20);
        sums2.setColSum(18);
        RolColSum.Sums sums3 = new RolColSum.Sums();
        sums3.setRowSum(16);
        sums3.setColSum(18);
        RolColSum.Sums[] result = new RolColSum.Sums[] {sums1, sums2, sums3};
        assertArrayEquals(result, RolColSum.sum(matrix));
    }

    @Test
    public void asyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = new int[][]{{2, 4, 6}, {7, 5, 8}, {3, 9, 4}};
        RolColSum.Sums sums1 = new RolColSum.Sums();
        sums1.setRowSum(12);
        sums1.setColSum(12);
        RolColSum.Sums sums2 = new RolColSum.Sums();
        sums2.setRowSum(20);
        sums2.setColSum(18);
        RolColSum.Sums sums3 = new RolColSum.Sums();
        sums3.setRowSum(16);
        sums3.setColSum(18);
        RolColSum.Sums[] result = new RolColSum.Sums[] {sums1, sums2, sums3};
        assertArrayEquals(result, RolColSum.asyncSum(matrix));
    }
}