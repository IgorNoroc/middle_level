package ru.job4j.linked;

/**
 * 3. Immutable объекты [#318318]
 *
 * @param <T>
 */
public class Node<T> {
    private final Node next;
    private final T value;

    public Node(Node next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}
