package Application.Model;

import java.util.ArrayList;

public class Hylde {

    //region Global Variables
    private int hyldeNr;
    private Reol reol;
    private ArrayList<Hyldeplads> hyldepladser = new ArrayList<>();
    //endregion

    /**
     * Skaber et hylde model-objekt
     * @param hyldeNr nummeret på hylden
     * @param reol reolen hylden ligger på
     */
    public Hylde(int hyldeNr, Reol reol){
        this.hyldeNr = hyldeNr;
        this.reol = reol;
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

    //region Getters
    public Reol getReol() {
        return this.reol;
    }
    public ArrayList<Hyldeplads> getHyldepladser() {
        return hyldepladser;
    }
    public int getHyldeNr() {
        return hyldeNr;
    }
    //endregion

    /**
     *
     * @return # + Hyldenummer
     */
    @Override
    public String toString(){
        return "#" + hyldeNr;
    }
}
