package org.zbinxp;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.zbinxp.rate.api.Quote;
import org.zbinxp.rate.impl.YahooQuoteManagerImpl;

/**
 * YahooQuoteManagerImplTest
 */
public class YahooQuoteManagerImplTest {
    @Test
    public void getQuotesTest() {
        YahooQuoteManagerImpl p = new YahooQuoteManagerImpl();
        List<Quote> list = p.getQuotes("USD", LocalDate.now());
        System.out.println(list);
        assertTrue(list.size() > 0);
    }
    
}