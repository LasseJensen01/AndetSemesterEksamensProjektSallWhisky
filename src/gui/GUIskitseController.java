import gui.Gui;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.NoSuchElementException;

public class GUIskitseController {
    public void initialize(){

    }

    @FXML
    private Button btnAddFarmer;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnSearch;

    @FXML
    private CheckBox cbIsCaskFull;

    @FXML
    private ChoiceBox<?> cboxGrain;

    @FXML
    private ChoiceBox<?> cboxMaltBatch;

    @FXML
    private ChoiceBox<?> cboxResponsibleEmployee;

    @FXML
    private ChoiceBox<?> cboxSmokeMaterial;

    @FXML
    private ComboBox<?> cboxType;

    @FXML
    private DatePicker datePickerEnd;

    @FXML
    private DatePicker datePickerStart;

    @FXML
    private ListView<?> lvwSupplierInfo;

    @FXML
    private ListView<?> lvwSuppliers;

    @FXML
    private ListView<?> lvwWhiskyList;

    @FXML
    private TextArea txaComment;

    @FXML
    private TextField txfAlcoholPercent;

    @FXML
    private TextField txfIDNewMake;

    @FXML
    private TextField txfProducedAmount;

    @FXML
    private TextArea txfWhiskyInfo;

    @FXML
    private Text txtAlcoholPercent;

    @FXML
    private TextField txtCaskID2;

    @FXML
    private TextField txtCaskSize2;

    @FXML
    private Text txtComment;

    @FXML
    private Text txtEndDate;

    @FXML
    private Text txtFarmers;

    @FXML
    private TextField txtFillNo2;

    @FXML
    private Text txtGrain;

    @FXML
    private Text txtInfo;

    @FXML
    private Text txtMaltBatch;

    @FXML
    private Text txtNewMakID;

    @FXML
    private Text txtProducedAmount;

    @FXML
    private Text txtResponsibleEmployee;

    @FXML
    private Text txtSmokeMaterial;

    @FXML
    private Text txtStartDate;

    @FXML
    private TextField txtVolume;

    @FXML
    private void addFarmerAction() throws Exception{
        Parent root = FXMLLoader.load(Gui.getFXMLFarmer());
        Stage stage = new Stage();
        stage.setMinWidth(root.minWidth(-1));
        stage.setMinHeight(root.minHeight(-1));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}