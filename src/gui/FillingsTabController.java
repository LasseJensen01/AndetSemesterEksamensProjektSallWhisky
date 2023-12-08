package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Amount;
import model.Cask;
import model.Filling;
import model.NewMake;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class FillingsTabController {
    private Cask cask;
    private List<Amount> amounts = new ArrayList<>();
    @FXML
    private Button btnAddNewMake;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnConfirmCask;
    @FXML
    private Button btnFillCask;

    @FXML
    private ChoiceBox<NewMake> chbNewMakes;

    @FXML
    private TextArea txaFillingContent;

    @FXML
    private TextField txfEmployeeSignature;
    @FXML
    private TextField txfCaskID;
    @FXML
    private TextField txfLitersOfNewMake;
    @FXML
    private TextField txfCaskInfo;
    @FXML
    public void initialize(){
        update();
    }
    @FXML
    void FillCask(ActionEvent event) {
        String errorMessage = "";
        try {
            String name = txfEmployeeSignature.getText();
            // Error handelig
            if (name.equals("")){
                errorMessage = "Please enter employee name";
                throw new IllegalArgumentException();
            }
            if (amounts.isEmpty()){
                errorMessage = "Please add newmakes";
                throw new IllegalArgumentException();
            }
            double totalLiters = 0;
            for (Amount a : amounts){
                totalLiters += a.getLiters();
            }
            if (cask.getFilling() != null){
                errorMessage = "The cask is not empty.";
                throw new IllegalArgumentException();
            }

            Filling filling = Controller.createFilling(this.cask, name);
            for (Amount a : amounts){
                Controller.addAmountToFilling(filling, a);
            }

            undoAmounts();
            txaFillingContent.clear();
            txfCaskID.clear();
            txfEmployeeSignature.clear();
            txfLitersOfNewMake.clear();
            txfCaskID.clear();

            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setResizable(true);
            info.setTitle("Sucess");
            info.setHeaderText("The cask has recived a new filling, by" + name);
            info.setContentText("The cask now contains " + cask.getLiters() + " liters.");
            info.show();

        } catch (Exception e){
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("An error has occured");
            err.setHeaderText("The following issues have been detected");
            err.setContentText(errorMessage);
            err.show();
        }

    }

    @FXML
    void addNewmake(ActionEvent event) {
        String errorMessage = "Illegal values. Please cheek inputs";
        try {
            int liters = Integer.parseInt(txfLitersOfNewMake.getText());
            NewMake selected = chbNewMakes.getValue();

            Amount amount = Controller.createAmount(selected, liters);

            double totalLiters = 0;
            for (Amount a : amounts){
                totalLiters += a.getLiters();
            }
            if (totalLiters + amount.getLiters() > cask.getVolume()){
                errorMessage = "Not enough room in the cask. Try a lower amount.";
                throw new IllegalArgumentException();
            }

            amounts.add(amount);
            txaFillingContent.appendText(amount.toString() + "\n");

        } catch (Exception e){
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("An error has occured");
            err.setHeaderText("The following issues have been detected");
            err.setContentText(errorMessage);
            err.show();
        }

    }

    @FXML
    void clearChoices(ActionEvent event) {
        txaFillingContent.clear();
        undoAmounts();
    }
    @FXML
    void caskConfimation(ActionEvent event) {
        txaFillingContent.clear();
        undoAmounts();

        String errorMessage = "";
        try {
            int id = Integer.parseInt(txfCaskID.getText());
            this.cask = Controller.getCaskByID(id);

            if (cask == null){
                errorMessage = "Cask not found.";
                throw new IllegalArgumentException();
            }

            if (cask.getFilling() != null){
                errorMessage = "The cask is not empty.";
                cask = null;
                throw new IllegalArgumentException();
            }

            txfCaskInfo.setText(cask.toString());

        } catch (Exception e){
            System.out.println("problem here");
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("An error has occured");
            err.setHeaderText("The following issues have been detected");
            err.setContentText(errorMessage);
            err.show();
        }
    }
    @FXML
    void update(){
        List<NewMake> list = Controller.getNewMakes().stream().filter(NewMake -> NewMake.isDone()).toList();
        chbNewMakes.getItems().setAll(list);
    }
    @FXML
    void undoAmounts(){
        for (Amount amount : amounts){
            NewMake newMake = amount.getNewMake();
            double volume = newMake.getVolume();
            newMake.setVolume(volume + amount.getLiters());
        }
        amounts.clear();
    }
}


