package Controller;

import Services.SceneSwitcher;
import Services.StyleService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import SerializableMessageClases.User;
import Client.Client;
import SerializableMessageClases.Message;
import Client.MessageType;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXPasswordField password_field;

    @FXML
    private JFXButton authSignInButton;

    @FXML
    private JFXTextField login_field;

    @FXML
    private JFXButton regButton;

    @FXML
    void initialize() {

        authSignInButton.setOnAction(event -> {
            Client.getInstance();

            if(!login_field.getText().equals("")|| !password_field.getText().equals("")) {

                User user = new User();
                user.setLogin(login_field.getText());
                user.setPassword(password_field.getText());

                Message message = new Message();
                message.setMessage(user);
                message.setTypeMessage(MessageType.SIGN_IN_USER.name());

                Client.sendMessage(message);

                String answer = (String) Client.receiveMessage();

                if(answer.equals("admin")){
                    authSignInButton.getScene().getWindow().hide();
                    SceneSwitcher.showDialog("../FXML/MainWindow.fxml", "Система управления бюджетом. Администратор");
                    Client.setAccountLogin(user.getLogin());
                }
                else{
                    if(answer.equals("user")){
                        authSignInButton.getScene().getWindow().hide();
                        SceneSwitcher.showDialog("../FXML/MainWindowUser.fxml", "Система управления бюджетом. Пользователь");
                        Client.setAccountLogin(user.getLogin());
                    }
                    else {
                        login_field.setStyle(StyleService.textFieldErrorStyle);
                        password_field.setStyle(StyleService.textFieldErrorStyle);
                    }
                }
            }
        });
        regButton.setOnAction(event -> {
            SceneSwitcher.showDialog("../FXML/SignUp.fxml", "Регистрация нового пользователя");
        });
    }
}
