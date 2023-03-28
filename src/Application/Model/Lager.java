package Application.Model;

import java.util.ArrayList;

public class Lager {
    private int hylder;
    private int pladsPåHylde;
    private int reoler;
    private String adresse;
    private ArrayList<Fad> fadArrayList;
    private ArrayList<Reol> reolArrayList;

    public Lager(int reol, int hylde, int pladsPåHylde, String adresse) {
        this.reoler = reol;
        this.hylder = hylde;
        this.pladsPåHylde = pladsPåHylde;
        this.adresse = adresse;
        this.fadArrayList = new ArrayList<>();
        this.reolArrayList = new ArrayList<>();
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

    public ArrayList<Reol> getReoler(){return reolArrayList;}

    public void addReol(Reol reol){
        if(!reolArrayList.contains(reol)){
            reolArrayList.add(reol);
        }
    }

}
