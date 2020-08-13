package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hibernate.model.Car;
import ru.job4j.hibernate.model.CarBrand;

public class HibernateRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            Car modelF = new Car("type6");
            Car modelG = new Car("type7");
            Car modelL = new Car("type8");
            Car modelM = new Car("type9");
            Car modelN = new Car("type10");
            Integer idF = (Integer) session.save(modelF);
            Integer idG = (Integer) session.save(modelG);
            Integer idL = (Integer) session.save(modelL);
            Integer idM = (Integer) session.save(modelM);
            Integer idN = (Integer) session.save(modelN);
            CarBrand bmb = new CarBrand("volvo");
            bmb.addCar(session.load(Car.class, idF));
            bmb.addCar(session.load(Car.class, idG));
            bmb.addCar(session.load(Car.class, idL));
            bmb.addCar(session.load(Car.class, idM));
            bmb.addCar(session.load(Car.class, idN));
            session.save(bmb);
            session.getTransaction().commit();
            session.close();
        } catch (
                Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
