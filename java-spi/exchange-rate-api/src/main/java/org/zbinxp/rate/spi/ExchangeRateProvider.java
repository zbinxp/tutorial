package org.zbinxp.rate.spi;

import org.zbinxp.rate.api.QuoteManager;

/**
 * ExchangeRateProvider
 */
public interface ExchangeRateProvider {
    QuoteManager create();
}