package model;

import java.time.LocalDate;

public class Bottle {
    private int id;
    private int no;
    private int noOfBottels;
    private double volume; //expresed in liters fx: 70cl = 0.7L
    private Filling filling;
    private LocalDate filledDate;
    private String whiskyName;

    public Bottle(double volume, Filling filling, String name, int noOfBottels) {
        this.volume = volume;
        this.filling = filling;
        this.noOfBottels = noOfBottels;
        filledDate = LocalDate.now();
        whiskyName = name;
        no++;
        id = no;
    }
    /**
     * @returns a string representation of the content.
     */
    public String getContentInfo(){
        String s = "";
        s += noOfBottels + ", " + volume + " of " + whiskyName + ". Containing :\n";
        s += filling.getContentsInfo();
        s += "Taped on " + filledDate + " with ID: " + id + ".";
        return s;
    }
    public int getId() {
        return id;
    }

    public int getNoOfBottels() {
        return noOfBottels;
    }

    public Filling getFilling() {
        return filling;
    }

    public LocalDate getFilledDate() {
        return filledDate;
    }

    public String getWhiskyName() {
        return whiskyName;
    }
}
