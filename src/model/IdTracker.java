package model;

public class IdTracker{
    private int caskId;
    private int fillingId;
    private int bottleId;

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
