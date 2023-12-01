package model;

import java.time.LocalDate;

public class Bottle {
    private int id;
    private static int no = 0; // Maybe connet to the product.
    private double size; // Expresed in liters fx: 70cl = 0.7L
    private LocalDate filledDate;
    private  WhiskyProduct whiskyProduct;


    public Bottle(double size, WhiskyProduct whiskyProduct) {
        this.size = size;
        this.whiskyProduct = whiskyProduct;
        filledDate = LocalDate.now();
        no++;
        id = no;
    }
    public static void setNo(int no) {
        Bottle.no = no;
    }
}
