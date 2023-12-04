package controller;

import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ListStorage;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @BeforeEach
    void setUpEmptyStorage(){
        Storage storage = new ListStorage();
        Controller.setStorage(storage);
    }

    @Test
    void createWhiskyProduct() {
        // Case 1: Simple create
        // Arrange
        Farmer farmer = new Farmer("Lars T", "Hvor kragerne vender.");
        Field field = new Field("Of green.", farmer);
        MaltBatch maltBatch = new MaltBatch("Søren Ryge","Nord Jylland","Byg", field);
        NewMake newMake77 = new NewMake("NM.77", LocalDate.now(), "Jonas", maltBatch);
        newMake77.setAlcPercent(0.80);
        NewMake newMake78 = new NewMake("NM.78", LocalDate.now(), "Jonas", maltBatch);
        newMake78.setAlcPercent(0.70);
        NewMake newMake79 = new NewMake("NM.79", LocalDate.now(), "Jonas", maltBatch);
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
        MaltBatch maltBatch = new MaltBatch("Søren Ryge","Nord Jylland","Byg", field);
        NewMake newMake77 = new NewMake("NM.77", LocalDate.now(), "Jonas", maltBatch);
        newMake77.setAlcPercent(0.80);
        NewMake newMake78 = new NewMake("NM.78", LocalDate.now(), "Jonas", maltBatch);
        newMake78.setAlcPercent(0.70);
        NewMake newMake79 = new NewMake("NM.79", LocalDate.now(), "Jonas", maltBatch);
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
    void locateFullCask(){
        for (int i = 0; i < 5; i++) {
            Cask c = Controller.createCask(Type.VIRGIN_OAK,30);
            c.setFilling(new Filling(c,"LJ"));
            c.setLiters(10);
        }
        Cask cask1 = Controller.createCask(Type.BEER, 55);
        cask1.setFilling(new Filling(cask1,"LJ"));
        cask1.setLiters(10);

        //Check if cask is correct
        assertEquals(cask1, Controller.locateFullCask(Type.BEER,null,null,null).get(0));
        //Check to see if non requested casks are returned
        assertEquals(1, Controller.locateFullCask(Type.BEER,null,null,null).size());

        //Check with volume instead of Type

        //Check if cask is correct
        assertEquals(cask1, Controller.locateFullCask(null,55.0,null,null).get(0));
        //Check to see if non requested casks are returned
        assertEquals(1, Controller.locateFullCask(null,55.0,null,null).size());

    }
}