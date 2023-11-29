package model;

import org.junit.Assert;
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
        cask.setFilling(filling);

        // Act
        message = cask.getContentsInfo();

        // Assert
        assertTrue(message.equals("The cask is empty."));

        // Case 3: The casket is not empty.
        // Arrange
        NewMake newMake = new NewMake("NM.77", 30);
        Amount amount = new Amount(newMake, 15);
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
        int daysUsed = cask.getDaysUsed();
        Filling currentfill = cask.getFilling();
        Location location = cask.getLocation();

        // Assert
        assertNull(currentfill);
        assertNull(location);
        assertEquals(1,timesUsed);
        assertEquals(1095,daysUsed);
    }

    @Test
    void containsWhisky() {
        // Case 1: Boundary value of 3 years
        // Arrange
        Cask cask = new Cask(Type.BORDEAUX, 125);
        Filling filling = new Filling(cask, "Maria");
        cask.setFilling(filling);
        NewMake newMake = new NewMake("NM.49", 60);
        Amount amount = new Amount(newMake, 50);
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