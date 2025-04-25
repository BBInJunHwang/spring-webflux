package com.webflux.springwebflux.user.repository;

import com.webflux.springwebflux.user.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, String> {
    // Custom query methods can be defined here if needed
}
