package ru.job4j.nonblockingcache;

import java.util.Objects;

/**
 * 1. Неблокирующий кеш [#318331].
 */
public class Base {
    private int id;
    private int version;

    public Base(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return id == base.id
                && version == base.version;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }
}
