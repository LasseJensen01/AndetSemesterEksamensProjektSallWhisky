package model;

public class MaltBatch {
    private int batchID;
    private String rygeMateriale;
    private String malteri;

    //---------------
    private Grain grain;

    public MaltBatch(int batchID, String rygeMateriale, String malteri, Grain grain) {
        this.batchID = batchID;
        this.rygeMateriale = rygeMateriale;
        this.malteri = malteri;
        this.grain = grain;
    }
}
