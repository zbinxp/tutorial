package org.zbinxp.rate.api;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

/**
 * Quote
 */
@Data
public class Quote {
    private String currency;
    private BigDecimal ask;
    private BigDecimal bid;
    private LocalDate date;
}