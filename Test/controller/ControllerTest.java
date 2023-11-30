package controller;

import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ListStorage;

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
}