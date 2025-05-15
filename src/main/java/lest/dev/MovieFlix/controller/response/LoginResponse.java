package lest.dev.MovieFlix.controller.response;

import lombok.Builder;

@Builder
public record LoginResponse(String token) {
}
