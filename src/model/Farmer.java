package model;

import java.util.ArrayList;
import java.util.List;

public class Farmer {
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

    public void addFields(Field field) {
        fields.add(field);
    }
}
