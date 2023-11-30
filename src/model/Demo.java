package model;

import controller.Controller;
import storage.ListStorage;

import java.time.LocalDate;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        Controller.setStorage(new ListStorage());
        Warehouse wh = Controller.createWarehouse("sall", "sall");
        Controller.createLocationsInWarehouse(wh, 01, 01, 01);
    }
}
