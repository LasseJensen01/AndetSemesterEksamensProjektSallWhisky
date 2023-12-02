import controller.Controller;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import jdk.jfr.Event;
import model.WhiskyProduct;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GUIskitseController implements Initializable{
    public GUIskitseController() {

    }

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnSupplier;

    @FXML
    private ChoiceBox<?> cboxGrain;

    @FXML
    private ChoiceBox<?> cboxMaltBatch;

    @FXML
    private ChoiceBox<?> cboxResponsibleEmployee;

    @FXML
    private ChoiceBox<?> cboxSmokeMaterial;

    @FXML
    private DatePicker datePickerEnd;

    @FXML
    private DatePicker datePickerStart;

    @FXML
    private ListView<?> lvwSupplierInfo;

    @FXML
    private ListView<?> lvwSuppliers;

    @FXML
    private ListView<WhiskyProduct> lvwWhiskyList;

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
    private Text txtComment;

    @FXML
    private Text txtEndDate;

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
    private Text txtSuppliers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<WhiskyProduct> whiskyProducts = FXCollections.observableArrayList(Controller.getWhiskyProducts());
        lvwWhiskyList.setItems(whiskyProducts);
        lvwWhiskyList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //lvwWhiskyList.getSelectionModel().selectedItemProperty().addListener(this::selectionChange);
        ChangeListener<WhiskyProduct> listener = (ov, o, n) -> this.wiskySelected();
        lvwWhiskyList.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    private void selectionChange(Observable observable, WhiskyProduct ov, WhiskyProduct nv){
        WhiskyProduct whiskyProducts = lvwWhiskyList.getSelectionModel().getSelectedItem();
        String wiskyDescription = (whiskyProducts == null) ? "Select a wisky." : whiskyProducts.toString();
        txfWhiskyInfo.clear();
    }
    private void wiskySelected(){
        WhiskyProduct whiskyProduct = lvwWhiskyList.getSelectionModel().getSelectedItem();
        String wiskyDescription = (whiskyProduct == null) ? "Select a wisky." : whiskyProduct.toString();
        txfWhiskyInfo.setText(wiskyDescription);
    }

}