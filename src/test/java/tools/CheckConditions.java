package tools;


import GUI.MainGUI;
import GUI.layouts.HomeLayout;
import api_requests.TAIndicatorRequest;
//import strategy.MainTmp;

public class CheckConditions{
    private static boolean orderPlaced = false;
    private static boolean macdHistNegative = false;
    private static boolean macdHistPositive = false;

    public CheckConditions(CandlestickEventToCandlestickConverter asset) {
        // First check if price is in an up-trend
        if  (CheckMA(asset)) {
            // Then check if MACD is below zero line
            if (CheckMACDBelowZero(asset.macdValue)) {
                // Lastly, check if there is MACD convergence/divergence
                if (CheckVergence(asset.macdHist)) {
                    if (orderPlaced) {
                        // Function call  for selling certain asset
                        // sell();
                        MarketSell.sell();
                        // Sets: .orderPlaced to "false" so that it can look out for buying next
                        //       .macdHistPositive to "false" for resetting CheckVergence()
                        orderPlaced = false;
                        macdHistPositive = false;
                        // "place" fake sell order
                        System.out.println("//// Fake sell order placed. ////" + System.currentTimeMillis());
                    } else {
                        // Function call for buying certain asset
                        // buy();
                        MarketBuy.buy();
                        // Sets  .orderPlaced to "true" so that it can look out for selling next
                        //       .macdHistNegative to "false" for resetting CheckVergence()
                        orderPlaced = true;
                        macdHistNegative = false;

                        // "place" fake buy order
                        System.out.println("//// Fake buy order placed. ////" + System.currentTimeMillis());
                    }
                }
            }
        }

        // Request MACD and MA200 indicators again
        new TAIndicatorRequest(HomeLayout.tradingPair, HomeLayout.interval);
        HomeLayout.indicators = new TAIndicatorReader();

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

    // Checks if MACD diVergence/conVergence happened
    private boolean CheckVergence(Double macdHist) {
        // Check divergence
        if (orderPlaced) {
            // First look for positive macdHist value
            if (macdHist > 0 || macdHistPositive) {
                macdHistPositive = true;
                // Then look for negative macdHist value
                // If both values are true, MACD divergence happened
                return macdHist < 0;
            }
        // Check convergence
        } else {
            // First look for negative macdHist value
            if (macdHist < 0 || macdHistNegative) {
                macdHistNegative = true;
                // Then look for positive macdHist value
                // If both values are true, MACD convergence happened
                return macdHist > 0;
            }
        }
        return false;
    }
}
