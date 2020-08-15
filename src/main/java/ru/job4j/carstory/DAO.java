package ru.job4j.carstory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.carstory.model.Car;
import ru.job4j.carstory.model.Driver;
import ru.job4j.carstory.model.Engine;

import java.util.List;
import java.util.function.Function;

public class DAO {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private DAO() {

    }

    /**
     * Create singleton.
     */
    private static final class Lazy {
        private static final DAO INST = new DAO();
    }

    public static DAO instOf() {
        return Lazy.INST;
    }

    public Car createCar(Car car) {
        request(session -> session.save(car));
        return car;
    }

    public Driver createDriver(Driver driver) {
        request(session -> session.save(driver));
        return driver;
    }

    public Engine createEngine(Engine engine) {
        request(session -> session.save(engine));
        return engine;
    }

    public void addHistory(Car car, Driver driver) {
        request(session -> {
            driver.addCar(session.load(Car.class, car.getId()));
            return null;
        });
    }

    public void update(int id, Car car) {
        request(session -> {
            session.update(String.valueOf(id), car);
            return null;
        });
    }

    public void update(int id, Driver driver) {
        request(session -> {
            session.update(String.valueOf(id), driver);
            return null;
        });
    }

    public void update(int id, Engine engine) {
        request(session -> {
            session.update(String.valueOf(id), engine);
            return null;
        });
    }

    public Car findCarById(int id) {
        return request(
                session -> session.get(Car.class, id)
        );
    }

    public Driver findDriverById(int id) {
        return request(
                session -> session.get(Driver.class, id)
        );
    }

    public Engine findEngineById(int id) {
        return request(
                session -> session.get(Engine.class, id)
        );
    }

    public List<Car> findAllCars() {
        return request(
                session -> session.createQuery("from Car").list()
        );
    }

    public List<Driver> findAllDrivers() {
        return request(
                session -> session.createQuery("from Driver").list()
        );
    }

    public List<Engine> findAllEngines() {
        return request(
                session -> session.createQuery("from Engine").list()
        );
    }

    public void deleteCar(Car car) {
        request(
                session -> {
                    session.delete(car);
                    return null;
                });
    }

    public void deleteDriver(Driver driver) {
        request(
                session -> {
                    session.delete(driver);
                    return null;
                });
    }

    public void deleteEngine(Engine engine) {
        request(
                session -> {
                    session.delete(engine);
                    return null;
                });
    }

    /**
     * Making function for request to database to exclude duplicating code.
     *
     * @param command request
     * @param <T>     model.
     * @return result of request.
     */
    private <T> T request(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
