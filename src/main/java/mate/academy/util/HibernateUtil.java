package mate.academy.util;

import mate.academy.model.Movie;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import mate.academy.DataProcessingException; // Якщо цей клас у вас є

public class HibernateUtil {
    private static SessionFactory sessionFactory = initSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Movie.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            // Замінюємо 'ExceptionInInitializerError' на RuntimeException або ваш DataProcessingException.
            // Припускаючи, що у вас є DataProcessingException
            throw new DataProcessingException("Error creating SessionFactory: " + e, e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
