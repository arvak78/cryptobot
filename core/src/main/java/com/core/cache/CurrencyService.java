package com.core.cache;

import com.commons.Exchanges;
import com.commons.constants.CacheConstants;
import com.commons.model.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = CacheConstants.CURRENCIES)
public class CurrencyService {

    private static Logger log = LoggerFactory.getLogger(CurrencyService.class);

    @CacheEvict(allEntries = true)
    public void clearCache(){}

    @Cacheable(value = CacheConstants.CURRENCIES, key = "#exchange+#currency.symbol")
    public Currency play(Exchanges exchange, Currency currency) {
        return currency;
    }

}
