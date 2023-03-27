package Storage;

import Application.Model.*;

import java.util.ArrayList;

public class Storage {
    private static ArrayList<Fad> fadArrayList = new ArrayList<>();
    private static ArrayList<Lager> lagerArrayList = new ArrayList<>();
    private static ArrayList<Destillat> destillatArrayList = new ArrayList<>();

    private static ArrayList<Whisky> whiskyArrayList = new ArrayList<>();

    private static ArrayList<WhiskyPåFlaske> whiskyPåFlaskeArrayList = new ArrayList<>();
    private static int destillatTæller;

    private static double destillatOverflød;

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

    public static void addDestillat(Destillat destillat){destillatArrayList.add(destillat); destillatTæller++;}

    public static ArrayList<Destillat> getDestillatArrayList(){return destillatArrayList;}

    public static int getDestillatTæller(){return destillatTæller;}

    public static void deleteDestillat(Destillat destillat){destillatArrayList.remove(destillat);}

    //________________________________________________________________________

    public static void addWhisky(Whisky whisky) {
        whiskyArrayList.add(whisky);
    }

    public static ArrayList<Whisky> getWhiskyArrayList() {
        return whiskyArrayList;
    }

    //________________________________________________________________________

    public static void addWhiskyPåFlaske(WhiskyPåFlaske whiskyPåFlaske) {
        whiskyPåFlaskeArrayList.add(whiskyPåFlaske);
    }

    public static ArrayList<WhiskyPåFlaske> getWhiskyPåFlaskeArrayList() {
        return whiskyPåFlaskeArrayList;
    }
}
