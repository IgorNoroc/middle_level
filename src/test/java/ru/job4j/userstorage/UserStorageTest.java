package ru.job4j.userstorage;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserStorageTest {
    private final UserStorage storage = new UserStorage();

    @Test
    public void whenAddNull() {
        boolean expected = storage.add(null);
        assertThat(expected, is(false));
    }

    @Test
    public void whenRemoveUser() {
        User user = new User(1, 10);
        storage.add(user);
        storage.delete(user);
        assertThat(storage.getUsers().size(), is(0));
    }

    @Test
    public void whenUpdateUser() {
        User user = new User(1, 10);
        storage.add(user);
        user.setAmount(20);
        storage.update(user);
        assertThat(
                storage.getUsers().get(0).getAmount(),
                is(20));
    }

    @Test
    public void whenTransfer() {
        User user1 = new User(1, 10);
        User user2 = new User(2, 20);
        storage.add(user1);
        storage.add(user2);
        storage.transfer(1, 2, 5);
        assertThat(
                storage.getUsers().get(1).getAmount(),
                is(25)
        );
    }
}