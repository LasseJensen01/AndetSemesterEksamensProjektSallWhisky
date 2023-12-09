package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WhiskyProduct implements Serializable {
    private Map<Filling,Double> fillings;
    private LocalDate date;
    private String whiskyName;
    private double liters;
    private double waterAmount = 0; // If this is 0 the product is "cask strength"
    private String waterSource;
    private double alcoholPercent;
    private Set<MaltBatch> malts;

    public WhiskyProduct(Map<Filling, Double> fillings, String whiskyName) {
        this.fillings = fillings;
        this.date = LocalDate.now();
        this.whiskyName = whiskyName;

        this.malts = getMalts();

        calcAlcoholPercentage();
    }
    public WhiskyProduct(Map<Filling, Double> fillings, String whiskyName, double waterAmount, String source) {
        this.fillings = fillings;
        this.date = LocalDate.now();
        this.whiskyName = whiskyName;
        this.waterAmount = waterAmount;
        this.waterSource = source;

        this.malts = getMalts();

        calcAlcoholPercentage();

    }
    /**
     * Helper method that calculates the Alcohol Percentage of the whisky.
     */
    public void calcAlcoholPercentage(){
        double totalLitersOfWater = waterAmount;
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
    /**
     * @returns a String with a complete description of the whisky.
     */
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
        if (waterAmount == 0){
            s += " cask strength,";
        } else {
            s += " diluted with " + waterAmount + " liters of water from " + waterSource;
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

    public double getWaterAmount() {
        return waterAmount;
    }

    public double getAlcoholPercent() {
        return alcoholPercent;
    }

}
