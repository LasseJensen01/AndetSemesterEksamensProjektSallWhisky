import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class GUIskitseController {

    @FXML
    private Button btnAddCask;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnSupplier;

    @FXML
    private ChoiceBox<?> cboxGrain;

    @FXML
    private ChoiceBox<?> cboxMaltBatch;

    @FXML
    private ChoiceBox<?> cboxResponsibleEmployee;

    @FXML
    private ChoiceBox<?> cboxSmokeMaterial;

    @FXML
    private ComboBox<?> cboxType;

    @FXML
    private DatePicker datePickerEnd;

    @FXML
    private DatePicker datePickerStart;

    @FXML
    private ListView<?> lvwCaskInfo;

    @FXML
    private ListView<?> lvwCasks;

    @FXML
    private ListView<?> lvwResults;

    @FXML
    private ListView<?> lvwSupplierInfo;

    @FXML
    private ListView<?> lvwSuppliers;

    @FXML
    private TextArea txaComment;

    @FXML
    private TextField txfAlcoholPercent;

    @FXML
    private TextField txfCaskID;

    @FXML
    private TextField txfCaskSize;

    @FXML
    private TextField txfFillNo;

    @FXML
    private TextField txfIDNewMake;

    @FXML
    private TextField txfProducedAmount;

    @FXML
    private TextField txfSupplier;

    @FXML
    private Text txtAlcoholPercent;

    @FXML
    private Text txtCaskID;

    @FXML
    private Text txtCaskInfo;

    @FXML
    private Text txtCaskSize;

    @FXML
    private Text txtCasks;

    @FXML
    private Text txtComment;

    @FXML
    private Text txtEndDate;

    @FXML
    private Text txtFillNo;

    @FXML
    private Text txtGrain;

    @FXML
    private Text txtInfo;

    @FXML
    private Text txtMaltBatch;

    @FXML
    private Text txtNewMakID;

    @FXML
    private Text txtProducedAmount;

    @FXML
    private Text txtResponsibleEmployee;

    @FXML
    private Text txtSmokeMaterial;

    @FXML
    private Text txtStartDate;

    @FXML
    private Text txtSupplier;

    @FXML
    private Text txtSuppliers;

    @FXML
    private Text txtType;

}