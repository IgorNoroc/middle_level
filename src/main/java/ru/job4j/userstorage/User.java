package ru.job4j.userstorage;

import java.util.Objects;

/**
 * 3. Класс хранилища пользователей UserStorage [#318325].
 */
public class User {
    private int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id
                && amount == user.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", amount="
                + amount + '}';
    }

    public int getId() {
        return this.id;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
