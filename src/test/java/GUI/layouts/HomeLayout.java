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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class HomeLayout {

    final int WINDOW_SIZE = 10;
    public static ScheduledExecutorService scheduledExecutorService;

    //components
    Button logoutButton = new Button("Logout");
    //title pane
    Label logo = new Label("sCrypto");
    //sidebar menu
    Label sidebar = new Label("SideBar");

    public VBox homeScreenLayout() {
        VBox layout = new VBox();
        BorderPane pane = new BorderPane();

        //title pane
        HBox titleHBox = new HBox(50);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.getChildren().addAll(logo);

        //sidebar menu
        VBox sideBarVBox = new VBox(20);
        sideBarVBox.setAlignment(Pos.TOP_CENTER);
        sideBarVBox.getChildren().addAll(sidebar, logoutButton);

        //mock graph
        CategoryAxis xAxis = new CategoryAxis();    //od kud, do kud, increment
        NumberAxis yAxis = new NumberAxis(38000, 40000, 10);
        xAxis.setLabel("Time/m");
        xAxis.setAnimated(false); // axis animations are removed
        yAxis.setLabel("Value");
        yAxis.setAnimated(false); // axis animations are removed

        //creating the line chart with two axis created above
        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Graph");
        lineChart.setAnimated(false); // disable animations

        //defining a series to display data
        XYChart.Series<String, Number> highSeries = new XYChart.Series<>();
        highSeries.setName("highSeries");
        XYChart.Series<String, Number> lowSeries = new XYChart.Series<>();
        highSeries.setName("lowSeries");

        // add series to chart
        lineChart.getData().addAll(highSeries, lowSeries);

        // this is used to display time in HH:mm:ss format
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        // setup a scheduled executor to periodically put data into the chart
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        // put dummy data onto graph per second
        scheduledExecutorService.scheduleAtFixedRate(() -> {

            Integer random = ThreadLocalRandom.current().nextInt(100);   //tu gre value od binancea ili čega god
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

        //actions
        logoutButton.setOnAction(e -> {
            MainGUI.getWindow().setScene(MainGUI.LoginScreen);
        });

        pane.setTop(titleHBox);
        pane.setLeft(sideBarVBox);
        pane.setCenter(lineChart);

        layout.getChildren().addAll(pane);

        return layout;
    }
}
