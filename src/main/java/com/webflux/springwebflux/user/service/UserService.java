package com.webflux.springwebflux.user.service;

import com.webflux.springwebflux.user.domain.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserService {

    private final List<User> users = List.of(
            new User("1", "Alice"),
            new User("2", "Bob"),
            new User("3", "Charlie")
    );

    public Flux<User> getAllUsers() {
        return Flux.fromIterable(users);
    }

    public Mono<User> getUserById(String id) {
        return Mono.justOrEmpty(users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst());
    }
}