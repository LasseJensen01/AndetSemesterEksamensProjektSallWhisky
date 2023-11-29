package model;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Warehouse {
    private int id;
    private String name;
    private String adress;
    private List<Location> lager = new ArrayList<>();

    public Warehouse(int id, String name, String adress) {
        this.id = id;
        this.name = name;
        this.adress = adress;
    }

    public void addLocation(Location l){
        lager.add(l);
    }

    public void extractOverview(){
        try(PrintWriter writer = new PrintWriter("src\\model\\WarehouseOverveiw.txt")){
            writer.println("Warehouse extract for: " + this.name);
            writer.println("-------------------------------------");
            writer.println("ID" + "   " + "Type" + "   ");
            for (Location l : lager){
                Cask c = l.getCask();
                if (c != null){
                    writer.print(c.getId() + "   ");
                    writer.print(c.getType() + "   ");
                    writer.print(c.getVolume() + "   ");
                    writer.print(c.getLiters() + "   ");
                    writer.print(l.getLocationID() + "   ");
                    writer.println("   " + "Fillings:");

                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
