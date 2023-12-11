package model;

import java.io.Serializable;

public class MaltBatch implements Serializable {
    private final int batchID;
    private static int no = 0;
    private String smokeMaterial;
    private final String maltery;
    private final String grain;

    //---------------
    private final Field field;

    public MaltBatch(String maltery, String grain, Field field) {
        no++;
        this.batchID = no;
        this.maltery = maltery;
        this.grain = grain;
        this.field = field;
    }

    public int getBatchID() {
        return batchID;
    }

    public static void setNo(int no) {
        MaltBatch.no = no;
    }

    public String getSmokeMaterial() {
        return smokeMaterial;
    }

    public void setSmokeMaterial(String smokeMaterial) {
        this.smokeMaterial = smokeMaterial;
    }

    public String getMaltery() {
        return maltery;
    }

    public String getGrain() {
        return grain;
    }

    public Field getField() {
        return field;
    }

    public static int getNo() {
        return no;
    }

    @Override
    public String toString(){
        if(smokeMaterial != null){
            return String.format("Maltbatch #%d made in %s with %s from %s smoked with %s", batchID, maltery, grain, field, smokeMaterial);
        }
        return String.format("Maltbatch #%d made in %s with %s from %s", batchID, maltery, grain, field);
    }
}
