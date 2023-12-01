package model;

public class Field {
    private int fieldID;
    private String name;
    private String grainType;

    //-----------
    private Supplier supplier;

    public Field(int fieldID, String name, String grainType, Supplier supplier) {
        this.fieldID = fieldID;
        this.name = name;
        this.grainType = grainType;
        this.supplier = supplier;
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

    public Supplier getSupplier() {
        return supplier;
    }
}
