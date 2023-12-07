package controller;

import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ListStorage;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @BeforeEach
    void setUpEmptyStorage(){
        Storage storage = new ListStorage();
        Controller.setStorage(storage);
    }
    @Test
    void formalCreateWhiskyProductTest(){
        /*
        * See the report for documentation of the test cases
        * */
        //
        Farmer farmer = new Farmer("Lars", "Where the crows turn.");
        Field field = new Field("Stadsgaard.", farmer);
        MaltBatch maltBatch = new MaltBatch("Smoke","Barley", field);
        NewMake newMake77 = new NewMake(LocalDate.now(), "Jonas", maltBatch);
        newMake77.setAlcPercent(0.80);
        NewMake newMake78 = new NewMake(LocalDate.now(), "Jonas", maltBatch);
        newMake78.setAlcPercent(0.70);
        NewMake newMake79 = new NewMake(LocalDate.now(), "Jonas", maltBatch);
        newMake79.setAlcPercent(0.60);

        Cask caskA = new Cask(Type.AMARONE, 200);
        Filling fillingA = new Filling(caskA, "Jonas");
        Amount amountA = new Amount(newMake77, 100);
        fillingA.addAmount(amountA);

        Cask caskB = new Cask(Type.BAROLO, 200);
        Filling fillingB = new Filling(caskB, "Jonas");
        Amount amountB = new Amount(newMake78, 100);
        fillingB.addAmount(amountB);
        Amount amountC = new Amount(newMake79, 100);
        fillingB.addAmount(amountC);

        // Test case 1:
        // Arrange
        HashMap<Cask, Double> testParam1 = new HashMap<>();

        // Act & Assert
        Exception tc1 = assertThrows(IllegalArgumentException.class, () ->
                Controller.CreateWhiskyProduct(testParam1,"Test Whiskey"));
        assertEquals(IllegalArgumentException.class, tc1.getClass());


        // Test case 2:
        // Arrange
        HashMap<Cask, Double> testParam2 = new HashMap<>();
        testParam2.put(caskA, Double.valueOf(110));

        // Act & Assert
        Exception tc2 = assertThrows(IllegalArgumentException.class, () ->
                Controller.CreateWhiskyProduct(testParam1,"Test Whiskey"));
        assertEquals(IllegalArgumentException.class, tc2.getClass());


        // Test case 3:
        // Arrange
        HashMap<Cask, Double> casks = new HashMap<>();
        casks.put(caskA,Double.valueOf(80));
        casks.put(caskB,Double.valueOf(200));

        WhiskyProduct whiskyProduct = Controller.CreateWhiskyProduct(casks,"Whiskers");

        // Act
        boolean isStored = Controller.getWhiskyProducts().contains(whiskyProduct);
        double alcPercent = whiskyProduct.getAlcoholPercent();
        double totalLiters = whiskyProduct.getLiters();

        double caskALiters = caskA.getLiters();
        double caskBLiters = caskB.getLiters();

        // Assert
        assertTrue(isStored);
        assertEquals(0.692,alcPercent,0.001);
        assertEquals(280,totalLiters);
        assertEquals(20, caskALiters);
        assertEquals(0, caskBLiters);
    }
    @Test
    void createWhiskyProduct() {
        /*
        * This test was written during the coding process. It is not the one formal test refferd to in the documentation.
        * It belongs to the previous iteration.
        * */
        // Case 1: Simple create
        // Arrange
        Farmer farmer = new Farmer("Lars T", "Hvor kragerne vender.");
        Field field = new Field("Of green.", farmer);
        MaltBatch maltBatch = new MaltBatch("Søren Ryge","Byg", field);
        NewMake newMake77 = new NewMake(LocalDate.now(), "Jonas", maltBatch);
        newMake77.setAlcPercent(0.80);
        NewMake newMake78 = new NewMake(LocalDate.now(), "Jonas", maltBatch);
        newMake78.setAlcPercent(0.70);
        NewMake newMake79 = new NewMake(LocalDate.now(), "Jonas", maltBatch);
        newMake79.setAlcPercent(0.60);

        Cask caskA = new Cask(Type.AMARONE, 200);
        Filling fillingA = new Filling(caskA, "Jonas");
        Amount amountA = new Amount(newMake77, 100);
        fillingA.addAmount(amountA);

        Cask caskB = new Cask(Type.BAROLO, 200);
        Filling fillingB = new Filling(caskB, "Jonas");
        Amount amountB = new Amount(newMake78, 100);
        fillingB.addAmount(amountB);
        Amount amountC = new Amount(newMake79, 100);
        fillingB.addAmount(amountC);

        HashMap<Cask, Double> casks = new HashMap<>();
        casks.put(caskA,Double.valueOf(80));
        casks.put(caskB,Double.valueOf(200));

        WhiskyProduct whiskyProduct = Controller.CreateWhiskyProduct(casks,"Whiskers");

        // Act
        boolean isStored = Controller.getWhiskyProducts().contains(whiskyProduct);
        boolean caskStrength = whiskyProduct.getWater() == 0;
        double alcPercent = whiskyProduct.getAlcoholPercent();
        double totalLiters = whiskyProduct.getLiters();

        double caskALiters = caskA.getLiters();
        double caskBLiters = caskB.getLiters();

        // Assert
        assertTrue(isStored);
        assertTrue(caskStrength);
        assertEquals(0.692,alcPercent,0.001);
        assertEquals(280,totalLiters);
        assertEquals(20, caskALiters);
        assertEquals(0, caskBLiters);
    }

    @Test
    void putOnBottle() {
        // Case 1: Simple create
        // Arrange
        Farmer farmer = new Farmer("Lars T", "Hvor kragerne vender.");
        Field field = new Field("By the river.", farmer);
        MaltBatch maltBatch = new MaltBatch("Søren Ryge","Byg", field);
        NewMake newMake77 = new NewMake( LocalDate.now(), "Jonas", maltBatch);
        newMake77.setAlcPercent(0.80);
        NewMake newMake78 = new NewMake( LocalDate.now(), "Jonas", maltBatch);
        newMake78.setAlcPercent(0.70);
        NewMake newMake79 = new NewMake( LocalDate.now(), "Jonas", maltBatch);
        newMake79.setAlcPercent(0.60);

        Cask caskA = new Cask(Type.AMARONE, 200);
        Filling fillingA = new Filling(caskA, "Jonas");
        Amount amountA = new Amount(newMake77, 100);
        fillingA.addAmount(amountA);

        Cask caskB = new Cask(Type.BAROLO, 200);
        Filling fillingB = new Filling(caskB, "Jonas");
        Amount amountB = new Amount(newMake78, 100);
        fillingB.addAmount(amountB);
        Amount amountC = new Amount(newMake79, 100);
        fillingB.addAmount(amountC);

        HashMap<Cask, Double> casks = new HashMap<>();
        casks.put(caskA,Double.valueOf(80));
        casks.put(caskB,Double.valueOf(200));

        WhiskyProduct whiskyProduct = Controller.CreateWhiskyProduct(casks,"Whiskers");

        Controller.putOnBottle(whiskyProduct,400,0.7);

        // Act
        int numberOfBottlesStored = Controller.getBottels().size();
        double whiskyLeft = whiskyProduct.getLiters();

        // Assert
        assertEquals(400,numberOfBottlesStored);
        assertEquals(0, whiskyLeft);

        // Case 2: partial tap
        // Arrage
        whiskyProduct.setLiters(280);
        Controller.putOnBottle(whiskyProduct,200,0.7);

        // Act
        numberOfBottlesStored = Controller.getBottels().size();
        whiskyLeft = whiskyProduct.getLiters();

        // Assert
        assertEquals(600,numberOfBottlesStored);
        assertEquals(140, whiskyLeft);

        // Case 3: Imposible tap
        // Three for one! The first two are already done!
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                Controller.putOnBottle(whiskyProduct,300, 0.7));
        assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @Test
    void locateEmptyCask(){
        //This test was made during the coding process
        for (int i = 0; i < 5; i++) {
            Controller.createCask(Type.VIRGIN_OAK,30);
            Controller.createCask(Type.BOURBON, 30);
            Controller.createCask(Type.BOURBON, 45);
        }
        Cask cask1 = Controller.createCask(Type.BEER, 55);
        //Check type
        //Check to see if cask is found
        assertEquals(cask1, Controller.locateEmptyCask(Type.BEER,null,null,null).get(0));
        //Check to see if non expected casks are on list
        assertEquals(1, Controller.locateEmptyCask(Type.BEER,null,null,null).size());

        //Check Volume
        //Check to see if cask is found
        assertEquals(cask1, Controller.locateEmptyCask(null,55.0,null,null).get(0));
        //Check to see if non expected casks are on list
        assertEquals(1, Controller.locateEmptyCask(null,55.0,null,null).size());
    }

    @Test
    void formalLocateEmptyCask(){
        //Setup

        //Casks & TimesUsed
        Cask caskA = Controller.createCask(Type.VIRGIN_OAK,100);
        Cask caskB = Controller.createCask(Type.BOURBON, 150);
        Cask caskC = Controller.createCask(Type.VIRGIN_OAK, 120);

        caskA.setTimesUsed(1);
        caskB.setTimesUsed(2);
        caskC.setTimesUsed(0);

        //Test

        //Test Case 1
        List<Cask> l = Controller.locateEmptyCask(null, null,null,null);
        //Check if the expected amount of cask are returned by the method
        assertEquals(3,l.size());
        //Check if the one cask is the expected cask
        assertEquals(caskA,l.get(0));
        assertEquals(caskB,l.get(1));
        assertEquals(caskC,l.get(2));

        //Test Case 2
        l = Controller.locateEmptyCask(Type.VIRGIN_OAK, null,null,null);
        //Check if the expected amount of cask are returned by the method
        assertEquals(2,l.size());
        //Check if the one cask is the expected cask
        assertEquals(caskA,l.get(0));
        assertEquals(caskC,l.get(1));

        //Test Case 3
        l = Controller.locateEmptyCask(null, 120.0,null,null);
        //Check if the expected amount of cask are returned by the method
        assertEquals(1,l.size());
        //Check if the one cask is the expected cask
        assertEquals(caskC,l.get(0));

        //Test Case 4
        l = Controller.locateEmptyCask(null, null,2,null);
        //Check if the expected amount of cask are returned by the method
        assertEquals(1,l.size());
        //Check if the one cask is the expected cask
        assertEquals(caskB,l.get(0));

        //Test Case 5
        l = Controller.locateEmptyCask(null, null,null,1);
        //Check if the expected amount of cask are returned by the method
        assertEquals(1,l.size());
        //Check if the one cask is the expected cask
        assertEquals(caskA,l.get(0));

        //Test Case 6
        l = Controller.locateEmptyCask(Type.BOURBON, 150.0,null,null);
        //Check if the expected amount of cask are returned by the method
        assertEquals(1,l.size());
        //Check if the one cask is the expected cask
        assertEquals(caskB,l.get(0));

        //Test Case 7
        l = Controller.locateEmptyCask(Type.TOKAY, 99.0,null,3);
        //Check if the expected amount of cask are returned by the method
        assertTrue(l.isEmpty());
    }

    @Test
    void formalLocateFullCask(){
        //Setup
        //Farmer, Field & Maltbatch
        Farmer farmerA = new Farmer("Test", "Test");
        Field fieldA = new Field("Test", farmerA);
        MaltBatch maltBatchA = new MaltBatch("Test","Test",fieldA);

        //NewMakes & Amounts
        NewMake newMakeA = new NewMake(LocalDate.now().minusYears(3),"Test", maltBatchA);
        NewMake newMakeB = new NewMake( LocalDate.now(), "Test", maltBatchA);

        Amount amountA = new Amount(newMakeA, 50);
        Amount amountB = new Amount(newMakeB, 50);

        //Casks & Filling
        Cask caskA = Controller.createCask(Type.VIRGIN_OAK,100);
        Cask caskB = Controller.createCask(Type.BOURBON, 150);
        Cask caskC = Controller.createCask(Type.VIRGIN_OAK, 120);
        Filling fillingA = Controller.createFilling(caskA,"test");
        fillingA.setDate(LocalDate.now().minusYears(3));
        Filling fillingB = Controller.createFilling(caskB,"test");
        Filling fillingC = Controller.createFilling(caskC, "test");

        caskA.setTimesUsed(1);
        caskB.setTimesUsed(2);
        caskC.setTimesUsed(0);

        Controller.addAmountToFilling(fillingA, amountA);
        Controller.addAmountToFilling(fillingB, amountB);
        Controller.addAmountToFilling(fillingC, amountB);

        //Test

        //Test Case 1
        List<Cask> l = Controller.locateFullCask(true,null, null,null,null);
        //Check if the expected amount of cask are returned by the method
        assertEquals(1,l.size());
        //Check if the one cask is the expected cask
        assertEquals(caskA,l.get(0));

        //Test Case 2
        l = Controller.locateFullCask(false,null, null,null,null);
        //Check if the expected amount of cask are returned by the method
        assertEquals(3, l.size());
        //Check if all casks are there
        assertEquals(caskA,l.get(0));
        assertEquals(caskB,l.get(1));
        assertEquals(caskC,l.get(2));

        //Test Case 3
        l = Controller.locateFullCask(false,Type.VIRGIN_OAK, null,null,null);
        //Check if the expected amount of casks are returned by the method
        assertEquals(2,l.size());
        //Check if the casks are correct
        assertEquals(caskA, l.get(0));
        assertEquals(caskC, l.get(1));

        //Test Case 4
        l = Controller.locateFullCask(false,null, 120.0,null,null);
        //Check if the expected amount of casks are returned by the method
        assertEquals(1,l.size());
        //Check for if its the right cask
        assertEquals(caskC, l.get(0));

        //Test Case 5
        l = Controller.locateFullCask(false,null, null,2,null);
        //Check if the expected amount of casks are returned by the method
        assertEquals(1,l.size());
        //Check for if its the right cask
        assertEquals(caskB, l.get(0));

        //Test Case 6
        l = Controller.locateFullCask(false,null, null,null,1);
        //Check if the expected amount of casks are returned by the method
        assertEquals(1,l.size());
        //Check for if its the right cask
        assertEquals(caskA, l.get(0));

        //Test Case 7
        l = Controller.locateFullCask(false,Type.BOURBON, 150.0,null,null);
        //Check if the expected amount of casks are returned by the method
        assertEquals(1,l.size());
        //Check for if its the right cask
        assertEquals(caskB, l.get(0));

        //Test Case 8
        l = Controller.locateFullCask(false,Type.TOKAY, 99.0,null,3);
        //Check if the expected amount of cask are returned by the method
        assertTrue(l.isEmpty());
    }
    @Test
    void createWarehouse(){
        //Setup
        Warehouse warehouse = Controller.createWarehouse("test", "test");

        assertEquals(warehouse, Controller.getWarehouses().get(0));
    }
}