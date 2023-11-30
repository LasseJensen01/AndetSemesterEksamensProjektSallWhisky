package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;

import model.Filling;
import model.NewMake;
import model.Amount;
import model.Cask;
import org.jetbrains.annotations.Nullable;

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
     * Will only sort the casks currently in use
     * @param type type of the cask
     * @param volume the amount of liquid the cask can hold
     * @return a list of cask meeting the criteria
     */
    public static List<Cask> locateFullCask(Type type, @Nullable Double volume){
        List<Cask> casks = storage.getCasks();
        // Starts filtering process of the values typed into the parameters, if parameters are null
        // the parameter is ignored
        List<Cask> goodCasks = casks.stream()
                // Makes sure all casks are currently in use
                .filter(cask -> cask.getLiters() > 0)
                // Checks parameter type
                .filter(cask -> type == null || cask.getType() == type)
                // Checks parameter volume
                .filter(cask -> Objects.isNull(volume) || cask.getVolume() == volume)
                // Converts Stream to list
                .collect(Collectors.toList());
        return goodCasks;
    }
    /**
     * This method will take paramterts type and volume, both nullable, and search storgage for elligible
     * casks matching the paramters and return them in a list
     * Will only sort the casks currently NOT in use
     * @param type type of the cask
     * @param volume the amount of liquid the cask can hold
     * @return a list of cask meeting the criteria
     */
    public static List<Cask> locateEmptyCask(Type type, @Nullable Double volume){
        List<Cask> casks = storage.getCasks();
        // Starts filtering process of the values typed into the parameters, if parameters are null
        // the parameter is ignored
        List<Cask> goodCasks = casks.stream()
                // Makes sure all casks are currently NOT in use
                .filter(cask -> cask.getLiters() == 0)
                // Checks parameter type
                .filter(cask -> type == null || cask.getType().equals(type))
                // Checks parameter volume
                .filter(cask -> Objects.isNull(volume) || cask.getVolume() == volume)
                // Converts Stream to list
                .collect(Collectors.toList());
        return goodCasks;
    }
    /**
     * This method loads the Ids from the the storage via the tracker.
     * @throws IllegalStateException if storage has not been loaded from a local file.
     */
    public static void loadIdsFromTracker(){
        if (storage == null) throw new IllegalStateException();
        IdTracker idTracker = storage.getIdTracker();
        Bottle.setNo(idTracker.getBottleId());
        Filling.setNo(idTracker.getFillingId());
        Cask.setNo(idTracker.getCaskId());
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
     * This method creates, stores and returns a cask.
     * @param type the type of the cask.
     * @param volume how many liters the cask can contain.
     * @pre employee not "".
     */
    public static Cask createCask(Type type, double volume){
        Cask cask = new Cask(type, volume);
        storage.storeCask(cask);
        storage.getIdTracker().setCaskId(cask.getId());
        return cask;
    }
    /**
     * This method creates and stores a given number of casks.
     * @param type the type of the cask.
     * @param volume how many liters the cask can contain.
     * @param num the number of casks.
     * @pre employee not "".
     */
    public static void registerCasks(Type type, double volume, int num){
        for (int i = 0; i < num; i++){
            createCask(type, volume);
        }
    }
    /**
     * This method creates, stores and returns a filling
     * @param employee the name of the employee
     * @param cask the cask containing the filling
     * @pre employee not "".
     */
    public static Filling createFilling(Cask cask, String employee){
        Filling filling = new Filling(cask, employee);
        storage.getIdTracker().setFillingId(filling.getId());
        return filling;
    }
    /**
     * Left for later... need newmake
     */
    public static Amount createAmount(NewMake newMake, int liters){
        Amount amount = new Amount(newMake, liters);
        return amount;
    }
    /**
     * This method adds a amount to a filling
     * @param filling the filling that the amount is to be added to.
     * @param Amount the amount to be added.
     * @throws IllegalArgumentException if the cask does not have enough volume left to contain the amount.
     */
    public static void addAmountToFilling(Filling filling, Amount amount) throws IllegalArgumentException{
        filling.addAmount(amount);
    }
    /**
     * This method gives a string repesentation of the constents of a cask
     * @param cask a cask.
     */
    public static String getCaskContent(Cask cask){
        return cask.getContentsInfo();
    }
    /**
     * This method tap the content of a cask into a specified number of bottles and stores them in the storage.
     * @param cask a containg a filling.
     * @param numberOfBottels how many bottles the user would like to fill.
     * @param bottleVolume the size of the bottles expressed in liters.
     * @param name the desired name of the whisky on the bottle.
     * @throws IllegalArgumentException if there is insufficient filling on the cask for the number of bottles.
     */
    public static Bottle tapToXnumOfBottels(Cask cask, int numberOfBottels, double bottleVolume, String name){
        Filling filling = cask.getFilling();
        List<Filling> fillings = new ArrayList<>();
        if (numberOfBottels * bottleVolume > filling.getLiters()) throw new IllegalArgumentException();
        Bottle newBatch = new Bottle(bottleVolume, fillings, name, numberOfBottels);
        storage.storeBottles(newBatch);
        storage.getIdTracker().setBottleId(newBatch.getId());
        filling.setLiters(filling.getLiters() - numberOfBottels * bottleVolume);
        if (filling.getLiters() <= 0) cask.emptyCask();
        return newBatch;
    }
    /**
     * This method tap the whole content of a cask into bottles and stores them in the storage.
     * @param cask a containg a filling.
     * @param bottleVolume the size of the bottles expressed in liters.
     * @param name the desired name of the whisky on the bottle.
     * @throws IllegalArgumentException if there is insufficient filling on the cask for the number of bottles.
     */
    public static Bottle tapWholeCaskToBottels(Cask cask, double bottleVolume, String name){
        Filling filling = cask.getFilling();
        List<Filling> fillings = new ArrayList<>();
        int numberOfBottels = (int) (cask.getLiters()/bottleVolume);
        Bottle newBatch = new Bottle(bottleVolume, fillings, name, numberOfBottels);
        storage.storeBottles(newBatch);
        storage.getIdTracker().setBottleId(newBatch.getId());
        cask.emptyCask();
        return newBatch;
    }
    public static void setCaskLiters(Cask cask, double liters){
        if (liters > cask.getVolume() || liters < 0) throw new IllegalArgumentException();
        cask.setLiters(liters); //Used to edit the cask incase of spills
    }
    public static List<Bottle> getBottels(){
        return storage.getBottles();
    }
    public static List<NewMake> getNewMakes(){
        return storage.getNewMakes();
    }
    public static List<Cask> getCasks(){
        return storage.getCasks();
    }
}
