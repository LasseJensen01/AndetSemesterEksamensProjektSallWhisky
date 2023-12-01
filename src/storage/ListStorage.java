package storage;

import controller.Storage;
import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListStorage implements Storage, Serializable {
        private final List<Farmer> farmers = new ArrayList<>();
        private final List<Field> fields = new ArrayList<>();
        private final List<MaltBatch> maltBatches = new ArrayList<>();
        private final List<Cask> casks = new ArrayList<>();
        private final List<NewMake> newMakes = new ArrayList<>();
        private final List<Bottle> bottles = new ArrayList<>();
        private final List<Warehouse> warehouses = new ArrayList<>();
        private final List<WhiskyProduct> whiskyProducts = new ArrayList<>();
        private IdTracker idTracker = new IdTracker();

        public static ListStorage loadStorage(){
                String fileName = "storage.save";
                try (FileInputStream fileIn = new FileInputStream(fileName);
                     ObjectInputStream objIn = new ObjectInputStream(fileIn);
                ){
                        Object obj = objIn.readObject();
                        ListStorage storage = (ListStorage) obj;
                        System.out.println("Storage loaded from file " + fileName);
                        return storage;
                } catch(Exception e) {
                        System.out.println(e.getMessage());
                        return null;
                }
        }
        public static void saveStorage(Storage storage){
                String fileName = "storage.save";
                try (FileOutputStream fileOut = new FileOutputStream(fileName);
                     ObjectOutputStream objOut = new ObjectOutputStream(fileOut)){
                        objOut.writeObject(storage);
                        System.out.println("Saved in file " + fileName);
                } catch (Exception e){
                        System.out.println(e.getMessage());
                }
        }

        //Supplier
        @Override
        public List<Farmer> getFarmers(){
                return new ArrayList<>(farmers);
        }
        @Override
        public void storeFarmer(Farmer farmer){
                farmers.add(farmer);
        }

        //Field
        @Override
        public List<Field> getFields(){return new ArrayList<>(fields);}
        @Override
        public void storeField(Field field){fields.add(field);}

        //MaltBatch
        @Override
        public List<MaltBatch> getMaltBatches(){return new ArrayList<>(maltBatches);}
        @Override
        public void storeMaltBatch(MaltBatch maltBatch){maltBatches.add(maltBatch);}

        //Cask
        @Override
        public List<Cask> getCasks() {
                return new ArrayList<>(casks);
        }

        @Override
        public void storeCask(Cask cask) {
                casks.add(cask);
        }

        //NewMake
        @Override
        public List<NewMake> getNewMakes() {
                return new ArrayList<>(newMakes);
        }

        @Override
        public void storeNewMakes(NewMake newMake) {
                newMakes.add(newMake);
        }

        //Bottle
        @Override
        public List<Bottle> getBottles() {
                return new ArrayList<>(bottles);
        }

        @Override
        public void storeBottles(Bottle bottle) {
                bottles.add(bottle);
        }

        //IdTracker
        @Override
        public void storeIdTracker(IdTracker idTracker) {
                this.idTracker = idTracker;
        }
        @Override
        public IdTracker getIdTracker() {
                return idTracker;
        }

        @Override
        public List<WhiskyProduct> getWhiskyProducts() {
                return new ArrayList<>(this.whiskyProducts);
        }

        @Override
        public void storeWhiskyProduct(WhiskyProduct whiskyProduct) {
                whiskyProducts.add(whiskyProduct);
        }

        @Override
        public List<Warehouse> getWarehouses() {
                return new ArrayList<>(warehouses);
        }

        @Override
        public void storeWarehouses(Warehouse warehouse) {
                warehouses.add(warehouse);
        }
}
