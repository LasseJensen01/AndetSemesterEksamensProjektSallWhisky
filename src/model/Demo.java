package model;

import controller.Controller;
import storage.ListStorage;

import java.time.LocalDate;

public class Demo {
    public static void main(String[] args) {
        Controller.setStorage(new ListStorage());
        Cask cask1 = new Cask(Type.VIRGIN_OAK, 30);

        NewMake nm1 = new NewMake("nm1", 100);
        NewMake nm2 = new NewMake("nm2",100);
        NewMake nm3 = new NewMake("nm3", 100);

        Amount amount1 = new Amount(nm1, 10);
        Amount amount2 = new Amount(nm2, 15);
        Amount amount3 = new Amount(nm3, 15);

        LocalDate oneMonthBack = LocalDate.now().minusMonths(1);
        LocalDate oneWeekBack = LocalDate.now().minusWeeks(1);

        Filling fill1 = new Filling(cask1, oneMonthBack, "Martin");
        fill1.addTap(amount1);
        fill1.addTap(amount2);

        System.out.println(cask1.getContentsInfo());

        Filling fill2 = new Filling(cask1, oneWeekBack, "Lars");
        fill2.addTap(amount3);

        System.out.println(cask1.getContentsInfo());
    }
}
