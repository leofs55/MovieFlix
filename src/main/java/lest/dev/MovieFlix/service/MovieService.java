package lest.dev.MovieFlix.service;

import lest.dev.MovieFlix.entity.Category;
import lest.dev.MovieFlix.entity.Movie;
import lest.dev.MovieFlix.entity.Streaming;
import lest.dev.MovieFlix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;


    public List<Movie> list() {
        return repository.findAll();
    }

    public List<Movie> listByCategory(Long category) {
        return repository.findMovieByCategories(List.of(Category.builder().id(category).build()));
    }

    public Optional<Movie> find(Long id) {
        return repository.findById(id);
    }

    public Movie create(Movie body) {
        body.setCategories(this.findCategories(body.getCategories()));
        body.setStreamings(this.findStreamings(body.getStreamings()));
        return repository.save(body);
    }

    public boolean delete(Long id) {
        Optional<Movie> movie = repository.findById(id);
        if (movie.isPresent()) {
            repository.delete(movie.orElse(null));
            return true;
        }
        return false;
    }

    public Optional<Movie> patch(Long id, Movie body) {
        Optional<Movie> movieOptional = repository.findById(id);
        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            if (body.getId() != null) movie.setId(body.getId());
            if (body.getTitle() != null) movie.setTitle(body.getTitle());
            if (body.getDescription() != null) movie.setDescription(body.getDescription());
            if (body.getReleaseDate() != null) movie.setReleaseDate(body.getReleaseDate());
            if (body.getRating() != 0.0) movie.setRating(body.getRating());
            if (!body.getCategories().isEmpty()) movie.setCategories(body.getCategories());
            if (!body.getStreamings().isEmpty()) movie.setStreamings(body.getStreamings());
            return Optional.of(repository.save(movie));
        }
        return Optional.empty();
    }

    private List<Category> findCategories(List<Category> categoriesList) {
        List<Category> foundCategories = new ArrayList<>();
        categoriesList
                .forEach(category -> categoryService.find(category.getId()).ifPresent(foundCategories::add));
        return foundCategories;
    }

    private List<Streaming> findStreamings(List<Streaming> streamingsList) {
        List<Streaming> streamingFound = new ArrayList<>();
        streamingsList
                .forEach(streaming -> streamingService.find(streaming.getId()).ifPresent(streamingFound::add));
        return streamingFound;
    }
}
