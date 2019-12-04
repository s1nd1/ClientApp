package Controller;

import Client.*;
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

public class IncomeController {

    @FXML
    private Pane pnlMoney;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDel;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TableView<Income> tblIncome;

    @FXML
    private TableColumn<Income, Integer> tcID;

    @FXML
    private TableColumn<Income, String> tcGroup;

    @FXML
    private TableColumn<Income, Integer> tcSum;

    @FXML
    private TableColumn<Income, String> tcBalance;

    @FXML
    private TableColumn<Income, Date> tcDate;

    @FXML
    private TableColumn<Income, String> tcComm;

    @FXML
    void addIncome(ActionEvent event) {
        SceneSwitcher.showDialog("../FXML/AddingIncome.fxml", "Добавление доходов");
    }

    @FXML
    void refreshIncome(ActionEvent event) {
    initialize();
    }

    @FXML
    void exel(ActionEvent event) {
        String where = Client.getAccountLogin();
        Client.sendMessage(new Message(where, MessageType.GET_INCOME.name()));
        List<Income> listIncome = (List<Income>) Client.receiveMessage();
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("Данные доходов");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Дата");
        row.createCell(1).setCellValue("Группа");
        row.createCell(2).setCellValue("Описание");
        row.createCell(3).setCellValue("В счёт");
        row.createCell(4).setCellValue("Cумма");

        for(int i = 1;i<listIncome.size();i++){

            Income income = listIncome.get(i);
            Row cells = sheet.createRow(i);

            cells.createCell(0).setCellValue(income.getDate().toString());
            cells.createCell(1).setCellValue(income.getGroup());
            cells.createCell(2).setCellValue(income.getComment());
            cells.createCell(3).setCellValue(income.getNameBalance());
            cells.createCell(4).setCellValue(income.getSum());

        }

        try {
            FileOutputStream fos = new FileOutputStream("Income.xls");
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
        Client.sendMessage(new Message(where, MessageType.GET_INCOME.name()));
        List<Income> listIncome = (List<Income>) Client.receiveMessage();
        ObservableList<Income> data = FXCollections.observableArrayList(listIncome);

        tcID.setCellValueFactory(new PropertyValueFactory<Income, Integer>("id"));
        tcComm.setCellValueFactory(new PropertyValueFactory<Income, String>("comment"));
        tcGroup.setCellValueFactory(new PropertyValueFactory<Income,String>("group"));
        tcSum.setCellValueFactory(new PropertyValueFactory<Income,Integer>("sum"));
        tcDate.setCellValueFactory(new PropertyValueFactory<Income, Date>("date"));
        tcBalance.setCellValueFactory(new PropertyValueFactory<Income, String>("nameBalance"));
        tblIncome.setItems(data);
    }

}
