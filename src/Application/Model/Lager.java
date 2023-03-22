package Application.Model;

import java.util.ArrayList;

public class Lager {
    private int reoler;
    private int hylder;
    private int pladsPåHylde;
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
}
