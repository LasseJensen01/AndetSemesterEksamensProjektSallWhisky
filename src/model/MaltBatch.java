package model;

public class MaltBatch {
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
        if(!rygeMateriale.equals(null)){
            String.format("Maltbatch #%d made with %s from %s smoked with %s", batchID, grain, field, rygeMateriale);
        }
        return String.format("Maltbatch #%d made with %s from %s", batchID, grain, field);
    }
}
