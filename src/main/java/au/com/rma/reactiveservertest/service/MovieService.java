package au.com.rma.reactiveservertest.service;

import au.com.rma.reactiveservertest.entity.Movie;
import au.com.rma.reactiveservertest.entity.MovieEvent;
import au.com.rma.reactiveservertest.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository repository;

    public Flux<Movie> allMovies() {
        return repository.findAll();
    }

    public Mono<Movie> findMovieById(String id) {
        return repository.findById(id);
    }

    public Mono<Movie> addMovie(Movie movie) {
        return repository.save(movie);
    }

    public Flux<MovieEvent> findMovieEventsById(String id) {
        return findMovieById(id)
                .flatMapMany(this::movieEvents);
    }

    private Flux<MovieEvent> movieEvents(Movie movie) {
        return Flux.interval(Duration.ofMillis(500))
                    .flatMap(i -> Flux.create(sink -> sink.next(MovieEvent.builder()
                            .movie(movie)
                            .started(LocalDateTime.now())
                            .username(randomUsername())
                            .build())));
    }

    private static String[] DEFAULT_USERNAMES = {
            "user1",
            "richard",
            "guest",
            "admin"
    };

    private String randomUsername() {
        return DEFAULT_USERNAMES[new Random().nextInt(DEFAULT_USERNAMES.length)];
    }
}
