package mate.academy.util;

import mate.academy.DataProcessingException;
import mate.academy.model.Movie;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
            throw new DataProcessingException(
                    "Error creating SessionFactory: " + e, e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
