package ru.job4j.concurrent;

/**
 * 2. Модель памяти Java [#318317]
 */
public class DCLSingleton {
    private static volatile DCLSingleton inst;

    public static DCLSingleton instOf() {
        if (inst == null) {
            inst = new DCLSingleton();
        }
        return inst;
    }

    private DCLSingleton() {
    }
}
