package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Bottle implements Serializable {
    private int bottleID;
    private static int no = 0;
    private double size; // Expresed in liters fx: 70cl = 0.7L
    private LocalDate filledDate;
    private  WhiskyProduct whiskyProduct;


    public Bottle(double size, WhiskyProduct whiskyProduct) {
        this.size = size;
        this.whiskyProduct = whiskyProduct;
        filledDate = LocalDate.now();
        no++;
        bottleID = no;
    }
    /**
     * @returns a String with a complete description of the bottle and its contents.
     */
    public String getFullProductionHistory(){
        String s = "This is a " + size + " of the following whisky:\n";
        s += whiskyProduct.getFullProductionHistory();
        return s;
    }
    public static void setNo(int no) {
        Bottle.no = no;
    }

    public int getBottleID() {
        return bottleID;
    }
}
