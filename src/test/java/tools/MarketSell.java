package tools;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.TickerPrice;
import static com.binance.api.client.domain.account.NewOrder.marketBuy;
import static com.binance.api.client.domain.account.NewOrder.marketSell;
import strategy.MainTmp;

public class MarketSell {
    static String symbolMT = MainTmp.tradingPair;
    static String symbol = symbolMT.replace("/","");
    static String quantity = MarketBuy.quantityString;
    public static void sellsCrypto(BinanceApiRestClient client, String symbol, String quantity){
        client.newOrder(marketSell(symbol,  quantity));
    }

    public static void main(String[] args) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("api_key", "secret_key");
        BinanceApiRestClient client = factory.newRestClient();
        sellsCrypto(client, symbol, quantity);
    }
}
