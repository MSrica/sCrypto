package GUI.layouts;

import GUI.MainGUI;

import api_requests.CandlestickDataStream;
import api_requests.CandlesticksCache;
import api_requests.TAIndicatorRequest;
import com.binance.api.client.domain.market.CandlestickInterval;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tools.BinanceTaapiDataConverter;
import tools.CandlestickEventToCandlestickConverter;
import tools.TAIndicatorReader;

import com.binance.api.client.domain.event.CandlestickEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HomeLayout {

    final int WINDOW_SIZE = 10;
    public static ScheduledExecutorService scheduledExecutorService;

    //title pane
    Label logo = new Label("sCrypto");
    //sidebar menu
    Label sidebar = new Label("SideBar");
    Label tradingPairLabel = new Label("TradingPair selection:");
    ComboBox<String> tradingPairSelectionBox = new ComboBox<String>();
    Label intervalLabel = new Label("Interval selection:");
    ComboBox<String> intervalSelectionBox = new ComboBox<String>();
    Button sendRequestButton = new Button("Send request");
    Button logoutButton = new Button("Logout");

    public static TAIndicatorReader indicators;

    public static String tradingPair;
    public static String interval;

    CandlestickDataStream instance;

    public VBox homeScreenLayout() {
        VBox layout = new VBox(50);
        BorderPane pane = new BorderPane();

        //title pane
        HBox titleHBox = new HBox(50);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.getChildren().addAll(logo);

        //sidebar menu
        VBox sideBarVBox = new VBox(20);
        sideBarVBox.setAlignment(Pos.TOP_CENTER);

        tradingPairSelectionBox.setValue(MainGUI.prop.getPropertyString("tradingPair").equals("") ? "" : MainGUI.prop.getPropertyString("tradingPair"));

        tradingPairSelectionBox.getItems().add("BTC/USDT");
//        tradingPairSelectionBox.getItems().add("second currency");
//        tradingPairSelectionBox.getItems().add("third currency");

        tradingPairSelectionBox.setOnAction((event) -> {
            int selectedIndex = tradingPairSelectionBox.getSelectionModel().getSelectedIndex();
            Object selectedItem = tradingPairSelectionBox.getSelectionModel().getSelectedItem();   //send to strategy
            System.out.println("TI: " + selectedIndex + " : " + selectedItem);

            MainGUI.prop.setPropertyString("tradingPair", (String) selectedItem);
        });

        intervalSelectionBox.setValue(MainGUI.prop.getPropertyString("interval").equals("") ? "" : MainGUI.prop.getPropertyString("interval"));

        //"1m", "3m", "5m", "15m", "30m", "1h", "2h", "4h", "6h", "8h", "12h", "1d", "3d", "1w", "1M"
        intervalSelectionBox.getItems().addAll("1m", "3m", "5m", "15m", "30m", "1h", "2h", "4h", "6h", "8h", "12h", "1d", "3d", "1w", "1M");

        intervalSelectionBox.setOnAction((event) -> {
            int selectedIndex = intervalSelectionBox.getSelectionModel().getSelectedIndex();
            Object selectedItem = intervalSelectionBox.getSelectionModel().getSelectedItem();   //send to strategy
            System.out.println("Interval: " + selectedIndex + " : " + selectedItem);

            MainGUI.prop.setPropertyString("interval", (String) selectedItem);
        });

        sendRequestButton.setOnAction(e -> {
            /////////////////////////////////
            tradingPair = tradingPairSelectionBox.getSelectionModel().getSelectedItem();
            interval = intervalSelectionBox.getSelectionModel().getSelectedItem();

            new TAIndicatorRequest(tradingPair, interval);

            // Request MACD and MA200 indicators
            indicators = new TAIndicatorReader();

            ////////////////////////////////////// Candlesticks stream //////////////////////////////////////
            BinanceTaapiDataConverter binanceData = new BinanceTaapiDataConverter(tradingPair, interval);
            instance = new CandlestickDataStream(binanceData.tradingPairB, binanceData.intervalB);
            //////////////////////////////////

            System.out.println(intervalSelectionBox.getSelectionModel().getSelectedItem());
            System.out.println(tradingPairSelectionBox.getSelectionModel().getSelectedItem());
        });

        logoutButton.setOnAction(e -> {
            MainGUI.prop.setPropertyString("username", "");
            MainGUI.prop.setPropertyString("password", "");
            MainGUI.prop.setPropertyString("logged", "false");
            MainGUI.prop.setPropertyString("tradingPair", "");
            MainGUI.prop.setPropertyString("interval", "");
            //clear the comboboxes since this user is not here anymore
            intervalSelectionBox.getSelectionModel().clearSelection();
            tradingPairSelectionBox.getSelectionModel().clearSelection();
            //try to close the client
            System.exit(0); //shut down entire program

//            MainGUI.getWindow().setScene(MainGUI.LoginScreen);
        });

        sideBarVBox.getChildren().addAll(sidebar, tradingPairLabel, tradingPairSelectionBox, intervalLabel, intervalSelectionBox, sendRequestButton, logoutButton);

        //mock graph
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time/m");
        yAxis.setLabel("Value");

        yAxis.setAutoRanging(false);

        //creating the line chart with two axis created above
        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("CandlestickCache Info");

        //chartB.prefHeightProperty().bind(chartA.heightProperty())
        lineChart.prefHeightProperty().bind(MainGUI.getWindow().heightProperty());
        lineChart.prefWidthProperty().bind(MainGUI.getWindow().widthProperty());

        //defining a series to display data
        XYChart.Series<String, Number> highSeries = new XYChart.Series<>();
        highSeries.setName("high val");
        XYChart.Series<String, Number> lowSeries = new XYChart.Series<>();
        lowSeries.setName("low val");
        XYChart.Series<String, Number> openSeries = new XYChart.Series<>();
        openSeries.setName("open val");
        XYChart.Series<String, Number> closeSeries = new XYChart.Series<>();
        closeSeries.setName("close val");

        // add series to chart
        lineChart.getData().addAll(closeSeries, highSeries, lowSeries, openSeries);

        // this is used to display time in HH:mm:ss format
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        // setup a scheduled executor to periodically put data into the chart
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        // put data onto graph per second
        scheduledExecutorService.scheduleAtFixedRate(() -> {


            //Integer random = ThreadLocalRandom.current().nextInt(100);   //tu gre value od binancea ili čega god
            // Update the chart
            Platform.runLater(() -> {
                String highVal;
                String lowVal;
                String openVal;
                String closeVal;
                if(instance.event != null) {
//                if(CandlesticksCache.getUpdateCandlestick() != null) {
//                    highVal = CandlesticksCache.getUpdateCandlestick().getHigh();
//                    lowVal = CandlesticksCache.getUpdateCandlestick().getLow();
//                    openVal = CandlesticksCache.getUpdateCandlestick().getOpen();
//                    closeVal = CandlesticksCache.getUpdateCandlestick().getClose();
                    highVal = instance.event.getHigh();
                    lowVal = instance.event.getLow();
                    openVal = instance.event.getOpen();
                    closeVal = instance.event.getClose();
                } else {
                    highVal = "0";
                    lowVal = "0";
                    openVal = "0";
                    closeVal = "0";
                }
                Double highValue = Double.parseDouble(highVal);
                Double lowValue = Double.parseDouble(lowVal);
                Double openValue = Double.parseDouble(openVal);
                Double closeValue = Double.parseDouble(closeVal);

                //dynamic bounds
                double max = Math.max(Math.max(highValue, lowValue), Math.max(openValue, closeValue));
                double min = Math.min(Math.min(highValue, lowValue), Math.min(openValue, closeValue));
                yAxis.setUpperBound(max + 10);
                yAxis.setLowerBound(min - 10);

                // get current time
                Date now = new Date();
                // put random number with current time
                highSeries.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), highValue));
                lowSeries.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), lowValue));
                openSeries.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), openValue));
                closeSeries.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), closeValue));

                //so the graph doesn't get unreadably shitfaced looking
                if (highSeries.getData().size() > WINDOW_SIZE)
                    highSeries.getData().remove(0);
                if (lowSeries.getData().size() > WINDOW_SIZE)
                    lowSeries.getData().remove(0);
                if (openSeries.getData().size() > WINDOW_SIZE)
                    openSeries.getData().remove(0);
                if (closeSeries.getData().size() > WINDOW_SIZE)
                    closeSeries.getData().remove(0);
            });
        }, 0, 1, TimeUnit.SECONDS);

//        double[][] data = new double[][]{};
//
//        scheduledExecutorService.scheduleAtFixedRate(() -> {
//
//            Platform.runLater(() -> {
//                // DAY, OPEN, CLOSE, HIGH, LOW, AVERAGE
//                String open = null;
//                String close = null;
//                String high = null;
//                String low = null;
//                if(CandlesticksCache.getUpdateCandlestick() != null) {
//                    high = CandlesticksCache.getUpdateCandlestick().getHigh();
//                    low = CandlesticksCache.getUpdateCandlestick().getLow();
//                    open = CandlesticksCache.getUpdateCandlestick().getOpen();
//                    close = CandlesticksCache.getUpdateCandlestick().getClose();
//                }
//                Double highValue = Double.parseDouble(high);
//                Double lowValue = Double.parseDouble(low);
//                Double openValue = Double.parseDouble(open);
//                Double closeValue = Double.parseDouble(close);
//                Double average = highValue/lowValue;
//
//                //dynamic bounds
//                double max = Math.max(Math.max(highValue, lowValue), Math.max(openValue, closeValue));
//                double min = Math.min(Math.min(highValue, lowValue), Math.min(openValue, closeValue));
//                yAxis.setUpperBound(max + 10);
//                yAxis.setLowerBound(min - 10);
//
//                // get current time
//                Date now = new Date();
//
//                //get it into data i guess????
//            });
//        }, 0, 1, TimeUnit.SECONDS);

        pane.setTop(titleHBox);
        pane.setLeft(sideBarVBox);
        pane.setCenter(lineChart);

        layout.getChildren().addAll(pane);

        return layout;
    }
}
