package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.Amount;
import model.Cask;
import model.NewMake;
import model.Type;

import java.net.URL;
import java.util.NoSuchElementException;

public class CasksTabController {
    private Cask chosenCask;
    @FXML
    private Button btnRegisterCasks;

    @FXML
    private Button btnSelect;

    @FXML
    private Button btnUpdate;

    @FXML
    private ChoiceBox<Type> cbType;

    @FXML
    private Text lblALC;

    @FXML
    private Text lblLiters;

    @FXML
    private Text lblLocation;

    @FXML
    private TextField txfAlc;

    @FXML
    private TextField txfCaskId;

    @FXML
    private TextField txfLiters;

    @FXML
    private TextField txfLokation;

    @FXML
    private TextField txfVolume;

    @FXML
    private TextField txtNumber;


    @FXML
    public void initialize(){
        cbType.getItems().setAll(Type.values());
    }

    @FXML
    void registerCasks(ActionEvent event) {
        String errorMessage = "";
        try {
            int numberOfCasks = Integer.parseInt(txtNumber.getText());
            double caskVolume = Double.parseDouble(txfVolume.getText());

            Type type = cbType.getValue();

            for (int i = 0; i < numberOfCasks; i++){
                Controller.createCask(type,caskVolume);
            }

            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setResizable(true);
            info.setTitle("Sucess");
            info.setHeaderText("The casks have been registered");
            info.setContentText(numberOfCasks + " " + type.toString() + " casks with volume " + caskVolume);
            info.show();

        } catch (Exception e){
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("An error has occured");
            err.setHeaderText("The following issues have been detected");
            err.setContentText("Please cheek values again.");
            err.show();
        }
    }

    @FXML
    void updateCask(ActionEvent event) {
        String errorMessage = "";
        try {

            if (chosenCask == null){
                errorMessage = "Please select a cask.";
                throw new IllegalArgumentException();
            }

            if (!txfLiters.getText().equals("")){
                double newLiters = Double.parseDouble(txfLiters.getText());
                chosenCask.setLiters(newLiters);
                lblLiters.setText(newLiters + "");
            }
            if (!txfAlc.getText().equals("")){
                double newAlcPercent = Double.parseDouble(txfAlc.getText());
                chosenCask.getFilling().setAlcoholPercent(newAlcPercent);
                lblALC.setText(newAlcPercent + "");
            }
            if (!txfLokation.getText().equals("")){
                //How to do this
            }


            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setResizable(true);
            info.setTitle("Sucess");
            info.setHeaderText("The cask contents have been updated.");
            info.setContentText(chosenCask.toString());
            info.show();

        } catch (Exception e){
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("An error has occured");
            err.setHeaderText("The following issues have been detected");
            err.setContentText("Please cheek values again.");
            err.show();
        }
    }

    @FXML
    void selectCask(ActionEvent event) {
        String errorMessage = "";
        chosenCask = null;

        try {
            int id = Integer.parseInt(txfCaskId.getText());
            Cask cask = Controller.getCaskByID(id);

            if (cask == null){
                errorMessage = "Cask not found.";
                lblLiters.setText("Liters");
                lblALC.setText("Alc.%");
                lblLocation.setText("Lokation");

                txfLiters.setEditable(false);
                txfAlc.setEditable(false);
                txfLokation.setEditable(false);

                throw new IllegalArgumentException();
            }

            if (cask.getFilling() == null){
                errorMessage = "Cask is empty";
                lblLiters.setText("Liters");
                lblALC.setText("Alc.%");
                lblLocation.setText("Lokation");

                txfLiters.setEditable(false);
                txfAlc.setEditable(false);
                txfLokation.setEditable(false);
                throw new IllegalArgumentException();
            }

            lblLiters.setText(cask.getLiters() + "");
            lblALC.setText(cask.getFilling().getAlcoholPercent() + "");
            lblLocation.setText(cask.getLocation().toString());

            txfLiters.setEditable(true);
            txfAlc.setEditable(true);
            txfLokation.setEditable(true);

            chosenCask = cask;
        } catch (Exception e){
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("An error has occured");
            err.setHeaderText("The following issues have been detected");
            err.setContentText(errorMessage);
            err.show();
        }
    }

}
