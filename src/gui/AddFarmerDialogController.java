package gui;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.Farmer;
import model.Field;

import java.util.List;

public class AddFarmerDialogController {

    @FXML
    private GridPane AddFarmerPane;

    @FXML
    private Button btnAddFarmer;

    @FXML
    private Button btnAddField;

    @FXML
    private ListView<Field> lvwFields = new ListView<>();

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

    @FXML
    void addFarmerAction(ActionEvent event) {
        String name = txfFarmerName.getText();
        String adress = txfAddress.getText();
        Farmer farmer = Controller.createFarmer(name, adress);

        List<Field> fields = lvwFields.getItems();
        //Controller.addFieldsToFarmer(farmer, fields);
    }

    @FXML
    void addFieldAction(ActionEvent event) {
        String name = txfFarmerName.getText();
        String adress = txfAddress.getText();
        Farmer farmer = Controller.createFarmer(name, adress);
        String fieldName = txfFieldName.getText();

        Field field = Controller.createField(fieldName, farmer);
    }

}
