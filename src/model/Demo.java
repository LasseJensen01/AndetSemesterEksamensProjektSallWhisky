package model;

import controller.Controller;
import storage.ListStorage;

import java.time.LocalDate;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        Controller.setStorage(new ListStorage());
        Cask cask1 = new Cask(Type.VIRGIN_OAK, 30);
        Controller.addCaskToStorage(cask1);
        Cask cask2 = new Cask(Type.VIRGIN_OAK, 30);
        Controller.addCaskToStorage(cask2);
        Cask cask3 = new Cask(Type.VIRGIN_OAK, 45);
        Controller.addCaskToStorage(cask3);
        Cask cask4 = new Cask(Type.VIRGIN_OAK, 30);
        Controller.addCaskToStorage(cask4);
        Cask cask5 = new Cask(Type.BOURBON, 30);
        Controller.addCaskToStorage(cask5);


        NewMake nm1 = new NewMake("nm1", 100);
        NewMake nm2 = new NewMake("nm2",100);
        NewMake nm3 = new NewMake("nm3", 100);

        Amount amount1 = new Amount(nm1, 10);
        Amount amount2 = new Amount(nm2, 15);
        Amount amount3 = new Amount(nm3, 15);

        LocalDate oneMonthBack = LocalDate.now().minusMonths(1);
        LocalDate oneWeekBack = LocalDate.now().minusWeeks(1);

        Filling fill1 = new Filling(cask1, oneMonthBack, "Martin");
        fill1.addAmount(amount1);
        fill1.addAmount(amount2);

        System.out.println(cask1.getContentsInfo());

        Filling fill2 = new Filling(cask1, oneWeekBack, "Lars");
        fill2.addAmount(amount3);

        // Test for Locate metoder
        List<Cask> l = Controller.locateEmptyCask(null, null);
        System.out.println(l.size());
        for (Cask c : l){
            System.out.println(c.toString());
        }

        //Test for Warehouse
        Warehouse wh1 = new Warehouse(1,"EAA","Strandvejen");
        int count = 1;
        for (int i = 0; i < 100; i++) {
            Location lo = new Location(count);
            Cask c = new Cask(Type.VIRGIN_OAK, count * 2);
            c.setLiters(c.getVolume() / 2);
            lo.setCask(c);
            wh1.addLocation(lo);
            Filling f = new Filling(c,LocalDate.now(),"Me");
            Controller.addTapToFilling(f,new Amount(nm1,3));
        }
        wh1.extractOverview();
    }
}
