package lest.dev.MovieFlix.mapper;

import lest.dev.MovieFlix.controller.request.UserResquest;
import lest.dev.MovieFlix.controller.response.UserResponse;
import lest.dev.MovieFlix.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User map(UserResquest userResquest) {
        return User.builder()
                .name(userResquest.name())
                .email(userResquest.email())
                .password(userResquest.password())
                .build();
    }

    public static UserResponse map(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
