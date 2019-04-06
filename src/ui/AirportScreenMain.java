package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Class
public class AirportScreenMain extends Application {

    // Methods
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("AirportScreenGUI.fxml"));
        primaryStage.setTitle("Airport Screen");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    // Main method
    public static void main(String[] args) {
        launch(args);
    }
}
