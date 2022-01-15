package ru.job4j.concurrent;

public final class DCLSingleton {

    private static volatile DCLSingleton inst;

    private DCLSingleton() {
    }

    public static DCLSingleton getInstance() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }
}