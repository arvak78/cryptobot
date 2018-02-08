package com.core;

import com.commons.model.BotPrice;

import java.io.IOException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import com.core.utils.Telegram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulePriceRequest {

    @Autowired
    private PricesServiceBean pricesServiceBean;

    @Autowired
    private ComparePrices comparePrices;

    @Autowired
    private Telegram telegram;

    @Scheduled(initialDelay=15000, fixedDelayString = "${core.compare.delay}")
    public void callPrice() throws IOException {
        Instant t0 = Instant.now();

        Map<String, BotPrice> marketPrices = pricesServiceBean.getMarketPrices();
        comparePrices.findAndComparePrices(marketPrices);

        Instant t1 = Instant.now();
        System.out.println(ZonedDateTime.now());
        System.out.println("Total: " + ChronoUnit.SECONDS.between(t0, t1));
    }


}
