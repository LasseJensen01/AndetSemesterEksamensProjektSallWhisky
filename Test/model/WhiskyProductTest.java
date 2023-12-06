package model;

import controller.Controller;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class WhiskyProductTest {

    @Test
    void calcAlcoholPercentage() {
        // Case 1: No water, three newMakes, two casks.
        // Arrange
        Farmer farmer = new Farmer("Lars T", "Hvor kragerne vender.");
        Field field = new Field("By the river.", farmer);
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

        HashMap<Filling, Double> fillings = new HashMap<>();
        fillings.put(fillingA,Double.valueOf(100));
        fillings.put(fillingB,Double.valueOf(200));

        WhiskyProduct whiskyProduct = new WhiskyProduct(fillings,"Wiskers");

        // Assert
        boolean caskStrength = whiskyProduct.getWater() == 0;
        double alcPercent = whiskyProduct.getAlcoholPercent();

        // Act
        assertTrue(caskStrength);
        assertEquals(0.7,alcPercent);

        //Case 2: Same as case 1, but with water added.
        // Arrange
        whiskyProduct.addWater(100);

        // Assert
        caskStrength = whiskyProduct.getWater() == 0;
        alcPercent = whiskyProduct.getAlcoholPercent();

        // Act
        assertFalse(caskStrength);
        assertEquals(0.525,alcPercent);
    }

    @Test
    void getFullProductionHistory() {
        //Arrange
        Farmer farmer = new Farmer("Lars T", "Hvor kragerne vender.");
        Field field = new Field("By the river.", farmer);
        MaltBatch maltBatch = new MaltBatch("North Jutland","Byg", field);
        NewMake newMake77 = new NewMake(LocalDate.now(), "Jonas", maltBatch);
        newMake77.setAlcPercent(0.80);
        NewMake newMake78 = new NewMake(LocalDate.now(), "Jonas", maltBatch);
        newMake78.setAlcPercent(0.70);
        NewMake newMake79 = new NewMake(LocalDate.now(), "Jonas", maltBatch);
        newMake79.setAlcPercent(0.60);

        Cask caskA = new Cask(Type.AMARONE, 200);
        Filling fillingA = new Filling(caskA, "Jonas");
        fillingA.setDate(LocalDate.now().minusYears(3));
        Amount amountA = new Amount(newMake77, 100);
        fillingA.addAmount(amountA);

        Cask caskB = new Cask(Type.BAROLO, 200);
        Filling fillingB = new Filling(caskB, "Jonas");
        fillingB.setDate(LocalDate.now().minusYears(4));
        Amount amountB = new Amount(newMake78, 100);
        fillingB.addAmount(amountB);
        Amount amountC = new Amount(newMake79, 100);
        fillingB.addAmount(amountC);

        HashMap<Filling, Double> fillings = new HashMap<>();
        fillings.put(fillingA,Double.valueOf(100));
        fillings.put(fillingB,Double.valueOf(200));

        WhiskyProduct whiskyProduct = new WhiskyProduct(fillings,"Wiskers");
        System.out.println(whiskyProduct.getFullProductionHistory());
    }
}