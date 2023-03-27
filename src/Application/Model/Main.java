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

        System.out.println("fad.BAS switch " + fad.beregnAngelShare2(destillat));
        System.out.println("fad.bas if-else 2 " + fad.beregnAngelShare3(destillat));
        System.out.println("dest.getliter " + destillat.getLiter());
        System.out.println();

        fad.setAlder(3);
        System.out.println();
        System.out.println(fad.getAlder());
        System.out.println("Fad.getstørrelse " + fad.getStørrelseLiter());
        System.out.println("Liter fra start " + destillat.getLiterFraStart());

        System.out.println("fad.BAS switch " + fad.beregnAngelShare2(destillat));
        System.out.println("fad.bas if-else 2 " + fad.beregnAngelShare3(destillat));
        System.out.println("dest.getliter " + destillat.getLiter());
        System.out.println();

        fad.setAlder(7);
        System.out.println();
        System.out.println(fad.getAlder());
        System.out.println("Fad.getstørrelse " + fad.getStørrelseLiter());
        System.out.println("Liter fra start " + destillat.getLiterFraStart());

        System.out.println("fad.BAS switch " + fad.beregnAngelShare2(destillat));
        System.out.println("fad.bas if-else 2 " + fad.beregnAngelShare3(destillat));
        System.out.println("dest.getliter " + destillat.getLiter());
        System.out.println();

        fad.setAlder(10);
        System.out.println();
        System.out.println(fad.getAlder());
        System.out.println("Fad.getstørrelse " + fad.getStørrelseLiter());
        System.out.println("Liter fra start " + destillat.getLiterFraStart());
        System.out.println("fad.BAS switch " + fad.beregnAngelShare2(destillat));
        System.out.println("fad.bas if-else 2 " + fad.beregnAngelShare3(destillat));
        System.out.println("dest.getliter " + destillat.getLiter());
        System.out.println();

        fad.setAlder(13);
        System.out.println();
        System.out.println(fad.getAlder());
        System.out.println("Fad.getstørrelse " + fad.getStørrelseLiter());
        System.out.println("Liter fra start " + destillat.getLiterFraStart());

        System.out.println("fad.BAS switch " + fad.beregnAngelShare2(destillat));
        System.out.println("fad.bas if-else 2 " + fad.beregnAngelShare3(destillat));
        System.out.println("dest.getliter " + destillat.getLiter());
        System.out.println();

        fad.setAlder(0);
        System.out.println("0 fad.BAS switch " + fad.beregnAngelShare2(destillat));

        fad.setAlder(1);
        System.out.println("1 fad.BAS switch " + fad.beregnAngelShare2(destillat));

        fad.setAlder(2);
        System.out.println("2 fad.BAS switch " + fad.beregnAngelShare2(destillat));

        fad.setAlder(4);
        System.out.println("4 fad.BAS switch " + fad.beregnAngelShare2(destillat));

        fad.setAlder(5);
        System.out.println("5 fad.BAS switch " + fad.beregnAngelShare2(destillat));

        fad.setAlder(7);
        System.out.println("7 fad.BAS switch " + fad.beregnAngelShare2(destillat));

        fad.setAlder(8);
        System.out.println("8 fad.BAS switch " + fad.beregnAngelShare2(destillat));

        fad.setAlder(11);
        System.out.println("11 fad.BAS switch " + fad.beregnAngelShare2(destillat));

        fad.setAlder(12);
        System.out.println("12 fad.BAS switch " + fad.beregnAngelShare2(destillat));

        fad.setAlder(13);
        System.out.println("13 fad.BAS switch " + fad.beregnAngelShare2(destillat));

        fad.setAlder(20);
        System.out.println("20 fad.BAS switch " + fad.beregnAngelShare2(destillat));

        Whisky whisky = new Whisky(100, 50, "", true, 50, fad);
        System.out.println(whisky.hældWhiskyPåFlaske(130));
    }
}
