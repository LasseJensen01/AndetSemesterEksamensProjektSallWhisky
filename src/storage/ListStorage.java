package storage;

import controller.Storage;
import model.Bottle;
import model.Cask;
import model.IdTracker;
import model.NewMake;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListStorage implements Storage, Serializable {
        private final List<Cask> casks = new ArrayList<>();
        private final List<NewMake> newMakes = new ArrayList<>();
        private final List<Bottle> bottles = new ArrayList<>();
        private IdTracker idTracker = new IdTracker();

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
