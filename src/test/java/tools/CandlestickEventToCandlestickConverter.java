package tools;

import com.binance.api.client.domain.event.CandlestickEvent;
import strategy.MainTmp;

public class CandlestickEventToCandlestickConverter {
    public Long openTime;
    public String open;
    public String high;
    public String low;
    public String close;
    public String volume;
    public Long closeTime;

    public CandlestickEventToCandlestickConverter(CandlestickEvent event) {
        this.openTime = event.getOpenTime();
        this.open = event.getOpen();
        this.high = event.getHigh();
        this.low = event.getLow();
        this.close = event.getClose();
        this.volume = event.getVolume();
        this.closeTime = event.getCloseTime();

        new MainTmp(this);
    }
}
