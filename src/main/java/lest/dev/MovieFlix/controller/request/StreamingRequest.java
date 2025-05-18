package lest.dev.MovieFlix.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record StreamingRequest(@NotEmpty(message = "Insert a value for Streaming!") String name) {
}
