package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Amount;
import model.Bottle;
import model.NewMake;
import model.WhiskyProduct;

import java.net.URL;
import java.util.NoSuchElementException;

public class WhiskyHistoryDialogController {

    @FXML
    private Button btnShowBottleHistory;

    @FXML
    private ListView<WhiskyProduct> lvwWhiskys;

    @FXML
    private TextArea txaHistory;

    @FXML
    private TextField txfBottleId;

    @FXML
    public void initialize(){
        ChangeListener<Object> listener = (ov, o, n) -> this.listViewSelected();
        lvwWhiskys.getSelectionModel().selectedItemProperty().addListener(listener);
        lvwWhiskys.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lvwWhiskys.getItems().setAll(Controller.getWhiskyProducts());
        txaHistory.setEditable(false);
    }

    private void listViewSelected() {
        txaHistory.clear();
        WhiskyProduct selectedItem = lvwWhiskys.getSelectionModel().getSelectedItem();
        txaHistory.setText(selectedItem.getFullProductionHistory());
    }

    @FXML
    void showBottleHistory(ActionEvent event) {
        String errorMessage = "Bottle with that id not found.";
        try {
            int id = Integer.parseInt(txfBottleId.getText());
            Bottle bottle = Controller.getBottleById(id);
            txaHistory.clear();
            txaHistory.setText(bottle.getFullProductionHistory());
            txfBottleId.clear();
        } catch (Exception e){
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("An error has occured");
            err.setHeaderText("The following issues have been detected");
            err.setContentText(errorMessage);
            err.show();
        }

    }

}
