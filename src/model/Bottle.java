package model;

import java.time.LocalDate;
import java.util.List;

public class Bottle {
    private int id;
    private static int no = 0;
    private int noOfBottels;
    private double volume; //expresed in liters fx: 70cl = 0.7L
    private List<Filling> fillings;
    private LocalDate filledDate;
    private String whiskyName;

    public Bottle(double volume, List<Filling> fillings, String name, int noOfBottels) {
        this.volume = volume;
        this.fillings = fillings;
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
        for (Filling f : fillings){
            s += f.getContentsInfo(filledDate) + "\n";
        }
        s += "Taped on " + filledDate + " with ID: " + id + ".";
        return s;
    }
    public int getId() {
        return id;
    }

    public int getNoOfBottels() {
        return noOfBottels;
    }

    public List<Filling> getFillings() {
        return fillings;
    }

    public LocalDate getFilledDate() {
        return filledDate;
    }

    public String getWhiskyName() {
        return whiskyName;
    }
    public static void setNo(int no) {
        Bottle.no = no;
    }

}
