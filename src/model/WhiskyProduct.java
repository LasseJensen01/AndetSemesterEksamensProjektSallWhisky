package model;

import java.time.LocalDate;
import java.util.Map;

public class WhiskyProduct {
    private Map<Filling,Double> fillings; // Fillings and what percent of the mix they make up.
    private LocalDate date;
    private String whiskyName;
    private double liters;
    private double water = 0; // If this is 0 the product is "cask strength"
    private double alcoholPercent;
    private String type;
    private int numberOfMalts; // Find a better way
    private Cask cask;

    public WhiskyProduct(Map<Filling, Double> fillings, String whiskyName) {
        this.fillings = fillings;
        this.date = LocalDate.now();
        this.whiskyName = whiskyName;

        // Malts
        // Loop and getters

        calcAlcoholPercentage();

    }
    public void addWater(double liters){
        water += liters;
        calcAlcoholPercentage();
    }
    public void calcAlcoholPercentage(){
        double totalLitersOfWater = water;
        double totalLitersOfAlcohol = 0;
        for (Filling filling : fillings.keySet()){
            double fillingLitersOfAlcohol = fillings.get(filling) * filling.getAlcoholPercent();
            double fillingLitersOfWater = fillings.get(filling) - fillingLitersOfAlcohol;
            totalLitersOfAlcohol += fillingLitersOfAlcohol;
            totalLitersOfWater += fillingLitersOfWater;
        }
        liters = totalLitersOfWater+totalLitersOfAlcohol;
        alcoholPercent = totalLitersOfAlcohol/(totalLitersOfWater+totalLitersOfAlcohol);
    }
    public String getLabelText(){
        String s = "";
        return s;
    }

    public String getFullProductionHistory(){
        String s = "";
        for (Filling f : fillings.keySet()){
            s += f.getContentsInfo(this.date) + "\n";
            s+= "---------------------------------------";
        }
        return s;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f%s %.1f",
                whiskyName, "Alc:", alcoholPercent*100, "% Liters: ", liters);
    }

    public double getLiters() {
        return liters;
    }

    public void setLiters(double liters) {
        this.liters = liters;
    }

    public double getWater() {
        return water;
    }

    public double getAlcoholPercent() {
        return alcoholPercent;
    }

    public Cask getCask() {
        return cask;
    }

    public void setCask(Cask cask) {
        this.cask = cask;
    }
}
