package ru.job4j.wait;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {
    private final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
    private final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();

    @Test
    public void whenHave2Threads() throws InterruptedException {
        Thread producer = new Thread(
                () -> queue.offer(1)
        );
        final Integer[] rsl = {null};
        Thread consumer = new Thread(
                () -> {
                    try {
                        rsl[0] = queue.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        consumer.start();
        producer.start();
        producer.join();
        consumer.join();
        assertThat(rsl[0], is(1));
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(
                            queue::offer
                    );
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }

    @Test
    public void whenHave3ThreadsAndCollectToList() throws InterruptedException {
        Thread producer = new Thread(
                () -> IntStream.range(0, 10).forEach(
                        queue::offer)
        );
        producer.start();
        Thread consumer1 = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        Thread consumer2 = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer1.start();
        consumer2.start();
        producer.join();
        consumer1.interrupt();
        consumer2.interrupt();
        consumer1.join();
        consumer2.join();
        assertThat(buffer.containsAll(Arrays.asList(
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9
                )),
                is(true)
        );
    }
}