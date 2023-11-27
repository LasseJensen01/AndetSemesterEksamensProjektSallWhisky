package model;

import java.time.LocalDate;

public class Demo {
    public static void main(String[] args) {
        Cask cask1 = new Cask(Type.VIRGIN_OAK, 30);

        NewMake nm1 = new NewMake("nm1", 100);
        NewMake nm2 = new NewMake("nm2",100);
        NewMake nm3 = new NewMake("nm3", 100);

        Tap tap1 = new Tap(nm1, 10);
        Tap tap2 = new Tap(nm2, 15);
        Tap tap3 = new Tap(nm3, 15);

        LocalDate oneMonthBack = LocalDate.now().minusMonths(1);
        LocalDate oneWeekBack = LocalDate.now().minusWeeks(1);

        Filling fill1 = new Filling(cask1, oneMonthBack, "Martin");
        fill1.addTap(tap1);
        fill1.addTap(tap2);

        System.out.println(cask1.getContentsInfo());

        Filling fill2 = new Filling(cask1, oneWeekBack, "Lars");
        fill2.addTap(tap3);

        System.out.println(cask1.getContentsInfo());
    }
}
