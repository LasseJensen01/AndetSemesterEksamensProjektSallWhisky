package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Warehouse;


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
                if (rows > 20 ||shelfUnits > 10 || shelfs > 10 || pallets > 10) throw new IllegalArgumentException();
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
        }catch (IllegalArgumentException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error in data");
            alert.setContentText("Reduce amount of Storage");
            alert.showAndWait();
        }
        catch (Exception e){
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
