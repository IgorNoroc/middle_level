package ru.job4j.candidate.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.candidate.model.Candidate;

import java.util.Collection;
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
        return DAO.Lazy.INST;
    }

    public Candidate create(Candidate candidate) {
        request(session -> session.save(candidate));
        return candidate;
    }

    public Collection<Candidate> allCandidates() {
        return request(session ->
                session.createQuery("from Candidate ").list());
    }

    public Candidate findById(int id) {
        return (Candidate) request(
                session -> session.createQuery("from Candidate c where c.id = :fId")
                        .setParameter("fId", id).getSingleResult());
    }

    public Candidate findByName(String name) {
        return (Candidate) request(
                session -> session.createQuery("from Candidate c where c.name = :name")
                        .setParameter("name", name).getSingleResult());
    }

    public void update(Candidate candidate, int id) {
        request(session ->
                session.createQuery("update Candidate c set c.name = :newName, c.experience = :newExp, c.salary = :newSalary where c.id = :fId")
                        .setParameter("newName", candidate.getName())
                        .setParameter("newExp", candidate.getExperience())
                        .setParameter("newSalary", candidate.getSalary())
                        .setParameter("fId", id)
                        .executeUpdate());
    }

    public void delete(int id) {
        request(session ->
                session.createQuery("delete from Candidate where id = :fId")
                        .setParameter("fId", id)
                        .executeUpdate());
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
