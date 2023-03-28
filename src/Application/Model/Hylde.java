package Application.Model;

import java.util.ArrayList;

public class Hylde {
    private int hyldeNr;
    private ArrayList<Hyldeplads> hyldepladser;
    private Reol reol;
    public Hylde(int hyldeNr, Reol reol){
        this.hyldeNr = hyldeNr;
        this.reol = reol;
    }

    public int getHyldeNr() {
        return hyldeNr;
    }

    public ArrayList<Hyldeplads> getHyldepladser() {
        return hyldepladser;
    }
}
