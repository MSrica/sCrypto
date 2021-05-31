package GUI.layouts;

import GUI.MainGUI;

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
        NumberAxis yAxis = new NumberAxis(20000, 40000, 100);    //TODO: dynamically set range because kinda shitty
        xAxis.setLabel("Time/m");
        //xAxis.setAnimated(false); // axis animations are removed
        yAxis.setLabel("Value");
        //yAxis.setAnimated(false); // axis animations are removed

        //creating the line chart with two axis created above
        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Graph");
        //lineChart.setAnimated(false); // disable animations

        //chartB.prefHeightProperty().bind(chartA.heightProperty())
        lineChart.prefHeightProperty().bind(MainGUI.getWindow().heightProperty());
        lineChart.prefWidthProperty().bind(MainGUI.getWindow().widthProperty());

        //defining a series to display data
        XYChart.Series<String, Number> highSeries = new XYChart.Series<>();
        highSeries.setName("candlestick high val");
        XYChart.Series<String, Number> lowSeries = new XYChart.Series<>();
        highSeries.setName("candlestick low val");

        // add series to chart
        lineChart.getData().addAll(highSeries, lowSeries);

        // this is used to display time in HH:mm:ss format
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        // setup a scheduled executor to periodically put data into the chart
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        // put dummy data onto graph per second
        scheduledExecutorService.scheduleAtFixedRate(() -> {

            //Integer random = ThreadLocalRandom.current().nextInt(100);   //tu gre value od binancea ili čega god
            // Update the chart
            Platform.runLater(() -> {
                String highVal;
                String lowVal;
                if(CandlesticksCache.getUpdateCandlestick() != null) {
                    highVal = CandlesticksCache.getUpdateCandlestick().getHigh();
                    lowVal = CandlesticksCache.getUpdateCandlestick().getLow();
                } else {
                    highVal = "0";
                    lowVal = "0";
                }
                Double highValue = Double.parseDouble(highVal);
                Double lowValue = Double.parseDouble(lowVal);

                // get current time
                Date now = new Date();
                // put random number with current time
                highSeries.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), highValue));
                lowSeries.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), lowValue));

                //so the graph doesn't get unreadably shitfaced looking
                if (highSeries.getData().size() > WINDOW_SIZE)
                    highSeries.getData().remove(0);
                if (lowSeries.getData().size() > WINDOW_SIZE)
                    lowSeries.getData().remove(0);
            });
        }, 0, 5, TimeUnit.SECONDS);

        pane.setTop(titleHBox);
        pane.setLeft(sideBarVBox);
        pane.setCenter(lineChart);

        layout.getChildren().addAll(pane);

        return layout;
    }
}
