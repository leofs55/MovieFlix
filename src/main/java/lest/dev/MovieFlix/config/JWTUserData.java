package lest.dev.MovieFlix.config;

import lombok.Builder;

@Builder
public record JWTUserData(Long id, String name, String email) {
}
