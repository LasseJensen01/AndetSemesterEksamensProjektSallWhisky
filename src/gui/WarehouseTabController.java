package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import model.Warehouse;


import java.util.Arrays;
import java.util.List;


public class WarehouseTabController {
    @FXML
    private Button btnCreateWarehouse;

    @FXML
    private Button btnExtractOverview;

    @FXML
    private ListView<Warehouse> lwWarehouses;

    @FXML
    private TextField txtAdress;

    @FXML
    private TextField txtName;



    @FXML
    public void initialize(){
    updateLWWarehouseOverview();
    lwWarehouses.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
    @FXML
    private void setBtnCreateWarehouse(){
        try{
            if (!txtName.getText().isEmpty() && !txtAdress.getText().isEmpty()) {
                String name = txtName.getText();
                String adress = txtAdress.getText();
                Controller.createWarehouse(name,adress);
                updateLWWarehouseOverview();
                txtName.clear();
                txtAdress.clear();
            }
        }catch (Exception e){
            System.err.println("error when creating warehouse " + e.getMessage());
        }
    }
    @FXML
    private void setBtnExtractOverview(){
        try {
            Warehouse wh = lwWarehouses.getSelectionModel().getSelectedItem();
            wh.extractOverview();
        }catch (Exception e){
            System.err.println("error when printing: " + e.getMessage());
        }
    }
    @FXML
    private void updateLWWarehouseOverview(){
        lwWarehouses.getItems().setAll(Controller.getWarehouses());
    }
}
