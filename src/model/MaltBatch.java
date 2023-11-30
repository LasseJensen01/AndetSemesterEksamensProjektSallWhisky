package model;

public class MaltBatch {
    private int batchID;
    private String rygeMateriale;
    private String malteri;

    //---------------
    private Field field;

    public MaltBatch(int batchID, String rygeMateriale, String malteri, Field field) {
        this.batchID = batchID;
        this.rygeMateriale = rygeMateriale;
        this.malteri = malteri;
        this.field = field;
    }

    public int getBatchID() {
        return batchID;
    }

    public String getRygeMateriale() {
        return rygeMateriale;
    }

    public String getMalteri() {
        return malteri;
    }

    public Field getField() {
        return field;
    }
}
