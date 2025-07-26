package ru.semin.springmvc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.semin.springmvc.model.Item;
import ru.semin.springmvc.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            Person person = session.find(Person.class, 4);
           Item item = session.find(Item.class, 1);
           item.getOwner().getItems().remove(item);

           item.setOwner(person);
           person.getItems().add(item);
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
