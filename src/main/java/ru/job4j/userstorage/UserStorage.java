package ru.job4j.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

/**
 * 3. Класс хранилища пользователей UserStorage [#318325].
 */
@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final List<User> users = new ArrayList<>();

    /**
     * Add user to storage users.
     *
     * @param user user.
     * @return true or false.
     */
    public synchronized boolean add(User user) {
        boolean rsl = false;
        if (user != null) {
            users.add(user);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Update user from storage.
     *
     * @param user user.
     * @return true or false.
     */
    public synchronized boolean update(User user) {
        int index = findById(user.getId());
        boolean rsl = false;
        if (index != -1) {
            users.set(index, user);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Delete user from storage.
     *
     * @param user user.
     * @return true or false.
     */
    public synchronized boolean delete(User user) {
        int index = findById(user.getId());
        boolean rsl = false;
        if (index != -1) {
            users.remove(index);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Find user's index by id.
     *
     * @param id id.
     * @return index.
     */
    private synchronized int findById(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Transfer amount from one user to another.
     *
     * @param fromId user's id.
     * @param toId   user's id.
     * @param amount amount.
     */
    public synchronized void transfer(int fromId, int toId, int amount) {
        int indexFrom = findById(fromId);
        int indexTo = findById(toId);
        if (indexFrom != -1 && indexTo != -1 && users.get(indexFrom).getAmount() >= amount) {
            User userFrom = users.get(indexFrom);
            userFrom.setAmount(userFrom.getAmount() - amount);
            update(userFrom);
            User userTo = users.get(indexTo);
            userTo.setAmount(userTo.getAmount() + amount);
            update(userTo);
        }
    }

    /**
     * Storage.
     *
     * @return storage.
     */
    public synchronized List<User> getUsers() {
        return users;
    }
}
