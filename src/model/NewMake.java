package model;

import java.time.LocalDate;
import java.util.List;

public class NewMake {
    private int newMakeID;
    private static int no = 0;
    private boolean isDone = false;
    private LocalDate startDate;
    private LocalDate endDate;
    private double volume;
    private String workerID;
    private String comment;
    private double alcPercent;

    //----------------
    private MaltBatch maltBatch;

    public NewMake(LocalDate startDate, String workerID, MaltBatch maltBatch) {
        no++;
        this.newMakeID = no;
        this.startDate = startDate;
        this.workerID = workerID;
        this.maltBatch = maltBatch;
    }

    public int getNewMakeID() {
        return newMakeID;
    }

    public boolean isDone() {
        return isDone;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getVolume() {
        return volume;
    }

    public String getWorkerID() {
        return workerID;
    }

    public String getComment() {
        return comment;
    }

    public double getAlcPercent() {
        return alcPercent;
    }

    public MaltBatch getMaltBatch() {
        return maltBatch;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setAlcPercent(double alcPercent) {
        this.alcPercent = alcPercent;
    }

    public static void setNo(int no) {
        NewMake.no = no;
    }

    @Override
    public String toString(){
        return String.format("New make #%d", newMakeID);
    }

    public String getContentInfo(){
        String s = "";
        s += "New make Id: " + this.newMakeID + "\nDistilled from " + maltBatch.toString();
        return s;
    }
}
