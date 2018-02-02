package com.core;

import com.commons.Exchanges;
import com.commons.annotations.Exchange;
import com.commons.model.BotPrice;
import com.commons.model.ExchangeTasks;
import com.commons.model.ExchangesApi;
import com.commons.utils.reflection.FindAnotations;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class PricesServiceBean {

    public Map<String, BotPrice> getMarketPrices() {

        Map<Exchanges, Class> allClassesAnnotatedBy = FindAnotations.getInstance().findAllClassesAnnotatedBy(Exchange.class);

        List<ExchangeTasks> markets = new ArrayList<>();
        for (Map.Entry<Exchanges, Class> entry : allClassesAnnotatedBy.entrySet()) {
            Exchanges key = entry.getKey();
            Class aClass = entry.getValue();

            try {
                ExchangesApi api = (ExchangesApi) aClass.newInstance();
                markets.addAll(api.getMarkets());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return getFutureData(markets);
    }

    private Map<String, BotPrice> getFutureData(List<ExchangeTasks> tasks) {

        Map<String, BotPrice> priceMap = new HashMap<>();

        for (ExchangeTasks task : tasks) {
            priceMap.putAll(task.getData());
        }

        return priceMap;
    }

}
