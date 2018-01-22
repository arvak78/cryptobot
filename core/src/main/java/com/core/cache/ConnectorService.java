package com.core.cache;

import com.commons.Exchanges;
import com.commons.constants.CacheConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = CacheConstants.CONNECTORS)
public class ConnectorService {

    private static Logger log = LoggerFactory.getLogger(ConnectorService.class);

    @CacheEvict(allEntries = true)
    public void clearCache(){}

    @Cacheable(value = CacheConstants.CONNECTORS, key = "#exchange")
    public Class play(Exchanges exchange, Class aclass) {
//        log.info("Executing: " + exchangeMap != null ? String.valueOf(exchangeMap.size()) : "0" + " Elements in " + CacheConstants.CONNECTORS);
//        return exchangeMap.get(exchange);
        return aclass;
    }

}
