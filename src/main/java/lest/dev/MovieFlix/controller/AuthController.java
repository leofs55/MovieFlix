package lest.dev.MovieFlix.controller;

import lest.dev.MovieFlix.config.TokenService;
import lest.dev.MovieFlix.controller.request.LoginRequest;
import lest.dev.MovieFlix.controller.request.UserResquest;
import lest.dev.MovieFlix.controller.response.LoginResponse;
import lest.dev.MovieFlix.controller.response.UserResponse;
import lest.dev.MovieFlix.entity.User;
import lest.dev.MovieFlix.mapper.UserMapper;
import lest.dev.MovieFlix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> resgister(@RequestBody UserResquest user) {
        User userMap = UserMapper.map(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.map(service.save(userMap)));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest login) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(login.email(), login.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(LoginResponse.builder()
                                            .token(tokenService.generateToken(user))
                                            .build());
    }
}
