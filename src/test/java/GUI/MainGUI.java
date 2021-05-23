package GUI;

import GUI.layouts.HomeLayout;
import GUI.layouts.LoginLayout;
import api_test.BinanceTaapiTest;
import api_test.CandlesticksCache;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainGUI extends Application {

    //stage
    private static Stage window;

    public static Stage getWindow() {
        return window;
    }

    //scenes
    public static Scene LoginScreen;
    public static Scene HomeScreen;

    //layouts
    LoginLayout loginLayout = new LoginLayout();
    HomeLayout homeLayout = new HomeLayout();

    public static void main(String[] args) {
        BinanceTaapiTest test = new BinanceTaapiTest(); //launches binance stuff --> api_test
        launch(args); // launches gui application
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;
        window.setTitle("sCrypto_Home");

        LoginScreen = new Scene(loginLayout.loginSceneLayout(), 1000, 700);
        HomeScreen = new Scene(homeLayout.homeScreenLayout(), 1000, 700);

        //scene.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());
        //getClass().getResource("style.css").toString()
        //define css for each or same for both idgaf

        //LoginScreen.getStylesheets().add(getClass().getResource("/stylesheets/login_reg.css").toExternalForm());

        //za početak login screen directly, kašnje provjera ako je već ulogiran korisnik
        window.setScene(LoginScreen);
        window.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        homeLayout.scheduledExecutorService.shutdownNow();  //use this later    //stops the graph updater
    }
}
