package model;

import java.io.Serializable;
import java.time.LocalDate;

public class NewMake implements Serializable {
    private final int newMakeID;
    private static int no = 0;
    private boolean isDone = false;
    private final LocalDate startDate;
    private LocalDate endDate;
    private double volume;
    private String employee;
    private String comment;
    private double alcPercent;

    //----------------
    private MaltBatch maltBatch;

    public NewMake(LocalDate startDate, String employee, MaltBatch maltBatch) {
        no++;
        this.newMakeID = no;
        this.startDate = startDate;
        this.employee = employee;
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

    public String getEmployee() {
        return employee;
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

    public static int getNo() {
        return no;
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
