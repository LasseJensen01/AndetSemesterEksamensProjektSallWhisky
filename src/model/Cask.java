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
    private double liters = 0; //How much newMake is filled in cask
    private Location location = null;
    private List<Filling> fillings = new ArrayList<>();


    public Cask(Type type, double volume) {
        this.type = type;
        this.volume = volume;
        no++;
        this.id = no;
    }

    public void addFilling(Filling filling){
        fillings.add(filling);
        liters += filling.getLiters();
    }

    public String getContentsInfo(){
        String s = "Casket " + id + " has had the following fills:\n";
        for (Filling f : fillings){
            s += f.getContentsInfo();
        }
        s += "Matured for " +
                Period.between(fillings.get(fillings.size()-1).getDate(), LocalDate.now()).toString();
        return s;
    }

    public Type getType() {
        return type;
    }

    public double getVolume() {
        return volume;
    }

    public double getLiters() {
        return liters;
    }

    public void setLiters(double liters) {
        this.liters = liters;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cask with ID: " + id + " Type: " + type + " Volume: " + volume;
    }
}
