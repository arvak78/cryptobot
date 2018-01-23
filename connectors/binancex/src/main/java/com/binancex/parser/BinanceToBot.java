package com.binancex.parser;

import com.binancex.constants.FilterType;
import com.binancex.model.FiltersItem;
import com.binancex.model.SymbolsItem;
import com.binancex.model.Response;
import com.commons.model.Currency;
import com.commons.utils.StringUtils;

import java.util.ArrayList;

/**
 * Created by manel on 22/01/18.
 */
public class BinanceToBot {

    public com.commons.model.Response parseCurrency(Response binanceResponse) {
        com.commons.model.Response response = new com.commons.model.Response();

        if (binanceResponse != null) {
            response.setSuccess(true);
            ArrayList<Currency> currencies = new ArrayList<>();

            for (SymbolsItem symbol : binanceResponse.getSymbols()) {
                Currency currency = new Currency();

                currency.setSymbol(symbol.getBaseAsset());
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
                if(!currencies.contains(currency)) {
                    currencies.add(currency);
                }

            }

            response.setData(currencies);
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
