package Client;

import Services.SceneSwitcher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        Client.getInstance();
        SceneSwitcher.showDialog("../FXML/SignIn.fxml", "Авторизация");

    }

    public static void main(String[] args) {
        launch(args);
    }

}
