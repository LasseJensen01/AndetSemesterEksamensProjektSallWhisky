package model;

public class MaltBatch {
    private int batchID;
    private static int no = 0;
    private String rygeMateriale;
    private String malteri;
    private String grain;

    //---------------
    private Field field;

    public MaltBatch(String rygeMateriale, String malteri, Field field) {
        no++;
        this.batchID = no;
        this.rygeMateriale = rygeMateriale;
        this.malteri = malteri;
        this.grain = grain;
        this.field = field;
    }

    public int getBatchID() {
        return batchID;
    }

    public static void setNo(int no) {
        MaltBatch.no = no;
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
