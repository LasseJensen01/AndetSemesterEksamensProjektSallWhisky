package storage;

import controller.Storage;
import model.Bottle;
import model.Cask;
import model.IdTracker;
import model.NewMake;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListStorage implements Storage, Serializable {
        private final List<Cask> casks = new ArrayList<>();
        private final List<NewMake> newMakes = new ArrayList<>();
        private final List<Bottle> bottles = new ArrayList<>();
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
        @Override
        public List<Cask> getCasks() {
                return new ArrayList<>(casks);
        }

        @Override
        public void storeCask(Cask cask) {
                casks.add(cask);
        }

        @Override
        public List<NewMake> getNewMakes() {
                return new ArrayList<>(newMakes);
        }

        @Override
        public void storeNewMakes(NewMake newMake) {
                newMakes.add(newMake);
        }

        @Override
        public List<Bottle> getBottles() {
                return new ArrayList<>(bottles);
        }

        @Override
        public void storeBottles(Bottle bottle) {
                bottles.add(bottle);
        }

        @Override
        public void storeIdTracker(IdTracker idTracker) {
                this.idTracker = idTracker;
        }
        @Override
        public IdTracker getIdTracker() {
                return idTracker;
        }
}
