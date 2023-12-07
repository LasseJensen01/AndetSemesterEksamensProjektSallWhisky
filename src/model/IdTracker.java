package model;


import java.io.Serializable;

public class IdTracker implements Serializable {
    private int caskId;
    private int fillingId;
    private int bottleId;
    private int newMakeID;
    private int maltBatchId;

    public int getMaltBatchId() {
        return maltBatchId;
    }

    public void setMaltBatchId(int maltBatchId) {
        this.maltBatchId = maltBatchId;
    }

    public int getNewMakeID() {
        return newMakeID;
    }

    public void setNewMakeID(int newMakeID) {
        this.newMakeID = newMakeID;
    }

    public void setCaskId(int caskId) {
        this.caskId = caskId;
    }

    public void setFillingId(int fillingId) {
        this.fillingId = fillingId;
    }

    public void setBottleId(int bottleId) {
        this.bottleId = bottleId;
    }

    public int getCaskId() {
        return caskId;
    }

    public int getFillingId() {
        return fillingId;
    }

    public int getBottleId() {
        return bottleId;
    }


}
