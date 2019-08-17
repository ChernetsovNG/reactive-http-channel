package ru.nchernetsov.reactive.rxjava.example;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

public class RxSeeEmitter extends SseEmitter {
    private static final long SSE_SESSION_TIMEOUT = 30 * 60 * 1000L;

    private final Observer<Temperature> subscriber;

    RxSeeEmitter() {
        super(SSE_SESSION_TIMEOUT);

        this.subscriber = new Observer<Temperature>() {

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Temperature temperature) {
                try {
                    RxSeeEmitter.this.send(temperature);
                } catch (IOException ignored) {
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };

        onCompletion(subscriber::onComplete);
        onTimeout(subscriber::onComplete);
    }

    Observer<Temperature> getSubscriber() {
        return subscriber;
    }
}
