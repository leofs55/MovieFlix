package lest.dev.MovieFlix.service;

import lest.dev.MovieFlix.controller.request.CategoryRequest;
import lest.dev.MovieFlix.controller.response.CategoryResponse;
import lest.dev.MovieFlix.entity.Category;
import lest.dev.MovieFlix.entity.Streaming;
import lest.dev.MovieFlix.mapper.CategoryMapper;
import lest.dev.MovieFlix.repository.CategoryRepository;
import lest.dev.MovieFlix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<CategoryResponse> list() {
        List<Category> categoryList = repository.findAll();
        return categoryList.stream()
                .map(CategoryMapper::map)
                .toList();
    }

    public CategoryResponse find(Long id) {
        Optional<Category> category = repository.findById(id);
        return CategoryMapper.map(category.orElse(null));
    }

    public CategoryResponse create(CategoryRequest body) {
        Category category = CategoryMapper.map(body);
        return CategoryMapper.map(repository.save(category));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
