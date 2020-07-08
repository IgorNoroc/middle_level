package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.collection.SimpleArray;

import java.util.Iterator;

/**
 * 4. ThreadSafe динамический список [#318326]
 * @param <T>
 */
@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final SimpleArray<T> array = new SimpleArray<>();

    public synchronized void add(T value) {
        array.add(value);
    }

    public synchronized T get(int index) {
       return array.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.array).iterator();
    }

    private synchronized SimpleArray<T> copy(SimpleArray<T> array) {
        SimpleArray<T> result = new SimpleArray<>();
        for (T t : array) {
            result.add(t);
        }
        return result;
    }
}
