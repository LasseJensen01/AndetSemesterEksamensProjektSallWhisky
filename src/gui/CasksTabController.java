package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
public class CasksTabController {
    @FXML
    private Button btnRegisterCasks;

    @FXML
    private Button btnUpdate;

    @FXML
    private CheckBox cbConfirm;

    @FXML
    private ChoiceBox<?> cbType;

    @FXML
    private Text lblALC;

    @FXML
    private Text lblLiters;

    @FXML
    private Text lblLocation;

    @FXML
    private TextField txtALC;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtLiters;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtVolume;
}
