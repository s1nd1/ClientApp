package Controller;

import Client.*;
import SerializableMessageClases.Balance;
import SerializableMessageClases.Message;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.util.List;

public class BalanceController {

    @FXML
    private Pane pnlBalance;

    @FXML
    private JFXButton btnAddMoney;

    @FXML
    private JFXTextField fieldName;

    @FXML
    private JFXButton btnDelMoney;

    @FXML
    private JFXTextField fieldBalance;

    @FXML
    private TableView<Balance> tableBalance;

    @FXML
    private TableColumn<Balance, Integer> idBalance;

    @FXML
    private TableColumn<Balance, String> NameBalance;

    @FXML
    private TableColumn<Balance, Integer> balance;

    @FXML
    void bntDelClicks(ActionEvent event) {
        if (!tableBalance.getSelectionModel().isEmpty()){
            int selectedIdx = tableBalance.getSelectionModel().getSelectedIndex();
            Balance balance = tableBalance.getItems().get(selectedIdx);

            Client.sendMessage(new Message(balance, MessageType.DEL_BALANCE.name()));
            Client.receiveMessage();

            tableBalance.getItems().remove(selectedIdx);
        }
    }

    @FXML
    void btnAddClicks(ActionEvent event) {
        if(!fieldName.getText().equals("") && !fieldBalance.getText().equals("")) {

            Balance balance = new Balance();
            balance.setName(fieldName.getText());
            balance.setBalance(Integer.parseInt(fieldBalance.getText()));
            balance.setNameAcc(Client.getAccountLogin());
            Message message = new Message();
            message.setMessage(balance);
            message.setTypeMessage(MessageType.ADD_BALANCE.name());


            Client.sendMessage(message);
            Client.receiveMessage();
            initialize();
            fieldName.setText("");
            fieldBalance.setText("");
        }
    }
    @FXML
    void initialize(){
        String where = Client.getAccountLogin();
        Client.sendMessage(new Message(where, MessageType.GET_BALANCE.name()));
        List<Balance> listBalance = (List<Balance>) Client.receiveMessage();
        ObservableList<Balance> data = FXCollections.observableArrayList(listBalance);

        idBalance.setCellValueFactory(new PropertyValueFactory<Balance, Integer>("id"));
        NameBalance.setCellValueFactory(new PropertyValueFactory<Balance, String>("name"));
        balance.setCellValueFactory(new PropertyValueFactory<Balance,Integer>("balance"));


        tableBalance.setItems(data);


    }
}
