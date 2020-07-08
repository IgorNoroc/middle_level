package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. Реализовать шаблон Producer Consumer. [#318306].
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int point = 10;

    public synchronized void offer(T value) {
        while (queue.size() >= point) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        queue.add(value);
        notify();
    }

    public synchronized T poll() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        T t = queue.poll();
        notify();
        return t;
    }
}
