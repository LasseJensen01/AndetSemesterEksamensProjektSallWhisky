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
    private ChoiceBox<String> cboxResponsibleEmployee;

    @FXML
    private DatePicker datePickerEnd;

    @FXML
    private DatePicker datePickerStart;

    @FXML
    private ListView<NewMake> lvwNewMakeinProgress = new ListView<>();

    @FXML
    private TextArea txaComment;

    @FXML
    private TextField txfAlcoholPercent;

    @FXML
    private TextField txfIDNewMake;

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
    private Text txtNewMakID;

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
    public void initialize(){
        cboxMaltBatch.getItems().setAll(Controller.getMaltBatches());
        lvwNewMakeinProgress.getItems().setAll(Controller.getNewMakes());
    }

    @FXML
    void registerAction(ActionEvent event) {
        LocalDate startDate = datePickerStart.getValue();
        MaltBatch maltBatch = cboxMaltBatch.getValue();
        String respEmp = txfResponsibleEmployee.getText();

        NewMake newMake = Controller.createNewMake(startDate,respEmp,maltBatch);
        lvwNewMakeinProgress.getItems().setAll(Controller.getNewMakes());
    }
}