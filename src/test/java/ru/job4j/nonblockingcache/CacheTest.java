package ru.job4j.nonblockingcache;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CacheTest {
    private final Cache cache = new Cache();
    private final AtomicReference<Exception> ex = new AtomicReference<>();

    @Before
    public void addToMapBases() {
        cache.add(new Base(1));
    }

    @Test
    public void whenTwoTreadsUpdateOneBase() throws InterruptedException {
       Thread first = new Thread(
                () -> {
                    try {
                        cache.update(new Base(1));
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    try {
                        cache.update(new Base(1));
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(ex.get().getMessage(), is("model has already been changed!"));
    }

    @Test
    public void exceptionTest() throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    try {
                        throw new OptimisticException("exception");
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread.start();
        thread.join();
        assertThat(ex.get().getMessage(), is("exception"));
    }

    @Test
    public void whenDelete() throws InterruptedException {
        Thread thread = new Thread(
                () -> cache.delete(new Base(1))
        );
        thread.start();
        thread.join();
        assertThat(cache.getCache().size(), is(0));
    }
}