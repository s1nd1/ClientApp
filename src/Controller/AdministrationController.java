package Controller;

import Client.*;
import SerializableMessageClases.User;
import SerializableMessageClases.Message;
import Services.EditingCellFieldText;
import Services.SceneSwitcher;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class AdministrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane pnlAdministration;

    @FXML
    private JFXButton btnAddUser;

    @FXML
    private JFXButton btnDelUser;

    @FXML
    private TableView<User> tblUser;

    @FXML
    private TableColumn<User, String> UserFIO;

    @FXML
    private TableColumn<User, String> UserContacts;

    @FXML
    private TableColumn<User, String> UserAge;

    @FXML
    private TableColumn<User, String> UserLogin;

    @FXML
    private TableColumn<User, String> UserPassword;

    @FXML
    private TableColumn<User, String> UserAccountType;

    @FXML
    void AddUser(ActionEvent event) {
        SceneSwitcher.showDialog("../FXML/AddingAccount.fxml", "Добавление пользователя");
    }

    @FXML
    void delUser(ActionEvent event) {
        if (!tblUser.getSelectionModel().isEmpty()){
            int selectedIdx = tblUser.getSelectionModel().getSelectedIndex();
            User user = tblUser.getItems().get(selectedIdx);
            Client.sendMessage(new Message(user, MessageType.DEL_USER.name()));
            Client.receiveMessage();

            tblUser.getItems().remove(selectedIdx);
        }
    }

//    @FXML
//    void changeUserContacts(TableColumn.CellEditEvent<User, String> event) {
//        TablePosition<User,String> position = event.getTablePosition();
//
//        String newFullName = event.getNewValue();
//
//        int row = position.getRow();
//        User user = event.getTableView().getItems().get(row);
//
//        user.setContacts(newFullName);
//    }

    public void updateTableDate(){

        List<User> updateUserList = new ArrayList<>(tblUser.getItems().size());

        updateUserList.addAll(tblUser.getItems());
        Client.sendMessage(new Message(updateUserList, MessageType.UPDATE_USER.name()));
        Client.receiveMessage();
    }

    public void getTableDate(){
        String typeGet = "";
        Client.sendMessage(new Message(typeGet, MessageType.GET_USER.name()));
        List<User> userList = (List<User>) Client.receiveMessage();

        ObservableList<User> data = FXCollections.observableArrayList(userList);
        tblUser.setItems(data);

    }


    @FXML
    void initialize() {

//        // ==== FULL NAME (TEXT FIELD) ===
//        fullNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
//
//        fullNameCol.setCellFactory(TextFieldTableCell.<Person> forTableColumn());
//
//        fullNameCol.setMinWidth(200);
//
//        // On Cell edit commit (for FullName column)
//        fullNameCol.setOnEditCommit((CellEditEvent<Person, String> event) -> {
//            TablePosition<Person, String> pos = event.getTablePosition();
//
//            String newFullName = event.getNewValue();
//
//            int row = pos.getRow();
//            Person person = event.getTableView().getItems().get(row);
//
//            person.setFullName(newFullName);
//        });

        getTableDate();

        tblUser.setEditable(true);

        UserFIO.setCellValueFactory(new PropertyValueFactory<User, String>("FIO"));
        UserContacts.setCellValueFactory(new PropertyValueFactory<User, String>("contacts"));
        UserAge.setCellValueFactory(new PropertyValueFactory<User, String>("age"));
        UserLogin.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        UserPassword.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        UserAccountType.setCellValueFactory(new PropertyValueFactory<User, String>("type"));


        UserFIO.setOnEditCommit((TableColumn.CellEditEvent<User, String> t) -> {
            ((User) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setFIO(t.getNewValue());
            updateTableDate();
        });

        UserContacts.setOnEditCommit((TableColumn.CellEditEvent<User, String> t) -> {
            ((User) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setContacts(t.getNewValue());
            updateTableDate();
        });

        UserPassword.setOnEditCommit((TableColumn.CellEditEvent<User, String> t) -> {
            ((User) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setPassword(t.getNewValue());
            updateTableDate();
        });

        Callback<TableColumn<User, String>, TableCell<User, String>> cellFactory
                = (TableColumn<User, String> p) -> new EditingCellFieldText<User>();

        UserFIO.setCellFactory(cellFactory);
        UserContacts.setCellFactory(cellFactory);
        UserPassword.setCellFactory(cellFactory);

    }

}

