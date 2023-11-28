package controller;

import model.Cask;
import model.Type;

import java.util.ArrayList;
import java.util.List;

import model.Cask;
import model.Filling;
import model.NewMake;
import model.Tap;

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

        //Both paramters fulfilled
        if (t != null && !Objects.isNull(v)){
            for (Cask c : casks){
                if (c.getType().equals(t) && c.getVolume() == v){
                    goodCasks.add(c);
                }
            }
        }
        // Only type fulfilled
        else if (t != null && Objects.isNull(v)){
            for (Cask c : casks){
                if (c.getType().equals(t)){
                    goodCasks.add(c);
                }
            }
        }
        //Only volume fulfilled
        else if (t == null && !Objects.isNull(v)){
            for (Cask c : casks){
                if (c.getVolume() == v){
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
    public static Tap createTap(NewMake newMake, int liters){
        Tap tap = new Tap(newMake, liters);
        return tap;
    }
    public static Tap addTapToFilling(Filling filling, Tap tap){
        filling.addTap(tap);
        return tap; //Maybe should be void or filling?
    }
    public static String getCaskContent(Cask cask){
        return cask.getContentsInfo();
    }
}
