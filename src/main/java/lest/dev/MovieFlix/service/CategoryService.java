package lest.dev.MovieFlix.service;

import lest.dev.MovieFlix.controller.request.CategoryRequest;
import lest.dev.MovieFlix.entity.Category;
import lest.dev.MovieFlix.mapper.CategoryMapper;
import lest.dev.MovieFlix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> list() {
        List<Category> categoryList = repository.findAll();
        return categoryList;
    }

    public Optional<Category> find(Long id) {
        Optional<Category> category = repository.findById(id);
        return category;
    }

    public Category create(CategoryRequest body) {
        Category category = CategoryMapper.map(body);
        return repository.save(category);
    }

    public boolean delete(Long id) {
        Optional<Category> category = repository.findById(id);
        if (category.isPresent()) {
            repository.delete(category.get());
            return true;
        }
        return false;
    }
}
