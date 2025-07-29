package ru.semin.springmvc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.semin.springmvc.model.Passport;
import ru.semin.springmvc.model.Person;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Passport.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
          Person person = new Person("Ilyas",37);
          Passport passport = new Passport(781955);

          person.setPassport(passport);
          session.persist(person);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
