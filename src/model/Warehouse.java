package model;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private int id;
    private static int no = 1;
    private String name;
    private String adress;
    private List<Location> locations = new ArrayList<>();

    public Warehouse(String name, String adress) {
        this.id = no;
        this.name = name;
        this.adress = adress;
        no++;
    }
}
