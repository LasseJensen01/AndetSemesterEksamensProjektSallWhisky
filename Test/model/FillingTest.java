package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FillingTest {

    @Test
    void addAmount() {
        // Case 1: Adding the first amount
        // Assert
        Cask cask = new Cask(Type.BORDEAUX, 200);
        Filling filling = new Filling(cask, "Steven");
        Farmer farmer = new Farmer("Lars T", "Hvor kragerne vender.");
        Field field = new Field("By the river.", farmer);
        MaltBatch maltBatch = new MaltBatch("Søren Ryge","Nord Jylland","Byg", field);
        NewMake newMake77 = new NewMake("NM.77", LocalDate.now(), "Jonas", maltBatch);
        newMake77.setAlcPercent(0.80);
        Amount amount = new Amount(newMake77, 100);
        filling.addAmount(amount);

        // Act
        double liters = filling.getLiters();
        double alcPercentage = filling.getAlcoholPercent();
        int noOfAmonts = filling.getAmounts().size();

        int noOfNewMakes = 0;
        for (Amount a : filling.getAmounts()){if(amount.getNewMake() != null) noOfNewMakes++;}

        // Assert
        assertEquals(100,liters);
        assertEquals(0.8,alcPercentage);
        assertEquals(1,noOfAmonts);
        assertEquals(1,noOfNewMakes);

        // Case 2: Adding the second amount
        // Assert
        NewMake newMake78 = new NewMake("NM.78", LocalDate.now(), "Jonas", maltBatch);
        newMake78.setAlcPercent(0.60);
        Amount amountSecond = new Amount(newMake78, 100);
        filling.addAmount(amountSecond);

        // Act
        liters = filling.getLiters();
        alcPercentage = filling.getAlcoholPercent();
        noOfAmonts = filling.getAmounts().size();

        noOfNewMakes = 0;
        for (Amount a : filling.getAmounts()){if(amount.getNewMake() != null) noOfNewMakes++;}

        // Assert
        assertEquals(200,liters);
        assertEquals(0.7,alcPercentage);
        assertEquals(2,noOfAmonts);
        assertEquals(2,noOfNewMakes);
    }
}