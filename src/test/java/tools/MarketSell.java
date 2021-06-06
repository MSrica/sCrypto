package tools;

import GUI.layouts.HomeLayout;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.TickerPrice;
import static com.binance.api.client.domain.account.NewOrder.marketSell;
import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.domain.account.Account;

public class MarketSell {
    static String symbolMT = HomeLayout.tradingPair;
    static String[] symbolSell = symbolMT.split("/");
    static String tradingPair = symbolSell[0];
    static String quantity = "";

    public static void getScryptoBalance(BinanceApiRestClient client) {
        Account account = client.getAccount(60_000L, System.currentTimeMillis());
        quantity = String.format("%.8s", account.getAssetBalance(tradingPair).getFree());
        //System.out.println(quantity);
    }
    public static void sellsCrypto(BinanceApiRestClient client, String tradingPair, String quantity){
        client.newOrder(marketSell(tradingPair,  quantity));
    }

    public static void main() {
        //System.out.println("prodajem");
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("api", "secret");
        BinanceApiRestClient client = factory.newRestClient();
        getScryptoBalance(client);
        sellsCrypto(client, tradingPair, quantity);
    }
}