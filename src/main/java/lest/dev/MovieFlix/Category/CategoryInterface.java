package lest.dev.MovieFlix.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryInterface extends JpaRepository<Category, Long>{}