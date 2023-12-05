package gui;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    public void initialize(){
        lwFarmers.getItems().setAll(Controller.getFarmers());
    }

    @FXML
    void addFarmerAction(ActionEvent event) {
        String name = txfFarmerName.getText();
        String address = txfAddress.getText();
        Controller.createFarmer(name, address);
        lwFarmers.getItems().setAll(Controller.getFarmers());
    }

    /*@FXML
    private void openAddFarmerAction() throws Exception{
        Parent root = FXMLLoader.load(Gui.getFXMLFarmer());
        Stage stage = new Stage();
        stage.setMinWidth(root.minWidth(-1));
        stage.setMinHeight(root.minHeight(-1));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/

}
