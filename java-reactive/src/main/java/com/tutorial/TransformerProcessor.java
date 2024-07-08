package com.tutorial;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.Flow.Subscription;
import java.util.function.Function;

public class TransformerProcessor<T,R> extends SubmissionPublisher<R> implements Flow.Processor<T,R> {

    private Subscription subscription;
    private Function<T,R> transformer;

    public TransformerProcessor(Function<T,R> transformer){
        this.transformer = transformer;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        submit(transformer.apply(item));
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        close();
    }
    
}
