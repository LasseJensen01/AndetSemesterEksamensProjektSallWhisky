package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Warehouse;


import java.util.Arrays;
import java.util.List;


public class WarehouseTabController {
    @FXML
    private Button btnCreateWarehouse;

    @FXML
    private Button btnExtractOverview;

    @FXML
    private Label lblError;

    @FXML
    private ListView<Warehouse> lwWarehouses;

    @FXML
    private TextField txtAdress;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPallets;

    @FXML
    private TextField txtRows;

    @FXML
    private TextField txtShelfUnits;

    @FXML
    private TextField txtShelfs;



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
                int rows = Integer.parseInt(txtRows.getText());
                int shelfUnits = Integer.parseInt(txtShelfUnits.getText());
                int shelfs = Integer.parseInt(txtShelfs.getText());
                int pallets = Integer.parseInt(txtPallets.getText());
                Warehouse wh = Controller.createWarehouse(name,adress);
                Controller.createLocationsInWarehouse(wh,rows,shelfUnits,shelfs,pallets);
                updateLWWarehouseOverview();
                txtName.clear();
                txtAdress.clear();
                txtRows.clear();
                txtShelfUnits.clear();
                txtShelfs.clear();
                txtPallets.clear();
            }
        }catch (Exception e){
            lblError.setText("Error: Check Data for errors");
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
