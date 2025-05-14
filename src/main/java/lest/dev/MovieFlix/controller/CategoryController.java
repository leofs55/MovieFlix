package lest.dev.MovieFlix.controller;

import lest.dev.MovieFlix.controller.request.CategoryRequest;
import lest.dev.MovieFlix.controller.response.CategoryResponse;
import lest.dev.MovieFlix.mapper.CategoryMapper;
import lest.dev.MovieFlix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategorys() {
        List<CategoryResponse> list = service.list().stream()
                .map(CategoryMapper::map)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long id) {
        return service.find(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.map(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> postCategory(@RequestBody CategoryRequest body) {
        CategoryResponse categoryResponse = CategoryMapper.map(service.create(body));
        if (categoryResponse != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Category deleted!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found!");
    }

}
