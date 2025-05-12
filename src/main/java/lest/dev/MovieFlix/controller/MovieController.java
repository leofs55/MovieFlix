package lest.dev.MovieFlix.controller;

import lest.dev.MovieFlix.entity.Movie;
import lest.dev.MovieFlix.entity.Streaming;
import lest.dev.MovieFlix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;

    @GetMapping
    public List<Movie> getAllMovies(){
        return service.list();
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable Long id){
        return service.find(id);
    }

    @PostMapping
    public Movie postMovie(@RequestBody Movie body){
        return service.create(body);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id){
        service.delete(id);
    }

}
