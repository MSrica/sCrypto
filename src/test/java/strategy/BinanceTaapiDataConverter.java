package strategy;

import com.binance.api.client.domain.market.CandlestickInterval;
import java.util.ArrayList;
import java.util.Arrays;

public class BinanceTaapiDataConverter {

    public String tradingPairB, interval;

    public BinanceTaapiDataConverter(String tradingPair, CandlestickInterval intervalB){

        // Adjusts trading pair to fit binanace-java-api
        String tradingPairB = tradingPair.replaceAll("/", "");

        ArrayList<String> intervalListTaapi = new ArrayList<>(Arrays.asList("1m", "3m", "5m", "15m", "30m", "1h", "2h", "4h", "6h", "8h", "12h", "1d", "3d", "1w", "1M"));
        ArrayList<String> intervalListBinance = new ArrayList<>(Arrays.asList("ONE_MINUTE", "THREE_MINUTES", "FIVE_MINUTES", "FIFTEEN_MINUTES", "HALF_HOURLY", "HOURLY", "TWO_HOURLY", "FOUR_HOURLY", "SIX_HOURLY", "EIGHT_HOURLY", "TWELVE_HOURLY", "DAILY", "THREE_DAILY", "WEEKLY", "MONTHLY"));

        // Adjusts trading pair to fit taapi-api
        String interval = intervalB.toString().replaceAll("CandlestickInterval.", "");
        for (int i = 0; i < intervalListBinance.size(); i++) {
            if (intervalListBinance.get(i).equals(interval)) {
                interval = intervalListTaapi.get(i);

            }
        }

        this.tradingPairB = tradingPairB;
        this.interval = interval;
    }
}
