package strategy;

import com.binance.api.client.domain.market.CandlestickInterval;
import api_requests.*;
import tools.BinanceTaapiDataConverter;
import tools.*;


public class MainTmp {

    public static String tradingPair = "BTC/USDT";
    static CandlestickInterval intervalB = CandlestickInterval.FIVE_MINUTES;
    private static TAIndicatorReader indicators;

    public static void main(String[] args) {
        ///////////////////////////////////////////// TA indicators value /////////////////////////////////////////////
        BinanceTaapiDataConverter taapiData = new BinanceTaapiDataConverter(intervalB);
        new TAIndicatorRequest(tradingPair, taapiData.interval);

        // Get MACD and MA200
        indicators = new TAIndicatorReader();

        ///////////////////////////////////////////// Candlesticks stream /////////////////////////////////////////////
        BinanceTaapiDataConverter binanceData = new BinanceTaapiDataConverter(tradingPair);
        new CandlestickDataStream(binanceData.tradingPairB, intervalB);
    }

    public MainTmp(CandlestickEventToCandlestickConverter candlestick){
//        System.out.println("MACD:\n- value: " + indicators.macdValue + "\n- signal: " + indicators.macdSignal + "\n- hist: " + indicators.macdHist + "\nMA200:\n- value: " + indicators.maValue);
//        System.out.println(tradingPair + "\n- high: " + candlestick.high + "\n- open: " + candlestick.open + "\n- close: " + candlestick.close + "\n- open: " + candlestick.low);

        // Gets asset's trend and checks if MACD is below zero line
        CheckTrendAndMACD asset = new CheckTrendAndMACD(candlestick.close, indicators);


//        System.out.println("Trend: " + (asset.trend ? "up":"down"));
//        System.out.println("macdBelowZero: " + (asset.macdBelowZero ? "yes":"no"));

        // Checks if given asset.trend and asset.macdBelowZero meets the conditions
        new CheckConditions(asset);
//        System.out.println("orderPlaced: " + asset.orderPlaced);
    }
}
