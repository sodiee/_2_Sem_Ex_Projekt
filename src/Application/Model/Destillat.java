package Application.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Destillat {

    private String medarbejder;
    private int liter;
    private double alkoholProcent;
    private LocalDate startDato;
    private LocalDate slutDato;
    private String kornSort;
    private String rygeMateriale;
    private String beskrivelse;
    private boolean isDone;
    private ArrayList<Fad> fade;

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

    public void færdigørDestillat(){
        isDone = true;
    }

    public ArrayList<Fad> getFade() {
        return fade;
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
