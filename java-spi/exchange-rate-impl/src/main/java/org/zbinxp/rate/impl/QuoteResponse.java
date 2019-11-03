package org.zbinxp.rate.impl;

import java.util.List;

import org.zbinxp.rate.api.Quote;

import lombok.Data;

/**
 * QuoteResponse
 */
@Data
public class QuoteResponse {
    private List<Quote> result;
    private String error;
}