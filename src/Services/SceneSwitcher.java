package Services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {
    public static Node loadNode(String path){
        Node node = null;
        try {
            node = FXMLLoader.load(SceneSwitcher.class.getResource(path));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }

    public static void showDialog(String path, String title){
        Parent root = null;
        try {
            root = FXMLLoader.load(SceneSwitcher.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();

    }

    public static void showAllertWarningWindow(String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void showAllertErrorWindow(String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
