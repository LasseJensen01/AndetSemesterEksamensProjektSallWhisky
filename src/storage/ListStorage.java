package storage;

import controller.Storage;
import model.Cask;
import model.NewMake;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListStorage implements Storage, Serializable {
        private final List<Cask> casks = new ArrayList<>();
        private final List<NewMake> newMakes = new ArrayList<>();

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
}
