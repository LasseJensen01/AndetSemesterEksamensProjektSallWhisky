package gui;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.Cask;
import model.WhiskyProduct;

import java.util.HashMap;

public class WhiskyRegistrationController {
    private HashMap<Cask,Double> chosen = new HashMap<>();
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnCreateWhisky;

    @FXML
    private CheckBox chbConfirm;

    @FXML
    private TextArea txachosenCasks;

    @FXML
    private TextField txfName;
    @FXML
    private TextField txfWaterLiters;
    @FXML
    private TextField txfSource;
    @FXML
    private ComboBox<Cask> cbCask;

    @FXML
    private TextField txfcaskLiters;

    @FXML
    private void initialize(){
        update();
        if (cbCask.getItems().isEmpty()){
            cbCask.setPlaceholder(new Text("No eligible whisky in system"));
        }
    }

    @FXML
    void addWhiskyAction(ActionEvent event) {
        try {
            Cask cask = cbCask.getSelectionModel().getSelectedItem();
            if (cask == null) throw new IllegalArgumentException();
            Double liters = Double.parseDouble(txfcaskLiters.getText());
            if (liters > cask.getLiters() || liters < 0) throw new IllegalArgumentException();

            this.chosen.put(cask, liters);
            txachosenCasks.appendText("ID: " + cask.getCaskID() + " Liters " + liters + "\n");

            cbCask.getSelectionModel().clearSelection();
            txfcaskLiters.clear();

        } catch (Exception e) {
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("An error has occured");
            err.setHeaderText("The following issues have been detected");
            err.setContentText("Please cheek Id and liter input");
            err.show();
        }
    }

    @FXML
    void clearChoicesAction(ActionEvent event) {
        this.chosen.clear();
        txachosenCasks.clear();
    }

    @FXML
    void createWhiskyAction(ActionEvent event) {
        try {
            if (chbConfirm.isSelected()){
                double litersOfWater = Double.parseDouble(txfWaterLiters.getText());
                String Source = txfSource.getText();
                if (litersOfWater < 0) throw new IllegalArgumentException();

                WhiskyProduct newWhisky = Controller.createWhiskyProduct(this.chosen,this.txfName.getText());
                txachosenCasks.clear();
                cbCask.getSelectionModel().clearSelection();
                txfName.clear();


                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setResizable(true);
                info.setTitle("Success");
                info.setHeaderText("The following whisky has been created:");
                info.setContentText(newWhisky.toString());
                info.show();
            } else {
                WhiskyProduct newWhisky = Controller.createWhiskyProduct(this.chosen,this.txfName.getText());
                txachosenCasks.clear();
                cbCask.getSelectionModel().clearSelection();
                txfName.clear();

                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setResizable(true);
                info.setTitle("Success");
                info.setHeaderText("The following whisky has been created:");
                info.setContentText(newWhisky.toString());
                info.show();
            }
        } catch (Exception e) {
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("An error has occured");
            err.setHeaderText("The following issues have been detected");
            err.setContentText("One or more of the chosen casks does not contain a large enough filling.");
            err.show();
        }
    }

    @FXML
    void update(){
        try {
            cbCask.getItems().setAll(Controller.locateFullCask(false,null,null,null,null));
        }catch (Exception e ){}
    }
}
