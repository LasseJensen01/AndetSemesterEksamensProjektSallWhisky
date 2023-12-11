package gui;

import controller.Controller;
import controller.Storage;
import javafx.application.Application;
import storage.ListStorage;
//import storage.Storage;

public class Main {
    public static void main(String[] args) {

        Storage storage = ListStorage.loadStorage();
        if (storage == null)storage = new ListStorage();
        Controller.setStorage(storage);
        Controller.loadIdsFromTracker();
        Controller.initTestStorage();

        Application.launch(Gui.class);

        Controller.saveIdsToTracker();
        //ListStorage.saveStorage(storage);
    }
}