package Application.Model;

import java.util.ArrayList;

public class Hyldeplads {

    private int hyldepladsNr;

    private Hylde hylde;
    private Fad fad;


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

    public void setFad(Fad fad) {
        this.fad = fad;
    }

    public void setHylde(Hylde hylde) {
        this.hylde = hylde;
    }
}
