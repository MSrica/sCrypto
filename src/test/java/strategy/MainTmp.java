package strategy;

import com.binance.api.client.domain.market.CandlestickInterval;
import api_requests.*;
import tools.BinanceTaapiDataConverter;
import tools.*;


public class MainTmp {

    static String tradingPair = "BTC/USDT";
    static CandlestickInterval intervalB = CandlestickInterval.ONE_MINUTE;

    public static void main(String[] args) {
        ///////////////////////////////////////////// Candlesticks stream /////////////////////////////////////////////
        BinanceTaapiDataConverter binanceData = new BinanceTaapiDataConverter(tradingPair);
        new CandlestickDataStream(binanceData.tradingPairB, intervalB);

        ///////////////////////////////////////////// TA indicators value /////////////////////////////////////////////
        BinanceTaapiDataConverter taapiData = new BinanceTaapiDataConverter(intervalB);
        new TAIndicatorRequest(tradingPair, taapiData.interval);

        TAIndicatorReader indicators = new TAIndicatorReader();

        System.out.println("MACD:\n- value: " + indicators.macdValue + "\n- signal: " + indicators.macdSignal + "\n- hist: " + indicators.macdHist + "\nMA200:\n- value: " + indicators.maValue);
    }

    public MainTmp(CandlestickEventToCandlestickConverter candlestick){
        System.out.println(tradingPair + "\n- high: " + candlestick.high + "\n- open: " + candlestick.open + "\n- close: " + candlestick.close + "\n- open: " + candlestick.low);
    }

}
