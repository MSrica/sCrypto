package tools;

import GUI.MainGUI;
import GUI.layouts.HomeLayout;
import com.binance.api.client.domain.event.CandlestickEvent;
//import strategy.MainTmp;

public class CandlestickEventToCandlestickConverter {
    public Long openTime;
    public Float open;
    public Float high;
    public Float low;
    public Float close;
    public Float volume;
    public Long closeTime;
    public Double macdValue;
    public Double macdSignal;
    public Double macdHist;
    public Double maValue;

    public CandlestickEventToCandlestickConverter(CandlestickEvent event) {
        this.openTime = event.getOpenTime();
        this.open = Float.parseFloat(event.getOpen());
        this.high = Float.parseFloat(event.getHigh());
        this.low = Float.parseFloat(event.getLow());
        this.close = Float.parseFloat(event.getClose());
        this.volume = Float.parseFloat(event.getVolume());
        this.closeTime = event.getCloseTime();
//        this.macdValue = MainTmp.getIndicators().macdValue;
//        this.macdSignal = MainTmp.getIndicators().macdSignal;
//        this.macdHist = MainTmp.getIndicators().macdHist;
//        this.maValue = MainTmp.getIndicators().maValue;
        this.maValue = HomeLayout.indicators.maValue;
        this.macdSignal = HomeLayout.indicators.macdSignal;
        this.macdHist = HomeLayout.indicators.macdHist;
        this.macdValue = HomeLayout.indicators.macdValue;

        // Check if given parameters meets required conditions
        new CheckConditions(this);
    }
}
