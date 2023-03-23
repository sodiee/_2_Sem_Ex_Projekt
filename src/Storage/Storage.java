package Storage;

import Application.Model.Destillat;
import Application.Model.Fad;
import Application.Model.Lager;

import java.util.ArrayList;

public class Storage {
    private static ArrayList<Fad> fadArrayList = new ArrayList<>();
    private static ArrayList<Lager> lagerArrayList = new ArrayList<>();
    private static ArrayList<Destillat> destillatArrayList = new ArrayList<>();
    //________________________________________________________________________

    public static void addFad(Fad fad) {
        fadArrayList.add(fad);
    }

    public static ArrayList<Fad> getFadArrayList() {
        return fadArrayList;
    }

    //________________________________________________________________________

    public static void addLager(Lager lager) {
        lagerArrayList.add(lager);
    }

    public static ArrayList<Lager> getLagerArrayList() {
        return lagerArrayList;
    }

    //________________________________________________________________________

    public static void addDestillat(Destillat destillat){destillatArrayList.add(destillat);}
    public static ArrayList<Destillat> getDestillatArrayList(){return destillatArrayList;}
}
