package com.binancex;


import com.binancex.model.prices.Prices;
import com.binancex.parser.BinanceToBot;
import com.commons.model.BotPrice;
import com.commons.model.ExchangeTasks;
import com.commons.model.Wrapper;
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
public class BinancePricesTask implements ExchangeTasks{

  private ListenableFuture<ResponseEntity<Prices[]>> future;

  public BinancePricesTask(ListenableFuture<ResponseEntity<Prices[]>> future) {
    this.future = future;
  }

  @Override
  public Map<String, BotPrice> getData() {

    Map<String, BotPrice> botPriceWrapper = null;

    try {
      Prices[] body = future.get(10, TimeUnit.SECONDS).getBody();
      BinanceToBot toBot = new BinanceToBot();
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