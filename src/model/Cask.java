package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Cask {
    private Type type;
    private static int no = 0;
    private int id;
    private double volume; // How big cask
    private Filling filling = null;
    private int timesUsed = 0;
    private int daysUsed = 0;
    private Location location = null;

    public Cask(Type type, double volume) {
        this.type = type;
        this.volume = volume;
        no++;
        this.id = no;
    }
    public String getContentsInfo(){
        String s = "";
        if (filling == null || filling.getLiters() == 0) s += "The cask is empty.";
        else {
            s = "Casket " + id + " contains the following:\n";
            s += filling.getContentsInfo();
        }
        return s;
    }
    public void emptyCask(){
        timesUsed++;
        daysUsed -= filling.getDate().toEpochDay()-LocalDate.now().toEpochDay(); // positive int minus a negative long
        filling = null;
        location = null;
    }
    public boolean containsWhisky(){
        return filling.isWisky();
    }
    //-----------------------------------------------------------------------
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

    public double getLiters() {
        if (filling != null) return filling.getLiters();
        return 0;
    }
    public void setFilling(Filling filling) {
        this.filling = filling;
    }

    public Filling getFilling() {
        return filling;
    }

    public int getTimesUsed() {
        return timesUsed;
    }

    public int getId() {
        return id;
    }
    public void setTimesUsed(int timesUsed) {
        this.timesUsed = timesUsed;
    }

    public int getDaysUsed() {
        return daysUsed;
    }

    public void setDaysUsed(int daysUsed) {
        this.daysUsed = daysUsed;
    }

    public Location getLocation() {
        return location;
    }
    public static void setNo(int no) {
        Cask.no = no;
    }


    @Override
    public String toString() {
        return "Cask with ID: " + id + " Type: " + type + " Volume: " + volume;
    }
}
