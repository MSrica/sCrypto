package api_test;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.TickerPrice;
import static com.binance.api.client.domain.account.NewOrder.marketBuy;
import static com.binance.api.client.domain.account.NewOrder.marketSell;
import strategy.MainTmp;

public class TradeTest {
    static String symbolMT = MainTmp.tradingPair;
    static String symbol = symbolMT.replace("/","");
    public static String pricesCrypto(BinanceApiRestClient client, String symbol){
        TickerPrice priceString = client.getPrice(symbol);
        float priceFloat = Float.parseFloat(priceString.getPrice());
        float quantityFloat = 10 / priceFloat;
        String quantityString = String.format("%.6f", quantityFloat);
        return quantityString;
    }
    public static void sellsCrypto(BinanceApiRestClient client, String symbol, String quantity){
        client.newOrder(marketSell(symbol,  quantity));
    }
    public static void buysCrypto(BinanceApiRestClient client, String symbol, String quantity){
        client.newOrder(marketBuy(symbol,  quantity));
    }
    public static void main(String[] args) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("api_key", "secret_key");
        BinanceApiRestClient client = factory.newRestClient();
        String quantity = pricesCrypto(client, symbol);
        //sellsCrypto(client, symbol, quantity);
        buysCrypto(client, symbol, quantity);
    }
}
