package ru.job4j.multithreads;

/**
 * 2. Thread switcher [#318335].
 */
public class Switcher {
    public static void main(String[] args) throws InterruptedException {
        MasterSlaveBarrier barrier = new MasterSlaveBarrier();
        Thread first = new Thread(
                () -> {
                    while (true) {
                        try {
                            System.out.println("Thread A");
                            Thread.sleep(1000);
                            barrier.doneSlave();
                            barrier.trySlave();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    while (true) {
                        try {
                            barrier.tryMaster();
                            System.out.println("Thread B");
                            Thread.sleep(1000);
                            barrier.doneMaster();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
