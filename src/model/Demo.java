package model;

import controller.Controller;
import controller.Storage;
import storage.ListStorage;

import java.time.LocalDate;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        Storage listStorage = new ListStorage();
        Controller.setStorage(listStorage);

        Farmer f = new Farmer("Frede", "Sønderhøj");

        Field field = new Field("Mark",f);

        MaltBatch mb = new MaltBatch("Tobak","NordFyn","Corn",field);
        NewMake nm1 = new NewMake("GoodShit", LocalDate.now(),"LJ",mb);

        Cask cask1 = new Cask(Type.VIRGIN_OAK, 30);
        Cask cask2 = new Cask(Type.VIRGIN_OAK, 30);
        Cask cask3 = new Cask(Type.VIRGIN_OAK, 30);

        Filling fill1 = new Filling(cask1,"LJ");
        Filling fill2 = new Filling(cask2,"LJ");
        Filling fill3 = new Filling(cask3,"LJ");

        Amount a = new Amount(nm1,30);

        Controller.addAmountToFilling(fill1,a);
        Controller.addAmountToFilling(fill2,a);
        Controller.addAmountToFilling(fill3,a);

        Warehouse wh = new Warehouse("Sall","Sall");
        Controller.createLocationsInWarehouse(wh,10,6,3,3);
    }
}
