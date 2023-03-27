package Application.Model;

import java.util.ArrayList;

public class Lager {
    private int hylder;
    private int pladsPåHylde;
    private int reoler;
    private String adresse;
    private ArrayList<Fad> fadArrayList;

    public Lager(int reol, int hylde, int pladsPåHylde, String adresse) {
        this.reoler = reol;
        this.hylder = hylde;
        this.pladsPåHylde = pladsPåHylde;
        this.adresse = adresse;
        this.fadArrayList = new ArrayList<>();
    }

    public void addToList(Fad fad) {
        if (!fadArrayList.contains(fad)) {
            fadArrayList.add(fad);
        }
    }

    public ArrayList<Fad> getFade() {
        return fadArrayList;
    }

    public String toString() {
        return adresse;
    }
}
