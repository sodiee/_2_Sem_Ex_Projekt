package Application.Model;

import javafx.scene.control.Label;

import java.util.ArrayList;

public class Reol {
    private int reolNr;
    private ArrayList<Hylde> hylder;

    private Lager lager;

    public Reol(int reolNr, Lager lager){
        this.reolNr = reolNr;
        this.lager = lager;
    }

    public ArrayList<Hylde> getHylder() {return hylder;}
    public int getReolNr() {return reolNr;}

    public void addHylde(Hylde hylde){
        if(!hylder.contains(hylde)){
            hylder.add(hylde);
        }
    }
}
