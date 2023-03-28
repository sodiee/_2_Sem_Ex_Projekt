package Application.Model;

import java.util.ArrayList;

public class Hyldeplads {

    private int hyldepladsNr;

    private Hylde hylde;
    private Fad fad;

    public boolean optaget;


    public Hyldeplads(int hyldepladsNr, Hylde hylde){
        this.hyldepladsNr = hyldepladsNr;
        this.hylde = hylde;
        this.optaget = false;
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

    public boolean isOptaget() {
        return optaget;
    }

    public void setOptaget(boolean optaget) {
        this.optaget = optaget;
    }
    @Override
    public String toString(){
        return "#" + hyldepladsNr;
    }
}
