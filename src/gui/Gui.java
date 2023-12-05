package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.NoSuchElementException;

public class Gui extends Application {

   private static URL FXMLFarmer;

    @Override
    public void start(Stage stage) throws Exception {

        TabPane tabpane = new TabPane();

        //Pane1 Farmers
        URL farmerPane = this.getClass().getResource("resources\\FarmerTab.fxml");
        if (farmerPane == null) throw new NoSuchElementException("farmerPane not found");
        Parent farmerPaneRoot = FXMLLoader.load(farmerPane);
        Tab farmerTab = new Tab("Farmers", farmerPaneRoot);
        farmerTab.setClosable(false);
        tabpane.getTabs().add(farmerTab);

        //Initializer FXMLAddFarmerGUI
        URL addFarmerDialog = this.getClass().getResource("resources\\AddFarmerDialog.fxml");
        if (addFarmerDialog == null) throw new NoSuchElementException("addFarmerDialog not found");
        FXMLFarmer = addFarmerDialog;

        //Pane2 NewMake
        URL newMakePane = this.getClass().getResource("resources\\NewMakeTab.fxml");
        if (newMakePane == null) throw new NoSuchElementException("farmerPane not found");
        Parent newMakePaneRoot = FXMLLoader.load(newMakePane);
        Tab newMakeTab = new Tab("Register NewMake", newMakePaneRoot);
        newMakeTab.setClosable(false);
        tabpane.getTabs().add(newMakeTab);

        //Pane 3 Casks
        URL casksPane = this.getClass().getResource("resources\\CasksTab.fxml");
        if(casksPane == null) throw new NoSuchElementException("caskPane not found");
        Parent casksPaneRoot = FXMLLoader.load(casksPane);
        Tab casksTab = new Tab("Casks",casksPaneRoot);
        casksTab.setClosable(false);
        tabpane.getTabs().add(casksTab);

        //Pane 4 Fillings
        URL fillingsPane = this.getClass().getResource("resources\\FillingsTab.fxml");
        if(fillingsPane == null) throw new NoSuchElementException("fillingsPane not found");
        Parent fillingsPaneRoot = FXMLLoader.load(fillingsPane);
        Tab fillingsTab = new Tab("Fillings",fillingsPaneRoot);
        fillingsTab.setClosable(false);
        tabpane.getTabs().add(fillingsTab);

        //Pane 5 Whisky
        URL whiskyPane = this.getClass().getResource("resources\\WhiskyTab.fxml");
        if(fillingsPane == null) throw new NoSuchElementException("whiskyPane not found");
        Parent whiskyPaneRoot = FXMLLoader.load(whiskyPane);
        Tab whiskyTab = new Tab("Whisky",whiskyPaneRoot);
        whiskyTab.setClosable(false);
        tabpane.getTabs().add(whiskyTab);

        //Pane 6
        URL warehousePane = this.getClass().getResource("resources\\WarehouseTab.fxml");
        if(fillingsPane == null) throw new NoSuchElementException("warehousePane not found");
        Parent warehousePaneRoot = FXMLLoader.load(warehousePane);
        Tab warehouseTab = new Tab("Warehouse",warehousePaneRoot);
        warehouseTab.setClosable(false);
        tabpane.getTabs().add(warehouseTab);

        // Setup TabPane
        Parent root = new AnchorPane(tabpane);
        Scene scene = new Scene(root);

        stage.setTitle("Sall Whisky System");
        stage.setScene(scene);
        stage.show();

        Tab temp = tabpane.getTabs().get(tabpane.getTabs().size()-1);
        var t = temp.getContent();
        System.out.println("Fuck");
    }

    public static URL getFXMLFarmer() {
        return FXMLFarmer;
    }
}
