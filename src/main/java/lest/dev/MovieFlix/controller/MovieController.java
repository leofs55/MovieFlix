package lest.dev.MovieFlix.controller;

import lest.dev.MovieFlix.controller.request.MovieRequest;
import lest.dev.MovieFlix.controller.response.MovieResponse;
import lest.dev.MovieFlix.entity.Movie;
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
        Movie movie = service.find(id);
        return ResponseEntity.ok(MovieMapper.map(movie));
    }

    @PostMapping
    public ResponseEntity<MovieResponse> postMovie(@RequestBody MovieRequest body) {
        Movie movie = MovieMapper.map(body);
        return ResponseEntity.ok(MovieMapper.map(service.create(movie)));
    }

    //Esperar para ver como ele irá fazer o endpoint de update

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Movie deleted!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
