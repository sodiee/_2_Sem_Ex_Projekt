package Application.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Destillat {

    private int destillatNr;
    private String medarbejder;
    private int liter;
    private double alkoholProcent;
    private LocalDate startDato;
    private LocalDate slutDato;
    private String kornSort;
    private String rygeMateriale;
    private String beskrivelse;
    private boolean isDone = false;
    private ArrayList<Fad> fade;

    //Uden røg
    public Destillat(int destillatNr ,String medarbejder, int liter, double alkoholProcent, LocalDate startDato, LocalDate slutDato, String kornSort, String beskrivelse, boolean isDone) {
        this.medarbejder = medarbejder;
        this.liter = liter;
        this.alkoholProcent = alkoholProcent;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.kornSort = kornSort;
        this.beskrivelse = beskrivelse;
        this.isDone = isDone;
    }

    //Med røg
    public Destillat(int destillatNr, String medarbejder, int liter, double alkoholProcent, LocalDate startDato, LocalDate slutDato, String kornSort, String rygeMateriale, String beskrivelse, boolean isDone) {
        this.medarbejder = medarbejder;
        this.liter = liter;
        this.alkoholProcent = alkoholProcent;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.kornSort = kornSort;
        this.rygeMateriale = rygeMateriale;
        this.beskrivelse = beskrivelse;
        this.isDone = isDone;
    }

    public void addFad(Fad fad){
        if(!fade.contains(fad)){
        fade.add(fad);
    }}

    public void færdigørDestillat(Fad fad){
        isDone = true;
        while(liter > 0) {
            addFad(fad);
            fad.addLiterTofad(liter);
            liter -= fad.getStørrelseLiter();
        }
    }

    public ArrayList<Fad> getFade() {
        return fade;
    }

    public String toString(){
        return beskrivelse;
    }

}
