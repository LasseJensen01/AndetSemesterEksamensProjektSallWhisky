package model;

import controller.Controller;
import controller.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ListStorage;

public class WarehouseTest {
    @BeforeEach
    void setUpEmptyStorage(){
        Storage storage = new ListStorage();
        Controller.setStorage(storage);
    }

    @Test
    void warehouseExtractOverview(){

    }
}
