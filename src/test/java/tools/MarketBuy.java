package tools;
import GUI.layouts.HomeLayout;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.TickerPrice;
import static com.binance.api.client.domain.account.NewOrder.marketBuy;

public class MarketBuy {
    static String tradingPair= HomeLayout.tradingPair;
    public static String quantityString;
    public static String pricesCrypto(BinanceApiRestClient client, String tradingPair){
        TickerPrice priceString = client.getPrice(tradingPair);
        float priceFloat = Float.parseFloat(priceString.getPrice());
        float quantityFloat = 12 / priceFloat;
        quantityString = String.format("%.6f", quantityFloat);
        return quantityString;
    }
    public static void buysCrypto(BinanceApiRestClient client, String tradingPair, String quantity){
        client.newOrder(marketBuy(tradingPair,  quantity));
    }
    public static void main() {
        //System.out.println("kupujem");
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("api", "secret");
        BinanceApiRestClient client = factory.newRestClient();
        String quantity = pricesCrypto(client, tradingPair);
        //System.out.println(quantity);
        buysCrypto(client, tradingPair, quantity);
    }
}