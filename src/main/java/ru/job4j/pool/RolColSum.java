package ru.job4j.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] result = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            Sums tmp = new Sums();
            for (int j = 0; j < matrix.length; j++) {
                tmp.setRowSum(tmp.getRowSum() + matrix[i][j]);
                tmp.setColSum(tmp.getColSum() + matrix[j][i]);
            }
            result[i] = tmp;
        }
        return result;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] result = new Sums[matrix.length];

        int n = matrix.length;
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int k = 0; k < n; k++) {
            futures.put(k, getTask(matrix, k));
        }
        for (Integer key : futures.keySet()) {
            result[key] = futures.get(key).get();
        }
        return result;
    }

    public static CompletableFuture<Sums> getTask(int[][] matrix, int number) {
        return CompletableFuture.supplyAsync(() -> {
            int row = 0;
            int col = 0;
            for (int i = 0; i < matrix.length; i++) {
                col += matrix[i][number];
                row += matrix[number][i];
            }
            Sums temp = new Sums();
            temp.setColSum(col);
            temp.setRowSum(row);
            return temp;
        });
    }
}
