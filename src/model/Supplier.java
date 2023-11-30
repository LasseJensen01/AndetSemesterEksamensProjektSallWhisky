package model;

import java.util.ArrayList;
import java.util.List;

public class Supplier {
    private int supplierID;
    private String name;
    private String address;

    //-------------
    private List<Field> fields = new ArrayList<>();

    public Supplier(int supplierID, String name, String address) {
        this.supplierID = supplierID;
        this.name = name;
        this.address = address;
    }

    public int getSupplierID() {
        return supplierID;
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
