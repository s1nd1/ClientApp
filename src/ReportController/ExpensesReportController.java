package ReportController;

import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import Client.*;
import SerializableMessageClases.Message;
import SerializableMessageClases.ReportPieQuery;
import SerializableMessageClases.ReportPieValue;
import Services.SceneSwitcher;
import Services.StyleService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ExpensesReportController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane pnlPartners;

    @FXML
    private PieChart chartExpensesReport;

    @FXML
    private JFXDatePicker dataStart;

    @FXML
    private JFXDatePicker dataEnd;

    @FXML
    private JFXComboBox<String> cmbReportType;

    @FXML
    private JFXButton btnCreateReportTime;

    @FXML
    private JFXButton btnCreateReportAllTime;

    @FXML
    void btnCreateReportAllTimeClicks(ActionEvent event) {
        if(cmbReportType.getSelectionModel().isEmpty()){
            cmbReportType.setStyle(StyleService.textFieldErrorStyle);
        }

        if(!cmbReportType.getSelectionModel().isEmpty()){

            ReportPieQuery query = new ReportPieQuery();

            ChartCreate(query);
        }
    }

    @FXML
    void btnCreateReportTimeClicks(ActionEvent event) {
        if((dataStart.getValue() ==null))
            dataStart.setStyle(StyleService.textFieldErrorStyle);
        else dataStart.setStyle(StyleService.textFieldStyle);
        if((dataEnd.getValue() ==null))
            dataEnd.setStyle(StyleService.textFieldErrorStyle);
        else dataEnd.setStyle(StyleService.textFieldStyle);
        if(cmbReportType.getValue()==null)
            cmbReportType.setStyle(StyleService.textFieldErrorStyle);
        else cmbReportType.setStyle(StyleService.textFieldStyle);

        if(!(dataStart.getValue()==null) && !(dataEnd.getValue()==null)
                && !(cmbReportType.getValue()==null)){

            ReportPieQuery query = new ReportPieQuery();

            query.setDateEnd(Date.from(dataEnd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            query.setDateStart(Date.from(dataStart.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            ChartCreate(query);
            dataStart.setStyle(StyleService.textFieldStyle);
            cmbReportType.setStyle(StyleService.textFieldStyle);
        }
        else {
            SceneSwitcher.showAllertWarningWindow("Введите все данные.");
        }
    }

    private void ChartCreate(ReportPieQuery query) {
        query.setReportType(cmbReportType.getSelectionModel().getSelectedItem());
        query.setNameAcc(Client.getAccountLogin());

        Client.sendMessage(new Message(query, MessageType.GET_REPORT_PIE_VALUE.name()));

        List<ReportPieValue> pieValues =(List<ReportPieValue>) Client.receiveMessage();

        List<PieChart.Data> data = new ArrayList<>(pieValues.size());

        for(ReportPieValue pieValue:pieValues) {
            data.add(new PieChart.Data(pieValue.getFinancialArticleName(), pieValue.getAmount()));
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(data);

        chartExpensesReport.setData(pieChartData);

        cmbReportType.setStyle(StyleService.textFieldStyle);
    }


    @FXML
    void initialize() {
        cmbReportType.getItems().addAll("доходы", "расходы");
    }
}
