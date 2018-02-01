package com.cryptopia.parser;

import com.commons.model.BotCurrency;
import com.commons.model.BotPrice;
import com.commons.model.BotResponse;
import com.commons.model.Wrapper;
import com.cryptopia.model.Currency;
import com.cryptopia.model.Price;

import java.util.ArrayList;
import java.util.List;

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

    public Wrapper<BotPrice> parsePrices(Wrapper<Price> priceWrap) {
        if (priceWrap != null) {
            Wrapper<BotPrice> botPriceWrap = new Wrapper<>();
            ArrayList<BotPrice> botPriceArrayList = new ArrayList<>();
            for (Price price : priceWrap.getData()) {
                BotPrice botPrice = new BotPrice();
                botPrice.setSymbol(price.getLabel());
                botPrice.setBidPrice(price.getBidPrice());
                botPrice.setAskPrice(price.getAskPrice());
                botPriceArrayList.add(botPrice);
            }
            botPriceWrap.setData(botPriceArrayList);
            return botPriceWrap;
        }
        return null;
    }

}
