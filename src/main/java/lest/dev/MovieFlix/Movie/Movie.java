package lest.dev.MovieFlix.Movie;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movie")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {

    private Long id;

    private String name;

    private String description;

}
