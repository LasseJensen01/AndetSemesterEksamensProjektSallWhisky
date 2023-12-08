package model;

import java.io.Serializable;

public class Amount implements Serializable {
    private NewMake newMake;
    private double liters;

    public Amount(NewMake newMake, double liters) {
        this.newMake = newMake;
        this.liters = liters;
    }

    public double getLiters() {
        return liters;
    }

    public NewMake getNewMake() {
        return newMake;
    }

    @Override
    public String toString() {
        return liters + "L of NewMake# " + newMake.getNewMakeID();
    }
    public String getContentInfo(){
        String s = liters + " liters of " + newMake.getContentInfo();
        return s;
    }
}
