package Application.Controller;

import Application.Model.Fad;
import Application.Model.Lager;
import Storage.Storage;

public class Controller {
    public static Fad createFad(String leverandør, String tidligereIndhold, int antalGangeBrugt, int nummer, int størrelseLiter, Lager lager) {
        Fad fad = new Fad(leverandør, tidligereIndhold, antalGangeBrugt, nummer, størrelseLiter, lager);
        Storage.addFad(fad);
        return fad;
    }

    public static Lager createLager(int reoler, int hylder, int pladsPåHylde, String adresse) {
        Lager lager = new Lager(reoler, hylder, pladsPåHylde, adresse);
        Storage.addLager(lager);
        return lager;
    }

    public static void initStorage() {
        Lager sønderhøj = createLager(2, 3, 3, "Sønderhøj 30");
        Lager sørenFrichsVej = createLager(3, 2, 2, "Søren Frichs Vej");

        Fad fad = createFad("Sherry distilleri, Lissabon", "Sherry", 1, 64, 130, sønderhøj);
        Fad fad1 = createFad("Bourbon distilleri, Texas", "Bourbon", 1, 326, 90, sønderhøj);
        Fad fad2 = createFad("Sherry distilleri, Madrid", "Sherry", 2, 2, 95, sønderhøj);
        Fad fad3 = createFad("Rødvin farm, Paris", "Rødvin", 1, 54, 50, sørenFrichsVej);
    }
}
