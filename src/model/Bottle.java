package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Bottle implements Serializable {
    private final int bottleID;
    private static int no = 0;
    private final double size; // Expressed in liters fx: 70cl = 0.7L
    private final LocalDate filledDate;
    private final  WhiskyProduct whiskyProduct;


    public Bottle(double size, WhiskyProduct whiskyProduct) {
        this.size = size;
        this.whiskyProduct = whiskyProduct;
        filledDate = LocalDate.now();
        no++;
        bottleID = no;
    }

    /**
     * @return String - String with a complete description of the bottle and its contents.
     */
    public String getFullProductionHistory(){
        String s = "This is a " + size + " liter bottle of the following whisky:\n";
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
