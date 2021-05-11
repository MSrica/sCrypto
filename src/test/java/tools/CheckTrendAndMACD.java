package tools;

public class CheckTrendAndMACD {
    public boolean trend;
    public boolean macdBelowZero;

    // Sets .trend to "true" if price is in up-trend (above 200MA), and to "false" if price is in down-trend (below 200MA)
    // Sets .macdBelowZero to "true" if MACD is below zero line, and to "false" if MACD is above zero line
    public CheckTrendAndMACD(String candlestick, TAIndicatorReader indicators) {
        this.trend = Double.parseDouble(candlestick) > indicators.maValue;

        this.macdBelowZero = indicators.macdValue < 0;
    }
}
