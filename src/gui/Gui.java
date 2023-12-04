package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.NoSuchElementException;

public class Gui extends Application {

   private static URL FXMLFarmer;

    @Override
    public void start(Stage stage) throws Exception {
        URL fxmlFileName = this.getClass().getResource("GUIskitse.fxml");
        if (fxmlFileName == null) throw new NoSuchElementException("FXML file not found");

        Parent root = FXMLLoader.load(fxmlFileName);
        stage.setMinWidth(root.minWidth(-1));
        stage.setMinHeight(root.minHeight(-1));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        //Initializer FXMLAddFarmerGUI
        URL fxmlFileName2 = this.getClass().getResource("AddFarmerGUI.fxml");
        if (fxmlFileName == null) throw new NoSuchElementException("FXML file not found");
        FXMLFarmer = fxmlFileName2;
    }

    public static URL getFXMLFarmer() {
        return FXMLFarmer;
    }
}
