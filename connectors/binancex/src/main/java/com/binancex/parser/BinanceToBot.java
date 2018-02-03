package com.binancex.parser;

import com.binancex.constants.FilterType;
import com.binancex.model.currencies.FiltersItem;
import com.binancex.model.currencies.Response;
import com.binancex.model.currencies.SymbolsItem;
import com.binancex.model.prices.Prices;
import com.commons.Exchanges;
import com.commons.constants.ExchangeConstants;
import com.commons.model.BotCurrency;
import com.commons.model.BotPrice;
import com.commons.model.BotResponse;
import com.commons.model.Wrapper;
import com.commons.utils.ListUtils;
import com.commons.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by manel on 22/01/18.
 */
public class BinanceToBot {

    public BotResponse parseCurrency(Response response) {
        BotResponse<BotCurrency> botResponse = new BotResponse<>();

        if (response != null) {
            botResponse.setSuccess(true);
            ArrayList<BotCurrency> currencies = new ArrayList<>();

            for (SymbolsItem symbol : ListUtils.nullSafe(response.getSymbols())) {

                BotCurrency currency = new BotCurrency();

                currency.setSymbol(symbol.getBaseAsset());

                if (currencies.contains(currency)) { // Si existe en la lista lo recuperamos
                    currency = currencies.get(currencies.indexOf(currency));
                } else { // Si no existe lo creamos y lo metemos en la lista
                    currencies.add(currency);
                }

                currency.getQuotes().add(symbol.getQuoteAsset());

                currency.setStatus(symbol.getStatus());

                FiltersItem priceFilter = getFilter(symbol, FilterType.PRICE_FILTER);
                if (priceFilter != null) {
                    currency.setMinBaseTrade(StringUtils.StringToDouble(priceFilter.getMinPrice())); //TODO: comprobar con Alex
                }

                FiltersItem lotFilter = getFilter(symbol, FilterType.LOT_SIZE);
                if (lotFilter != null) {
                    currency.setMaxQty(lotFilter.getMaxQty()); //TODO: comprobar con Alex
                    currency.setMinQty(lotFilter.getMinQty());
                }

                // Evita duplicidades ya que en la lista salen varios casos
                // BTCEHC
                // BTCUSD


//                    if (!currencies.contains(currency)) {
//                        currencies.add(currency);
//                    }



            botResponse.setData(currencies);
        }

            return botResponse;
        }
        return null;
    }

    private FiltersItem getFilter(SymbolsItem symbol, FilterType filter) {
        for (FiltersItem item : symbol.getFilters()) {
            if (item.getFilterType().equals(filter.name())) {
                return item;
            }
        }

        return null;
    }

    public Map<String, BotPrice> parsePrices(Prices[] pricesWrapper) {

        Map<String, BotPrice> binanceMapPrice = new HashMap<>();

        if (pricesWrapper != null) {
            for (Prices price : pricesWrapper) {
                BotPrice botPrice = new BotPrice();
                botPrice.setSymbol(price.getSymbol());
                botPrice.setAskPrice(price.getAskPrice());
                botPrice.setBidPrice(price.getBidPrice());
                botPrice.setAskQty(price.getAskQty());
                botPrice.setBidQty(price.getBidQty());

                //BINANCE#BRDBTC
                StringBuffer sb = new StringBuffer();
                sb.append(Exchanges.BINANCE).append(ExchangeConstants.PRICE_KEY_SPLIT).
                        append(price.getSymbol());
                binanceMapPrice.put(sb.toString(), botPrice);
            }
        }

        return binanceMapPrice;
    }

}
