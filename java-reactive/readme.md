# concepts

- publisher: source of stream. Publisher has one method – subscribe().
- subscriber: sink of stream. This has four methods that need to be overridden – onSubscribe(), onNext(), onError(), and onComplete().

If any of the subscribers want to receive events published by it, they need to subscribe to the given Publisher.

The receiver of messages needs to implement the Subscriber interface. Typically this is the end for every Flow processing because the instance of it does not send messages further.

If we want to transform incoming message and pass it further to the next Subscriber, we need to implement the Processor interface. This acts both as a Subscriber because it receives messages, and as the Publisher because it processes those messages and sends them for further processing.