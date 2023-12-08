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
    private Label lblError;

    @FXML
    public void initialize(){
        update();
    }

    @FXML
    void chooseFarmerAction() {
        try {
            Farmer farmer = cboxFarmer.getSelectionModel().getSelectedItem();
            List<Field> farmersFields = Controller.getFarmersFields(farmer);
            lvwChosenFarmersFields.getItems().setAll(farmersFields);
        }catch (Exception e){

        }
    }
    @FXML
    void addFieldAction() {
        if(!txfFieldName.getText().isEmpty() && !cboxFarmer.getSelectionModel().isEmpty()){
            String fieldName = txfFieldName.getText();
            Farmer farmer = cboxFarmer.getSelectionModel().getSelectedItem();
            Field field = Controller.createField(fieldName, farmer);
            lvwChosenFarmersFields.getItems().setAll(Controller.getFarmersFields(farmer));
            txfFieldName.clear();
            lblError.setText("");
        }else{
            lblError.setText("Error: Check data");
        }

    }
    @FXML
    void update(){
        try {
            cboxFarmer.getItems().setAll(Controller.getFarmers());
        }catch (Exception e){}

    }

}
