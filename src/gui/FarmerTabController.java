package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class FarmerTabController {
    @FXML
    private Button btnAddFarmer;

    @FXML
    private ListView<?> lwFarmers;

    @FXML
    private ListView<?> lwInfo;

    @FXML
    private void addFarmerAction() throws Exception{
        Parent root = FXMLLoader.load(Gui.getFXMLFarmer());
        Stage stage = new Stage();
        stage.setMinWidth(root.minWidth(-1));
        stage.setMinHeight(root.minHeight(-1));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
