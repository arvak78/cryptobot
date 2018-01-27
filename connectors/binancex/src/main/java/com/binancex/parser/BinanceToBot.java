package com.binancex.parser;

import com.binancex.constants.FilterType;
import com.binancex.model.FiltersItem;
import com.binancex.model.Response;
import com.binancex.model.SymbolsItem;
import com.commons.model.BotResponse;
import com.commons.model.BotCurrency;
import com.commons.model.Wrapper;
import com.commons.utils.StringUtils;

import java.util.ArrayList;

/**
 * Created by manel on 22/01/18.
 */
public class BinanceToBot {

    public BotResponse parseCurrency(Wrapper<Response> wrapper) {
        BotResponse<BotCurrency> response = new BotResponse<>();

        if (wrapper != null) {
            response.setSuccess(true);
            ArrayList<BotCurrency> currencies = new ArrayList<>();

            for (com.binancex.model.Response binanceResponse : wrapper.getData()) {

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
                        currency.setMaxWithdraw(lotFilter.getMaxQty()); //TODO: comprobar con Alex
                        currency.setMinWithdraw(lotFilter.getMinQty());
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

}
