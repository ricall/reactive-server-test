package au.com.rma.reactiveservertest.controller;

import au.com.rma.reactiveservertest.entity.Movie;
import au.com.rma.reactiveservertest.entity.MovieEvent;
import au.com.rma.reactiveservertest.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    private final MovieService service;

    @GetMapping("")
    public Flux<Movie> getMovies() {
        return service.allMovies();
    }

    @GetMapping("/{id}")
    public Mono<Movie> getMovieById(@PathVariable("id") String id) {
        return service.findMovieById(id);
    }

    @PostMapping("")
    public Mono<Movie> addMovie(@RequestBody Movie movie) {
        return service.addMovie(movie);
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MovieEvent> getMovieEvents(@PathVariable("id") String id) {
        return service.findMovieEventsById(id);
    }

}
