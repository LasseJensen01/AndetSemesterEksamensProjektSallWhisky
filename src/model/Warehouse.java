package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private String name;
    private String adress;
    private List<Location> locations = new ArrayList<>();

    public Warehouse(String name, String adress) {
        this.name = name;
        this.adress = adress;
    }

    /**
     * This method print an overview of the repectable Warehouse object and the cask
     * which it has stored. It wont print locations with empty casks.
     * The ouput of the method is the WarehouseOver
     */
    public void extractOverview(){
        try(PrintWriter writer = new PrintWriter("src\\model\\WarehouseOverview.txt")){
            writer.println("Warehouse extract for: " + this.name);
            writer.println("-------------------------------------");
            writer.println();
            for (Location l : locations){
                Cask c = l.getCask();
                if (c != null){
                    int id = c.getId();
                    Type type = c.getType();
                    double volume = c.getVolume();
                    double liters = c.getLiters();
                    String location = l.getLocationID();
                    writer.printf("ID: %3d, Type: %-13s Volume: %1.2f, Liters: %1.2f, Location: %6s%n", id, type, volume, liters, location);
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
    public void addLocation(Location location){
        locations.add(location);
    }

    /**
     * Take a cask and move it to a new location
     * Clears up the old location for new storage
     * @param cask - The cask to be moved
     * @param newLocation - The new location
     */
    public void moveCask(Cask cask, Location newLocation){
        if (newLocation.getCask() != null){
            throw new IllegalArgumentException();
        }
        newLocation.setCask(cask);
        //If cask had a previous location, free up that spot
        if (cask.getLocation() != null){
            cask.getLocation().setCask(null);
        }
        cask.setLocation(newLocation);
    }

    public List<Location> getLocations() {
        return new ArrayList<>(locations);
    }
}
