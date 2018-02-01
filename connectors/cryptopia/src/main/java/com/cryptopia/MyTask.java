package com.cryptopia;


import com.commons.model.BotPrice;
import com.commons.model.ExchangeTasks;
import com.commons.model.Wrapper;
import com.cryptopia.model.Price;
import com.cryptopia.parser.CryptopiaToBot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Getter
@Setter
public class MyTask implements ExchangeTasks{
  private ListenableFuture<ResponseEntity<Wrapper<Price>>> future;

  public MyTask(ListenableFuture<ResponseEntity<Wrapper<Price>>> future) {
    this.future = future;
  }

  @Override
  public Wrapper<BotPrice> getData() {

//    com.commons.utils.Response response = new com.commons.utils.Response();

    Wrapper<BotPrice> botPriceWrapper = null;

    try {
      Wrapper<Price> body = future.get(50, TimeUnit.SECONDS).getBody();
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