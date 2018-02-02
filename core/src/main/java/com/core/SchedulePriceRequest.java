package com.core;

import com.commons.model.BotPrice;
import com.commons.model.Wrapper;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulePriceRequest {

    @Autowired
    private PricesServiceBean pricesServiceBean;

    @Scheduled(initialDelay=300000, fixedDelay = 1500000)
    public void callPrice() throws IOException {
        Instant t0 = Instant.now();

        Map<String, BotPrice> marketPrices = pricesServiceBean.getMarketPrices();

        Instant t1 = Instant.now();
        System.out.println("Total: " + ChronoUnit.SECONDS.between(t0, t1));
    }


}
