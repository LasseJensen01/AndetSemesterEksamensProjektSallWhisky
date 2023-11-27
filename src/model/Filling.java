package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Filling {
    private List<Tap> taps = new ArrayList<>();
    private Cask cask;
    private int liters;
    private static int no = 0;
    private int id;
    private LocalDate date;
    private String employee;

    public LocalDate getDate() {
        return date;
    }

    public String getEmployee() {
        return employee;
    }

    public int getLiters() {
        return liters;
    }

    public Filling(Cask cask, LocalDate date, String employee) {
        this.cask = cask;
        this.date = date;
        this.employee = employee;
        no++;
        this.id = no;
        cask.addFilling(this);
    }
    public void addTap(Tap tap){
        taps.add(tap);
        liters += tap.getLiters();
    }
    public String getContentsInfo(){
        String s = "";
        for (Tap t : taps){
            s += t.toString() + " ";
        }
        s += "\nTapped by " + employee + " on " + date.toString() + "\n";
        return s;
    }
}
