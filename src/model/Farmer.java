package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Farmer implements Serializable {
    private String name;
    private String address;

    //-------------
    private List<Field> fields = new ArrayList<>();

    public Farmer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Field> getFields() {
        return List.copyOf(fields);
    }

    public void addField(Field field) {
        fields.add(field);
    }

    @Override
    public String toString(){
        return String.format("%s", name);
    }
}
