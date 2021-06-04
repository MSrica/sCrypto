package GUI;

import GUI.layouts.ChartTest;
import GUI.layouts.HomeLayout;
import GUI.layouts.LoginLayout;
import GUI.preferences.LocalProperties;
import api_test.BinanceTaapiTest;
import api_test.CandlesticksCache;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

public class MainGUI extends Application {

    //stage
    private static Stage window;
    public static Stage getWindow() {
        return window;
    }

    //scenes
    public static Scene LoginScreen;
    public static Scene HomeScreen;
    public static Scene ChartScreen;

    //layouts
    LoginLayout loginLayout = new LoginLayout();
    HomeLayout homeLayout = new HomeLayout();
    ChartTest chartLayout = new ChartTest();

    //stuff
    static BinanceTaapiTest test;
    public static LocalProperties prop;

    public static void main(String[] args) {
        prop  = new LocalProperties();
        test = new BinanceTaapiTest(); //launches binance stuff --> api_test
        launch(args); // launches gui application
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;
        window.setTitle("sCrypto_Home");

        LoginScreen = new Scene(loginLayout.loginSceneLayout(), 1000, 700);
        HomeScreen = new Scene(homeLayout.homeScreenLayout(), 1000, 700);
        ChartScreen = new Scene(chartLayout.chartScreenLayout(), 1000, 700);

        //scene.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());
        //getClass().getResource("style.css").toString()
        //define css for each or same for both idgaf

        LoginScreen.getStylesheets().add(getClass().getResource("/stylesheets/login_reg.css").toExternalForm());
        HomeScreen.getStylesheets().add(getClass().getResource("/stylesheets/home.css").toExternalForm());

        //testing the chart
        ChartScreen.getStylesheets().add(getClass().getResource("/stylesheets/candlestick_chart.css").toExternalForm());
//        window.setScene(ChartScreen);

        //za početak login screen directly, kašnje provjera ako je već ulogiran korisnik
        window.setScene(LoginScreen);
        window.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        homeLayout.scheduledExecutorService.shutdownNow();  //use this later    //stops the graph update
    }
}
