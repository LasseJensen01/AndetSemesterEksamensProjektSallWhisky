package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class AddFarmerDialogController {

    @FXML
    private GridPane AddFarmerPane;

    @FXML
    private Button btnAddFarmer;

    @FXML
    private Button btnAddField;

    @FXML
    private ListView<?> lvwFields;

    @FXML
    private TextField txfAddress;

    @FXML
    private TextField txfFarmerName;

    @FXML
    private TextField txfFieldName;

    @FXML
    private Text txtAddress;

    @FXML
    private Text txtFarmerName;

    @FXML
    private Text txtFieldName;

    @FXML
    private Text txtFields;

}
