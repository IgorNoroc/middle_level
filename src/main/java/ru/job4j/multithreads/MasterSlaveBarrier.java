package ru.job4j.multithreads;

/**
 * 2. Thread switcher [#318335].
 */
public class MasterSlaveBarrier {
    private volatile int count = 0;

    public synchronized void tryMaster() throws InterruptedException {
        while (count == 0) {
            wait();
        }
    }

    public synchronized void trySlave() throws InterruptedException {
        while (count == 1) {
            wait();
        }
    }

    public synchronized void doneMaster() {
        count--;
        notify();
    }

    public synchronized void doneSlave() {
        count++;
        notify();
    }
}
