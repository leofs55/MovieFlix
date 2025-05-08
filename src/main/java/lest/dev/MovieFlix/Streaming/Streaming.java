package lest.dev.MovieFlix.Streaming;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "streaming")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Streaming {

    private Long id;

    private String name;


}
