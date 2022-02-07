package ru.job4j.nonblocking.cache;

import org.junit.Test;

import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenAddNewThenTrue() {
        Base b1 = new Base(1, 1);
        Cache cache = new Cache();
        assertTrue(cache.add(b1));
    }

    @Test
    public void whenAddCopyThenFalse() {
        Base b1 = new Base(1, 1);
        Cache cache = new Cache();
        cache.add(b1);
        assertFalse(cache.add(b1));
    }

    @Test
    public void whenDeleteSucceeded() {
        Base b1 = new Base(1, 1);
        Cache cache = new Cache();
        cache.add(b1);
        cache.delete(b1);
        assertTrue(cache.add(b1));
    }

    @Test
    public void whenUpdateOfSingleChangeThenTrue() {
        Base b1 = new Base(1, 1);
        Cache cache = new Cache();
        cache.add(b1);
        b1.setName("1");
        assertTrue(cache.update(b1));
        b1.setName("2");
    }

    @Test (expected = OptimisticException.class)
    public void whenUpdateOfMoreChangeThenException() {
        Base b1 = new Base(1, 1);
        Cache cache = new Cache();
        cache.add(b1);
        b1.setName("1");
        cache.update(b1);
        b1.setName("2");
        cache.update(b1);
    }

    @Test
    public void whenUpdateNotExistingEntityThenFalse() {
        Base b1 = new Base(1, 1);
        Cache cache = new Cache();
        cache.add(b1);
        Base b2 = new Base(2, 1);
        assertFalse(cache.update(b2));
    }
}