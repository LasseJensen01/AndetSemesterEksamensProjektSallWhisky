package model;

public class Field {
    private int fieldID;
    private String name;
    private String grainType;

    //-----------
    private Farmer farmer;

    public Field(int fieldID, String name, String grainType, Farmer farmer) {
        this.fieldID = fieldID;
        this.name = name;
        this.grainType = grainType;
        this.farmer = farmer;
    }

    public int getFieldID() {
        return fieldID;
    }

    public String getName() {
        return name;
    }
    public String getGrainType() {
        return grainType;
    }

    public Farmer getSupplier() {
        return farmer;
    }
}
