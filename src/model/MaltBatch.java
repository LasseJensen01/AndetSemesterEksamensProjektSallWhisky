package model;

import java.io.Serializable;

public class MaltBatch implements Serializable {
    private int batchID;
    private static int no = 0;
    private String rygeMateriale;
    private String malteri;
    private String grain;

    //---------------
    private Field field;

    public MaltBatch(String malteri, String grain, Field field) {
        no++;
        this.batchID = no;
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

    public void setRygeMateriale(String rygeMateriale) {
        this.rygeMateriale = rygeMateriale;
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

    @Override
    public String toString(){
        if(rygeMateriale != null){
            return String.format("Maltbatch #%d made in %s with %s from %s smoked with %s", batchID,  malteri, grain, field, rygeMateriale);
        }
        return String.format("Maltbatch #%d made in %s with %s from %s", batchID, malteri, grain, field);
    }
}
