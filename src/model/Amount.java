package model;

public class Amount {
    private NewMake newMake;
    private int liters;
    private double alcoholPercent;

    public Amount(NewMake newMake, int liters) {
        this.newMake = newMake;
        this.liters = liters;
    }

    public int getLiters() {
        return liters;
    }
    public double getAlcoholPercent() {
        return alcoholPercent;
    }

    public NewMake getNewMake() {
        return newMake;
    }

    @Override
    public String toString() {
        return liters + "L of " + newMake.getName();
    }
}
