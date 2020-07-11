package ru.job4j.wait;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {
    private final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();

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
}