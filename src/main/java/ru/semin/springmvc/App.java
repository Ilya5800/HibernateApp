package ru.semin.springmvc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.semin.springmvc.model.Item;
import ru.semin.springmvc.model.Person;

import java.util.ArrayList;
import java.util.Collection;
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
            Person person = new Person("Test Cascading", 30);
            person.addItem(new Item("Test Cascadind item1"));

           person.addItem(new Item("Test Cascadind item2"));
          person.addItem(new Item("Test Cascadind item3"));
            session.persist(person);


            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
