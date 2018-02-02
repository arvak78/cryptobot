package com.cryptopia;


import com.commons.model.BotPrice;
import com.commons.model.ExchangeTasks;
import com.commons.model.Wrapper;
import com.cryptopia.model.Price;
import com.cryptopia.parser.CryptopiaToBot;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;

@Getter
@Setter
public class CryptopiaPricesTask implements ExchangeTasks{
  private ListenableFuture<ResponseEntity<Wrapper<Price>>> future;

  public CryptopiaPricesTask(ListenableFuture<ResponseEntity<Wrapper<Price>>> future) {
    this.future = future;
  }

  @Override
  public Map<String, BotPrice> getData() {

    Map<String, BotPrice> botPriceWrapper = null;

    try {
      Wrapper<Price> body = future.get(10, TimeUnit.SECONDS).getBody();
      CryptopiaToBot toBot = new CryptopiaToBot();
      botPriceWrapper = toBot.parsePrices(body);
    } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      } catch (TimeoutException e) {
        e.printStackTrace();
      }
      return botPriceWrapper;
    }


}