package Application.Model;

import Application.Controller.Controller;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Lager lager = Controller.createLager(1, 1, 1, "h");
        Fad fad = Controller.createFad("h", "h", 1, 1, 100, lager);
        Destillat destillat = Controller.createDestillat("h", 100, 40, LocalDate.of(2001, 01, 01), LocalDate.of(2004, 01, 01), "h", "h");
        destillat.hældDestillatPåfad(fad);
        Whisky whisky = Controller.createWhisky(fad);
/*
        System.out.println("dest.getliter " + destillat.getLiter());

        System.out.println(fad.getAlder());
        System.out.println("Fad.getstørrelse " + fad.getStørrelseLiter());
        System.out.println("Liter fra start " + destillat.getLiterFraStart());

        System.out.println("fad.bas if-else 2 " + fad.beregnAngelShare(destillat));
        System.out.println("dest.getliter " + destillat.getLiter());
        System.out.println();

        fad.setAlder(3);
        System.out.println();
        System.out.println(fad.getAlder());
        System.out.println("Fad.getstørrelse " + fad.getStørrelseLiter());
        System.out.println("Liter fra start " + destillat.getLiterFraStart());

        System.out.println("fad.bas if-else 2 " + fad.beregnAngelShare(destillat));
        System.out.println("dest.getliter " + destillat.getLiter());
        System.out.println();

        fad.setAlder(7);
        System.out.println();
        System.out.println(fad.getAlder());
        System.out.println("Fad.getstørrelse " + fad.getStørrelseLiter());
        System.out.println("Liter fra start " + destillat.getLiterFraStart());

        System.out.println("fad.bas if-else 2 " + fad.beregnAngelShare(destillat));
        System.out.println("dest.getliter " + destillat.getLiter());
        System.out.println();

        fad.setAlder(10);
        System.out.println();
        System.out.println(fad.getAlder());
        System.out.println("Fad.getstørrelse " + fad.getStørrelseLiter());
        System.out.println("Liter fra start " + destillat.getLiterFraStart());
        System.out.println("fad.bas if-else 2 " + fad.beregnAngelShare(destillat));
        System.out.println("dest.getliter " + destillat.getLiter());
        System.out.println();

        fad.setAlder(13);
        System.out.println();
        System.out.println(fad.getAlder());
        System.out.println("Fad.getstørrelse " + fad.getStørrelseLiter());
        System.out.println("Liter fra start " + destillat.getLiterFraStart());

        System.out.println("fad.bas if-else 2 " + fad.beregnAngelShare(destillat));
        System.out.println("dest.getliter " + destillat.getLiter());
        System.out.println();
*/
        System.out.println(whisky.getLiter());

        ArrayList<WhiskyPåFlaske> whiskyPåFlaskeArrayList = Controller.createWhiskyPåFlaske(whisky, 50, 15);
        System.out.println(whiskyPåFlaskeArrayList);
        System.out.println(whisky.getLiter());
    }
}
