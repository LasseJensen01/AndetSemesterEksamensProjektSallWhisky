package controller;

import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ListStorage;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @BeforeEach
    void setUpEmptyStorage(){
        Storage storage = new ListStorage();
        Controller.setStorage(storage);
    }
    @Test
    void tapToXnumOfBottels() {
        //Might be v2 leave for now.
        //Might also be redundant return later...
    }

    @Test
    void tapWholeCaskToBottels() {
        // Case 1
        // Arrange
        Cask cask = Controller.createCask(Type.MANZANILLA, 100);
        Filling filling = Controller.createFilling(cask, "Robert");
        NewMake newMake = Controller.createNewMake("NV,81", 250);
        Amount amount = Controller.createAmount(newMake,80);
        Controller.addAmountToFilling(filling, amount);
        Bottle bottle = Controller.tapWholeCaskToBottels(cask,0.8,"Jonathan");

        // Act
        boolean isStored = Controller.getBottels().contains(bottle);
        double caskLiters = cask.getLiters();
        int noOfBottels = bottle.getNoOfBottels();

        // Assert
        assertTrue(isStored);
        assertEquals(0,caskLiters);
        assertEquals(100,noOfBottels);


        // Case 2
        // Arrange
        cask = Controller.createCask(Type.MANZANILLA, 100);
        filling = Controller.createFilling(cask, "Beth");
        newMake = Controller.createNewMake("NV,41", 200);
        amount = Controller.createAmount(newMake,80);
        Controller.addAmountToFilling(filling, amount);
        bottle = Controller.tapWholeCaskToBottels(cask,0.7,"Martin");

        // Act
        isStored = Controller.getBottels().contains(bottle);
        caskLiters = cask.getLiters();
        noOfBottels = bottle.getNoOfBottels();

        // Assert
        assertTrue(isStored);
        assertEquals(0,caskLiters);
        assertEquals(114,noOfBottels);
    }

    @Test
    void addAmountToFilling() {
        // Case 1
        // Arrange
        Cask cask = Controller.createCask(Type.PEDRO_XIMÉNEZ, 120);
        Filling filling = Controller.createFilling(cask, "Robert");
        NewMake newMake = Controller.createNewMake("NV,81", 250);
        Amount amount1 = Controller.createAmount(newMake,80);
        Amount amount2 = Controller.createAmount(newMake,50);
        Controller.addAmountToFilling(filling, amount1);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Controller.addAmountToFilling(filling, amount2));
        assertEquals(exception.getClass(), IllegalArgumentException.class);
    }

    @Test
    void tapFromMultipleCasksToBottels() {
        // Case 1
        // Arrange
        Cask cask1 = Controller.createCask(Type.MANZANILLA, 100);
        Filling filling1 = Controller.createFilling(cask1, "Janus");
        NewMake newMake1 = Controller.createNewMake("NV,81", 250);
        Amount amount1 = Controller.createAmount(newMake1,80);
        Controller.addAmountToFilling(filling1, amount1);

        Cask cask2 = Controller.createCask(Type.BURGUNDY, 120);
        Filling filling2 = Controller.createFilling(cask2, "Janus");
        NewMake newMake2 = Controller.createNewMake("NV,46", 180);
        Amount amount2 = Controller.createAmount(newMake2,100);
        Controller.addAmountToFilling(filling2, amount2);

        HashMap<Cask, Double> casks = new HashMap<>();
        casks.put(cask1, Double.valueOf(60));
        casks.put(cask2, Double.valueOf(40));

        Bottle bottle = Controller.tapFromMultipleCasksToBottels(casks, 0.7, "Emma");

        // Act
        boolean isStored = Controller.getBottels().contains(bottle);
        double cask1LitersLeft = cask1.getLiters();
        double cask2LitersLeft = cask2.getLiters();
        int noOfBottels = bottle.getNoOfBottels();
        double percentOfWhiskyfromCask1 = bottle.getFillings().get(filling1);
        double percentOfWhiskyfromCask2 = bottle.getFillings().get(filling2);

        // Assert
        assertTrue(isStored);
        assertEquals(20, cask1LitersLeft);
        assertEquals(60, cask2LitersLeft);
        assertEquals(142,noOfBottels);
        assertEquals(0.6,percentOfWhiskyfromCask1);
        assertEquals(0.4,percentOfWhiskyfromCask2);

        // Case 2 Error incase of invalid combination of cask and liters
        // Arrange
        HashMap<Cask, Double> invalidCasks = new HashMap<>();
        invalidCasks.put(cask1, Double.valueOf(10));
        invalidCasks.put(cask2, Double.valueOf(100));

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Controller.tapFromMultipleCasksToBottels(invalidCasks, 0.7, "Frank"));
        assertEquals(exception.getClass(), IllegalArgumentException.class);
    }
}