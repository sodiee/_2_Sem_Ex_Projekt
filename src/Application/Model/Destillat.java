package Application.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Destillat {

    private int objectNummer;
    private static int destillatNr = 0;
    private String medarbejder;
    private double liter;
    private double literFraStart;
    private double alkoholProcent;
    private LocalDate datoForPåhldningPåFad;
    private String kornSort;
    private String rygeMateriale;
    private String beskrivelse;
    private boolean isDone;
    private ArrayList<Fad> fade = new ArrayList<>();

    public Destillat(String medarbejder, double liter, double alkoholProcent, LocalDate datoForPåhldningPåFad, String kornSort, String beskrivelse, String rygeMateriale) {
        this.medarbejder = medarbejder;
        this.liter = liter;
        this.alkoholProcent = alkoholProcent;
        this.datoForPåhldningPåFad = datoForPåhldningPåFad;
        this.kornSort = kornSort;
        this.beskrivelse = beskrivelse;
        this.isDone = false;
        this.literFraStart = liter;
        destillatNr++;
        objectNummer += destillatNr;
        this.rygeMateriale = rygeMateriale;
    }

    public void addFad(Fad fad){
        if(!fade.contains(fad)){
        fade.add(fad);
        fad.setDestillat(this);
    }}

    public void hældDestillatPåfad(Fad fad){
        while(liter > 0) {
            addFad(fad);
            fad.addDestilatTofad(this);
            liter -= fad.getStørrelseLiter();
            fad.setStatus(Status.DESTILLAT);
            fad.setOpfyldtLiter(fad.getStørrelseLiter());
        }
    }

    //region Getters
    public ArrayList<Fad> getFade() {
        return fade;
    }
    public Fad getAktuelFad() {
        Fad fad = null;
        if (!fade.isEmpty()) {
            return fad = fade.get(fade.size() - 1);
        }
        return fad;
    }
    public double getLiterFraStart() {
        return literFraStart;
    }
    public int getDestillatAge() {
        int now = LocalDate.now().getYear();
        return  now - this.datoForPåhldningPåFad.getYear();
    }
    public int getDestillatNr(){
        return destillatNr;
    }
    public String getMedarbejder() {
        return medarbejder;
    }
    public double getLiter() {
        return liter;
    }
    public double getAlkoholProcent() {
        return alkoholProcent;
    }
    public LocalDate getDatoForPåhldningPåFad() {
        return datoForPåhldningPåFad;
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
    public int getObjectNummer() {
        return objectNummer;
    }
    //endregion

    //region setters
    public void setMedarbejder(String medarbejder) {
        this.medarbejder = medarbejder;
    }
    public void setLiter(double liter) {
        this.liter = liter;
    }
    public void setAlkoholProcent(double alkoholProcent) {
        this.alkoholProcent = alkoholProcent;
    }
    public void setDatoForPåhldningPåFad(LocalDate datoForPåhldningPåFad) { this.datoForPåhldningPåFad = datoForPåhldningPåFad;}
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
    //endregion
}
