package GUI.layouts;

import GUI.MainGUI;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginLayout {

    //components
    TextField username = new TextField();
    TextField password = new TextField();
    Button loginButton = new Button("Login");

    public VBox loginSceneLayout() {

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        loginButton.setOnAction(e -> {
            MainGUI.getWindow().setScene(MainGUI.HomeScreen);
        });

        layout.getChildren().addAll(username, password, loginButton);

        return layout;
    }
}