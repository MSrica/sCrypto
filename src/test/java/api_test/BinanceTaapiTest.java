package api_test;


import com.binance.api.client.domain.market.CandlestickInterval;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Arrays;

public class BinanceTaapiTest {

    static String tradingPair = "BTC/USDT";
    static CandlestickInterval intervalB = CandlestickInterval.ONE_MINUTE;
    static ArrayList<String> endpoints = new ArrayList<>(Arrays.asList("wma", "sma"));

    public CandlesticksCache getCandlestickRaw() {
        return candlestickRaw;
    }

    private CandlesticksCache candlestickRaw;

    public BinanceTaapiTest(){
        // Adjusts parameters to fit binanace-java-api
        String currencyB = tradingPair.replaceAll("/", "");

        ArrayList<String> intervalListTaapi = new ArrayList<>(Arrays.asList("1m", "3m", "5m", "15m", "30m", "1h", "2h", "4h", "6h", "8h", "12h", "1d", "3d", "1w", "1M"));
        ArrayList<String> intervalListBinan = new ArrayList<>(Arrays.asList("ONE_MINUTE", "THREE_MINUTES", "FIVE_MINUTES", "FIFTEEN_MINUTES", "HALF_HOURLY", "HOURLY", "TWO_HOURLY", "FOUR_HOURLY", "SIX_HOURLY", "EIGHT_HOURLY", "TWELVE_HOURLY", "DAILY", "THREE_DAILY", "WEEKLY", "MONTHLY"));

        String interval = intervalB.toString().replaceAll("CandlestickInterval.", "");
        for (int i = 0; i < intervalListBinan.size(); i++) {
            if (intervalListBinan.get(i).equals(interval)) {
                interval = intervalListTaapi.get(i);

                break;
            }

        }

        ///////////////////////////////////// Candlesticks cache /////////////////////////////////////
        candlestickRaw = new CandlesticksCache(currencyB, intervalB);

        //String candlestickData = candlestickRaw.toString();

        // Log candlestick data on console
        // System.out.println(candlestickData);

        //////////////////////////////////// TA indicators value ////////////////////////////////////
        //TAIndicatorRequest indicatorData = new TAIndicatorRequest(endpoints, tradingPair, interval);

        }
    };

    /*public static void main(String[] args) {

        // Adjusts parameters to fit binanace-java-api
        String currencyB = tradingPair.replaceAll("/", "");

        ArrayList<String> intervalListTaapi = new ArrayList<>(Arrays.asList("1m", "3m", "5m", "15m", "30m", "1h", "2h", "4h", "6h", "8h", "12h", "1d", "3d", "1w", "1M"));
        ArrayList<String> intervalListBinan = new ArrayList<>(Arrays.asList("ONE_MINUTE", "THREE_MINUTES", "FIVE_MINUTES", "FIFTEEN_MINUTES", "HALF_HOURLY", "HOURLY", "TWO_HOURLY", "FOUR_HOURLY", "SIX_HOURLY", "EIGHT_HOURLY", "TWELVE_HOURLY", "DAILY", "THREE_DAILY", "WEEKLY", "MONTHLY"));

        String interval = intervalB.toString().replaceAll("CandlestickInterval.", "");
        for (int i = 0; i < intervalListBinan.size(); i++) {
            if (intervalListBinan.get(i).equals(interval)) {
                interval = intervalListTaapi.get(i);

                break;
            }
            
        }

        ///////////////////////////////////// Candlesticks cache /////////////////////////////////////
        CandlesticksCache candlestickRaw = new CandlesticksCache(currencyB, intervalB);

        String candlestickData = candlestickRaw.toString();

        // Log candlestick data on console
//        System.out.println(candlestickData);

        //////////////////////////////////// TA indicators value ////////////////////////////////////
        TAIndicatorRequest indicatorData = new TAIndicatorRequest(endpoints, tradingPair, interval);

    }


}
*/