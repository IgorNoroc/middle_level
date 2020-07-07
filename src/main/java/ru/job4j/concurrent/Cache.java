package ru.job4j.concurrent;

/**
 * 1. Синхронизация общих ресурсов. [#318320]
 */
public class Cache {
    private static Cache cache;

    public synchronized static Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }
}
