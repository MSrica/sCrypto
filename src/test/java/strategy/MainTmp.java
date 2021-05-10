package strategy;

import com.binance.api.client.domain.market.CandlestickInterval;
import api_requests.*;
import tools.BinanceTaapiDataConverter;
import tools.*;


public class MainTmp {

    static String tradingPair = "BTC/USDT";
    static CandlestickInterval intervalB = CandlestickInterval.ONE_MINUTE;
    private static TAIndicatorReader indicators;

    public static void main(String[] args) {
        ///////////////////////////////////////////// TA indicators value /////////////////////////////////////////////
        BinanceTaapiDataConverter taapiData = new BinanceTaapiDataConverter(intervalB);
        new TAIndicatorRequest(tradingPair, taapiData.interval);

        indicators = new TAIndicatorReader();

        ///////////////////////////////////////////// Candlesticks stream /////////////////////////////////////////////
        BinanceTaapiDataConverter binanceData = new BinanceTaapiDataConverter(tradingPair);
        new CandlestickDataStream(binanceData.tradingPairB, intervalB);
    }

    public MainTmp(CandlestickEventToCandlestickConverter candlestick){
        System.out.println("MACD:\n- value: " + indicators.macdValue + "\n- signal: " + indicators.macdSignal + "\n- hist: " + indicators.macdHist + "\nMA200:\n- value: " + indicators.maValue);
        System.out.println(tradingPair + "\n- high: " + candlestick.high + "\n- open: " + candlestick.open + "\n- close: " + candlestick.close + "\n- open: " + candlestick.low);


        CheckTrend price = new CheckTrend(candlestick.close, indicators.maValue);
        System.out.println("Trend: " + (price.trend ? "up":"down"));

    }
}
