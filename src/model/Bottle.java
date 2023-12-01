package model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Bottle {
    private int id;
    private static int no = 0;
    private int noOfBottels;
    private double volume; //expresed in liters fx: 70cl = 0.7L
    private Map<Filling,Double> fillings; //Fillings and what percent of the mix they make up.
    private LocalDate filledDate;
    private String whiskyName;
    private double alcoholPercent;

    public Bottle(double volume, Map<Filling,Double> fillings, String name, int noOfBottels) {
        this.volume = volume;
        this.fillings = fillings;
        this.noOfBottels = noOfBottels;
        filledDate = LocalDate.now();
        whiskyName = name;
        calcAlcoholPercentage();
        no++;
        id = no;
    }
    public void calcAlcoholPercentage(){
        double totalLitersOfWater = 0;
        double totalLitersOfAlcohol = 0;
        for (Filling filling : fillings.keySet()){
            double fillingLitersOfAlcohol = fillings.get(filling) * filling.getAlcoholPercent();
            double fillingLitersOfWater = fillings.get(filling) - fillingLitersOfAlcohol;
            totalLitersOfAlcohol += fillingLitersOfAlcohol;
            totalLitersOfWater += fillingLitersOfWater;
        }
        alcoholPercent = totalLitersOfAlcohol/totalLitersOfAlcohol;
    }
    /**
     * @returns a string representation of the content.
     */
    public String getContentInfo(){
        String s = "";
        s += noOfBottels + ", " + volume + " of " + whiskyName + ". Containing :\n";
        for (Filling f : fillings.keySet()){
            s += fillings.get(f) + "% of: ";
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

    public LocalDate getFilledDate() {
        return filledDate;
    }

    public String getWhiskyName() {
        return whiskyName;
    }
    public static void setNo(int no) {
        Bottle.no = no;
    }

    public Map<Filling, Double> getFillings() {
        return fillings;
    }
}
