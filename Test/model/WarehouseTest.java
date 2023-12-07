package model;

import controller.Controller;
import controller.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ListStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WarehouseTest {
    @BeforeEach
    void setUpEmptyStorage(){
        Storage storage = new ListStorage();
        Controller.setStorage(storage);
    }

    @Test
    void warehouseExtractOverview(){
        //Clear file WarehouseOverview.txt
       try{
           File file = new File("src\\model\\WarehouseOverview.txt");
           file.delete();
       }catch (Exception e){

       }
       //Setup
        Storage listStorage = new ListStorage();
        Controller.setStorage(listStorage);

        Farmer f = new Farmer("Frede", "Sønderhøj");

        Field field = new Field("Mark",f);

        MaltBatch mb = new MaltBatch("NordFyn","Corn",field);

        NewMake nm1 = new NewMake(LocalDate.now(),"LJ",mb);

        Cask cask1 = new Cask(Type.VIRGIN_OAK, 30, "Big Barrel");

        Filling fill1 = new Filling(cask1,"LJ");

        Amount a = new Amount(nm1,30);

        Controller.addAmountToFilling(fill1,a);

        Warehouse wh = new Warehouse("Sall","Sall");
        Controller.createLocationsInWarehouse(wh,10,6,3,3);

        wh.getLocations().get(0).setCask(cask1);

        wh.extractOverview();

        //Test
        try{
            File file = new File("src\\model\\WarehouseOverview.txt");
            assertTrue(file.isFile());
            assertTrue(file.exists());

        }catch (Exception e){
            System.err.println("Error when testing Warehouse extractOverview: " + e.getMessage());
        }
    }

    @Test
    void warehouseMoveCask(){
        Warehouse wh = Controller.createWarehouse("Sall", "Sall");
        Controller.createLocationsInWarehouse(wh,10,6,3,3);
        //Check that all locations dont have a cask
        List<Location> locations = wh.getLocations();
        for (Location l : locations){
            assertTrue(l.getCask() == null);
        }
        Location location1 = wh.getLocations().get(0);
        Location location2 = wh.getLocations().get(1);
        //Check that a cask can be moved into a location
        Cask cask1 = Controller.createCask(Type.VIRGIN_OAK, 30,"Big Barrel");
        Controller.moveCask(wh,cask1,location1);

        //Test if the cask is on the location
        assertEquals(cask1, wh.getLocations().get(0).getCask());

        //Test if the location is on the cask
        assertEquals(location1, cask1.getLocation());

        //Move cask to location 2
        Controller.moveCask(wh,cask1,location2);

        //Test if the cask is on the new location
        assertEquals(cask1, wh.getLocations().get(1).getCask());

        //Test if the new location is on the cask
        assertEquals(location2, cask1.getLocation());

        //Test if the old location has been freed up
        assertTrue(wh.getLocations().get(0).getCask() == null);
    }
}
