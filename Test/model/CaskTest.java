package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CaskTest {

    @Test
    void getContentsInfo() {
        // Case 1: The casket is empty because no filling has been added.
        // Arrange
        Cask cask = new Cask(Type.AMARONE, 50);

        // Act
        String message = cask.getContentsInfo();

        // Assert
        assertTrue(message.equals("The cask is empty."));

        // Case 2: The casket is empty because filling.liters == 0.
        // Arrange
        Filling filling = new Filling(cask, "Jesper");

        // Act
        message = cask.getContentsInfo();

        // Assert
        assertTrue(message.equals("The cask is empty."));

        // Case 3: The casket is not empty.
        // Arrange
        Farmer farmer = new Farmer("Lars T", "Hvor kragerne vender.");
        Field field = new Field("By the river.", farmer);
        MaltBatch maltBatch = new MaltBatch("Søren Ryge","Barley", field);
        NewMake newMake77 = new NewMake(LocalDate.now(), "Jonas", maltBatch);
        newMake77.setAlcPercent(0.80);
        Amount amount = new Amount(newMake77, 15);
        filling.addAmount(amount);

        // Act
        message = cask.getContentsInfo();

        // Assert
        assertFalse(message.equals("The cask is empty."));
    }

    @Test
    void emptyCask() {
        // Arrange
        Cask cask = new Cask(Type.AMARONE, 50);
        Filling filling = new Filling(cask, "Jesper");
        filling.setDate(LocalDate.now().minusYears(3));
        cask.emptyCask();

        // Act
        int timesUsed = cask.getTimesUsed();
        Filling currentfill = cask.getFilling();
        Location location = cask.getLocation();

        // Assert
        assertNull(currentfill);
        assertNull(location);
        assertEquals(1, timesUsed);
    }

    @Test
    void containsWhisky() {
        // Case 1: Boundary value of 3 years
        // Arrange
        Cask cask = new Cask(Type.BORDEAUX, 125);
        Filling filling = new Filling(cask, "Maria");
        Farmer farmer = new Farmer("Lars T", "Hvor kragerne vender.");
        Field field = new Field("By the river.", farmer);
        MaltBatch maltBatch = new MaltBatch("Søren Ryge","Byg", field);
        NewMake newMake77 = new NewMake(LocalDate.now(), "Jonas", maltBatch);
        newMake77.setAlcPercent(0.80);
        Amount amount = new Amount(newMake77, 15);
        filling.addAmount(amount);
        filling.setDate(LocalDate.now().minusYears(3));

        // Act
        boolean containsWhisky = cask.containsWhisky();

        // Assert
        assertTrue(containsWhisky);

        //Case 2: One day short
        // Arrange
        filling.setDate(filling.getDate().plusDays(1));

        // Act
        containsWhisky = cask.containsWhisky();

        // Assert
        assertFalse(containsWhisky);
    }
}