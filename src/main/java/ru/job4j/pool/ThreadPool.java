package ru.job4j.pool;

import ru.job4j.wait.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * 1. Реализовать ThreadPool [#318302].
 */
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    /**
     * When create instance we create several streams.
     * Number of streams is depend on your cpu.
     * Each stream get a new task from tasks queue while the tread pool is work.
     */
    public ThreadPool() {
        int core = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < core; i++) {
            threads.add(new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                tasks.poll().run();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    /**
     * Add a new task to queue.
     *
     * @param job task.
     */
    public void work(Runnable job) {
        tasks.offer(job);
    }

    /**
     * Interrupting all thread pool.
     */
    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
