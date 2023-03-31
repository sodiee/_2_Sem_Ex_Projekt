package Application.Model;

import Application.Controller.Controller;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Lager lager = Controller.createLager(1, 1, 1, "Søndervej 50");
        Reol reol = Controller.createReol(lager, 2, 2);
        Hyldeplads hyldePlads = reol.getHylder().get(1).getHyldepladser().get(1);
        Fad fad = Controller.createFad("Lissabon rødvinsfarm", "Bourbon", 1, 100, hyldePlads);
        Destillat destillat = Controller.createDestillat("Lise", 100, 40, LocalDate.of(2001, 01, 01), "Evergreen", "Super duper destillat", null);
        destillat.hældDestillatPåfad(fad);
        String navn = "WhiskyOnTheRobs";
        System.out.println(fad.getDestillat());
        Controller.convertToWhisky(fad, navn);
        System.out.println(fad.getDestillat());
        Whisky whisky = (Whisky) fad.getDestillat();

        ArrayList<WhiskyPåFlaske> wpf = whisky.hældWhiskyPåFlaske(2, 15);

        for (WhiskyPåFlaske flaske : wpf) {
          System.out.println(flaske.getHistorik());
        }
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
        //System.out.println(whisky.getLiter());

        // ArrayList<WhiskyPåFlaske> whiskyPåFlaskeArrayList = Controller.createWhiskyPåFlaske(whisky, 50, 15, lager);
        // System.out.println(whiskyPåFlaskeArrayList);
        // System.out.println(whisky.getLiter());

    }
}
