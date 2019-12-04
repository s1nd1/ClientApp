package Controller;

import Client.Client;
import Client.MessageType;
import SerializableMessageClases.Message;
import SerializableMessageClases.User;
import Services.StyleService;
import Services.ValidateService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;

public class SignUpController {

    @FXML
    private JFXTextField fieldFIO;

    @FXML
    private JFXTextField fieldContacts;

    @FXML
    private JFXTextField fieldAge;

    @FXML
    private JFXTextField fieldLogin;

    @FXML
    private JFXPasswordField fieldPassword;

    @FXML
    private JFXButton btnAdd;

    @FXML
    void btnAddClicks(ActionEvent event) {

        if(!fieldLogin.getText().equals("") && !fieldPassword.getText().equals(""))  {
            User users = new User();
            users.setLogin(fieldLogin.getText());
            users.setPassword(fieldPassword.getText());
            users.setAge(Integer.parseInt(fieldAge.getText()));
            users.setContacts(fieldContacts.getText());
            users.setFIO(fieldFIO.getText());
            users.setType("user");

            Message message = new Message();
            message.setMessage(users);
            message.setTypeMessage(MessageType.ADD_USER.name());

            Client.sendMessage(message);
            Client.receiveMessage();

            fieldLogin.clear();
            fieldContacts.clear();
            fieldAge.clear();
            fieldFIO.clear();
            fieldPassword.clear();
            btnAdd.getScene().getWindow().hide();
        }
    }
    @FXML
    void initialize() {


        fieldAge.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if(!newValue.equals("")) {
                    if (!ValidateService.isAgeValid(newValue)) {
                        fieldAge.setStyle(StyleService.textFieldErrorStyle);
                        btnAdd.setDisable(true);
                    } else {
                        fieldAge.setStyle(StyleService.textFieldStyle);
                        btnAdd.setDisable(false);
                    }
                }
            }
        });

        fieldFIO.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if(!newValue.equals("")) {
                    if (ValidateService.isUserNameValid(newValue)) {
                        fieldFIO.setStyle(StyleService.textFieldStyle);
                        btnAdd.setDisable(false);
                    } else {
                        fieldFIO.setStyle(StyleService.textFieldErrorStyle);
                        btnAdd.setDisable(true);
                    }
                }
            }
        });

        fieldPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if(!newValue.equals("")) {
                    if (ValidateService.isPasswordValid(newValue)) {
                        fieldPassword.setStyle(StyleService.textFieldStyle);
                        btnAdd.setDisable(false);
                    } else {
                        fieldPassword.setStyle(StyleService.textFieldErrorStyle);
                        btnAdd.setDisable(true);
                    }
                }
            }
        });


        fieldLogin.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if(!newValue.equals("")) {

                    if (!ValidateService.isLoginValid(newValue)) {
                        fieldLogin.setStyle(StyleService.textFieldErrorStyle);
                        btnAdd.setDisable(true);

                    } else {
                        String typeGet = "";
                        Client.sendMessage(new Message(typeGet, MessageType.GET_USER.name()));
                        List<User> userList = (List<User>) Client.receiveMessage();

                        List<String> loginList = new ArrayList<>(userList.size());

                        for (User user : userList)
                            loginList.add(user.getLogin());

                        boolean isUniqueLogin = !loginList.contains(fieldLogin.getText());

                        if(!isUniqueLogin){
                            fieldLogin.setStyle(StyleService.textFieldErrorStyle);
                            fieldLogin.setText("error: логин (" + fieldLogin.getText() + ") занят");
                            btnAdd.setDisable(true);
                        }
                        else {
                            fieldLogin.setStyle(StyleService.textFieldStyle);
                            btnAdd.setDisable(false);
                        }

                    }
                }
            }
        });

    }
}
