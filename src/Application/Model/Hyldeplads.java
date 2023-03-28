package Application.Model;

import java.util.ArrayList;

public class Hyldeplads {

    private int hyldepladsNr;
    private Fad fad;

    private Hylde hylde;

    public Hyldeplads(int hyldepladsNr, Hylde hylde){
        this.hyldepladsNr = hyldepladsNr;
        this.hylde = hylde;
    }

    public int getHyldepladsNr() {
        return hyldepladsNr;
    }

    public Fad getFad() {
        return fad;
    }

    public void setHylde(Hylde hylde) {
        this.hylde = hylde;
    }
}
