package model;

import java.io.Serializable;

public class Location implements Serializable {
    private Cask cask = null;
    private String locationID; // LocationID functions as follows,
    //The first two numbers symbolises what Row
    //The next two which shelf unit
    //The next two which shelf
    //The Last tow which pallet
    //An example is 4-3-2-1, which is row 4, shelf unit 3, shelf 2, pallet 1.
    //From here employee should be able to disinct from the barrels by the use of ID

    /**
     * Location ID consists of of a total 6 numbers
     * First two direct to row
     * Next two direct to a shelf unit
     * Last two to a specific shelf
     * @param locationID
     */
    public Location(String locationID) {
        this.locationID = locationID;
    }

    public void setCask(Cask cask) {
        this.cask = cask;
    }

    public Cask getCask() {
        return cask;
    }

    public String getLocationID() {
        return locationID;
    }

    @Override
    public String toString() {
        return locationID;
    }
}
