package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Powerschool - ASD");
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Pane myPane = (Pane)myLoader.load();
        loginController controller = (loginController)myLoader.getController();
        controller.setPrevStage(primaryStage);
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
