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
        primaryStage.setTitle("JavaFX Test, window#1");
//        This(vv) or see line 52
//        primaryStage.setWidth(400);
//        primaryStage.setHeight(500);

        // Vertical box (~div in css/html)(parent node)
        VBox parent = new VBox();

        // Child nodes
        Label label1 = new Label("Label test");
        Label label2 = new Label("What is this??");
        label2.setRotate(20);

        // Image object
        Image image1 = new Image("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/672ce926-18e3-4ce8-b83c-30b238435e68/d6pwl8f-40b45946-4bd9-47c9-92b7-805e51de95fa.gif?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOiIsImlzcyI6InVybjphcHA6Iiwib2JqIjpbW3sicGF0aCI6IlwvZlwvNjcyY2U5MjYtMThlMy00Y2U4LWI4M2MtMzBiMjM4NDM1ZTY4XC9kNnB3bDhmLTQwYjQ1OTQ2LTRiZDktNDdjOS05MmI3LTgwNWU1MWRlOTVmYS5naWYifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6ZmlsZS5kb3dubG9hZCJdfQ.NreEqIYJsczIIMjooN9jcwHz9PPsHCkrUl0PvIVq7D8");

        // Image view, description unnecessary..
        ImageView imageView = new ImageView(image1);
        // This(^^) or that(vv) to add pics (image object or link)
        ImageView image2 = new ImageView("https://microenterprisedotblog.files.wordpress.com/2017/05/circle-256.gif");

        Label imageLabel = new Label("Help label", image2);
        imageLabel.setTextFill(Color.web("#9042f5"));
        imageLabel.setFont(new Font("Cambria", 20));

        // Adding child nodes to parent node (view link on line 55)
        parent.getChildren().addAll(label1, imageLabel, label2);
//        parent.getChildren().add(imageView);

        // Adding VBox to scene
        // root of scene graph (https://docs.oracle.com/javafx/2/scenegraph/jfxpub-scenegraph.htm) which is root of content displayed inside stage(window)
        Scene scene1 = new Scene(parent, 400, 500);

        // Change how cursor appears
        scene1.setCursor(Cursor.CROSSHAIR);

        // Initializing new window
        VBox parent2 = new VBox();
        Stage stage2 = new Stage();
        stage2.setTitle("Second window");

        // Modality
        stage2.initModality(Modality.APPLICATION_MODAL);
        // Unable to interact with "primaryStage" until "stage2" is closed
        // At least that's what it's supposed to do on Windows.. On Linux it removes everything except "x" for closing on "stage2" and
        // makes "x" on "primaryStage" unable to click
        // TL;DR doesn't even matter, just showing off pussybilites

        // Adding hyperlinks
        Hyperlink link = new Hyperlink("Click Me (this time for real), and then watch console Boii");

        // On event do something
        link.setOnAction(e ->{
            System.out.println("The link was smashed *bites lip* *looks you disturbingly*");
        });
        // This(^^) or that(vv aka lambda expression (arrow function in JS)) to add pics (image object or link)
//        link.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                System.out.println("The link was smashed *bites lip* *looks you disturbingly*");
//            }
//        });

        // Just props for further walkthrough
        Label label3 = new Label("Link not smashed");
        Image image3 = new Image("https://ih1.redbubble.net/image.1012639807.7285/flat,128x128,075,f-pad,128x128,f8f8f8.jpg");
        ImageView imageView2 = new ImageView("https://preview.redd.it/t903v2u23hnz.png?auto=webp&s=c681b40ee1bc2d4a61080001ee722e859c6c7a59");
        Hyperlink link2 = new Hyperlink("Smack me again pls", imageView2);

        // On event do something
        link2.setOnAction(e ->{
            imageView2.setImage(image3);
            label3.setText("I've been smacked enough for today");
            link2.setText("UwU, You've finally smashed me");
        });

        // Adding child nodes to parent node
        parent2.getChildren().addAll(link, link2, label3);

        // Adding VBox to scene
        Scene scene2 = new Scene(parent2, 400, 500);

        // Adding scene to stage
        primaryStage.setScene(scene1);
        stage2.setScene(scene2);

        // Showing stage
        stage2.show();
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}