package org.zbinxp.rate;

import java.time.LocalDate;
import java.util.List;

import org.zbinxp.rate.api.Quote;

/**
 * App
 */
public class App {

    public static void main(String[] args) {
        ExchangeRate.providers().forEach(provider -> {
            System.out.println("Retreiving USD quotes from provider :" + provider);
            List<Quote> quotes = provider.create().getQuotes("USD", LocalDate.now());
            System.out.println(String.format("%14s%12s|%12s", "","Ask", "Bid"));
            System.out.println("----------------------------------------");
            quotes.forEach(quote -> {
                System.out.println("USD --> " + quote.getCurrency() + " : " + String.format("%12f|%12f", quote.getAsk(), quote.getBid()));
            });
        });
        System.out.println("providers iterator over");
    }
}