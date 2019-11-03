# SPI

## structure

service provider interface

service provider

define a file in META-INF/services with name = SPI name(org.zbinxp.rate.spi.ExchangeRateProvider), and content equals service provider implementation(org.zbinxp.rate.impl.YahooFinanceExchangeRateProvider)

ServiceLoader.load(ExchangeRateProvider.class)自动加载service provider实现，当然代码需要放在classpath or lib/ext下面

## how to run

```shell
mvn clean package
# no provider
java -cp ./exchange-rate-api/target/exchange-rate-api-1.0-SNAPSHOT.jar org.zbinxp.rate.App
# put provider in
java -Djava.ext.dirs=$JAVA_HOME/jre/lib/ext:./exchange-rate-impl/target/:./exchange-rate-impl/target/depends -cp ./exchange-rate-api/target/exchange-rate-api-1.0-SNAPSHOT.jar org.zbinxp.rate.App
```
