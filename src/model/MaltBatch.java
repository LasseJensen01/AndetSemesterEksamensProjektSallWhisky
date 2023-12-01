package model;

public class MaltBatch {
    private int batchID;
    private String rygeMateriale;
    private String malteri;
    private String grain;

    //---------------
    private Field field;

    public MaltBatch(int batchID, String rygeMateriale, String malteri, String grain, Field field) {
        this.batchID = batchID;
        this.rygeMateriale = rygeMateriale;
        this.malteri = malteri;
        this.grain = grain;
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

    public String getGrain() {
        return grain;
    }

    public Field getField() {
        return field;
    }
}
