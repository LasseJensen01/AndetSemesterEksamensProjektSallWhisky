package model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class WhiskyProduct {
    private Map<Filling,Double> fillings; // Fillings and what percent of the mix they make up.
    private LocalDate saleDate;
    private String whiskyName;
    private double liters;
    private double water = 0; // If this is 0 the product is "cask strength"
    private double alcoholPercent;
    private String type = "Single Cask"; // In V1 every liquid is a single cask.
    private int numberOfMalts;

    public WhiskyProduct(Map<Filling, Double> fillings, String whiskyName) {
        this.fillings = fillings;
        this.saleDate = LocalDate.now();
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
        alcoholPercent = totalLitersOfAlcohol/totalLitersOfWater;
    }
    public String getLabelText(){
        String s = "";
        return s;
    }

    public String getFullProductionHistory(){
        String s = "";
        return s;
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
}
