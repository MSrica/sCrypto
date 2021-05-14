package tools;


import api_requests.TAIndicatorRequest;
import strategy.MainTmp;

public class CheckConditions{
    private static boolean orderPlaced = false;
    private static boolean macdHistNegative = false;
    private static boolean macdHistPositive = false;

    public CheckConditions(CandlestickEventToCandlestickConverter asset) {
        // First check if price is in an up-trend
        if  (CheckMA(asset)) {
            // Then check if MACD is below zero line
            if (CheckMACDBelowZero(asset.macdValue)) {
                // Lastly, check if there is MACD convergence
                if (CheckConvergence(asset)) {
                    if (orderPlaced) {
                        // Function call  for selling certain asset
                        // sell();

                        // Sets .orderPlaced to "false" so that it can look out for selling next
                        orderPlaced = false;

                        // "place" fake sell order
                        System.out.println("//// Fake sell order placed. ////" + System.currentTimeMillis());
                    } else {
                        // Function call for buying certain asset
                        // buy();

                        // Sets .orderPlaced to "true" so that it can look out for selling next
                        orderPlaced = true;

                        // "place" fake buy order
                        System.out.println("//// Fake buy order placed. ////" + System.currentTimeMillis());
                    }
                }
            }
        }
        // Request MACD and MA200 indicators again
        new TAIndicatorRequest(MainTmp.tradingPair, MainTmp.taapiData.interval);
        MainTmp.indicators = new TAIndicatorReader();

//        System.out.println("MACD:\n- value: " + asset.macdValue + "\n- signal: " + asset.macdSignal + "\n- hist: " + asset.macdHist + "\nMA200:\n- value: " + asset.maValue);
//        System.out.println(MainTmp.tradingPair + "\n- high: " + asset.high + "\n- open: " + asset.open + "\n- close: " + asset.close + "\n- open: " + asset.low);

        System.out.println("Trend: " + (CheckMA(asset) ? "up":"down"));
        System.out.println("macdBelowZero: " + (CheckMACDBelowZero(asset.macdValue) ? "yes":"no"));

        System.out.println("orderPlaced: " + orderPlaced);
        System.out.println("macdHistNegative: " + macdHistNegative);
        System.out.println("macdHistPositive: " + macdHistPositive);

        System.out.println("//////////////////////////////////////////");
    }

    // Checks if the MACD indicator is below zero line
    private boolean CheckMACDBelowZero(Double macdValue) {
        return macdValue < 0;
    }

    // Checks if the price is in an up-trend
    private boolean CheckMA(CandlestickEventToCandlestickConverter asset) {
        return asset.close > asset.maValue;
    }

    // Checks if MACD convergence happened
    private boolean CheckConvergence(CandlestickEventToCandlestickConverter asset) {
        // First look for negative macdHist value
        if (asset.macdHist < 0 || macdHistNegative) {
            macdHistNegative = true;
            // Then look for positive macdHist value
            if (asset.macdHist > 0) {
                macdHistPositive = true;
            }
        }
        // If both values are true, MACD convergence happened
        return macdHistNegative && macdHistPositive;
    }
}
