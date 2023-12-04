package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Cask;
import model.Location;
import model.Type;
import model.Warehouse;


public class WarehouseTabController {

    @FXML
    private Button btnMoveCask;

    @FXML
    private Button btnSearch;

    @FXML
    private CheckBox cbCaskIsInUse;

    @FXML
    private ChoiceBox<Type> cbCaskType;

    @FXML
    private CheckBox cbIsWhisky;

    @FXML
    private ChoiceBox<Location> cbNewLocation;

    @FXML
    private ChoiceBox<Warehouse> cbNewWarehouse;

    @FXML
    private ListView<Cask> lwCasks;

    @FXML
    private TextField txtCaskID;

    @FXML
    private TextField txtCaskVolume;

    @FXML
    private TextField txtTimesUsed;
    @FXML
    private Label lblErrorLabel;

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
}
