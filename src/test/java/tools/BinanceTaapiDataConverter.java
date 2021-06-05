package tools;

import com.binance.api.client.domain.market.CandlestickInterval;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class BinanceTaapiDataConverter {

    public String tradingPairB;
    public CandlestickInterval intervalB;


    public BinanceTaapiDataConverter(String tradingPair, String interval){

        // Adjusts trading pair to fit binanace-java-api
        this.tradingPairB = tradingPair.replaceAll("/", "").toLowerCase();

        CandlestickInterval intervalB;
        switch(interval) {
            case "1m":
                intervalB = CandlestickInterval.ONE_MINUTE;
                break;
            case "3m":
                intervalB = CandlestickInterval.THREE_MINUTES;
                break;
            case "5m":
                intervalB = CandlestickInterval.FIVE_MINUTES;
                break;
            case "15m":
                intervalB = CandlestickInterval.FIFTEEN_MINUTES;
                break;
            case "30m":
                intervalB = CandlestickInterval.HALF_HOURLY;
                break;
            case "1h":
                intervalB = CandlestickInterval.HOURLY;
                break;
            case "2h":
                intervalB = CandlestickInterval.TWO_HOURLY;
                break;
            case "4h":
                intervalB = CandlestickInterval.FOUR_HOURLY;
                break;
            case "6h":
                intervalB = CandlestickInterval.SIX_HOURLY;
                break;
            case "8h":
                intervalB = CandlestickInterval.EIGHT_HOURLY;
                break;
            case "12h":
                intervalB = CandlestickInterval.TWELVE_HOURLY;
                break;
            case "1d":
                intervalB = CandlestickInterval.DAILY;
                break;
            case "3d":
                intervalB = CandlestickInterval.THREE_DAILY;
                break;
            case "1w":
                intervalB = CandlestickInterval.WEEKLY;
                break;
            case "1M":
                intervalB = CandlestickInterval.MONTHLY;
                break;
            default:
                intervalB = CandlestickInterval.FIVE_MINUTES;
                break;

        }
        this.intervalB = intervalB;


    }
}