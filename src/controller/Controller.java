package controller;

import model.Cask;
import model.Type;

import java.util.ArrayList;
import java.util.List;

import model.Filling;
import model.NewMake;
import model.Amount;

import java.time.LocalDate;

import java.util.Objects;

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
    //TODO
    //Locate cask tager lige PT alle casks. Skal kun tage enten fyldte eller tomme så skal splittes
    //til to metoder locateFilledCasks() og locateEmptyCasks()
    public List<Cask> locateCask(Type t, double v){
        List<Cask> casks = storage.getCasks();
        List<Cask> goodCasks = new ArrayList<>();

        return goodCasks;
    }

    public List<Cask> locateFullCask(Type type, double volume){
        List<Cask> casks = storage.getCasks();
        List<Cask> goodCasks = new ArrayList<>();

        //Both paramters fulfilled
        if (type != null && !Objects.isNull(volume)){
            for (Cask c : casks){
                if (c.getLiters() > 0 && c.getType().equals(type) && c.getVolume() == volume){
                    goodCasks.add(c);
                }
            }
        }
        // Only type fulfilled
        else if (type != null && Objects.isNull(volume)){
            for (Cask c : casks){
                if (c.getLiters() > 0 && c.getType().equals(type)){
                    goodCasks.add(c);
                }
            }
        }
        //Only volume fulfilled
        else if (type == null && !Objects.isNull(volume)){
            for (Cask c : casks){
                if (c.getLiters() > 0 && c.getVolume() == volume){
                    goodCasks.add(c);
                }
            }
        }
        return goodCasks;
    }

    public List<Cask> locateEmptyCask(Type type, double volume){
        List<Cask> casks = storage.getCasks();
        List<Cask> goodCasks = new ArrayList<>();

        //Both paramters fulfilled
        if (type != null && !Objects.isNull(volume)){
            for (Cask c : casks){
                if (c.getLiters() == 0 && c.getType().equals(type) && c.getVolume() == volume){
                    goodCasks.add(c);
                }
            }
        }
        // Only type fulfilled
        else if (type != null && Objects.isNull(volume)){
            for (Cask c : casks){
                if (c.getLiters() == 0 && c.getType().equals(type)){
                    goodCasks.add(c);
                }
            }
        }
        //Only volume fulfilled
        else if (type == null && !Objects.isNull(volume)){
            for (Cask c : casks){
                if (c.getLiters() == 0 && c.getVolume() == volume){
                    goodCasks.add(c);
                }
            }
        }
        return goodCasks;
    }
    /**
     * This method creates, stores and returns a newmake
     * @param name name of the newmake, pre: not empty
     * @param volume the amount of liquid produced from the distillation process, pre: must be positive
     * @return the newmake
     */
    public static NewMake createNewMake(String name, double volume){
        NewMake newMake = new NewMake(name, volume);
        storage.storeNewMakes(newMake);
        return newMake;
    }
    /**
     * This method creates, stores and returns a filling
     * @param Cask
     * @param volume the amount of liquid produced from the distillation process, pre: must be positive
     * @return the newmake
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
        filling.addTap(amount);
        return amount; //Maybe should be void or filling?
    }
    public static String getCaskContent(Cask cask){
        return cask.getContentsInfo();
    }
}
