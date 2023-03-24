package Application.Model;

import Application.Controller.Controller;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Lager lager = Controller.createLager(1, 1, 1, "h");
        Destillat destillat = Controller.createDestillat("h", 100, 40, LocalDate.of(2001, 01, 01), LocalDate.of(2004, 01, 01), "h", "h");
        Fad fad = Controller.createFad("h", "h", 1, 1, 100, lager);

        System.out.println("dest.getliter " + destillat.getLiter());

        System.out.println(fad.getAlder());
        System.out.println("Fad.getstørrelse " + fad.getStørrelseLiter());
        System.out.println("Liter fra start " + destillat.getLiterFraStart());
        System.out.println("fad.bAS " + fad.beregnAngelShare(destillat));
        System.out.println("fad.BAS switch " + fad.beregnAngelShare2(destillat));
        System.out.println("dest.getliter " + destillat.getLiter());
        System.out.println();

        fad.setAlder(3);
        System.out.println();
        System.out.println(fad.getAlder());
        System.out.println("Fad.getstørrelse " + fad.getStørrelseLiter());
        System.out.println("Liter fra start " + destillat.getLiterFraStart());
        System.out.println("fad.bAS " + fad.beregnAngelShare(destillat));
        System.out.println("fad.BAS switch " + fad.beregnAngelShare2(destillat));
        System.out.println("dest.getliter " + destillat.getLiter());
        System.out.println();

        fad.setAlder(7);
        System.out.println();
        System.out.println(fad.getAlder());
        System.out.println("Fad.getstørrelse " + fad.getStørrelseLiter());
        System.out.println("Liter fra start " + destillat.getLiterFraStart());
        System.out.println("fad.bAS " + fad.beregnAngelShare(destillat));
        System.out.println("fad.BAS switch " + fad.beregnAngelShare2(destillat));
        System.out.println("dest.getliter " + destillat.getLiter());
        System.out.println();

        fad.setAlder(10);
        System.out.println();
        System.out.println(fad.getAlder());
        System.out.println("Fad.getstørrelse " + fad.getStørrelseLiter());
        System.out.println("Liter fra start " + destillat.getLiterFraStart());
        System.out.println("fad.bAS " + fad.beregnAngelShare(destillat));
        System.out.println("fad.BAS switch " + fad.beregnAngelShare2(destillat));
        System.out.println("dest.getliter " + destillat.getLiter());
        System.out.println();

        fad.setAlder(13);
        System.out.println();
        System.out.println(fad.getAlder());
        System.out.println("Fad.getstørrelse " + fad.getStørrelseLiter());
        System.out.println("Liter fra start " + destillat.getLiterFraStart());
        System.out.println("fad.bAS " + fad.beregnAngelShare(destillat));
        System.out.println("fad.BAS switch " + fad.beregnAngelShare2(destillat));
        System.out.println("dest.getliter " + destillat.getLiter());
        System.out.println();
    }
}
