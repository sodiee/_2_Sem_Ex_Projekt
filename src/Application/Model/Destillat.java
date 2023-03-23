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
        this.destillatNr = destillatNr;
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
        this.destillatNr = destillatNr;
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

    public Destillat færdigørDestillat(Fad fad){
        isDone = true;
        hældDestillatPåfad(fad);
        return this;
    }

    public void hældDestillatPåfad(Fad fad){
        while(liter > 0) {
            addFad(fad);
            fad.addDestilatTofad(this);
            liter -= fad.getStørrelseLiter();
        }
    }

    public ArrayList<Fad> getFade() {
        return fade;
    }

    public int getDestillatNr(){
        return destillatNr;
    }
    public String getMedarbejder() {
        return medarbejder;
    }

    public int getLiter() {
        return liter;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public String getKornSort() {
        return kornSort;
    }

    public String getRygeMateriale() {
        return rygeMateriale;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public boolean isDone() {
        return isDone;
    }

    public String toString(){
        return beskrivelse;
    }

}
