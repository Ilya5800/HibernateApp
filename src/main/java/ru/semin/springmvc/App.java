package ru.semin.springmvc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.List;
import ru.semin.springmvc.model.Actor;
import ru.semin.springmvc.model.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class).addAnnotatedClass(Movie.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();


            Actor actor=session.find(Actor.class,2);
            System.out.println(actor.getMovieList());

            Movie movieToRemove = actor.getMovieList().get(0);
            actor.getMovieList().remove(0);
            movieToRemove.getActors().remove(actor);



            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
