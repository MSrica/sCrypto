import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;

public class gui extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // stage (or window)
        VBox sCrypto = new VBox();
        primaryStage.setTitle("sCrypto");
        primaryStage.setScene(new Scene(sCrypto, 500, 500));


        sCrypto.setAlignment(Pos.CENTER);
        final Object[] request = {""};
        final String[] sPrice = {""};
        final String[] buyOrSell = {""};
        String[] cryptos = {"BTC", "ETH", "XRP", "LTC", "XMR"};
        Label lName = new Label("sCrypto");
        Label lCrypto = new Label("Crypto: ");
        ComboBox cCrypto = new ComboBox(FXCollections.observableArrayList(cryptos));
        Label lPrice = new Label("Price: ");
        TextField tPrice = new TextField();
        Button bBuy = new Button("BUY");
        Button bSell = new Button("SELL");
        EventHandler<ActionEvent> eBuy = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                request[0] = cCrypto.getValue();
                request[0] += "/USDT";
                sPrice[0] = tPrice.getText();
                buyOrSell[0] = "BUY";
                System.out.println(request[0] +sPrice[0] + buyOrSell[0]);
            }
        };
        EventHandler<ActionEvent> eSell = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                request[0] = cCrypto.getValue();
                request[0] += "/USDT";
                sPrice[0] = tPrice.getText();
                buyOrSell[0] = "SELL";
                System.out.println(request[0] +sPrice[0] + buyOrSell[0]);
            }
        };
        bBuy.setOnAction(eBuy);
        bSell.setOnAction(eSell);
        sCrypto.getChildren().addAll(lName, lCrypto, cCrypto, lPrice, tPrice, bBuy, bSell);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}