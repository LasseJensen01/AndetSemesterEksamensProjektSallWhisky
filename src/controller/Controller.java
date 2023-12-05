package controller;

import model.*;

import java.util.*;

import model.Filling;
import model.NewMake;
import model.Amount;
import model.Cask;

import java.time.LocalDate;

import java.util.stream.Collectors;

public abstract class Controller {
    private static Storage storage;

    public static void setStorage(Storage listStorage) {
        storage = listStorage;
    }

    //hej
    //hi

    //CRUD

    /**
     * Creates Farmer object and stores it in storage.
     * @param name name of farmer
     * @param address address of farmer
     * @return The created Farmer object
     */
    public static Farmer createFarmer(String name, String address){
        Farmer farmer = new Farmer(name, address);
        storage.storeFarmer(farmer);
        return farmer;
    }

    /**
     * Adds a Field object to a Farmer object
     * @param farmer a Farmer object
     * @param field a Field objects
     */
    public static void addFieldsToFarmer(Farmer farmer, Field field){
        farmer.addField(field);
    }

    /**
     * @return all Farmer objects in storage
     */
    public static List<Farmer> getFarmers(){
        return new ArrayList<>(storage.getFarmers());
    }

    /**
     * Creates a Field object, adds it to a Farmer object and stores the Field object in storage
     * @param name name of Field
     * @param farmer the Farmer object that the Field should belong to
     * @return the created Field object
     */
    public static Field createField(String name, Farmer farmer){
        Field field = new Field(name, farmer);
        farmer.addField(field);
        storage.storeField(field);
        return field;
    }

    /**
     * Creates a MaltBatch object and sets its smoke material. The MaltBatch object is stored in storage.
     * @param rygeMateriale material used to smoke the grain
     * @param malteri place that malted the grain
     * @param grain grain used in the MaltBatch
     * @param field which field the grain came from
     * @return the created MaltBatch object
     */
    public static MaltBatch createMaltBatch(String rygeMateriale, String malteri, String grain, Field field){
        MaltBatch maltBatch = new MaltBatch(malteri, grain, field);
        maltBatch.setRygeMateriale(rygeMateriale);
        storage.storeMaltBatch(maltBatch);
        return maltBatch;
    }

    /**
     * Creates a MaltBatch object without smoke material. The MaltBatch object is stored in storage.
     * @param malteri place that malted the grain
     * @param grain grain used in the MaltBatch
     * @param field which field the grain came from
     * @return the created MaltBatch object
     */
    public static MaltBatch createMaltBatch(String malteri, String grain, Field field){
        MaltBatch maltBatch = new MaltBatch(malteri, grain, field);
        storage.storeMaltBatch(maltBatch);
        return maltBatch;
    }

    /**
     * @return all MaltBatch objects in storage
     */
    public static List<MaltBatch> getMaltBatches(){
        return new ArrayList<>(storage.getMaltBatches());
    }

    /**
     * This method creates, stores and returns a newmake
     * @param startDate day production started
     * @param workerID id of responsible worker
     * @param maltBatch the malt batch involved in making the new make
     * @pre name not "", volume > 0
     * @return the NewMake object
     */
    public static NewMake createNewMake(LocalDate startDate, String workerID, MaltBatch maltBatch){
        NewMake newMake = new NewMake(startDate, workerID, maltBatch);
        storage.storeNewMakes(newMake);
        return newMake;
    }

    /**
     * This method updates a given NewMake object and marks it done.
     * It sets the remaining attributes of the NewMake class, including comment.
     * @param newMake chosen NewMak object
     * @param volume amount new make produced
     * @param alcoholPercent alcohol percentage of the produced new make
     * @param endDate day production ended
     * @param comment comment about anything related to the production
     */
    public static void finishNewMakeProcess(NewMake newMake, double volume, double alcoholPercent,
                                            LocalDate endDate, String comment){
        newMake.setVolume(volume);
        newMake.setAlcPercent(alcoholPercent);
        newMake.setEndDate(endDate);
        newMake.setComment(comment);
        newMake.setDone(true);
    }

    /**
     * This method updates a given NewMake object and marks it done.
     * It sets the remaining attributes of the NewMake class, not including comment.
     * @param newMake chosen NewMak object
     * @param volume amount new make produced
     * @param alcoholPercent alcohol percentage of the produced new make
     * @param endDate day production ended
     */
    public static void finishNewMakeProcess(NewMake newMake, double volume, double alcoholPercent, LocalDate endDate){
        newMake.setVolume(volume);
        newMake.setAlcPercent(alcoholPercent);
        newMake.setEndDate(endDate);
        newMake.setDone(true);
    }

    public static void setCaskLiters(Cask cask, double liters){
        if (liters > cask.getVolume() || liters < 0) throw new IllegalArgumentException();
        cask.setLiters(liters); //Used to edit the cask incase of spills
    }
    public static List<Bottle> getBottels(){
        return storage.getBottles();
    }

    /**
     * @return all NewMake objects in storage
     */
    public static List<NewMake> getNewMakes(){
        return storage.getNewMakes();
    }
    public static List<Cask> getCasks(){
        return storage.getCasks();
    }
    public static List<WhiskyProduct> getWhiskyProducts(){
        return storage.getWhiskyProducts();
    }
    /**
     * Creates a warehouse. Will have an Id assigned.
     * @param name - Name of the warehouse
     * @param adress - Adress for the warehouse
     * @return
     */
    public static Warehouse createWarehouse(String name, String adress){
        Warehouse wh = new Warehouse(name, adress);
        storage.storeWarehouses(wh);
        return wh;
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
     * This method adds an amount to a filling
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
    public static Cask getCaskByID(int id){
        for (Cask cask : storage.getCasks()){
            if (cask.getId() == id) return cask;
        }
        return null;
    }

    public static List<Warehouse> getWarehouses(){
        return storage.getWarehouses();
    }

    //---------------------------------------------------------
    //Business logic

    /**
     * This method will take paramterts type and volume, both nullable, and search storgage for elligible
     * casks matching the paramters and return them in a list
     * Will only sort the casks currently in use
     * @param isWhisky whether not content has aged 3 years
     * @param type type of the cask
     * @param volume the amount of liquid the cask can hold
     * @param ID cask ID
     * @param timesUsed times casked has been used
     * @return a list of cask meeting the criteria
     */
    public static List<Cask> locateFullCask(boolean isWhisky, Type type, Double volume, Integer ID, Integer timesUsed){
        List<Cask> casks = storage.getCasks();
        // Starts filtering process of the values typed into the parameters, if parameters are null
        // the parameter is ignored
        List<Cask> goodCasks = casks.stream()
                // Makes sure all casks are currently in use
                .filter(cask -> cask.getLiters() != 0)
                // Checks parameter type
                .filter(cask -> type == null || cask.getType().equals(type))
                // Checks parameter volume
                .filter(cask -> volume == null || cask.getVolume() == volume)
                // Check parameter ID
                .filter(cask -> ID == null || cask.getId() == ID)
                //Check parameter timesUsed
                .filter(cask -> timesUsed == null || cask.getTimesUsed() == timesUsed)
                //Check if isWhisky is Enabled
                .filter(cask -> isWhisky == false || cask.containsWhisky() == true)
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
    public static List<Cask> locateEmptyCask(Type type, Double volume, Integer ID, Integer timesUsed){
        List<Cask> casks = storage.getCasks();
        // Starts filtering process of the values typed into the parameters, if parameters are null
        // the parameter is ignored
        List<Cask> goodCasks = casks.stream()
                // Makes sure all casks are currently NOT in use
                .filter(cask -> cask.getLiters() == 0)
                // Checks parameter type
                .filter(cask -> type == null || cask.getType().equals(type))
                // Checks parameter volume
                .filter(cask -> volume == null || cask.getVolume() == volume)
                // Check parameter ID
                .filter(cask -> ID == null || cask.getId() == ID)
                //Check parameter timesUsed
                .filter(cask -> timesUsed == null || cask.getTimesUsed() == timesUsed)
                // Converts Stream to list
                .collect(Collectors.toList());
        return goodCasks;
    }

    /**
     * Moves a cask in a certain warehouse to a new location.
     * @param warehouse Warehouse of the newLocation
     * @param cask Cask to be moved
     * @param newLocation  New location for cask
     * @throws IllegalArgumentException if newLocation already has a cask
     */
    public static void moveCask(Warehouse warehouse, Cask cask, Location newLocation){
        warehouse.moveCask(cask,newLocation);
    }

    /**
     * Stores a cask in a warehouses first available location
     * @param warehouse warehouse to store cask
     * @param cask cask to store
     */
    public static void storeCask(Warehouse warehouse, Cask cask){
        boolean hasNotBeenStored = true;
        List<Location> locations = warehouse.getLocations();
        // Her er din for-loop med if statement Michael
        for (int i = 0; i < locations.size() && hasNotBeenStored; i++) {
            if (locations.get(i).getCask() == null){
                moveCask(warehouse,cask,locations.get(i));
                hasNotBeenStored = false;
            }
        }
    }

    /**
     * Creates Locations in a warehouse based on the information given
     * @param wh - Warehouse which is getting location created
     * @param rows - Nr. of rows in the location
     * @param shelfUnitsPerRow - Number og shelf units pr. row
     * @param shelfsPerUnit - Nr. of shelfs pr. shelf unit
     */
    public static void createLocationsInWarehouse(Warehouse wh, int rows, int shelfUnitsPerRow, int shelfsPerUnit, int pallet){
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= shelfUnitsPerRow; j++) {
                for (int k = 1; k <= shelfsPerUnit; k++) {
                    for (int l = 1; l <= pallet ; l++) {
                        Location lo = new Location(Integer.toString(i)+
                                "-"+ Integer.toString(j) +"-"+ Integer.toString(k)
                                +"-"+ Integer.toString(l));
                        wh.addLocation(lo);
                    }
                }
            }
        }
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
        NewMake.setNo(idTracker.getNewMakeID());
        MaltBatch.setNo(idTracker.getMaltBatchId());
    }

    /**
     * This method creates a finished whisky.
     * @pram casks a map of cask objects as keys and the desired amount to be taped as values.
     * If a cask is emptied it will have its lokation and filling removed when this method is called.
     * @pram whiskyName the name of the finished whisky.
     */
    public static WhiskyProduct CreateWhiskyProduct(HashMap<Cask, Double> casks, String whiskyName){
        if (!validateCaskSet(casks)) throw new IllegalArgumentException();

        HashMap<Filling, Double> fillings = new HashMap<>();

        for (Cask cask : casks.keySet()){
            fillings.put(cask.getFilling(),(casks.get(cask)));

            if (cask.getLiters() == casks.get(cask)){
                cask.emptyCask();
            } else {
                cask.setLiters(cask.getLiters()-casks.get(cask));
            }
            //Should a cask be empty if it is below a threshold?
        }

        WhiskyProduct whiskyProduct = new WhiskyProduct(fillings, whiskyName);
        storage.storeWhiskyProduct(whiskyProduct);
        return whiskyProduct;
    }
    /**
     * Adds water to a whisky.
     * @param whiskyProduct the whisky.
     * @param liters how much water.
     */
    public static void addWaterToWhisky(WhiskyProduct whiskyProduct, double liters){
        whiskyProduct.addWater(liters);
    }
    /**
     * Helpermethod. It checks if the individual casks contain enought liters for the desired tap.
     * @param casks a set of casks with a double representing the number of liter to be tapped for each cask.
     */
    private static boolean validateCaskSet(HashMap<Cask, Double> casks){
        boolean valid = true;
        for (Cask cask : casks.keySet()){
            if (cask.getLiters() < casks.get(cask)) valid = false;
        }
        return valid;
    }
    public static void putOnBottle(WhiskyProduct whiskyProduct, int noOfBottels, double bottleSize){
        if (noOfBottels*bottleSize > whiskyProduct.getLiters()) throw new IllegalArgumentException();
        for (int i = 0; i < noOfBottels; i++){
            Bottle bottle = new Bottle(bottleSize, whiskyProduct);
            storage.storeBottles(bottle);
        }
        whiskyProduct.setLiters(whiskyProduct.getLiters() - noOfBottels * bottleSize);
    }

    public static List<Field> getFarmersFields(Farmer farmer){
        List<Farmer> allFarmers = storage.getFarmers();
        for (Farmer f : allFarmers){
            if (f.equals(farmer)){
                return f.getFields();
            }
        }
        return null;
    }

    //Update this as the last few CRUD details are added to the controler
    public static void initTestStorage(){
        Farmer Lars = Controller.createFarmer("Lars T", "Hvor kragerne vender.");
        Field fieldOne = Controller.createField("By the hill", Lars);
        Field fieldTwo = Controller.createField("By the river", Lars);
        MaltBatch maltBatchOne = Controller.createMaltBatch("Søren Ryge","Nord Jylland","Byg", fieldOne);
        MaltBatch maltBatchTwo = Controller.createMaltBatch("Søren Ryge","Syd Jylland","Byg", fieldTwo);
        NewMake newMake77 = Controller.createNewMake(LocalDate.now(), "Jonas", maltBatchOne);
        newMake77.setAlcPercent(0.80);
        NewMake newMake78 = Controller.createNewMake(LocalDate.now(), "Maria", maltBatchOne);
        newMake78.setAlcPercent(0.70);
        NewMake newMake79 = Controller.createNewMake(LocalDate.now(), "Ashley", maltBatchTwo);
        newMake79.setAlcPercent(0.60);

        Warehouse warehouse = Controller.createWarehouse("Storage", "Storage Street");
        Controller.createLocationsInWarehouse(warehouse,10,4,3,3);

        Cask caskA = Controller.createCask(Type.AMARONE, 200);
        Filling fillingA = Controller.createFilling(caskA, "Jonas");
        Amount amountA = Controller.createAmount(newMake77, 120);
        Controller.addAmountToFilling(fillingA,amountA);
        Location locationA = new Location("1-1-1-1");
        caskA.setLocation(locationA);

        Cask caskB = Controller.createCask(Type.BAROLO, 200);
        Filling fillingB = Controller.createFilling(caskB, "Jonas");
        Amount amountB = Controller.createAmount(newMake78, 80);
        Controller.addAmountToFilling(fillingB,amountB);
        Amount amountB1 = new Amount(newMake79, 120);
        Controller.addAmountToFilling(fillingB,amountB1);
        Location locationB = new Location("1-1-1-2");
        caskA.setLocation(locationB);

        Cask caskC = Controller.createCask(Type.CHARDONNAY, 200);
        Filling fillingC = Controller.createFilling(caskC, "Jonas");
        Amount amountC1 = Controller.createAmount(newMake79, 175);
        Controller.addAmountToFilling(fillingC,amountC1);
        Location locationC = new Location("1-1-1-3");
        caskA.setLocation(locationC);

        Cask caskD = Controller.createCask(Type.PALO_CORTADO, 200);
        Filling fillingD = Controller.createFilling(caskD, "Jonas");
        Amount amountD = Controller.createAmount(newMake78, 50);
        Amount amountD1 = Controller.createAmount(newMake77, 150);
        Controller.addAmountToFilling(fillingD,amountD);
        Controller.addAmountToFilling(fillingD,amountD1);
        Location locationD = new Location("1-1-2-1");
        caskA.setLocation(locationD);

        Cask caskE = Controller.createCask(Type.SAUTERNES, 200);

        Cask caskF = Controller.createCask(Type.FINO, 200);
        Controller.storeCask(warehouse, caskA);
        Controller.storeCask(warehouse, caskB);
        Controller.storeCask(warehouse, caskC);
        Controller.storeCask(warehouse, caskD);
        Controller.storeCask(warehouse, caskE);
        Controller.storeCask(warehouse, caskF);

        HashMap<Cask, Double> whiskersCasks = new HashMap<>();
        whiskersCasks.put(caskA,Double.valueOf(80));
        whiskersCasks.put(caskB,Double.valueOf(200));
        WhiskyProduct whiskyProduct1 = Controller.CreateWhiskyProduct(whiskersCasks,"Whiskers whisky");


        HashMap<Cask, Double> whimsyWhiskyCasks = new HashMap<>();
        whimsyWhiskyCasks.put(caskC,Double.valueOf(75));
        whimsyWhiskyCasks.put(caskD,Double.valueOf(100));
        WhiskyProduct whiskyProduct2 = Controller.CreateWhiskyProduct(whimsyWhiskyCasks,"Whimsy whisky");
    }
}
