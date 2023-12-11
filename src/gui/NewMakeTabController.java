package gui;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.MaltBatch;
import model.NewMake;

import java.time.LocalDate;

public class NewMakeTabController {

    @FXML
    private Button btnDone;

    @FXML
    private Button btnRegister;

    @FXML
    private ChoiceBox<MaltBatch> cboxMaltBatch;

    @FXML
    private DatePicker datePickerEnd;

    @FXML
    private DatePicker datePickerStart;

    @FXML
    private Label lblConfirmation = new Label();

    @FXML
    private ListView<NewMake> lvwNewMakeinProgress = new ListView<>();

    @FXML
    private TextArea txaComment;

    @FXML
    private TextField txfAlcoholPercent;

    @FXML
    private TextField txfProducedAmount;

    @FXML
    private TextField txfResponsibleEmployee;

    @FXML
    private Text txtAlcoholPercent;

    @FXML
    private Text txtComment;

    @FXML
    private Text txtEndDate;

    @FXML
    private Text txtFinish;

    @FXML
    private Text txtMaltBatch;

    @FXML
    private Text txtNewMakesInProgress;

    @FXML
    private Text txtProducedAmount;

    @FXML
    private Text txtResponsibleEmployee;

    @FXML
    private Text txtStart;

    @FXML
    private Text txtStartDate;
    @FXML
    private Label lblError;
    @FXML
    private Label lblErrorFinish;

    @FXML
    public void initialize(){
        update();
        lvwNewMakeinProgress.getItems().setAll(
                Controller.getNewMakes().stream().filter(newMake -> !newMake.isDone()).toList()
        );
    }

    @FXML
    void registerAction(ActionEvent event) {
        try {
            if (txfResponsibleEmployee.getText().isEmpty() || datePickerStart.getValue() == null || cboxMaltBatch.getSelectionModel().isEmpty()){
                throw new IllegalArgumentException();
            }
            LocalDate startDate = datePickerStart.getValue();
            MaltBatch maltBatch = cboxMaltBatch.getSelectionModel().getSelectedItem();
            String respEmp = txfResponsibleEmployee.getText();

            NewMake newMake = Controller.createNewMake(startDate,respEmp,maltBatch);
            lvwNewMakeinProgress.getItems().setAll(
                    Controller.getNewMakes().stream().filter(nW -> !nW.isDone()).toList()
            );
            lblError.setText("");

            txfResponsibleEmployee.clear();
            datePickerStart.getEditor().clear();
            cboxMaltBatch.getSelectionModel().clearSelection();
        }catch (Exception e){
           lblError.setText("Error: Check data");
        }
    }

    @FXML
    void doneAction(ActionEvent event){
        try {
            NewMake newMake = lvwNewMakeinProgress.getSelectionModel().getSelectedItem();

            LocalDate endDate = datePickerEnd.getValue();
            double producedAmount = Double.parseDouble(txfProducedAmount.getText());
            double alcoholdPercent = Double.parseDouble(txfAlcoholPercent.getText());

            if(txaComment.getText().isEmpty()){
                if (newMake.getStartDate().isAfter(endDate)) throw new IllegalArgumentException();
                Controller.finishNewMakeProcess(newMake,producedAmount,alcoholdPercent,endDate);
            }
            else{
                String comment = txaComment.getText();
                if (newMake.getStartDate().isAfter(endDate)) throw new IllegalArgumentException();
                Controller.finishNewMakeProcess(newMake,producedAmount,alcoholdPercent,endDate, comment);
            }

            lvwNewMakeinProgress.getItems().remove(newMake);
            lblErrorFinish.setText("");
            lblConfirmation.setText(newMake.toString() + "is registered");

            datePickerEnd.getEditor().clear();
            txfProducedAmount.clear();
            txfAlcoholPercent.clear();
            txaComment.clear();
            lblConfirmation.setText("");
        }
        catch (IllegalArgumentException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error in data");
            alert.setContentText("Start date is after end date");
            alert.showAndWait();
        }
        catch (Exception e){
            lblConfirmation.setText("");
            lblErrorFinish.setText("Error: Check data");
        }

    }
    @FXML
    void update(){
        try {
            cboxMaltBatch.getItems().setAll(Controller.getMaltBatches());
        }catch (Exception e){}
    }
}