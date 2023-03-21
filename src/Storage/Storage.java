package Storage;

import Application.Model.Fad;
import Application.Model.Lager;

import java.util.ArrayList;

public class Storage {
    private ArrayList<Fad> fadArrayList = new ArrayList<>();
    private ArrayList<Lager> lagerArrayList = new ArrayList<>();
    //________________________________________________________________________

    public void addFad(Fad fad) {
        fadArrayList.add(fad);
    }

    public ArrayList<Fad> getFadArrayList() {
        return fadArrayList;
    }

    //________________________________________________________________________

    public void addLager(Lager lager) {
        lagerArrayList.add(lager);
    }

    public ArrayList<Lager> getLagerArrayList() {
        return lagerArrayList;
    }
}
