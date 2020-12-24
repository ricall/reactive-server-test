package au.com.rma.reactiveservertest.repository;

import au.com.rma.reactiveservertest.entity.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {

}
