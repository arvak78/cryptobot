package com.core;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;

/**
 * Created by manel on 20/01/18.
 */
@EnableCaching
@Controller
public class CoreController {

//    private OperationCurrencies operationCurrencies;
//    private Map<Exchanges, ExchangeMatch> matchCurrenciesByExchange;
//
//    @Autowired
//    private ConnectorService connectorService;
//
//    @Autowired
//    private CurrencyService currencyService;
//
//    @Autowired
//    private CacheManager cacheManager;
//
//    @Autowired
//    private FindAnnotation findAnnotation;
//
//    @RequestMapping("/core")
//    public void startExchanges() throws MarshallException, IOException {
//
//        Map<Exchanges, Class> allClassesAnnotatedBy = findAnnotation.findAllClassesAnnotatedBy(Exchange.class);
//        initConnectorsAndCurrencies(allClassesAnnotatedBy);
//
//        List<ExchangeTasks> markets1 = null;
//        for (Map.Entry<Exchanges, Class> entry : allClassesAnnotatedBy.entrySet()) {
//             Exchanges key = entry.getKey();
//            Class aClass = entry.getValue();
//
//            try {
//                ExchangesApi api = (ExchangesApi) aClass.newInstance();
//                markets1 = api.getMarkets();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        List<Response> futureData = getFutureData(markets1);
//
//        System.out.println("");
//
////        findMatchCurrenciesByExchange();
//
//    }
//
//    private List<com.commons.utils.Response> getFutureData(List<ExchangeTasks> tasks) {
//        long start = System.nanoTime();
//        ExecutorService executor = Executors.newFixedThreadPool(Math.min(tasks.size(), 10));
//
//
//                tasks.stream().
//                        map(t -> CompletableFuture.supplyAsync(() -> t.getData(), executor))
//                        .collect(Collectors.toList());
//
//        List<CompletableFuture<Wrapper<BotPrice>>> futures =
//                tasks.stream()
//                        .map(t -> CompletableFuture.supplyAsync(() -> t.getData(), executor))
//                        .collect(Collectors.toList());
//
//
//        List<Wrapper<BotPrice>> collect = futures.stream()
//                .map(CompletableFuture::join)
//                .collect(Collectors.toList());
//
//        long duration = (System.nanoTime() - start) / 1_000_000;
//
//        return null;
//    }
//
//
//    private void findMatchCurrenciesByExchange() {
//        Map<Exchanges, ExchangeMatch> mapMatch = new HashMap<>();
//        for (Exchanges exchange0 : Exchanges.values()) {
//            ExchangeMatch match = new ExchangeMatch(exchange0);
//            List<String> currencyMatchList = new ArrayList<>();
//            for (Exchanges exchange1 : Exchanges.values()) {
//                for (com.core.utils.Currency currency : operationCurrencies.getCurrency()) {
//                    if (exchange0 != exchange1) {
//                        BotCurrency currency0 = cacheManager.getCache(CacheConstants.CURRENCIES).get(exchange0 + currency.getSymbol(), BotCurrency.class);
//                        BotCurrency currency1 = cacheManager.getCache(CacheConstants.CURRENCIES).get(exchange1 + currency.getSymbol(), BotCurrency.class);
//                        if (currency0 != null && currency1 != null) {
//                            currencyMatchList.add(currency.getSymbol());
//                        }
//                        match.addMatch(exchange1, currencyMatchList);
//                    }
//                }
//            }
//            mapMatch.put(exchange0, match);
//        }
//
//        matchCurrenciesByExchange = mapMatch;
//    }
//
//    /**
//     * Retrieve connector classes and store in cache
//     * Retrieve currencies from exchange and store in cache
//     * @param allClassesAnnotatedBy
//     * @throws MarshallException
//     */
//    private void initConnectorsAndCurrencies(Map<Exchanges, Class> allClassesAnnotatedBy) throws MarshallException {
//        for (Map.Entry<Exchanges, Class> entry : allClassesAnnotatedBy.entrySet()) {
//            Exchanges key = entry.getKey();
//            Class aClass = entry.getValue();
//
//            // Add to cache all classes retrieved from Connectors
//            connectorService.play(key, aClass);
//
////            // Puede que no sea necesario
////            try {
////                ExchangesApi api = (ExchangesApi) aClass.newInstance();
////                BotResponse<BotCurrency> response = api.getCurrencies();
////
////                addToCacheCurrenciesByExchange(key, response.getData());
////
////            } catch (InstantiationException|IllegalAccessException|ExchangeException e) {
////                e.printStackTrace();
////            }
//
//        }
//
//        operationCurrencies = ReadXml.readOperatingCurrencies();
//
//    }
//
//    private void addToCacheCurrenciesByExchange(Exchanges key, List<BotCurrency> data) {
//        for (BotCurrency currency : data) {
//            currencyService.play(key, currency);
//        }
//    }

}
