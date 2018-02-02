package com.cryptopia.parser;

import com.commons.Exchanges;
import com.commons.constants.ExchangeConstants;
import com.commons.model.BotCurrency;
import com.commons.model.BotPrice;
import com.commons.model.BotResponse;
import com.commons.model.Wrapper;
import com.cryptopia.model.Currency;
import com.cryptopia.model.Price;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Manel on 27/01/2018.
 */
public class CryptopiaToBot {

    public BotResponse parseCurrencies(Wrapper<Currency> cryptopiaResponse) {

        BotResponse<BotCurrency> botResponse = new BotResponse<>();

        if (cryptopiaResponse != null) {
            botResponse.setSuccess(true);
            List<BotCurrency> currencyList = new ArrayList<>();
            for (Currency currency : cryptopiaResponse.getData()) {
                BotCurrency botCurrency = new BotCurrency();
                botCurrency.setSymbol(currency.getSymbol());
                botCurrency.setName(currency.getName());
                botCurrency.setAlgorithm(currency.getAlgorithm());
                botCurrency.setDepositConfirmations(currency.getDepositConfirmations());
                botCurrency.setMaxWithdraw(currency.getMaxWithdraw());
                botCurrency.setMinWithdraw(currency.getMinWithdraw());
                botCurrency.setMinBaseTrade(currency.getMinBaseTrade());
                botCurrency.setWithdrawFee(currency.getWithdrawFee());
                botCurrency.setMinTip(currency.getMinTip());
                botCurrency.setStatus(currency.getStatus());
                botCurrency.setId(currency.getId());
                currencyList.add(botCurrency);
            }
            botResponse.setData(currencyList);
        }

        return botResponse;
    }

    public Map<String, BotPrice> parsePrices(Wrapper<Price> priceWrap) {
        if (priceWrap != null) {
            Map<String, BotPrice> cryptopiaMapPrice = new HashMap<>();
            for (Price price : priceWrap.getData()) {
                BotPrice botPrice = new BotPrice();
                botPrice.setSymbol(price.getLabel());
                botPrice.setBidPrice(price.getBidPrice());
                botPrice.setAskPrice(price.getAskPrice());

                StringBuffer sb = new StringBuffer();
                sb.append(Exchanges.CRYPTOPIA).append(ExchangeConstants.PRICE_KEY_SPLIT).
                        append(price.getLabel());
                cryptopiaMapPrice.put(sb.toString(), botPrice);
            }

            return cryptopiaMapPrice;
        }
        return null;
    }

}
