package lest.dev.MovieFlix.controller.request;

import lombok.Builder;

@Builder
public record UserResquest(String name,
                           String email,
                           String password) {
}
