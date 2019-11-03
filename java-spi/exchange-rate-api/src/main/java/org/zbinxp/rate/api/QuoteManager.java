package org.zbinxp.rate.api;

import java.time.LocalDate;
import java.util.List;

/**
 * QuoteManager
 */
public interface QuoteManager {
    List<Quote> getQuotes(String baseCurrency, LocalDate date);
}