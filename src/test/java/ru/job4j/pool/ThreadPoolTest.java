package ru.job4j.pool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ThreadPoolTest {
    private final ThreadPool threadPool = new ThreadPool();
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream std = System.out;

    @Before
    public void setOut() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void shutdown() {
        System.setOut(std);
    }

    @Test
    public void whenHave3Tasks() throws InterruptedException {
        threadPool.work(
                () -> System.out.println("hello this is first task"));
        Thread.sleep(50);
        threadPool.work(
                () -> System.out.println("this is second task"));
        Thread.sleep(50);
        threadPool.work(
                () -> System.out.println("this is last task")
        );
        Thread.sleep(50);
        threadPool.shutdown();
        assertThat(out.toString(), is(new StringBuilder()
                .append("hello this is first task").append(System.lineSeparator())
                .append("this is second task").append(System.lineSeparator())
                .append("this is last task").append(System.lineSeparator())
                .toString()
        ));
    }
}