package model;

import java.io.Serializable;

public class Amount implements Serializable {
    private NewMake newMake;
    private int liters;

    public Amount(NewMake newMake, int liters) {
        this.newMake = newMake;
        this.liters = liters;
    }

    public int getLiters() {
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
