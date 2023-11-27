package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Cask {
    private type type;
    private static int no = 0;
    private int id;
    private int volume;
    private int liters;
    private List<Filling> fillings = new ArrayList<>();

    public Cask(type type) {
        this.type = type;
        no++;
        this.id = no;
    }

    public void addFilling(Filling filling){
        fillings.add(filling);
        liters += filling.getLiters();
    }

    public String getContentsInfo(){
        String s = "Casket " + id + " has had the following fills:\n";
        for (Filling f : fillings){
            s += f.getContentsInfo();
        }
        s += "Matured for " +
                Period.between(fillings.get(fillings.size()-1).getDate(), LocalDate.now()).toString();
        return s;
    }
}
