package model;

public class Grain {
    private String type;

    //-------------
    private Field field;

    public Grain(String type, Field field) {
        this.type = type;
        this.field = field;
    }
}
