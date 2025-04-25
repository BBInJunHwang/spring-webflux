package com.webflux.springwebflux.user.controller;

import com.webflux.springwebflux.user.domain.User;
import com.webflux.springwebflux.user.event.EventPublisher;
import com.webflux.springwebflux.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EventPublisher publisher;


    @GetMapping
    public Flux<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Mono<User> getUser(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public Mono<User> createUser(@RequestBody User user) {
        return userService.createUser(user)
                .doOnSuccess(savedUser ->
                        publisher.publish("🆕 New user added: " + savedUser.getName()));
    }
}
