package gui;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Farmer;
import model.Field;

import java.util.List;

public class FieldTabController {

    @FXML
    private Button btnAddField;

    @FXML
    private ComboBox<Farmer> cboxFarmer;

    @FXML
    private Label lblChoseFarmersFields;

    @FXML
    private Label lblFarmer;

    @FXML
    private Label lblFieldName;

    @FXML
    private ListView<Field> lvwChosenFarmersFields;

    @FXML
    private TextField txfFieldName;

    @FXML
    public void initialize(){
        cboxFarmer.getItems().setAll(Controller.getFarmers());
    }

    @FXML
    void chooseFarmerAction(ActionEvent event) {
        Farmer farmer = cboxFarmer.getSelectionModel().getSelectedItem();
        List<Field> farmersFields = Controller.getFarmersFields(farmer);
        lvwChosenFarmersFields.getItems().setAll(farmersFields);
    }
    @FXML
    void addFieldAction(ActionEvent event) {
        String fieldName = txfFieldName.getText();
        Farmer farmer = cboxFarmer.getSelectionModel().getSelectedItem();
        Field field = Controller.createField(fieldName, farmer);
        lvwChosenFarmersFields.getItems().setAll(Controller.getFarmersFields(farmer));
    }

}
