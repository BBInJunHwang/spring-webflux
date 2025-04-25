package com.webflux.springwebflux.user.init;

import com.webflux.springwebflux.user.domain.User;
import com.webflux.springwebflux.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
@RequiredArgsConstructor
public class SampleDateInit {
    private final UserRepository userRepository;

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            userRepository.deleteAll()
                    .thenMany(
                            Flux.just("Alice", "Bob", "Charlie")
                                    .map(name -> new User(name, name.toLowerCase() + "@example.com"))
                                    .flatMap(userRepository::save)
                    )
                    .subscribe(
                            user -> System.out.println("Saved user: " + user),
                            error -> System.err.println("Error: " + error),
                            () -> System.out.println("All users saved.")
                    );
        };
    }
}
