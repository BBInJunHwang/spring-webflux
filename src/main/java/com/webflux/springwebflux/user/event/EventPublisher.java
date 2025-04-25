package com.webflux.springwebflux.user.event;

import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * 이벤트를 여러 구독자에게 스트리밍하는 브로드 캐스터
 *
 * */
@Configuration
public class EventPublisher {

    // Sinks.Many는 여러 구독자에게 이벤트를 브로드캐스트하는데 사용
    // multicast()는 여러 구독자에게 이벤트를 브로드캐스트하는 Sink를 생성 -> 새로운 구독자는 이전 메세지 안봄 (핫 스트림)
    // onBackpressureBuffer()는 백프레셔를 처리하기 위한 버퍼를 생성 -> 기본적으로 무제한 버퍼임으로 주의
    private final Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();

    public void publish(String message) {
        sink.tryEmitNext(message); // 유저 추가 시 메세지 푸시
    }

    public Flux<String> getStream() {
        return sink.asFlux(); // SSE 스트림 연결자에게 실시간 메세지 스트림 제공
    }
}
