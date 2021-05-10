package tools;

public class CheckTrend {
    public boolean trend;

    // Sets .trend to "true" if price is in up-trend, and to "false" if price is in down-trend
    public CheckTrend(String candlestick, double maValue){
        if (Double.parseDouble(candlestick)> maValue){
            this.trend = true;
        } else {
            this.trend = false;
        }
    }
}
