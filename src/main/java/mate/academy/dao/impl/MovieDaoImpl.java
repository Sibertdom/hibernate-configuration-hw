package mate.academy.dao.impl;

import java.util.Optional;
import mate.academy.DataProcessingException;
import mate.academy.dao.MovieDao;
import mate.academy.lib.Dao;
import mate.academy.model.Movie;
import mate.academy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieDaoImpl implements MovieDao {

    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        // ❗ ВИКОРИСТОВУЄМО TRY-WITH-RESOURCES:
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            // Використовуємо DataProcessingException для всіх DAO помилок
            throw new DataProcessingException("Can't insert movie " + movie, e);
        }
        // Блок 'finally' з session.close() тепер не потрібен!
    }

    @Override
    public Optional<Movie> get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Movie> getMovieQuery = session.createQuery(
                    "FROM Movie m WHERE m.id = :id", Movie.class);
            getMovieQuery.setParameter("id", id);
            return getMovieQuery.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get movie by id: " + id, e);
        }
    }
}
