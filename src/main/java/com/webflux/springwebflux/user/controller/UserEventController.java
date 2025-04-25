package com.webflux.springwebflux.user.controller;

import com.webflux.springwebflux.user.event.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sse")
public class UserEventController {

    private final EventPublisher publisher;

    @GetMapping(value = "/users", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamUsers() {
        return publisher.getStream();
    }
}

