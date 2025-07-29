package ru.semin.springmvc;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.semin.springmvc.model.Item;
import ru.semin.springmvc.model.Person;

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
            Person person= session.find(Person.class, 1);
            System.out.println("Получили человека из таблицы");

            session.getTransaction().commit();

            session =sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Находимся внутри второй транзакции");

            person=(Person) session.merge(person);
            session.createQuery("select i from Item i where i.owner.id=:personID", Item.class)
                    .setParameter("personID", person.getId()).getResultList();
            Hibernate.initialize(person.getItems());
            session.getTransaction().commit();
            System.out.println("Вне сессии");
            System.out.println(person.getItems());


        } finally {
            sessionFactory.close();
        }
    }
}
