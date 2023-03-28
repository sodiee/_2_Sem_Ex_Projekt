package Application.Model;

import java.util.ArrayList;

public class Hylde {
    private int hyldeNr;
    private Reol reol;
    private ArrayList<Hyldeplads> hyldepladser = new ArrayList<>();
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

    public void addHyldePlads(Hyldeplads hyldeplads){
        if(!hyldepladser.contains(hyldeplads)){
            hyldepladser.add(hyldeplads);
            hyldeplads.setHylde(this);
        }
    }

    public void setReol(Reol reol) {
        this.reol = reol;
    }
    @Override
    public String toString(){
        return "#" + hyldeNr;
    }
}
