package ru.job4j.pool;

public class MergeSort {

    public static int[] sort(int[] array) {
        return sort(array, 0, array.length - 1);
    }

    private static int[] sort(int[] array, int from, int to) {
        if (from == to) {
            return new int[] {array[from]};
        }
        int mid = (from + to) / 2;
        return merge(sort(array, from, mid), sort(array, mid + 1, to));
    }

    public static int[] merge(int[] left, int[] right) {
        int lI = 0;
        int rI = 0;
        int resI = 0;
        int[] result = new int[left.length + right.length];
        while (resI != result.length) {
            if (lI == left.length) {
                result[resI++] = right[rI++];
            } else if (rI == right.length) {
                result[resI++] = left[lI++];
            } else if (left[lI] <= right[rI]) {
                result[resI++] = left[lI++];
            } else {
                result[resI++] = right[rI++];
            }
        }
        return result;
    }
}
