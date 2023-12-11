package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Farmer implements Serializable {
    private final String name;
    private final String address;

    //-------------
    private final List<Field> fields = new ArrayList<>();

    public Farmer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public List<Field> getFields() {
        return List.copyOf(fields);
    }

    public void addField(Field field) {
        fields.add(field);
    }

    @Override
    public String toString(){
        return String.format("%s, %s", name, address);
    }
}
