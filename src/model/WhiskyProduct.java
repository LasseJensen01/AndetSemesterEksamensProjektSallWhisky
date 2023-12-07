package model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WhiskyProduct {
    private Map<Filling,Double> fillings; // Fillings and what percent of the mix they make up.
    private LocalDate date;
    private String whiskyName;
    private double liters;
    private double water = 0; // If this is 0 the product is "cask strength"
    private String waterSource;
    private double alcoholPercent;
    private Cask cask;
    private Set<MaltBatch> malts;

    public WhiskyProduct(Map<Filling, Double> fillings, String whiskyName) {
        this.fillings = fillings;
        this.date = LocalDate.now();
        this.whiskyName = whiskyName;

        this.malts = getMalts();

        calcAlcoholPercentage();
    }
    public WhiskyProduct(Map<Filling, Double> fillings, String whiskyName, double water, String source) {
        this.fillings = fillings;
        this.date = LocalDate.now();
        this.whiskyName = whiskyName;
        this.water = water;
        this.waterSource = source;

        this.malts = getMalts();

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

    public String getFullProductionHistory(){
        String s = "";
        s += this.whiskyName + " is a";
        if (isSingleCask()){
            s += " single cask,";
        }
        if (malts.size() == 1){
            s += " sigle malt,";
        }
        if (isSingleCask()){
            s += " sigle cask,";
        }
        if (water == 0){
            s += " cask strength,";
        } else {
            s += " diluted with " + water + " liters of water from " + waterSource;
        }
        s += " with an alcohol percent of " + alcoholPercent*100 + "%.\n" +
                "With the following production histoy:\n" +
        "*************************************************\n";
        for (Filling f : fillings.keySet()){
            s += f.getContentsInfo(this.date) + "\n";
            s+= "-------------------------------------------------\n";
        }
        return s;
    }
    private Set<MaltBatch> getMalts(){
        Set<MaltBatch> malts = new HashSet<>();
        for (Filling filling : fillings.keySet()){
            malts.addAll(filling.getMalts());
        }
        return malts;
    }
    private boolean isSingleCask(){
        boolean single = false;
        if (fillings.size() == 1){
            single = true;
        }
        return single;
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
}
