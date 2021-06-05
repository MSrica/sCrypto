package strategy;

import com.binance.api.client.domain.market.CandlestickInterval;
import api_requests.*;
import tools.BinanceTaapiDataConverter;
import tools.*;


public class MainTmp {

    public static final String tradingPair = "BTC/USDT";
    public static final CandlestickInterval intervalB = CandlestickInterval.ONE_MINUTE;
    public static TAIndicatorReader indicators;
    public static BinanceTaapiDataConverter taapiData;

    public static void main(String[] args) {
        ////////////////////////////////////// TA indicators value //////////////////////////////////////
        taapiData = new BinanceTaapiDataConverter(intervalB);
        new TAIndicatorRequest(tradingPair, taapiData.interval);

        // Request MACD and MA200 indicators
        indicators = new TAIndicatorReader();

        ////////////////////////////////////// Candlesticks stream //////////////////////////////////////
        BinanceTaapiDataConverter binanceData = new BinanceTaapiDataConverter(tradingPair);
        new CandlestickDataStream(binanceData.tradingPairB, intervalB);
    }

    public static TAIndicatorReader getIndicators() {
        return indicators;
    }
}

