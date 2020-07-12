package ru.job4j.nonblockingcache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 1. Неблокирующий кеш [#318331]
 */
public class Cache {
    private final ConcurrentHashMap<Integer, Base> cache = new ConcurrentHashMap<>();

    /**
     * Add Base to cache.
     *
     * @param model base.
     */
    public void add(Base model) {
        cache.put(model.getId(), model);
    }

    /**
     * Update base if present.
     *
     * @param model base.
     * @throws OptimisticException if the base has already been changed.
     */
    public void update(Base model) throws OptimisticException {
        int currentVersion = model.getVersion();
        cache.computeIfPresent(model.getId(), (integer, base) -> {
            if (base.getVersion() != currentVersion) {
                throw new OptimisticException("model has already been changed!");
            }
            base.setVersion(
                    base.getVersion() + 1
            );
            return base;
        });
    }

    /**
     * Delete base.
     *
     * @param model base.
     */
    public void delete(Base model) {
        cache.remove(model.getId());
    }

    /**
     * Storage.
     *
     * @return storage.
     */
    public ConcurrentHashMap<Integer, Base> getCache() {
        return cache;
    }
}
