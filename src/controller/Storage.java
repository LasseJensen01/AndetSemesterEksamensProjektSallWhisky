package controller;

import model.*;

import java.util.List;

public interface Storage {
    List<Supplier> getSuppliers();
    void storeSupplier(Supplier supplier);


    List<Cask> getCasks();
    void storeCask(Cask cask);


    List<NewMake> getNewMakes();
    void storeNewMakes(NewMake newMake);


    List<Bottle> getBottles();
    void storeBottles(Bottle bottle);


    void storeIdTracker(IdTracker idTracker);
    IdTracker getIdTracker();
}
