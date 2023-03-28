package Application.Controller;

import Application.Model.*;
import Storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Controller {

    //region Fad
    public static Fad createFad(String leverandør, String tidligereIndhold, int antalGangeBrugt, int nummer, int størrelseLiter, Lager lager) {
        //TODO: tjek om det er sådan her man sørger for at lageret ikke er null
        if (lager == null){
            throw new IllegalArgumentException("Lager må ikke være null");
        }
        else{
            Fad fad = new Fad(leverandør, tidligereIndhold, antalGangeBrugt, nummer, størrelseLiter, lager);
            Storage.addFad(fad);
            return fad;
        }
    }
    public static ArrayList<Fad> getFad(){
        return Storage.getFadArrayList();
    }
    //endregion

    //region Lager
    public static Lager createLager(int reoler, int hylder, int pladsPåHylde, String adresse) {
        Lager lager = new Lager(reoler, hylder, pladsPåHylde, adresse);
        Storage.addLager(lager);
        return lager;
    }
    public static ArrayList<Lager> getLager(){
        return Storage.getLagerArrayList();
    }
    //TODO: dobbelcheck lige at den her (createReol) er som den skal være
    public static Reol createReol(Lager lager, int antalHylder, int antalHyldepladser){
        Reol reol = new Reol(lager.getReoler().size()+1, lager);

        //create hylder med pladser
        for(int i = 0; i < antalHylder; i++){
            Hylde hylde = new Hylde(i+1, reol);
            for(int j = 0; j < antalHyldepladser; j++){
                Hyldeplads hyldeplads = new Hyldeplads(j+1, hylde);
                hylde.addHyldePlads(hyldeplads);
            }
            reol.addHylde(hylde);
        }
        lager.addReol(reol);
        return reol;
    }
    //endregion

    //region Destillat
    //TODO: i stedet for at have 2 forskellige constructere, kunne man vel bare have én, hvor den uden indeholder en tom streng?
    public static Destillat createDestillat(String medarbejder, int liter, double alkoholProcent, LocalDate startDato, LocalDate slutDato, String kornSort, String beskrivelse){
        if (liter <= 0 || alkoholProcent < 0 || startDato.isAfter(slutDato)) {
            throw new IllegalArgumentException("Ugyldig data");
        } else {
            Destillat destillat = new Destillat(medarbejder, liter, alkoholProcent, startDato, slutDato, kornSort, beskrivelse);
            destillat.setDestillatNr(Storage.getDestillatTæller() + 1);
            Storage.addDestillat(destillat);
            return destillat;
        }
    }
    public static Destillat createDestillatRøg(String medarbejder, int liter, double alkoholProcent, LocalDate startDato, LocalDate slutDato, String kornSort, String rygeMateriale, String beskrivelse){
        if (liter <= 0 || alkoholProcent < 0 || startDato.isAfter(slutDato)) {
            throw new IllegalArgumentException("Ugyldig data");
        } else {
            Destillat destillat = new Destillat(medarbejder, liter, alkoholProcent, startDato, slutDato, kornSort, rygeMateriale, beskrivelse);
            destillat.setDestillatNr(Storage.getDestillatTæller() + 1);
            Storage.addDestillat(destillat);
            return destillat;
        }
    }
    public static ArrayList<Destillat> getDestillat(){return Storage.getDestillatArrayList();}
    public static ArrayList<Fad> getDestillatFade(Destillat destillat){return destillat.getFade();}
    public static void redigerDestillat(Destillat destillat, String medarbejder, int liter, double alkoholProcent, LocalDate startDato, LocalDate slutDato, String kornSort, String beskrivelse){
        destillat.setMedarbejder(medarbejder);
        destillat.setLiter(liter);
        destillat.setAlkoholProcent(alkoholProcent);
        destillat.setStartDato(startDato);
        destillat.setSlutDato(slutDato);
        destillat.setKornSort(kornSort);
        destillat.setBeskrivelse(beskrivelse);
    }
    public static void redigerDestillatRøg(Destillat destillat, String medarbejder, int liter, double alkoholProcent, LocalDate startDato, LocalDate slutDato, String kornSort, String rygeMateriale,  String beskrivelse){
        destillat.setMedarbejder(medarbejder);
        destillat.setLiter(liter);
        destillat.setAlkoholProcent(alkoholProcent);
        destillat.setStartDato(startDato);
        destillat.setSlutDato(slutDato);
        destillat.setKornSort(kornSort);
        destillat.setRygeMateriale(rygeMateriale);
        destillat.setBeskrivelse(beskrivelse);
    }
    public static void deleteDestillat(Destillat destillat){
        Storage.deleteDestillat(destillat);
    }
    //endregion

    //region Whisky
    public static Whisky createWhisky(Fad fad) {
        Whisky whisky = fad.createWhisky(fad);
        Storage.addWhisky(whisky);
        return whisky;
    }
    public static ArrayList<WhiskyPåFlaske> createWhiskyPåFlaske(Whisky whisky, int antal, double fortyndelseIML) {
        ArrayList<WhiskyPåFlaske> whiskyPåFlasker = whisky.hældWhiskyPåFlaske(antal, fortyndelseIML);
        for(WhiskyPåFlaske wpf : whiskyPåFlasker) {
            Storage.addWhiskyPåFlaske(wpf);
        }
        return whiskyPåFlasker;
    }
    //endregion

    public static void initStorage() {
        Lager sønderhøj = createLager(2, 3, 3, "Sønderhøj 30");
        Lager sørenFrichsVej = createLager(3, 2, 2, "Søren Frichs Vej");

        Fad fad = createFad("Sherry distilleri, Lissabon", "Sherry", 1, 64, 130, sønderhøj);
        Fad fad1 = createFad("Bourbon distilleri, Texas", "Bourbon", 1, 326, 90, sønderhøj);
        Fad fad2 = createFad("Sherry distilleri, Madrid", "Sherry", 2, 2, 95, sønderhøj);
        Fad fad3 = createFad("Rødvin farm, Paris", "Rødvin", 1, 54, 50, sørenFrichsVej);

        Destillat destillat = createDestillat("John Dillermand", 500, 80.0, LocalDate.of(2022, 5, 20), LocalDate.of(2023, 4, 14), "Rug", "Bedste Whiskey ever");
        Destillat destillat2 = createDestillatRøg("Bingo Dorte", 600, 60, LocalDate.of(2021, 2, 14), LocalDate.of(2022, 1, 19), "Byg", "Strå", "I can't believe its not whiskey");

        Reol reol = createReol(sønderhøj, 5, 8);
        Reol reol2 = createReol(sønderhøj, 4, 8);
        Reol reol3 = createReol(sørenFrichsVej, 6, 8);
        Reol reol4 = createReol(sørenFrichsVej, 3, 8);
    }
}
