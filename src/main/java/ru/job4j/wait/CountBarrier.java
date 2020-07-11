package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;

/**
 * 0. Управление нитью через wait. [#318307]
 */
public class CountBarrier {
    private final Object monitor = this;
    private final int total;
    @GuardedBy("this")
    private volatile int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public synchronized void count() {
        count++;
        if (count == total) {
            notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (monitor) {
            if (count != total) {
                Thread.currentThread().wait();
            }
        }
    }
}
