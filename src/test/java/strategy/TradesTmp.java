package strategy;


import com.binance.api.client.domain.market.CandlestickInterval;
import api_requests.*;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Arrays;

public class TradesTmp {

    static String tradingPair = "BTC/USDT";
    static CandlestickInterval intervalB = CandlestickInterval.ONE_MINUTE;
    static ArrayList<String> endpoints = new ArrayList<>(Arrays.asList("macd", "rsi"));

    public static void main(String[] args) {

        BinanceTaapiDataConverter convertedData = new BinanceTaapiDataConverter(tradingPair, intervalB);

        ///////////////////////////////////// Candlesticks cache /////////////////////////////////////
        CandlesticksCache candlestickRaw = new CandlesticksCache(convertedData.tradingPairB, intervalB);

        String candlestickData = candlestickRaw.toString();

        // Log candlestick data on console
//        System.out.println(candlestickData);

        //////////////////////////////////// TA indicators value ////////////////////////////////////
        TAIndicatorRequest indicatorData = new TAIndicatorRequest(endpoints, tradingPair, convertedData.interval);

    }

}
