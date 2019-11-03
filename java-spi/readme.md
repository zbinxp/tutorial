# SPI

## structure

1. exchange-rate-api 

    - api: business logic
    - spi: service provider interface
    - ExchangeRate.java: helper class
    - App.java: entry

2. exchange-rate-impl: service provider implementation

    - YahooFinanceExchangeRateProvider.java: service provider
    - YahooQuoteManagerImple.java: business logic
    - QuoteResponse.java, QuoteResponseWrapper: json entity
    - pom.xml: pay attention to `maven-dependency-plugin`, which copies dependencies to a folder named `depends`. These dependencies are used by classloaders.

## how it works

1. service provider interface

2. service provider

3. define a file in META-INF/services with name = SPI name(org.zbinxp.rate.spi.ExchangeRateProvider), and content equals service provider implementation(org.zbinxp.rate.impl.YahooFinanceExchangeRateProvider)

4. ServiceLoader.load(ExchangeRateProvider.class) loads the implementation of service provider，which is packaged and put into ext dirs.

## how to run

```shell
mvn clean package
# no provider
java -cp ./exchange-rate-api/target/exchange-rate-api-1.0-SNAPSHOT.jar org.zbinxp.rate.App
# put provider in
java -Djava.ext.dirs=$JAVA_HOME/jre/lib/ext:./exchange-rate-impl/target/:./exchange-rate-impl/target/depends -cp ./exchange-rate-api/target/exchange-rate-api-1.0-SNAPSHOT.jar org.zbinxp.rate.App
```
