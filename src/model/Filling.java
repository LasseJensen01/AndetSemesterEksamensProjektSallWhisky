package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Filling {
    private List<Amount> amounts = new ArrayList<>();
    private Cask cask;
    private double liters;
    private static int no = 0;
    private int id;
    private LocalDate date;
    private String employee;
    private double alcoholPercent;
    /**
     * Creats a filling and sets its relation to its cask
     * @param cask an empty cask
     * @param employee the name of the employee
     */
    public Filling(Cask cask, String employee) {
        this.cask = cask;
        this.employee = employee;
        this.date = LocalDate.now();
        no++;
        this.id = no;

        cask.setFilling(this);
    }
    /**
     * @param amount an amount to be added.
     * @throws IllegalArgumentException if the cask does not have enough volume left to contain the amount.
     */
    public void addAmount(Amount amount){
        if ((liters + amount.getLiters()) > cask.getVolume()) throw new IllegalArgumentException();
        updateAlcoholPercentage(amount.getLiters(), amount.getAlcoholPercent());
        amounts.add(amount);
        liters += amount.getLiters();
    }
    /**
     * @returns a string representation of the content.
     * @pre amounts have been added.
     */
    public String getContentsInfo(LocalDate periodEndDate){
        Period maturity = calcMaturiy(periodEndDate);
        String s = ""; //Add single cask, sigle malt v1 and blend in v2
        for (Amount t : amounts){
            s += t.toString() + " ";
        }
        s += "\nTapped by " + employee + " on " + date.toString() + "\n";
        s += "Matured for " + maturity.getYears() + " years " + maturity.getMonths() + " months "
                + maturity.getDays() + " days on a " + cask.getType() + " cask.";
        return s;
    }
    /**
     * Updates the maturity of the filling to between the date of the filling and when the method is called.
     */
    private Period calcMaturiy(LocalDate date){
        return Period.between(this.date, date);
    }
    /**
     * @returns a boolean value representing if filling can legaly be considered whisky.
     */
    public boolean isWisky(){
        return calcMaturiy(LocalDate.now()).getYears() >= 3;
    }
    /**
     * Helpermethod that updates the alcohol percentage when adding a new liquid.
     */
    public void updateAlcoholPercentage(double liters, double percent){
        double currentLitersOfAlcohol = this.liters * alcoholPercent;
        double currentLitersOfWater = this.liters - currentLitersOfAlcohol;
        double litersAlcoholAdded = liters * percent;
        double litersWaterAdded = liters - litersAlcoholAdded;
        alcoholPercent = (currentLitersOfAlcohol + litersAlcoholAdded) / (this.liters + litersWaterAdded);
    }
    //---------------------------------------------------------------------------------------------------
    public void setLiters(double liters) {
        this.liters = liters;
    }
    public LocalDate getDate() {
        return date;
    }

    public String getEmployee() {
        return employee;
    }

    public double getLiters() {
        return liters;
    }

    public Cask getCask() {
        return cask;
    }

    public int getId() {
        return id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Amount> getAmounts() {
        return new ArrayList<>(amounts);
    }

    public static void setNo(int no) {
        Filling.no = no;
    }
}
