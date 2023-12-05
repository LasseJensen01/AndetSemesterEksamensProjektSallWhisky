package gui;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Cask;
import model.Location;
import model.Type;
import model.Warehouse;

import java.util.List;

public class LocateCasksTabController {
    @FXML
    private Button btnMoveCask;

    @FXML
    private Button btnSearch;

    @FXML
    private CheckBox cbCaskIsInUse;

    @FXML
    private ComboBox<Type> cbCaskType;

    @FXML
    private CheckBox cbIsWhisky;

    @FXML
    private ComboBox<Location> cbNewLocation;

    @FXML
    private ComboBox<Warehouse> cbNewWarehouse;

    @FXML
    private Label lblErrorLabel;

    @FXML
    private ListView<Cask> lwCasks;

    @FXML
    private TextField txtCaskID;

    @FXML
    private TextField txtCaskVolume;

    @FXML
    private TextField txtTimesUsed;

    @FXML
    public void initialize(){
        cbCaskType.getItems().setAll(Type.values());
        List<Warehouse> warehouseList = Controller.getWarehouses();
        cbNewWarehouse.getItems().setAll(warehouseList);
        lwCasks.getItems().setAll(Controller.getCasks());
        lwCasks.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        cbIsWhisky.setDisable(true);
    }


    @FXML
    private void updateCBLocationBox(){
        Warehouse wh = null;
        try{
            wh = cbNewWarehouse.getSelectionModel().getSelectedItem();
            cbNewLocation.getItems().setAll(wh.getLocations());
        }catch (Exception e){
            System.err.println();
        }
    }

    @FXML
    private void setBtnSearch(){
        boolean iswhisky = cbIsWhisky.isSelected();
        boolean caskInUse = cbCaskIsInUse.isSelected();

        Integer ID = null;
        Type type = null;
        Double volume = null;
        Integer timesUsed = null;

        try {
            if (!txtCaskID.getText().isEmpty()){
                ID = Integer.parseInt(txtCaskID.getText());
            }
        } catch (Exception e){
            lblErrorLabel.setText("ID skal være et helt tal");
        }

        if(cbCaskType.getItems() != null){
            type = cbCaskType.getSelectionModel().getSelectedItem();
        }

        try {
            if (!txtCaskVolume.getText().isEmpty()){
                volume = Double.parseDouble(txtCaskVolume.getText());
            }
        } catch (Exception e){
            lblErrorLabel.setText("Cask Volume skal være et tal");
        }

        try {
            if (!txtTimesUsed.getText().isEmpty()){
                timesUsed = Integer.parseInt(txtTimesUsed.getText());
            }
        } catch (Exception e){
            lblErrorLabel.setText("Times used skal være et helt tal");
        }
        if (caskInUse){
            lwCasks.getItems().setAll(Controller.locateFullCask(iswhisky,type,volume,ID,timesUsed));
        } else lwCasks.getItems().setAll(Controller.locateEmptyCask(type,volume,ID, timesUsed));
    }
    @FXML
    private void setBtnMoveCask(){
        try {
            Cask cask = lwCasks.getSelectionModel().getSelectedItem();
            Location location = cbNewLocation.getSelectionModel().getSelectedItem();
            Warehouse warehouse = cbNewWarehouse.getSelectionModel().getSelectedItem();

            Controller.moveCask(warehouse, cask, location);
        } catch (Exception e){
            System.err.println("error when moveing cask" + e.getMessage());
        }
    }

    @FXML
    private void setStateForcbIsWhisky(){
        if(cbCaskIsInUse.isSelected()){
            cbIsWhisky.setDisable(false);
        } else cbIsWhisky.setDisable(true);
    }
}
