package lest.dev.MovieFlix.service;

import lest.dev.MovieFlix.entity.Category;
import lest.dev.MovieFlix.entity.Movie;
import lest.dev.MovieFlix.entity.Streaming;
import lest.dev.MovieFlix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;

    public List<Movie> list() {
        return repository.findAll();
    }

    public Movie find(Long id) {
        Optional<Movie> movie = repository.findById(id);
        return movie.orElse(null);
    }

    public Movie create(Movie body) {
        return repository.save(body);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
