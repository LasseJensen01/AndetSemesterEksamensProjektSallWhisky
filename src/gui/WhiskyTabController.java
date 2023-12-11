package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.WhiskyProduct;

import java.net.URL;
import java.util.NoSuchElementException;

public class WhiskyTabController{
    private static URL FXMLWhiskyRegistration;
    private static URL FXMLWhiskyHistory;
    @FXML
    private Button btnBottleWhisky;

    @FXML
    private Button btnRegisterNewWhisky;

    @FXML
    private Button btnShowHistory;

    @FXML
    private ListView<WhiskyProduct> lvwWhiskyList;

    @FXML
    private TextArea txfWhiskyInfo;

    @FXML
    private TextField txfBottleSize;

    @FXML
    private TextField txfNumberOfBottles;


    @FXML
    public void initialize(){
        ChangeListener<Object> listener = (ov, o, n) -> this.listViewSelected();
        lvwWhiskyList.getSelectionModel().selectedItemProperty().addListener(listener);
        lvwWhiskyList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lvwWhiskyList.getItems().setAll(
                Controller.getWhiskyProducts()
        );

        URL fxmlWhiskyRegistration = this.getClass().getResource("resources\\WhiskyRegistrationDialog.fxml");
        if (fxmlWhiskyRegistration == null) throw new NoSuchElementException("FXML file not found");
        this.FXMLWhiskyRegistration = fxmlWhiskyRegistration;

        URL fxmlWhiskyHistoryDialog = this.getClass().getResource("resources\\WhiskyHistoryDialog.fxml");
        if (fxmlWhiskyHistoryDialog == null) throw new NoSuchElementException("FXML file not found");
        this.FXMLWhiskyHistory = fxmlWhiskyHistoryDialog;
    }

    private void listViewSelected() {
        try {
            txfWhiskyInfo.clear();
            String s = lvwWhiskyList.getSelectionModel().getSelectedItem().toString();
            txfWhiskyInfo.setText(s);
        }catch(Exception e){}

    }
    @FXML
    void btnBottleWhiskyAction(ActionEvent event) {
        try {
            int numberOfBottles = Integer.parseInt(txfNumberOfBottles.getText());
            double bottleSize = Double.parseDouble(txfBottleSize.getText());

            if(numberOfBottles < 0 || bottleSize < 0) throw new IllegalArgumentException();

            WhiskyProduct selected = lvwWhiskyList.getSelectionModel().getSelectedItem();
            Controller.putOnBottle(selected,numberOfBottles,bottleSize);

            txfWhiskyInfo.clear();
            lvwWhiskyList.getItems().clear();
            lvwWhiskyList.refresh();
            lvwWhiskyList.getItems().setAll(
                    Controller.getWhiskyProducts()
            );

            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setResizable(true);
            info.setTitle("Success");
            info.setHeaderText("The whisky has been taped on to bottles.");
            info.setContentText(numberOfBottles + " registered");
            info.show();
        } catch (Exception e){
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("An error has occured");
            err.setHeaderText("The following issues have been detected");
            err.setContentText("Please cheek number of bottle and bottle size fields.");
            err.show();
        }
    }

    @FXML
    void openRegistrationWindow(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(FXMLWhiskyRegistration);
        Stage stage = new Stage();
        stage.setMinWidth(root.minWidth(-1));
        stage.setMinHeight(root.minHeight(-1));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showHistoryDialog(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(FXMLWhiskyHistory);
        Stage stage = new Stage();
        stage.setMinWidth(root.minWidth(-1));
        stage.setMinHeight(root.minHeight(-1));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
