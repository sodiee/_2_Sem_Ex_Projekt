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
    private ArrayList<Fad> fade = new ArrayList<>();

    //Uden røg
    public Destillat(String medarbejder, int liter, double alkoholProcent, LocalDate startDato, LocalDate slutDato, String kornSort, String beskrivelse, boolean isDone) {
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
    public Destillat(String medarbejder, int liter, double alkoholProcent, LocalDate startDato, LocalDate slutDato, String kornSort, String rygeMateriale, String beskrivelse, boolean isDone) {
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

    public void setDestillatNr(int destillatNr) {this.destillatNr = destillatNr;}

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

    //Getters_____________________________________________________________________________________
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

    //Setters_____________________________________________________________________________________
    public void setMedarbejder(String medarbejder) {
        this.medarbejder = medarbejder;
    }

    public void setLiter(int liter) {
        this.liter = liter;
    }

    public void setAlkoholProcent(double alkoholProcent) {
        this.alkoholProcent = alkoholProcent;
    }

    public void setStartDato(LocalDate startDato) {
        this.startDato = startDato;
    }

    public void setSlutDato(LocalDate slutDato) {
        this.slutDato = slutDato;
    }

    public void setKornSort(String kornSort) {
        this.kornSort = kornSort;
    }

    public void setRygeMateriale(String rygeMateriale) {
        this.rygeMateriale = rygeMateriale;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setFade(ArrayList<Fad> fade) {
        this.fade = fade;
    }

    public String toString(){
        return beskrivelse;
    }

}
