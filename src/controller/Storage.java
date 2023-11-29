package controller;

import model.Bottle;
import model.Cask;
import model.IdTracker;
import model.NewMake;

import java.util.List;

public interface Storage {
    List<Cask> getCasks();
    void storeCask(Cask cask);
    List<NewMake> getNewMakes();
    void storeNewMakes(NewMake newMake);
    List<Bottle> getBottles();
    void storeBottles(Bottle bottle);
    void storeIdTracker(IdTracker idTracker);
    IdTracker getIdTracker();
}
