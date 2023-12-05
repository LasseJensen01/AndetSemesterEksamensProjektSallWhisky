package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;

public class WhiskyTabController {

    @FXML
    private Button btnBottleWhisky;

    @FXML
    private Button btnRegisterNewWhisky;

    @FXML
    private Button btnStoreWhisky;

    @FXML
    private ListView<?> lvwWhiskyList;

    @FXML
    private TextArea txfWhiskyInfo;

    @FXML
    private void registerWhiskeyAction() throws Exception{
        Parent root = FXMLLoader.load(Gui.getFXMLWhiskyRegistration());
        Stage stage = new Stage();
        stage.setMinWidth(root.minWidth(-1));
        stage.setMinHeight(root.minHeight(-1));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
