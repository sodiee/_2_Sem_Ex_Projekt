package Application.Model;

import java.util.ArrayList;

public class Lager {
    private int hylder;
    private int pladsPåHylde;
    private int reole;
    private String adresse;
    private ArrayList<Reol> reoler;

    private ArrayList<WhiskyPåFlaske> flasker;

    public Lager(int reol, int hylde, int pladsPåHylde, String adresse) {
        this.reole = reol;
        this.hylder = hylde;
        this.pladsPåHylde = pladsPåHylde;
        this.adresse = adresse;
        this.reoler = new ArrayList<>();
        this.flasker = new ArrayList<>();
    }

    public void addFlaske(WhiskyPåFlaske whiskyPåFlaske) {
        if (!flasker.contains(whiskyPåFlaske)) {
            flasker.add(whiskyPåFlaske);
            whiskyPåFlaske.setLager(this);
        }
    }

    public ArrayList<WhiskyPåFlaske> getFlasker() {
        return flasker;
    }

    public String toString() {
        return adresse;
    }

    public ArrayList<Reol> getReoler(){return reoler;}

    public void addReol(Reol reol){
        if(!reoler.contains(reol)){
            reoler.add(reol);
            reol.setLager(this);
        }
    }

}
