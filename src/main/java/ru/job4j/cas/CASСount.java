package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 0. CAS - операции [#318332].
 */
@SuppressWarnings("CheckStyle")
@ThreadSafe
public class CASСount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int current;
        int next;
        do {
            current = get();
            next = current + 1;
        } while (!count.compareAndSet(current, next));
    }

    public int get() {
        return count.get();
    }
}

