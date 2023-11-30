package model;

public class Field {
    private int fieldID;
    private String name;

    //-----------
    private Grain grain;
    private Supplier supplier;

    public Field(int fieldID, String name, Grain grain, Supplier supplier) {
        this.fieldID = fieldID;
        this.name = name;
        this.grain = grain;
        this.supplier = supplier;
    }
}
