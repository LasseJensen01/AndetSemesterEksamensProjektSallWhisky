package model;

public class Tap {
    private NewMake newMake;
    private int liters;

    public Tap(NewMake newMake, int liters) {
        this.newMake = newMake;
        this.liters = liters;
    }

    public int getLiters() {
        return liters;
    }

    @Override
    public String toString() {
        return liters + "L of " + newMake.getName();
    }
}
