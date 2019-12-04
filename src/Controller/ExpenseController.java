package Controller;

import Client.*;
import SerializableMessageClases.Expense;
import SerializableMessageClases.Income;
import SerializableMessageClases.Message;
import Services.SceneSwitcher;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ExpenseController {

    @FXML
    private Pane pnlMoney;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDel;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TableView<Expense> tblExpense;

    @FXML
    private TableColumn<Expense, Integer> tcID;

    @FXML
    private TableColumn<Expense, String> tcGroup;

    @FXML
    private TableColumn<Expense, Integer> tcSum;

    @FXML
    private TableColumn<Expense, String> tcBalance;

    @FXML
    private TableColumn<Expense, Date> tcDate;

    @FXML
    private TableColumn<Expense, String> tcComm;

    @FXML
    void addExpense(ActionEvent event) {
        SceneSwitcher.showDialog("../FXML/AddingExpense.fxml", "Добавление расхода");
    }

    @FXML
    void refreshExpense(ActionEvent event) {
        initialize();
    }

    @FXML
    void exel(ActionEvent event) {
        String where = Client.getAccountLogin();
        Client.sendMessage(new Message(where, MessageType.GET_EXPENSE.name()));
        List<Expense> listExpense = (List<Expense>) Client.receiveMessage();
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("Данные всех расходов");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Дата");
        row.createCell(1).setCellValue("Группа");
        row.createCell(2).setCellValue("Описание");
        row.createCell(3).setCellValue("Со счёта");
        row.createCell(4).setCellValue("Cумма");

        for(int i = 1;i<listExpense.size();i++){

            Expense expense = listExpense.get(i);
            Row cells = sheet.createRow(i);

            cells.createCell(0).setCellValue(expense.getDate().toString());
            cells.createCell(1).setCellValue(expense.getGroup());
            cells.createCell(2).setCellValue(expense.getComment());
            cells.createCell(3).setCellValue(expense.getNameBalance());
            cells.createCell(4).setCellValue(expense.getSum());

        }

        try {
            FileOutputStream fos = new FileOutputStream("Expense.xls");
            try {
                ((HSSFWorkbook) wb).write(fos);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            SceneSwitcher.showAllertWarningWindow("Данные сохранены");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void initialize(){
        String where = Client.getAccountLogin();
        Client.sendMessage(new Message(where, MessageType.GET_EXPENSE.name()));
        List<Expense> listExpense = (List<Expense>) Client.receiveMessage();
        ObservableList<Expense> data = FXCollections.observableArrayList(listExpense);

        tcID.setCellValueFactory(new PropertyValueFactory<Expense, Integer>("id"));
        tcComm.setCellValueFactory(new PropertyValueFactory<Expense, String>("comment"));
        tcGroup.setCellValueFactory(new PropertyValueFactory<Expense,String>("group"));
        tcSum.setCellValueFactory(new PropertyValueFactory<Expense,Integer>("sum"));
        tcDate.setCellValueFactory(new PropertyValueFactory<Expense, Date>("date"));
        tcBalance.setCellValueFactory(new PropertyValueFactory<Expense, String>("NameBalance"));
        tblExpense.setItems(data);
    }

}
