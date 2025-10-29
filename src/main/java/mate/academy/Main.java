package mate.academy;

import mate.academy.lib.Injector;
import mate.academy.model.Movie;
import mate.academy.service.MovieService;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.academy");

    public static void main(String[] args) {

        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        Movie interstellar = new Movie("Interstellar", "A team of explorers travels through a wormhole.");

        Movie addedMovie = movieService.add(interstellar);
        System.out.println("1. Added Movie: " + addedMovie);

        Movie retrievedMovie = movieService.get(addedMovie.getId());
        System.out.println("2. Retrieved Movie: " + retrievedMovie);

        System.out.println("3. Is movieDao injected? -> Yes, if no exception was thrown.");

    }
}

