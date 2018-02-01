package com.cryptopia.pub;

import com.commons.Exchanges;
import com.commons.annotations.Exchange;
import com.commons.exceptions.ExchangeException;
import com.commons.exceptions.MarshallException;
import com.commons.model.*;
import com.commons.utils.JsonClient;
import com.commons.utils.JsonClients;
import com.commons.utils.Properties;
import com.cryptopia.MyTask;
import com.cryptopia.model.Currency;
import com.cryptopia.model.Price;
import com.cryptopia.model.Response;
import com.cryptopia.parser.CryptopiaToBot;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.core.ParameterizedTypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by manel on 19/01/18.
 */
@Exchange(name = Exchanges.CRYPTOPIA)
public class CryptopiaPublicApi implements ExchangesApi {

    public static final String PROPERTIES_PATH = CryptopiaPublicApi.class.getProtectionDomain().
            getCodeSource().getLocation().getPath();

    @Override
    public BotResponse getCurrencies() throws MarshallException, ExchangeException {

        String url = Properties.getApplicationProperties(PROPERTIES_PATH)
                .getProperty("url.currencies");

        Wrapper cryptopiaResponse = JsonClients.getInstance().
                buildWithMapper(new TypeReference<Wrapper<Currency>>() {}, url);

        CryptopiaToBot toBot = new CryptopiaToBot();
        BotResponse botResponse = toBot.parseCurrencies(cryptopiaResponse);

        return botResponse;

    }

    @Override
    public List<ExchangeTasks> getMarkets() throws MarshallException, ExchangeException {

        String url = Properties.getApplicationProperties(PROPERTIES_PATH)
                .getProperty("url.prices");
        List<ExchangeTasks> tasks = new ArrayList<>();

        JsonClient cli = new JsonClient();
        try {
            ParameterizedTypeReference<Wrapper<Price>> typeReference = new ParameterizedTypeReference<Wrapper<Price>>() {
            };
            tasks.add(new MyTask(cli.client(typeReference, url)));
        } catch (IOException e) {
            throw new ExchangeException();
        }

//        JsonClients.getInstance().
//                buildWithMapperAsync(new TypeReference<Wrapper<Price>>() {}, url);


//        CryptopiaToBot toBot = new CryptopiaToBot();
//        Map<String, BotPrice> priceMap = toBot.parsePrices(wrapperPrices);

        return tasks;
    }

//    private List<com.commons.utils.Response> getFutureData(List<MyTask> tasks) {
//        long start = System.nanoTime();
//        ExecutorService executor = Executors.newFixedThreadPool(Math.min(tasks.size(), 10));
//        CryptopiaToBot toBot = new CryptopiaToBot();
//
//
//        List<CompletableFuture<Wrapper<BotPrice>>> futures =
//                tasks.stream()
//                        .map(t -> CompletableFuture.supplyAsync(() -> t.getData(), executor))
//                        .collect(Collectors.toList());
//
//
//        List<Wrapper<BotPrice>> result = futures.stream()
//                .map(CompletableFuture::join)
//                .collect(Collectors.toList());
//
//        long duration = (System.nanoTime() - start) / 1_000_000;
//
//        return null;
//    }

}
