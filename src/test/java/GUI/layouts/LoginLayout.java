package GUI.layouts;

import GUI.MainGUI;
import GUI.preferences.LocalProperties;
import db.Constants;
import db.Encryption;
import db.User;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static db.Login.getData;
import static db.Registration.addData;

public class LoginLayout {

    //components
    TextField loginUsername = new TextField();
    PasswordField loginPassword = new PasswordField();
    Button loginButton = new Button("Login");

    Label loginResponse = new Label("");

    TextField registerUsername = new TextField();
    TextField registerEmail = new TextField();
    PasswordField registerPassword = new PasswordField();
    PasswordField apiKey = new PasswordField();
    PasswordField apiSecret = new PasswordField();
    PasswordField taapiKey = new PasswordField();
    Button registerButton = new Button("Register");

    Label registerResponse = new Label("");

    public HBox loginSceneLayout() {

        //main HBox za login livo i reg desno
        HBox mainLayout = new HBox(40);

        //login layout
        //////////////////////////////////////////////
        VBox loginLayout = new VBox(20);
        loginLayout.setAlignment(Pos.CENTER);

        //input fields
        loginUsername.setPromptText("Please input your username");
        loginPassword.setPromptText("Please input your password");

        loginUsername.setMaxHeight(30);
        loginUsername.setMinHeight(30);
        loginUsername.setMinWidth(300);
        loginUsername.setMaxWidth(300);
        loginUsername.setPrefHeight(Region.USE_COMPUTED_SIZE);
        loginPassword.setMaxHeight(30);
        loginPassword.setMinHeight(30);
        loginPassword.setMinWidth(300);
        loginPassword.setMaxWidth(300);
        loginPassword.setPrefHeight(Region.USE_COMPUTED_SIZE);


        loginButton.setOnAction(e -> {
            User user = new User();
            //validation
            if (loginUsername.getText().isEmpty() || loginPassword.getText().isEmpty()) {
                loginResponse.setText("Username or password cannot be empty");
            } else {
                loginResponse.setText("");
                //TODO: check with db
                user.username = loginUsername.getText();
                try {
                    user.password = Encryption.getEncryptedBytes(loginPassword.getText(), Constants.salt);
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    System.out.println(noSuchAlgorithmException);
                } catch (InvalidKeySpecException invalidKeySpecException) {
                    System.out.println(invalidKeySpecException);
                }

                try {
                    if (getData(user)) {
                        MainGUI.prop.setPropertyString("username", loginUsername.getText());
                        MainGUI.prop.setPropertyString("logged", "true");
                        MainGUI.getWindow().setScene(MainGUI.HomeScreen);
                    } else {
                        loginResponse.setText("Couldn't login user");
                    }
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    System.out.println(noSuchAlgorithmException);
                } catch (InvalidKeySpecException invalidKeySpecException) {
                    System.out.println(invalidKeySpecException);
                }
            }
        });

        loginLayout.getChildren().addAll(loginUsername, loginPassword, loginResponse, loginButton);
        ///////////////////////////////////////////////////////////////////

        //reg layout
        //////////////////////////////////////////////////////////////////
        VBox registerLayout = new VBox(20);
        registerLayout.setAlignment(Pos.CENTER);

        //input fields
        registerUsername.setPromptText("Please input your username");
        registerPassword.setPromptText("Please input your password");
        apiKey.setPromptText("Please input your apiKey");
        apiSecret.setPromptText("Please input your apiSecret");
        taapiKey.setPromptText("Please input your taapiKey");
        registerEmail.setPromptText("Please input your email");

        registerUsername.setMaxHeight(30);
        registerUsername.setMinHeight(30);
        registerUsername.setMinWidth(300);
        registerUsername.setMaxWidth(300);
        registerUsername.setPrefHeight(Region.USE_COMPUTED_SIZE);
        registerPassword.setMaxHeight(30);
        registerPassword.setMinHeight(30);
        registerPassword.setMinWidth(300);
        registerPassword.setMaxWidth(300);
        registerPassword.setPrefHeight(Region.USE_COMPUTED_SIZE);
        apiKey.setMaxHeight(30);
        apiKey.setMinHeight(30);
        apiKey.setMinWidth(300);
        apiKey.setMaxWidth(300);
        apiKey.setPrefHeight(Region.USE_COMPUTED_SIZE);
        apiSecret.setMaxHeight(30);
        apiSecret.setMinHeight(30);
        apiSecret.setMinWidth(300);
        apiSecret.setMaxWidth(300);
        apiSecret.setPrefHeight(Region.USE_COMPUTED_SIZE);
        taapiKey.setMaxHeight(30);
        taapiKey.setMinHeight(30);
        taapiKey.setMinWidth(300);
        taapiKey.setMaxWidth(300);
        taapiKey.setPrefHeight(Region.USE_COMPUTED_SIZE);
        registerEmail.setMaxHeight(30);
        registerEmail.setMinHeight(30);
        registerEmail.setMinWidth(300);
        registerEmail.setMaxWidth(300);
        registerEmail.setPrefHeight(Region.USE_COMPUTED_SIZE);


        registerButton.setOnAction(e -> {
            //create new user instance
            User user = new User();

            //validation
            if (registerUsername.getText().isEmpty() || registerPassword.getText().isEmpty() || apiKey.getText().isEmpty() || apiSecret.getText().isEmpty() || taapiKey.getText().isEmpty() || registerEmail.getText().isEmpty()) {
                registerResponse.setText("Username, password, apiKey, taapiKey, apiSecret or email cannot be empty");
            } else {
                registerResponse.setText("");
                user.username = registerUsername.getText();
                user.email = registerEmail.getText();
                try {
                    user.password = Encryption.getEncryptedBytes(registerPassword.getText(), Constants.salt);
                    user.taapiKey = Encryption.getEncryptedBytes(taapiKey.getText(), Constants.salt);
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    System.out.println(noSuchAlgorithmException);
                } catch (InvalidKeySpecException invalidKeySpecException) {
                    System.out.println(invalidKeySpecException);
                }
                user.apiKey = apiKey.getText();
                user.apiSecret = apiSecret.getText();

                try {
                    if (addData(user)) {
                        //add to local storage
                        MainGUI.prop.setPropertyString("username", registerUsername.getText());
                        MainGUI.prop.setPropertyString("logged", "true");

                        MainGUI.getWindow().setScene(MainGUI.HomeScreen);
                    } else {
                        registerResponse.setText("Couldn't register user");
                    }
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    System.out.println(noSuchAlgorithmException + "REG FAIL");
                    registerResponse.setText("Couldn't register user");
                } catch (InvalidKeySpecException invalidKeySpecException) {
                    System.out.println(invalidKeySpecException + "REG FAIL");
                    registerResponse.setText("Couldn't register user");
                }
            }

        });

        registerLayout.getChildren().addAll(registerUsername, registerEmail, registerPassword, apiKey, apiSecret, taapiKey, registerResponse, registerButton);
        /////////////////////////////////////////////////////////////////
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(new Separator(), loginLayout, new Separator(), registerLayout, new Separator());

        return mainLayout;
    }
}