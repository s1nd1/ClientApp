package Controller;

import Services.SceneSwitcher;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class MainWindowUserController {

    @FXML
    private JFXButton btnExpense;

    @FXML
    private JFXButton btnIncome;

    @FXML
    private JFXButton btnBalance;

    @FXML
    private JFXButton btnExpensesReport;

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

        btnIncome.setOnAction(event -> {
            switchPane.getChildren().add(SceneSwitcher.loadNode("../FXML/Income.fxml"));
        });

        btnBalance.setOnAction(event -> {
            switchPane.getChildren().add(SceneSwitcher.loadNode("../FXML/AddingBalance.fxml"));
        });

        btnExpensesReport.setOnAction(event -> {
            switchPane.getChildren().add(SceneSwitcher.loadNode("../FXML/ExpensesReport.fxml"));
        });
    }
}

