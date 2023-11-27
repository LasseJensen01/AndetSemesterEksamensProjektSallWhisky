package controller;

import model.Cask;
import model.Type;
import storage.ListStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Controller {
    private static ListStorage storage;

    public static void setStorage(ListStorage listStorage) {
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
}
