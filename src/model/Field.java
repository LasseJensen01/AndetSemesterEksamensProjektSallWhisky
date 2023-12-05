package model;

public class Field {
    private String name;

    //-----------
    private Farmer farmer;

    public Field(String name, Farmer farmer) {
        this.name = name;
        this.farmer = farmer;
    }
    public String getName() {
        return name;
    }

    public Farmer getSupplier() {
        return farmer;
    }

    @Override
    public String toString(){
        return String.format("Field '%s' owned by %s", name, farmer);
    }
}
