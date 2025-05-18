package lest.dev.MovieFlix.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record CategoryRequest(@NotEmpty(message = "Insert a value for category!") String name) {
}
