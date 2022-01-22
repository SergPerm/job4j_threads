package ru.job4j.userstorage;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void whenAddNewUserThenTrue() {
        UserStore store = new UserStore();
         assertTrue(store.add(new User(1, 100)));
    }

    @Test
    public void whenAddCopyUserThenFalse() {
        UserStore store = new UserStore();
        store.add(new User(1, 100));
        assertFalse(store.add(new User(1, 100)));
    }

    @Test
    public void testDelete() {
        UserStore store = new UserStore();
        User user = new User(1, 100);
        assertTrue(store.add(user));
        assertTrue(store.delete(user));
    }

    @Test
    public void testUpdate() {
        UserStore store = new UserStore();
        User user = new User(1, 100);
        assertTrue(store.add(user));
        User user1 = new User(1, 150);
        assertTrue(store.update(user1));
    }

    @Test
    public void whenTransferSuccess() {
        UserStore store = new UserStore();
        store.add(new User(1, 100));
        store.add(new User(2, 200));
        assertTrue(store.transfer(1, 2, 100));
    }

    @Test
    public void whenAmountFromIsNotEnoughThenFalse() {
        UserStore store = new UserStore();
        store.add(new User(1, 100));
        store.add(new User(2, 200));
        assertFalse(store.transfer(1, 2, 101));
    }

    @Test
    public void whenUserFromIsNotExistThenFalse() {
        UserStore store = new UserStore();
        store.add(new User(1, 100));
        store.add(new User(2, 200));
        assertFalse(store.transfer(3, 2, 100));
    }

    @Test
    public void whenUserToIsNotExistThenFalse() {
        UserStore store = new UserStore();
        store.add(new User(1, 100));
        store.add(new User(2, 200));
        assertFalse(store.transfer(1, 3, 100));
    }
}