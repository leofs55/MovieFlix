package lest.dev.MovieFlix.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieInterface extends JpaRepository<Movie, Long> {}