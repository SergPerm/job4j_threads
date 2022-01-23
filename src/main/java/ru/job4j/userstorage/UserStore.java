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
        User tmp = users.putIfAbsent(user.getId(), user);
        return tmp == null;
    }

    @Override
    public synchronized boolean delete(User user) {
        return users.remove(user.getId(), user);
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
        userFrom.setAmount(userFrom.getAmount() - amount);
        userTo.setAmount(userTo.getAmount() + amount);
        return true;
    }
}
