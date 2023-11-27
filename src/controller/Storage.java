package controller;

import model.Cask;
import model.NewMake;

import java.util.List;

public interface Storage {
    List<Cask> getCasks();
    void storeCask(Cask cask);
    List<NewMake> getNewMakes();
    void storeNewMakes(NewMake newMake);
}
