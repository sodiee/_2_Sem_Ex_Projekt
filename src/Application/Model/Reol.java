package Application.Model;

import java.util.ArrayList;

public class Reol {
    private int reolNr;
    private Lager lager;
    private ArrayList<Hylde> hylder = new ArrayList<>();

    /**
     * Opretter en reol ud fra de angivede parametre
     * @param reolNr reolens nummer
     * @param lager lageret som reolen skal oprettes på
     */
    public Reol(int reolNr, Lager lager){
        this.reolNr = reolNr;
        this.lager = lager;
    }
    public ArrayList<Hylde> getHylder() {return hylder;}
    public int getReolNr() {return reolNr;}
    public void addHylde(Hylde hylde){
        if(!hylder.contains(hylde)){
            hylder.add(hylde);
            hylde.setReol(this);
        }
    }
    public void setLager(Lager lager) {
        this.lager = lager;
    }

    /**
     *
     * @return "# + Reolnummer"
     */
    @Override
    public String toString(){
        return "#" + reolNr;
    }
}


