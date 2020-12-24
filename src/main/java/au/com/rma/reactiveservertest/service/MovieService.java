package au.com.rma.reactiveservertest.service;

import au.com.rma.reactiveservertest.entity.Movie;
import au.com.rma.reactiveservertest.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

}
