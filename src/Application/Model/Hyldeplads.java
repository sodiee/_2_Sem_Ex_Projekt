package Application.Model;

import java.util.ArrayList;

public class Hyldeplads {

    private int hyldepladsNr;
    private Fad fad;

    public Hyldeplads(int hyldepladsNr){
        this.hyldepladsNr = hyldepladsNr;
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
}
