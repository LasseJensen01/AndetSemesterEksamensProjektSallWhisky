package model;

import java.io.Serializable;

public class Field implements Serializable {
    private final String name;

    //-----------
    private final Farmer farmer;

    public Field(String name, Farmer farmer) {
        this.name = name;
        this.farmer = farmer;
    }

    @Override
    public String toString(){
        return String.format("Field '%s' owned by %s", name, farmer);
    }
}
