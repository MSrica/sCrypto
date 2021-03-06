package api_requests;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.CandlestickEvent;
import com.binance.api.client.domain.market.CandlestickInterval;
import tools.CandlestickEventToCandlestickConverter;

public class CandlestickDataStream {

  public CandlestickEvent event;
  public BinanceApiWebSocketClient client;

  public CandlestickDataStream(String tradingPair, CandlestickInterval interval){

    try {
      client = BinanceApiClientFactory.newInstance().newWebSocketClient();
      System.out.println("Connected to web socket successfully");
    } catch (Exception e){
      System.out.println("Connecting to web socket failed\n" + e);
      return;
    }

    client.onCandlestickEvent(tradingPair, interval, (response) -> {
      this.event = response;
      new CandlestickEventToCandlestickConverter(response);
//      System.out.println(event);
    });
  }
}
