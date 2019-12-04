package AddingElementController;

import Client.*;
import SerializableMessageClases.Balance;
import SerializableMessageClases.Expense;
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

import javax.swing.text.Style;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class AddingExpenseController {

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
        fieldCheck(fieldDate, cmbBalance, fieldComm, cmbGroup, fieldSum);
        if(!(fieldDate.getValue() ==null) && !(cmbBalance.getValue() ==null) && !fieldComm.getText().equals("")
        && !(cmbGroup.getValue() ==null) && !fieldSum.getText().equals("")) {
            Expense expense = new Expense();
            expense.setNameBalance(cmbBalance.getValue());
            expense.setDate(Date.from(fieldDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            expense.setComment(fieldComm.getText());
            expense.setGroup(cmbGroup.getValue());
            expense.setSum(Integer.parseInt(fieldSum.getText()));
            expense.setAccount(Client.getAccountLogin());

            Message message = new Message();
            message.setMessage(expense);
            message.setTypeMessage(MessageType.ADD_EXPENSE.name());

            Client.sendMessage(message);
            String out = (String) Client.receiveMessage();
            if (out.equals("Ошибка. Расход превышает текущий баланс счёта.")) {
                SceneSwitcher.showAllertWarningWindow(out);
                fieldSum.setStyle(StyleService.textFieldErrorStyle);
            } else {
                cmbGroup.setStyle(StyleService.textFieldStyle);
                cmbBalance.setStyle(StyleService.textFieldStyle);
                fieldSum.setStyle(StyleService.textFieldStyle);
                fieldDate.setStyle(StyleService.textFieldStyle);
                fieldComm.setStyle(StyleService.textFieldStyle);
                btnAdd.getScene().getWindow().hide();
            }
        }
        else {
            SceneSwitcher.showAllertWarningWindow("Введите все данные.");
            fieldDate.setStyle(StyleService.textFieldErrorStyle);
        }
    }

    static void fieldCheck(JFXDatePicker fieldDate, JFXComboBox<String> cmbBalance, JFXTextField fieldComm, JFXComboBox<String> cmbGroup, JFXTextField fieldSum) {
        if((fieldDate.getValue() ==null))
            fieldDate.setStyle(StyleService.textFieldErrorStyle);
        else  fieldDate.setStyle(StyleService.textFieldStyle);
        if(cmbBalance.getValue()==null)
            cmbBalance.setStyle(StyleService.textFieldErrorStyle);
        else cmbBalance.setStyle(StyleService.textFieldStyle);
        if(fieldComm.getText().equals(""))
            fieldComm.setStyle(StyleService.textFieldErrorStyle);
        else fieldComm.setStyle(StyleService.textFieldStyle);
        if(cmbGroup.getValue()==null)
            cmbGroup.setStyle(StyleService.textFieldErrorStyle);
        else cmbGroup.setStyle(StyleService.textFieldStyle);
        if(fieldSum.getText().equals(""))
            fieldSum.setStyle(StyleService.textFieldErrorStyle);
        else fieldSum.setStyle(StyleService.textFieldStyle);
    }

    @FXML
    void initialize(){
        AddingIncomeController.fieldCheckValid(fieldSum, btnAdd, cmbBalance);
        cmbGroup.setEditable(true);
        cmbGroup.getItems().add("Развлечение");
        cmbGroup.getItems().add("Платежи");
        cmbGroup.getItems().add("Покупки");
        cmbGroup.getItems().add("Прочее");
    }
}
