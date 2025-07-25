package ru.semin.springmvc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.semin.springmvc.model.Person;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
     session.createQuery("delete from Person  where age<30").executeUpdate();


session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
