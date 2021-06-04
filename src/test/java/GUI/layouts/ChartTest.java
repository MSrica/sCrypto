package GUI.layouts;

import GUI.candlestickChart.AdvCandleStickChart;
import javafx.scene.layout.HBox;

public class ChartTest {

    public HBox chartScreenLayout() {
        HBox layout = new HBox();
        layout.getChildren().add(new AdvCandleStickChart());

        return layout;
    }
}
