package lest.dev.MovieFlix.mapper;

import lest.dev.MovieFlix.controller.request.MovieRequest;
import lest.dev.MovieFlix.controller.response.CategoryResponse;
import lest.dev.MovieFlix.controller.response.MovieResponse;
import lest.dev.MovieFlix.controller.response.StreamingResponse;
import lest.dev.MovieFlix.entity.Category;
import lest.dev.MovieFlix.entity.Movie;
import lest.dev.MovieFlix.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public static Movie map(MovieRequest movieRequest) {

        List<Category> categoryList = List.of();
        List<Streaming> streamingList = List.of();

        if (movieRequest.categories() != null){
            categoryList = movieRequest.categories().stream()
                    .map(id -> Category.builder().id(id).build())
                    .toList();
        }

        if (movieRequest.streamings() != null) {
            streamingList = movieRequest.streamings().stream()
                    .map(id -> Streaming.builder().id(id).build())
                    .toList();
        }

        return Movie
                .builder()
                .title(movieRequest.title())
                .description(movieRequest.description())
                .releaseDate(movieRequest.releaseDate())
                .rating(movieRequest.rating())
                .categories(categoryList)
                .streamings(streamingList)
                .build();
    }

    public static MovieResponse map(Movie movie) {

        List<CategoryResponse> categoryList = movie.getCategories().stream()
                .map(CategoryMapper::map)
                .toList();

        List<StreamingResponse> streamingList = movie.getStreamings().stream()
                .map(StreamingMapper::map)
                .toList();

        return MovieResponse
                .builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(categoryList)
                .streamings(streamingList)
                .build();
    }

}
