package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private int id;
    private static int no = 1;
    private String name;
    private String adress;
    private List<Location> lager = new ArrayList<>();

    public Warehouse(String name, String adress) {
        this.id = no;
        this.name = name;
        this.adress = adress;
        no++;
    }

    public void extractOverview(){
        try(PrintWriter writer = new PrintWriter("src\\model\\WarehouseOverveiw.txt")){
            writer.println("Warehouse extract for: " + this.name);
            writer.println("-------------------------------------");
            writer.println();
            for (Location l : lager){
                Cask c = l.getCask();
                if (c != null){
                    int id = c.getId();
                    Type type = c.getType();
                    double volume = c.getVolume();
                    double liters = c.getLiters();
                    int location = l.getLocationID();
                    writer.printf("ID: %3d, Type: %-13s Volume: %1.2f, Liters: %1.2f, Location: %6d%n", id, type, volume, liters, location);
                    writer.printf("     Fillings: %n");
                    Filling f = c.getFilling();
                    int fid = f.getId();
                    double fliters = f.getLiters();
                    String employee = f.getEmployee();
                    LocalDate date = f.getDate();
                    writer.printf("            ID: %3d, Liters: %1.2f, Employee: %-13s Date: %tF%n", fid, fliters,employee,date);
                    List<Amount> amount = f.getAmounts();
                    for (Amount a : amount){
                        String nm = a.getNewMake().getName();
                        double aliters = a.getLiters();
                        writer.printf("            NewMake: %-13s Liters: %1.2f%n", nm,aliters);
                    }
                }
                writer.println();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
