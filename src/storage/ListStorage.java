package storage;

import controller.Storage;
import model.Cask;
import model.NewMake;
import model.Warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListStorage implements Storage, Serializable {
        private final List<Cask> casks = new ArrayList<>();
        private final List<NewMake> newMakes = new ArrayList<>();
        private final List<Warehouse> warehouses = new ArrayList<>();

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
        public void storeWarehouse(Warehouse wh) {
                warehouses.add(wh);
        }
}
