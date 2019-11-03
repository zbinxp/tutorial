package org.zbinxp.rate.impl;

import org.zbinxp.rate.api.QuoteManager;
import org.zbinxp.rate.spi.ExchangeRateProvider;

/**
 * YahooFinanceExchangeRateProvider
 */
public class YahooFinanceExchangeRateProvider implements ExchangeRateProvider {

	@Override
	public QuoteManager create() {
		System.out.println("yahoo finance provider");
		return new YahooQuoteManagerImpl();
	}
    
}