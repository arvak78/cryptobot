package com.core;

import com.commons.Exchanges;
import com.commons.constants.ExchangeConstants;
import com.commons.constants.TelegramEmojis;
import com.commons.model.BotPrice;
import com.core.utils.ExchangeMatch;
import com.core.utils.Telegram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Manel on 02/02/2018.
 */
@Component
public class ComparePrices {

    public static final String QUOTE_CURRENCY = "BTC";
    public static final String SPACE = " ";
    public static final String NEW_LINE = "\n";
    public static final BigDecimal CENT = new BigDecimal("100");
    private Map<Exchanges, ExchangeMatch> matchCurrenciesByExchange;

    @Autowired
    private MatchCurrencies matchCurrencies;

    @Autowired
    private Telegram telegram;

    @Autowired
    private FilterResults<Exchanges> filterResults;

    @Value("${core.limit.minpercent}")
    private String minPercentProfit;

    @Value("${core.config.telegram.chatid}")
    private String chatId;

    public void findAndComparePrices(Map<String, BotPrice> marketPrices) {

        matchCurrenciesByExchange = matchCurrencies.findMatchCurrenciesByExchange();

        for (Map.Entry<Exchanges, ExchangeMatch> entry : matchCurrenciesByExchange.entrySet()) {
            ExchangeMatch originExchange = entry.getValue();

            Iterator<Map.Entry<Exchanges, List<String>>> iterator = originExchange.getExchangeMapMatch().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Exchanges, List<String>> destinyExchange = iterator.next();
                destinyExchange.getKey();

                if (filterResults.existPair(originExchange.getExchange(), destinyExchange.getKey())) {
                    continue;
                }

                filterResults.addPair(originExchange.getExchange(), destinyExchange.getKey());

                Iterator<String> currencyIt = destinyExchange.getValue().iterator();
                while (currencyIt.hasNext()) {
                    String currency = currencyIt.next();

                    BotPrice price0 = getFromMarketPriceMap(marketPrices, originExchange.getExchange(), currency);
                    BotPrice price1 = getFromMarketPriceMap(marketPrices, destinyExchange.getKey(), currency);

                    if (price0 != null && price1 != null) {
                        MaxMinPrices maxMinPrices = getMaxMinPrices(price0, price1);
                        if (maxMinPrices.getMinPrice() != null && maxMinPrices.getMaxPrice() != null) {
                            BigDecimal askMinPrices = maxMinPrices.getMinPrice().getAskPrice();
                            BigDecimal askMaxPrices = maxMinPrices.getMaxPrice().getAskPrice();
                            BigDecimal bidMinPrices = maxMinPrices.getMinPrice().getBidPrice();
                            BigDecimal bidMaxPrices = maxMinPrices.getMaxPrice().getBidPrice();
                            BigDecimal limit = calcProfitLimitPrice(askMinPrices, new BigDecimal(minPercentProfit));

                            if (isOportunitat(bidMaxPrices, limit)) {
                                Oportunitat oportunitat = createOportunitat(originExchange, destinyExchange,
                                        currency, maxMinPrices.getMinPrice(), maxMinPrices.getMaxPrice());
                                BigDecimal profitPercent = getProfit(askMinPrices, bidMaxPrices);


                                Oportunitat findedOportunity = filterResults.findOportunitat(oportunitat);
                                if (findedOportunity == null) {
                                    filterResults.addOportunitat(oportunitat);
                                    addTelegramOportunitat(maxMinPrices.getMinPrice().getExchange(), maxMinPrices.getMaxPrice().getExchange(),
                                            currency, askMinPrices, bidMaxPrices, profitPercent);
                                } else {
                                    findedOportunity.setLastPickOutInstant(ZonedDateTime.now());

                                    BigDecimal lastProfitSeen = findedOportunity.getProfitList().get(findedOportunity.getProfitList().size()-1);
                                    BigDecimal plusOne = lastProfitSeen.add(BigDecimal.ONE);
                                    BigDecimal minusOne = lastProfitSeen.subtract(BigDecimal.ONE);
                                    boolean isMore = profitPercent.compareTo(plusOne) > 0;
                                    boolean isMinus = profitPercent.compareTo(minusOne) < 0;
                                    if (isMore || isMinus) {
                                        findedOportunity.addProfitList(profitPercent);
                                        addTelegramPlusProfit(maxMinPrices.getMinPrice().getExchange(), maxMinPrices.getMaxPrice().getExchange(),
                                                currency, askMinPrices, bidMaxPrices, profitPercent, isMore);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        filterResults.removePairs();

    }

    private BigDecimal getProfit(BigDecimal minPrice, BigDecimal maxPrice) {
        return (BigDecimal.ONE
                .subtract(minPrice
                .divide(maxPrice, 3, RoundingMode.HALF_UP)))
                .multiply(CENT).setScale(2);
    }

    private void addTelegramOportunitat(Exchanges minExchange, Exchanges maxExchange, String currency, BigDecimal askMinPrices, BigDecimal bidMaxPrices, BigDecimal profitPercent) {
        StringBuilder telegramSb = new StringBuilder();
        telegramSb.append("OPORTUNITAT!! ").append(NEW_LINE)
                .append(minExchange).append(SPACE)
                .append(maxExchange).append(SPACE).append(NEW_LINE)
                .append(currency).append("/").append(QUOTE_CURRENCY).append(NEW_LINE)
                .append("askMinPrices: ").append(askMinPrices).append(NEW_LINE)
                .append("bidMaxPrices: ").append(bidMaxPrices).append(NEW_LINE)
                .append("Profit: " + profitPercent).append(" %");

        telegram.sendMessage(telegramSb.toString(), chatId);
    }

    private void addTelegramPlusProfit(Exchanges minExchange, Exchanges maxExchange, String currency, BigDecimal askMinPrices, BigDecimal bidMaxPrices, BigDecimal profitPercent, boolean isMore) {
        StringBuilder telegramSb = new StringBuilder();

        telegramSb
                .append(getEmoji(isMore))
                .append(minExchange).append(SPACE)
                .append(maxExchange).append(SPACE).append(NEW_LINE)
                .append(currency).append("/").append(QUOTE_CURRENCY).append(NEW_LINE)
                .append("askMinPrices: ").append(askMinPrices).append(NEW_LINE)
                .append("bidMaxPrices: ").append(bidMaxPrices).append(NEW_LINE)
                .append("Profit: " + profitPercent).append(" %");

        telegram.sendMessage(telegramSb.toString(), chatId);
    }

    private StringBuilder getEmoji(boolean isMore) {
        StringBuilder emoji = new StringBuilder();

        if (isMore) {
            emoji
            .append(TelegramEmojis.GREEN_BOOK).append(TelegramEmojis.GREEN_BOOK)
            .append(TelegramEmojis.GREEN_BOOK).append(TelegramEmojis.GREEN_BOOK)
            .append(TelegramEmojis.GREEN_BOOK).append(TelegramEmojis.GREEN_BOOK)
            .append(NEW_LINE);
        } else {
            emoji
            .append(TelegramEmojis.RED_BOOK).append(TelegramEmojis.RED_BOOK)
            .append(TelegramEmojis.RED_BOOK).append(TelegramEmojis.RED_BOOK)
            .append(TelegramEmojis.RED_BOOK).append(TelegramEmojis.RED_BOOK)
            .append(NEW_LINE);
        }
        return emoji;
    }

    private Oportunitat createOportunitat(ExchangeMatch originExchange, Map.Entry<Exchanges, List<String>> destinyExchange, String currency, BotPrice price0, BotPrice price1) {
        Oportunitat oportunitat = new Oportunitat();
        oportunitat.setOriginExchange(originExchange.getExchange());
        oportunitat.setDestinyExchange(destinyExchange.getKey());
        oportunitat.setBaseCurrency(currency);
        oportunitat.setQuoteCurrency(QUOTE_CURRENCY);
        oportunitat.setOriginPrice(price0);
        oportunitat.setDestinyPrice(price1);
        oportunitat.setExposedInstant(ZonedDateTime.now());
        oportunitat.setLastPickOutInstant(ZonedDateTime.now());
        oportunitat.addProfitList(getProfit(price0.getAskPrice(), price1.getBidPrice()));
        return oportunitat;
    }

    private BotPrice getFromMarketPriceMap(Map<String, BotPrice> marketPrices, Exchanges exchange, String currency) {
        StringBuilder sb0 = new StringBuilder();
        sb0.append(exchange)
                .append(ExchangeConstants.PRICE_KEY_SPLIT)
                .append(currency)
                .append(QUOTE_CURRENCY); //TODO: POr ahora siempre compara con BTC habra que cambiarlo
        BotPrice botPrice = marketPrices.get(sb0.toString());

        if (botPrice != null) {
            botPrice.setExchange(exchange);
        }

        return botPrice;
    }

    private MaxMinPrices getMaxMinPrices(BotPrice price0, BotPrice price1) {

        MaxMinPrices maxMin = new MaxMinPrices();

        if (price0.getAskPrice().compareTo(price1.getAskPrice()) < 0) {
            maxMin.setMaxPrice(price1);
            maxMin.setMinPrice(price0);
        } else {
            maxMin.setMaxPrice(price0);
            maxMin.setMinPrice(price1);
        }

        return maxMin;
    }

    private boolean isOportunitat(BigDecimal destiny, BigDecimal limit) {
        if (destiny.compareTo(limit) > 0) {
            return true;
        }

        return false;
    }

    private BigDecimal calcProfitLimitPrice(BigDecimal price, BigDecimal minPercent) {
        BigDecimal percMultiplicator = BigDecimal.ONE.add(minPercent);
        return price.multiply(percMultiplicator);
    }
}
