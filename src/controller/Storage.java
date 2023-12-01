package controller;

import model.*;

import java.util.List;

public interface Storage {
    List<Farmer> getFarmers();
    void storeFarmer(Farmer farmer);


    List<Field> getFields();
    void storeField(Field field);


    List<MaltBatch> getMaltBatches();
    void storeMaltBatch(MaltBatch maltBatch);


    List<NewMake> getNewMakes();
    void storeNewMakes(NewMake newMake);


    List<Cask> getCasks();
    void storeCask(Cask cask);


    List<Bottle> getBottles();
    void storeBottles(Bottle bottle);


    List<Warehouse> getWarehouses();
    void storeWarehouses(Warehouse warehouse);


    void storeIdTracker(IdTracker idTracker);
    IdTracker getIdTracker();
}
