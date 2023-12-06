package gui;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Field;
import model.MaltBatch;

public class MaltBatchTabController {

    @FXML
    private Button btnAddMaltBatch;

    @FXML
    private ComboBox<Field> cboxField;

    @FXML
    private Label lblField;

    @FXML
    private Label lblGrain;

    @FXML
    private Label lblMaltBatches;

    @FXML
    private Label lblMaltery;

    @FXML
    private Label lblSmokingMaterial;

    @FXML
    private ListView<MaltBatch> lvwMaltBatches;

    @FXML
    private TextField txfGrain;

    @FXML
    private TextField txfMaltery;

    @FXML
    private TextField txfSmokingMaterial;

    @FXML
    public void initialize(){
        lvwMaltBatches.getItems().setAll(Controller.getMaltBatches());
        cboxField.getItems().setAll(Controller.getAllFields());
    }

    @FXML
    void addMaltBatchAction(ActionEvent event) {
        String grain = txfGrain.getText();
        Field field = cboxField.getSelectionModel().getSelectedItem();
        String maltery = txfMaltery.getText();
        if(txfSmokingMaterial.getText().isEmpty()) {
            MaltBatch maltBatch = Controller.createMaltBatch(maltery, grain, field);
        }
        else{
            String smokeMaterial = txfSmokingMaterial.getText();
            MaltBatch maltBatch = Controller.createMaltBatch(smokeMaterial, maltery, grain, field);
        }
        lvwMaltBatches.getItems().setAll(Controller.getMaltBatches());
    }

}
