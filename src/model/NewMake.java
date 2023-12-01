package model;

import java.time.LocalDate;
import java.util.List;

public class NewMake {
    private int newMakeID;
    private static int no = 0;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private double volume;
    private String workerID;
    private String comment;
    private double alcPercent;

    public double getAlcPercent() {
        return alcPercent;
    }

    public static void setNo(int no) {
        NewMake.no = no;
    }

    //----------------
    private MaltBatch maltBatch;

    public NewMake(String name, LocalDate startDate, LocalDate endDate, double volume,
                   String workerID, String comment, double alcPercent, MaltBatch maltBatch) {
        no++;
        this.newMakeID = no;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.volume = volume;
        this.workerID = workerID;
        this.comment = comment;
        this.alcPercent = alcPercent;
        this.maltBatch = maltBatch;
    }

    public String getName() {
        return name;
    }
}
