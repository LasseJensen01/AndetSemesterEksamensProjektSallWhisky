package model;

import java.time.LocalDate;

public class Bottle {
    private int id;
    private int no;
    private int noOfBottels;
    private double volume;
    private Filling filling;
    private LocalDate filledDate;
    private String whiskyName;

    public Bottle(double volume, Filling filling, String name) {
        volume = volume;
        filling = filling;
        filledDate = LocalDate.now();
        whiskyName = name;
        no++;
        id = no;
    }

    public int getId() {
        return id;
    }
}
