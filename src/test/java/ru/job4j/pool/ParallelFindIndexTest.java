package ru.job4j.pool;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ParallelFindIndexTest {

    @Test
    public void findIndex() {
        int[] array = new int[] {2, 10, 48, 95, 7, 12, 7, 68, 4, 100, 1, -4, 8, -8};
        assertThat(3, is(ParallelFindIndex.findIndex(95, array)));
    }

    @Test
    public void findIndexSmallArray() {
        int[] array = new int[] {2, 10, 48, 95, 7, 12, 7, 68, 4, 100};
        assertThat(3, is(ParallelFindIndex.findIndex(95, array)));
    }
}