package ru.nchernetsov.reactive.http.channel;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.IntStream;

@Slf4j
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Flux.push(emitter -> IntStream
            .range(2000, 3000).forEach(emitter::next))
            .delayElements(Duration.ofMillis(10))
            .subscribe(e -> log.info("onNext: {}", e));

        Thread.sleep(10_000L);
    }
}
