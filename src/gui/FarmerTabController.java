package gui;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Farmer;

public class FarmerTabController {
    @FXML
    private Button btnAddFarmer;

    @FXML
    private ListView<Farmer> lwFarmers;

    @FXML
    private TextField txfAddress;

    @FXML
    private TextField txfFarmerName;

    @FXML
    private Text txtAddress;

    @FXML
    private Text txtFarmerName;

    @FXML
    private Label lblOurFarmers;
    @FXML
    private Label lblError;


    @FXML
    public void initialize(){
        lwFarmers.getItems().setAll(Controller.getFarmers());
    }

    @FXML
    void addFarmerAction(ActionEvent event) {
        String name = txfFarmerName.getText();
        String address = txfAddress.getText();
        if(!name.isEmpty() && !address.isEmpty()) {
            Controller.createFarmer(name, address);
            lwFarmers.getItems().setAll(Controller.getFarmers());
            txfAddress.clear();
            txfFarmerName.clear();
            lblError.setText("");

        } else {
            lblError.setText("Error: Check data");
        }
    }
}
