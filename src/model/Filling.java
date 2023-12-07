package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Filling implements Serializable {
    private List<Amount> amounts = new ArrayList<>();
    private Cask cask;
    private double liters;
    private static int no = 0;
    private int id;
    private LocalDate date;
    private String employee;
    private double alcoholPercent;
    private int fillNo;

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
        this.fillNo = cask.getTimesUsed();
    }
    /**
     * @param amount an amount to be added.
     * @throws IllegalArgumentException if the cask does not have enough volume left to contain the amount.
     */
    public void addAmount(Amount amount){
        if ((liters + amount.getLiters()) > cask.getVolume()) throw new IllegalArgumentException();
        if (amounts.isEmpty()) {
            this.alcoholPercent = amount.getNewMake().getAlcPercent();
        } else {
            this.alcoholPercent = calcAlcPercent(this.liters, this.alcoholPercent ,
                    amount.getLiters(), amount.getNewMake().getAlcPercent());
        }

        amounts.add(amount);
        liters += amount.getLiters();
    }
    /**
     * @returns a string representation of the content.
     * @pre amounts have been added.
     */
    public String getContentsInfo(LocalDate periodEndDate){
        Period maturity = calcMaturiy(periodEndDate);
        String s = "Filling id: " + this.id + ", made from:\n";
        for (Amount t : amounts){
            s += t.getContentInfo() + "\n";
        }
        s += "Tapped by " + employee + " on " + date.toString() + "\n";
        s += "Matured for " + maturity.getYears() + " years " + maturity.getMonths() + " months "
                + maturity.getDays() + " days on a " + cask.getType() + " cask, as the " + fillNo + " fill.";
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
    public double calcAlcPercent(double currentLiters, double currentAlcPercent, double litersAdded, double percentAdded){
        return  ((currentLiters * currentAlcPercent) + (litersAdded * percentAdded)) / (currentLiters + litersAdded);
    }
    public Set<MaltBatch> getMalts(){
        Set<MaltBatch> malts = new HashSet<>();
        for (Amount amount : amounts){
            malts.add(amount.getNewMake().getMaltBatch());
        }
        return malts;
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
    public double getAlcoholPercent() {
        return alcoholPercent;
    }

    public void setAlcoholPercent(double alcoholPercent) {
        this.alcoholPercent = alcoholPercent;
    }
}
