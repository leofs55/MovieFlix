package lest.dev.MovieFlix.controller;

import lest.dev.MovieFlix.controller.request.MovieRequest;
import lest.dev.MovieFlix.controller.response.MovieResponse;
import lest.dev.MovieFlix.entity.Movie;
import lest.dev.MovieFlix.mapper.CategoryMapper;
import lest.dev.MovieFlix.mapper.MovieMapper;
import lest.dev.MovieFlix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        List<MovieResponse> list = service.list().stream()
                .map(MovieMapper::map)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovie(@PathVariable Long id) {
        return service.find(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.map(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MovieResponse> postMovie(@RequestBody MovieRequest body) {
        Movie movie = MovieMapper.map(body);
        return ResponseEntity.ok(MovieMapper.map(service.create(movie)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieResponse> patchMovie(@PathVariable Long id, @RequestBody MovieRequest body) {
        Movie movie = MovieMapper.map(body);
        return service.patch(id, movie)
                .map(movieSaved -> ResponseEntity.ok(MovieMapper.map(movieSaved)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> getMovieByCategory(@RequestParam Long category){
        List<MovieResponse> list = service.listByCategory(category).stream()
                .map(MovieMapper::map)
                .toList();
        return ResponseEntity.ok(list);
    }

}
