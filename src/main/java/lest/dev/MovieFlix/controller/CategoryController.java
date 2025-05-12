package lest.dev.MovieFlix.controller;

import lest.dev.MovieFlix.controller.request.CategoryRequest;
import lest.dev.MovieFlix.controller.response.CategoryResponse;
import lest.dev.MovieFlix.entity.Category;
import lest.dev.MovieFlix.entity.Movie;
import lest.dev.MovieFlix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public List<CategoryResponse> getAllCategorys(){
        return service.list();
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategory(@PathVariable Long id){
        return service.find(id);
    }

    @PostMapping
    public CategoryResponse postCategory(@RequestBody CategoryRequest body){
        return service.create(body);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        service.delete(id);
    }

}
