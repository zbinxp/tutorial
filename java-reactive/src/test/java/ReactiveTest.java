import java.util.List;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import com.tutorial.CollectSubscriber;
import com.tutorial.TransformerProcessor;

public class ReactiveTest {
    @Test
    public void whenSubscribeToIt_thenShouldConsumeAll(){
        // given
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        CollectSubscriber<String> subscriber = new CollectSubscriber<>();
        publisher.subscribe(subscriber);
        List<String> items = List.of("1", "x", "2", "x", "3", "x");

        // when
        assertThat(publisher.getNumberOfSubscribers()).isEqualTo(1);
        items.forEach(publisher::submit);
        publisher.close();

        // then
        await().atMost(1000, TimeUnit.MILLISECONDS)
        .untilAsserted(
            () -> assertThat(subscriber.consumedList)
            .containsExactlyElementsOf(items)
        );
    }    


    @Test
    public void whenSubscribeAndTransformElements_thenShouldConsumeAll(){
        // given
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        TransformerProcessor<String, Integer> tf = new TransformerProcessor<>(Integer::parseInt);
        CollectSubscriber<Integer> sink = new CollectSubscriber<>();
        List<String> items = List.of("1","2","42");
        List<Integer> expected = List.of(1,2,42);

        // when
        publisher.subscribe(tf);
        tf.subscribe(sink);
        items.forEach(publisher::submit);
        publisher.close();

        // then
        await().atMost(1000, TimeUnit.MILLISECONDS)
        .untilAsserted(() -> assertThat(sink.consumedList).containsExactlyElementsOf(expected));
    }
}
