package ru.job4j.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStore implements Storage {

    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();

    @Override
    public synchronized boolean add(User user) {
        users.putIfAbsent(user.getId(), user);
        return users.containsValue(user);
    }

    @Override
    public synchronized boolean delete(User user) {
        users.remove(user.getId());
        return !users.containsValue(user);
    }

    @Override
    public synchronized boolean update(User user) {
        return users.replace(user.getId(), users.get(user.getId()), user);
    }

    synchronized boolean transfer(int fromId, int toId, int amount) {
        User userFrom = users.get(fromId);
        User userTo = users.get(toId);
        if (userFrom == null || userFrom.getAmount() < amount || userTo == null) {
            return false;
        }
        userFrom.decrease(amount);
        userTo.increase(amount);
        return true;
    }
}
