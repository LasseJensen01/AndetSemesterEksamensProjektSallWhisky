package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.WhiskyProduct;

import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

public class WhiskyTabController{
    private static URL FXMLWhiskyRegistration;
    @FXML
    private Button btnBottleWhisky;

    @FXML
    private Button btnRegisterNewWhisky;

    @FXML
    private Button btnStoreWhisky;

    @FXML
    private ListView<WhiskyProduct> lvwWhiskyList;

    @FXML
    private TextArea txfWhiskyInfo;

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
        System.out.println("init");
    }

    private void listViewSelected() {
        txfWhiskyInfo.clear();
        txfWhiskyInfo.setText("HELLO");

    }

    @FXML
    private void registerWhiskeyAction() throws Exception{

    }

    @FXML
    void openRegistrationWindow(ActionEvent event) throws Exception{
        System.out.println(FXMLWhiskyRegistration.toString());
        Parent root = FXMLLoader.load(FXMLWhiskyRegistration);
        Stage stage = new Stage();
        stage.setMinWidth(root.minWidth(-1));
        stage.setMinHeight(root.minHeight(-1));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
