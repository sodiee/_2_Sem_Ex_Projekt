package Storage;

import Application.Model.*;

import java.util.ArrayList;

public class Storage {

    //region Global Variables
    private static ArrayList<Whisky> whiskyArrayList = new ArrayList<>();
    private static ArrayList<WhiskyPåFlaske> whiskyPåFlaskeArrayList = new ArrayList<>();
    private static ArrayList<Destillat> destillatArrayList = new ArrayList<>();
    private static ArrayList<Lager> lagerArrayList = new ArrayList<>();
    private static ArrayList<Fad> fadArrayList = new ArrayList<>();
    //endregion

    //region Fad
    public static void addFad(Fad fad) {
        fadArrayList.add(fad);
    }
    public static ArrayList<Fad> getFadArrayList() {
        return fadArrayList;
    }
    public static void deleteFad(Fad fad){fadArrayList.remove(fad);}
    //endregion

    //region Lager
    public static void addLager(Lager lager) {
        lagerArrayList.add(lager);
    }
    public static ArrayList<Lager> getLagerArrayList() {
        return lagerArrayList;
    }
    //endregion

    //region Destillat
    private static int destillatTæller;
    private static double destillatOverflød;
    public static void addDestillat(Destillat destillat){destillatArrayList.add(destillat); destillatTæller++;}
    public static ArrayList<Destillat> getDestillatArrayList(){return destillatArrayList;}
    public static void deleteDestillat(Destillat destillat){destillatArrayList.remove(destillat);}

    //endregion

    //region Whisky
    public static void addWhisky(Whisky whisky) {
        whiskyArrayList.add(whisky);
    }
    public static void removeWhisky(Whisky whisky){whiskyArrayList.remove(whisky);}
    public static ArrayList<Whisky> getWhiskyArrayList() {
        return whiskyArrayList;
    }
    public static void addWhiskyPåFlaske(WhiskyPåFlaske whiskyPåFlaske) {
        whiskyPåFlaskeArrayList.add(whiskyPåFlaske);
    }
    public static ArrayList<WhiskyPåFlaske> getWhiskyPåFlaskeArrayList() {
        return whiskyPåFlaskeArrayList;
    }
    //endregion

}
