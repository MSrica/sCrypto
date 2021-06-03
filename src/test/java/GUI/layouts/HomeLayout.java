package GUI.layouts;

import GUI.MainGUI;

import GUI.candlestickChart.AdvCandleStickChart;
import api_test.CandlesticksCache;
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
    Label tradingPairLabel = new Label("TI selection:");
    ComboBox<String> tradingPairSelectionBox = new ComboBox<String>();
    Label intervalLabel = new Label("interval selection:");
    ComboBox<String> intervalSelectionBox = new ComboBox<String>();
    Button sendRequestButton = new Button("Commit changes/Send request");   //TODO: needs implementation --> on press take interval and TI and give to strategy
    Button logoutButton = new Button("Logout");

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

        tradingPairSelectionBox.setValue(MainGUI.prop.getPropertyString("tradingPair"));

        tradingPairSelectionBox.getItems().add("BTC/USDT");
        tradingPairSelectionBox.getItems().add("second currency");
        tradingPairSelectionBox.getItems().add("third currency");

        tradingPairSelectionBox.setOnAction((event) -> {
            int selectedIndex = tradingPairSelectionBox.getSelectionModel().getSelectedIndex();
            Object selectedItem = tradingPairSelectionBox.getSelectionModel().getSelectedItem();   //send to strategy //TODO: first get the bitch out the fuck ove lambde jebu
            System.out.println("TI: " + selectedIndex + " : " + selectedItem);

            MainGUI.prop.setPropertyString("tradingPair", (String) selectedItem);
        });

        intervalSelectionBox.setValue(MainGUI.prop.getPropertyString("interval"));

        intervalSelectionBox.getItems().add("1min");
        intervalSelectionBox.getItems().add("5min");
        intervalSelectionBox.getItems().add("15min");
        intervalSelectionBox.getItems().add("1h");

        intervalSelectionBox.setOnAction((event) -> {
            int selectedIndex = intervalSelectionBox.getSelectionModel().getSelectedIndex();
            Object selectedItem = intervalSelectionBox.getSelectionModel().getSelectedItem();   //send to strategy
            System.out.println("Interval: " + selectedIndex + " : " + selectedItem);

            MainGUI.prop.setPropertyString("interval", (String) selectedItem);
        });

        sendRequestButton.setOnAction(e -> {
            //TODO: send request with interval and TI
            System.out.println(intervalSelectionBox.getSelectionModel().getSelectedItem());
            System.out.println(tradingPairSelectionBox.getSelectionModel().getSelectedItem());
        });

        logoutButton.setOnAction(e -> {
//            System.out.println("On logout output: " + MainGUI.prop.getPropertyString("username"));
            MainGUI.getWindow().setScene(MainGUI.LoginScreen);
        });

        sideBarVBox.getChildren().addAll(sidebar, tradingPairLabel, tradingPairSelectionBox, intervalLabel, intervalSelectionBox, sendRequestButton, logoutButton);


        //mock graph
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();    //TODO: dynamically set range because kinda shitty --> take from all vals min and max --> min/max + set val are new bounds
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
        highSeries.setName("low val");
        XYChart.Series<String, Number> openSeries = new XYChart.Series<>();
        highSeries.setName("open val");
        XYChart.Series<String, Number> closeSeries = new XYChart.Series<>();
        highSeries.setName("close val");

        // add series to chart
        lineChart.getData().addAll(highSeries, lowSeries, openSeries, closeSeries);

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
                if(CandlesticksCache.getUpdateCandlestick() != null) {
                    highVal = CandlesticksCache.getUpdateCandlestick().getHigh();
                    lowVal = CandlesticksCache.getUpdateCandlestick().getLow();
                    openVal = CandlesticksCache.getUpdateCandlestick().getOpen();
                    closeVal = CandlesticksCache.getUpdateCandlestick().getClose();
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
