package Controller;

import Services.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.StackPane;

public class MainWindowController {

    @FXML
    private JFXButton btnExpense;

    @FXML
    private JFXButton btnIncome;

    @FXML
    private JFXButton btnBalance;

    @FXML
    private JFXButton btnExpensesReport;

    @FXML
    private JFXButton btnAdministration;

    @FXML
    private StackPane switchPane;



    @FXML
    void menuClicks(ActionEvent event) {

    }

    @FXML
    void initialize() {

        btnExpense.setOnAction(event -> {
            switchPane.getChildren().add(SceneSwitcher.loadNode("../FXML/Expense.fxml"));
        });

        btnBalance.setOnAction(event -> {
            switchPane.getChildren().add(SceneSwitcher.loadNode("../FXML/AddingBalance.fxml"));
        });


        btnIncome.setOnAction(event -> {
            switchPane.getChildren().add(SceneSwitcher.loadNode("../FXML/Income.fxml"));
        });

        btnAdministration.setOnAction(event -> {
            switchPane.getChildren().add(SceneSwitcher.loadNode("../FXML/Administration.fxml"));

        });

        btnExpensesReport.setOnAction(event -> {
            switchPane.getChildren().add(SceneSwitcher.loadNode("../FXML/ExpensesReport.fxml"));

        });

    }

}
