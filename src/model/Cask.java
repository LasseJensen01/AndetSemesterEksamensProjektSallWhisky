package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Cask implements Serializable {
    private Type type;
    private static int no = 0;
    private int caskID;
    private double volume; // How big cask
    private Filling filling = null;
    private int timesUsed = 0;
    private Location location = null;
    /**
     * This method creates a cask.
     * @param type the type of the cask.
     * @param volume how many liters the cask can contain.
     * @param supplier the name of the supplier.
     */
    public Cask(Type type, double volume, String supplier) {
        this.type = type;
        this.volume = volume;
        no++;
        this.caskID = no;
    }
    /**
     * This method empties a cask, and sets it filling, liters and location to null.
     */
    public void emptyCask(){
        filling.setLiters(0);
        this.filling = null;
        if (this.location != null) this.location.setCask(null);
        this.location = null;
    }
    /**
     * Calls the fillings isWisky() method to check if the filling is 3 or more years old.
     * @return false if filling is null.
     */
    public boolean containsWhisky(){
        if (this.filling == null) return false;
        return filling.isWisky();
    }
    //-----------------------------------------------------------------------
    /**
     * Sets the amount of filling in the cask. Used incase of spills or evaporation.
     */
    public void setLiters(double liters){filling.setLiters(liters);}
    public void setLocation(Location location) {
        this.location = location;
    }
    public Type getType() {
        return type;
    }

    public double getVolume() {
        return volume;
    }
    /**
     * @return the liters of fillling in the cask.
     */
    public double getLiters() {
        if (filling != null) return filling.getLiters();
        return 0;
    }
    /**
     * @param filling a new filling, pre not null.
     */
    public void setFilling(Filling filling) {
        this.filling = filling;
        this.timesUsed++;
    }

    public Filling getFilling() {
        return filling;
    }

    public int getTimesUsed() {
        return timesUsed;
    }

    public int getCaskID() {
        return caskID;
    }

    public Location getLocation() {
        return location;
    }
    /**
     * @param no the no variable of a class using the Id-tracker. Pre only positive integers.
     */
    public static void setNo(int no) {
        Cask.no = no;
    }

    /**
     * @returns a string with the format:
     * "Cask with ID: " + this.id + " Type: " + this.type + " Volume: " + this.volume
     */
    @Override
    public String toString() {
        return "Cask with ID: " + caskID + " Type: " + type + " Volume: " + volume;
    }

    public static int getNo() {
        return no;
    }
}
