package model;

public class Location {
    private Cask cask = null;
    private int locationID; // LocationID functions as follows,
    //The first two numbers symbolises what Row
    //The next two which shelf unit
    //The last two which shelf
    //An example is 040302, which is row 4, shelf unit 3, shelf 2.
    //From here employee should be able to disinct from the barrels by the use of ID

    public Location(int locationID) {
        this.locationID = locationID;
    }

    public void setCask(Cask cask) {
        this.cask = cask;
    }

    public Cask getCask() {
        return cask;
    }

    public int getLocationID() {
        return locationID;
    }
}
