package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CaskTest {
    private Farmer farmer;
    private Field field;
    private MaltBatch maltBatch;
    private NewMake newMake;
    private Cask cask;
    private Filling filling;
    private Amount amount;
    @BeforeEach
    void setup(){
        Cask.setNo(0);

        farmer = new Farmer("Lars T", "Hvor kragerne vender.");
        field = new Field("By the river.", farmer);
        maltBatch = new MaltBatch("North Jutland","Byg", field);
        newMake = new NewMake(LocalDate.now(), "Jonas", maltBatch);

        cask = new Cask(Type.TOKAY, 200, "Supplier");
        cask.setLocation(null);
        filling = new Filling(cask, "Thomas");
        amount = new Amount(newMake, 100);
        filling.addAmount(amount);
    }
    @Test
    void setLiters() {
        // TC1
        // Arrange
        cask.setLiters(80);

        // Act
        double newLiters = cask.getLiters();

        // Assert
        assertEquals(80, newLiters);
    }
    @Test
    void getLiters() {
        // TC1
        // Arrange
        // Only basic data used.

        // Act
        double liters = cask.getLiters();

        // Assert
        assertEquals(100, liters);
    }

    @Test
    void setLocation() {
        // TC1
        // Arrange
        Location location = new Location("1-1-1-1");
        cask.setLocation(location);

        // Act
        Location newLocation = cask.getLocation();

        // Assert
        assertEquals(location, newLocation);
    }
    @Test
    void getLocation() {
        // TC1
        // Arrange
        // Only basic data used.

        // Act
        Location caskLocationTC1 = cask.getLocation();

        // Assert
        assertEquals(null, caskLocationTC1);

        // TC2
        // Arrange
        Location location = new Location("1-1-1-1");
        cask.setLocation(location);

        // Act
        Location caskLocationTC2 = cask.getLocation();

        // Assert
        assertEquals(location, caskLocationTC2);
    }

    @Test
    void getType() {
        // TC1
        // Arrange
        // Only basic data used.

        // Act
        Type type = cask.getType();

        // Assert
        assertEquals(Type.TOKAY, type);
    }

    @Test
    void getVolume() {
        // TC1
        // Arrange
        // Only basic data used.

        // Act
        double volume = cask.getVolume();

        // Assert
        assertEquals(200, volume);
    }

    @Test
    void setFilling() {
        // TC1
        // Arrange
        Cask newCask = new Cask(Type.AMARONE,200,"Supplier");

        // Act
        Filling newFilling = new Filling(newCask,"James");
        cask.setFilling(newFilling);

        // Assert
        assertEquals(newFilling, cask.getFilling());
    }

    @Test
    void getFilling() {
        // TC1
        // Arrange
        // Only basic data used.

        // Act & Assert
        assertEquals(this.filling, cask.getFilling());
    }

    @Test
    void getTimesUsed() {
        // TC1
        // Arrange
        cask.emptyCask();

        // Act
        int timesUsed = cask.getTimesUsed();

        // Assert
        assertEquals(1, timesUsed);

        // TC2
        // Arrange
        cask = new Cask(Type.BORDEAUX,200,"Supplier");

        // Act
        timesUsed = cask.getTimesUsed();

        // Assert
        assertEquals(0, timesUsed);
    }

    @Test
    void getId() {
        // TC1
        // Arrange
        // Only basis data used

        // Act
        int id = cask.getCaskID();

        // Assert
        assertEquals(1, id);
    }
    @Test
    void setNo() {
        // TC1
        // Arrange
        Cask.setNo(100);

        // Act
        int no = Cask.getNo();

        // Assert
        assertEquals(100, no);
    }
    @Test
    void getNo() {
        // TC1
        // Arrange
        // Only basic data used.

        // Act
        int no = Cask.getNo();

        // Assert
        assertEquals(1, no);


        // TC2
        // Arrange
        cask = new Cask(Type.BORDEAUX,200,"Supplier");

        // Act
        no = Cask.getNo();

        // Assert
        assertEquals(2, no);
    }

    @Test
    void testToString() {
        // TC1
        // Arrange
        String expected = "Cask with ID: 1 Type: TOKAY Volume: 200.0 Supplier: Supplier";

        // Act
        String toString = cask.toString();

        // Assert
        assertTrue(expected.equals(toString));
    }

    @Test
    void emptyCask() {
        // TC1
        // Arrange
        Location location = new Location("1-1-1-1");
        cask.setLocation(location);
        cask.emptyCask();

        // Act
        Location caskLokation = cask.getLocation();
        double litersInCask = cask.getLiters();
        Filling caskFill = cask.getFilling();

        // Assert
        assertNull(caskLokation);
        assertNull(caskFill);
        assertEquals(0, litersInCask);
    }

    @Test
    void containsWhisky() {
        // TC1
        // Arrange
        filling.setDate(LocalDate.now().minusYears(3).plusDays(1));

        // Act
        boolean containsWhisky = cask.containsWhisky();

        // Assert
        assertFalse(containsWhisky);

        // TC2
        // Arrange
        filling.setDate(LocalDate.now().minusYears(3));

        // Act
        containsWhisky = cask.containsWhisky();

        // Assert
        assertTrue(containsWhisky);

        // TC3
        // Arrange
        filling.setDate(LocalDate.now().minusYears(3).minusDays(1));

        // Act
        containsWhisky = cask.containsWhisky();

        // Assert
        assertTrue(containsWhisky);
    }

    //-----------------------------------------------------------------------------------------------------
    /*
    * Tests belonging to an older iteration.
    * */
    @Test
    void iteration7EmptyCask() {
        // Arrange
        Cask cask = new Cask(Type.AMARONE, 50, "Big Barrel");
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
    void iteration7ContainsWhisky() {
        // Case 1: Boundary value of 3 years
        // Arrange
        Cask cask = new Cask(Type.BORDEAUX, 125, "Big Barrel");
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