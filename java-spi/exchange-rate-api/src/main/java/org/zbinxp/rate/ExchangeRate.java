package org.zbinxp.rate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import org.zbinxp.rate.spi.ExchangeRateProvider;

/**
 * ExchangeRate
 */
public final class ExchangeRate {
    // private static final String DEFAULT_PROVIDER = "org.zbinxp.rate.spi.";

    public static List<ExchangeRateProvider> providers() {
        List<ExchangeRateProvider> services = new ArrayList<>();
        ServiceLoader<ExchangeRateProvider> loader = ServiceLoader.load(ExchangeRateProvider.class);
        loader.forEach(p -> {
            services.add(p);
        });
        System.out.println("services count:" + services.size());
        return services;
    }

    public static ExchangeRateProvider provider(String name) {
        ServiceLoader<ExchangeRateProvider> loader = ServiceLoader.load(ExchangeRateProvider.class);
        Iterator<ExchangeRateProvider> it = loader.iterator();
        while(it.hasNext()) {
            ExchangeRateProvider provider = it.next();
            if(name.equals(provider.getClass().getName())) return provider;
        }
        throw new ProviderNotFoundException("provider " + name + " not found!");
    }

    // public static ExchangeRateProvider provider() {
    //     return provider(DEFAULT_PROVIDER);
    // }
    
}