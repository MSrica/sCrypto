import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class gui extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // stage (or window)
        primaryStage.setTitle("JavaFX Test, sCrypto");
//        This(vv) or see line 52
//        primaryStage.setWidth(400);
//        primaryStage.setHeight(500);

        // Vertical box (~div in css/html)(parent node)
        VBox sCrypto_main = new VBox();
        
    }


    public static void main(String[] args) {
        launch(args);
    }
}