package AddingElementController;

import Client.*;
import SerializableMessageClases.Balance;
import SerializableMessageClases.Expense;
import SerializableMessageClases.Income;
import SerializableMessageClases.Message;
import Services.SceneSwitcher;
import Services.StyleService;
import Services.ValidateService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class AddingIncomeController {

    @FXML
    private JFXDatePicker fieldDate;

    @FXML
    private JFXComboBox<String> cmbGroup;

    @FXML
    private JFXTextField fieldComm;

    @FXML
    private JFXComboBox<String> cmbBalance;

    @FXML
    private AnchorPane cmbType;

    @FXML
    private JFXTextField fieldSum;

    @FXML
    private JFXButton btnAdd;

    @FXML
    void btnAddClicks(ActionEvent event) {
        AddingExpenseController.fieldCheck(fieldDate, cmbBalance, fieldComm, cmbGroup, fieldSum);
        if(!(fieldDate.getValue() ==null) && !(cmbBalance.getValue() ==null) && !fieldComm.getText().equals("")
                && !(cmbGroup.getValue() ==null) && !fieldSum.getText().equals("")) {
            Income income = new Income();
            income.setNameBalance(cmbBalance.getValue());
            income.setDate(Date.from(fieldDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            income.setComment(fieldComm.getText());
            income.setGroup(cmbGroup.getValue());
            income.setSum(Integer.parseInt(fieldSum.getText()));
            income.setLoginAcc(Client.getAccountLogin());

            Message message = new Message();
            message.setMessage(income);
            message.setTypeMessage(MessageType.ADD_INCOME.name());

            Client.sendMessage(message);
            Client.receiveMessage();

            cmbGroup.setStyle(StyleService.textFieldStyle);
            cmbBalance.setStyle(StyleService.textFieldStyle);
            fieldSum.setStyle(StyleService.textFieldStyle);
            fieldDate.setStyle(StyleService.textFieldStyle);
            fieldComm.setStyle(StyleService.textFieldStyle);
            btnAdd.getScene().getWindow().hide();
        }
    else {
            SceneSwitcher.showAllertWarningWindow("Введите все данные.");
            fieldDate.setStyle(StyleService.textFieldErrorStyle);
        }
    }
    @FXML
    void initialize(){
        fieldCheckValid(fieldSum, btnAdd, cmbBalance);
        cmbGroup.setEditable(true);
        cmbGroup.getItems().add("Зарплата");
        cmbGroup.getItems().add("Премия");
        cmbGroup.getItems().add("Прочее");
    }

    static void fieldCheckValid(JFXTextField fieldSum, JFXButton btnAdd, JFXComboBox<String> cmbBalance) {
        fieldSum.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if(!newValue.equals("")) {
                    if (!ValidateService.isAmountValid(newValue)) {
                        fieldSum.setStyle(StyleService.textFieldErrorStyle);
                        btnAdd.setDisable(true);
                    } else {
                        fieldSum.setStyle(StyleService.textFieldStyle);
                        btnAdd.setDisable(false);
                    }
                }
            }
        });
        String where = Client.getAccountLogin();
        Client.sendMessage(new Message(where, MessageType.GET_BALANCENAME.name()));
        List<String> listBalance = (List<String>) Client.receiveMessage();
        cmbBalance.getItems().addAll(listBalance);
    }
}
