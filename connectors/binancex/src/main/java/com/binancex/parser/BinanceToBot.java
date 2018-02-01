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
import com.commons.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by manel on 22/01/18.
 */
public class BinanceToBot {

    public BotResponse parseCurrency(Wrapper<Response> wrapper) {
        BotResponse<BotCurrency> response = new BotResponse<>();

        if (wrapper != null) {
            response.setSuccess(true);
            ArrayList<BotCurrency> currencies = new ArrayList<>();

            for (Response binanceResponse : wrapper.getData()) {

                for (SymbolsItem symbol : binanceResponse.getSymbols()) {

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
                }


                response.setData(currencies);
            }
        }

        return response;
    }

    private FiltersItem getFilter(SymbolsItem symbol, FilterType filter) {
        for (FiltersItem item : symbol.getFilters()) {
            if (item.getFilterType().equals(filter.name())) {
                return item;
            }
        }

        return null;
    }

    public Map<String, BotPrice> parsePrices(Wrapper<Prices> pricesWrapper) {

        Map<String, BotPrice> binanceMapPrice = new HashMap<>();

        if (pricesWrapper != null) {
            for (Prices price : pricesWrapper.getData()) {
                BotPrice botPrice = new BotPrice();
                botPrice.setAskPrice(price.getAskPrice() != null ? Double.parseDouble(price.getAskPrice()) : 0);
                botPrice.setBidPrice(price.getBidPrice() != null ? Double.parseDouble(price.getBidPrice()) : 0);
                botPrice.setAskQty(price.getAskQty() != null ? Double.parseDouble(price.getAskQty()) : 0);
                botPrice.setBidQty(price.getBidQty() != null ? Double.parseDouble(price.getBidQty()) : 0);

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
