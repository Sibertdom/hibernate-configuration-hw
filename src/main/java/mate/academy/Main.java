package mate.academy;

import mate.academy.lib.Injector;
import mate.academy.model.Movie;
import mate.academy.service.MovieService;

public class Main {
    // Ініціалізація інжектора, припускаючи, що injector знаходиться у lib
    private static final Injector injector = Injector.getInstance("mate.academy");

    public static void main(String[] args) {

        // Отримання сервісу через інжектор
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        // 1. Створення нового об'єкта Movie
        Movie interstellar = new Movie("Interstellar", "A team of explorers travels through a wormhole.");

        // 2. Тестування методу add()
        Movie addedMovie = movieService.add(interstellar);
        System.out.println("1. Added Movie: " + addedMovie);

        // 3. Тестування методу get()
        Movie retrievedMovie = movieService.get(addedMovie.getId());
        System.out.println("2. Retrieved Movie: " + retrievedMovie);

        // 4. Тестування наявності movieDao, якщо injector працює
        System.out.println("3. Is movieDao injected? -> Yes, if no exception was thrown.");

        // Приклад: спроба отримати неіснуючий фільм (викличе NoSuchElementException)
        /*
        try {
            movieService.get(999L);
        } catch (Exception e) {
            System.out.println("4. Testing not found: " + e.getMessage());
        }
        */
    }
}