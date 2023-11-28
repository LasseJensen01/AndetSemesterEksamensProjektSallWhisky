package controller;

import model.Cask;
import model.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Cask;
import model.Filling;
import model.NewMake;
import model.Amount;

import java.time.LocalDate;

import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Controller {
    private static Storage storage;

    public static void setStorage(Storage listStorage) {
        storage = listStorage;
    }

    //hej
    //hi

    /**
     * This method will take paramterts type and volume, both nullable, and search storgage for elligible
     * casks matching the paramters and return them in a list
     * @param type type of the cask
     * @param volume the amount of liquid the cask can hold
     * @return a list of cask meeting the criteria
     */
    public List<Cask> locateCask(Type type, double volume){
        List<Cask> casks = storage.getCasks();
        // Starts filtering process of the values typed into the parameters, if parameters are null
        // the parameter is ignored
        List<Cask> goodCasks = casks.stream()
                // Makes sure all casks are currently filled
                .filter(cask -> cask.getLiters() > 0)
               // Checks parameter type
                .filter(cask -> cask.getType() == null || cask.getType() == type)
               // Checks parameter volume
                .filter(cask -> Objects.isNull(cask.getVolume()) || cask.getVolume() == volume)
               // Converts Stream to list
                .collect(Collectors.toList());
        return goodCasks;
    }

    public List<Cask> locateFullCask(Type type, double volume){
        List<Cask> casks = storage.getCasks();
        // Starts filtering process of the values typed into the parameters, if parameters are null
        // the parameter is ignored
        List<Cask> goodCasks = casks.stream()
                // Makes sure all casks are currently filled
                .filter(cask -> cask.getLiters() > 0)
                // Checks parameter type
                .filter(cask -> cask.getType() == null || cask.getType() == type)
                // Checks parameter volume
                .filter(cask -> Objects.isNull(cask.getVolume()) || cask.getVolume() == volume)
                // Converts Stream to list
                .collect(Collectors.toList());
        return goodCasks;
    }

    public List<Cask> locateEmptyCask(Type type, double volume){
        List<Cask> casks = storage.getCasks();
        // Starts filtering process of the values typed into the parameters, if parameters are null
        // the parameter is ignored
        List<Cask> goodCasks = casks.stream()
                // Makes sure all casks are currently empty
                .filter(cask -> cask.getLiters() == 0)
                // Checks parameter type
                .filter(cask -> cask.getType() == null || cask.getType() == type)
                // Checks parameter volume
                .filter(cask -> Objects.isNull(cask.getVolume()) || cask.getVolume() == volume)
                // Converts Stream to list
                .collect(Collectors.toList());
        return goodCasks;
    }
    /**
     * This method creates, stores and returns a newmake
     * @param name name of the newmake
     * @param volume the amount of liquid produced from the distillation process
     * @pre name not "", volume > 0
     * @return the newmake
     */
    public static NewMake createNewMake(String name, double volume){
        NewMake newMake = new NewMake(name, volume);
        storage.storeNewMakes(newMake);
        return newMake;
    }
    /**
     * This method creates, stores and returns a filling
     * @param employee the name of the employee
     * @param cask the cask containing the filling
     * @pre employee not "", cask.volume-cask.liters >= filling.liters
     */
    public Filling createFilling(Cask cask, String employee){
        Filling filling = new Filling(cask, LocalDate.now(), employee);
        return filling;
    }
    public static Amount createTap(NewMake newMake, int liters){
        Amount amount = new Amount(newMake, liters);
        return amount;
    }
    public static Amount addTapToFilling(Filling filling, Amount amount){
        filling.addAmount(amount);
        return amount; //Maybe should be void or filling?
    }
    public static String getCaskContent(Cask cask){
        return cask.getContentsInfo();
    }
}
