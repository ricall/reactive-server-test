package au.com.rma.reactiveservertest;

import au.com.rma.reactiveservertest.entity.Movie;
import au.com.rma.reactiveservertest.entity.MovieGenre;
import au.com.rma.reactiveservertest.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitialiser implements CommandLineRunner {
    private static String[] DEFAULT_MOVIES = {
        "Tenet",
        "Hitchhikers guide to the Galaxy",
        "In Time",
        "Life"
    };

    private final MovieRepository repository;

    @Override
    public void run(String... args) {
        log.info("Application has started - lets insert some data...");
        Flux.fromArray(DEFAULT_MOVIES)
            .flatMap(this::createMovie)
            .subscribe(movie -> log.info("Added Movie: {}", movie));
    }

    private Mono<Movie> createMovie(String name) {
        return repository.save(Movie.builder()
                .name(name)
                .genre(MovieGenre.random())
                .build());
    }
}
