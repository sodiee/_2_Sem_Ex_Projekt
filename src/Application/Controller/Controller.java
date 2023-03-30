package Application.Controller;

import Application.Model.*;
import Storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    //region Fad
    public static Fad createFad(String leverandør, String tidligereIndhold, int antalGangeBrugt, int størrelseLiter, Hyldeplads hyldeplads) {
        if (hyldeplads == null){
            throw new IllegalArgumentException("Hyldeplads må ikke være null");
        }
        else{
            Fad fad = new Fad(leverandør, tidligereIndhold, antalGangeBrugt, størrelseLiter, hyldeplads);
            Storage.addFad(fad);
            return fad;
        }
    }
    public static ArrayList<Fad> getFad(){
        return Storage.getFadArrayList();
    }
    public static void deleteFad(Fad fad){Storage.deleteFad(fad);}

    //TODO: virker ikke, returns null
    public static Lager findLagerAfFad(Fad fad){
        for(int i = 0; i < Storage.getLagerArrayList().size(); i++){
            if(Storage.getLagerArrayList().get(i).getReoler().contains(fad.getReol())){
                return Storage.getLagerArrayList().get(i);
            }
        }
        return null;
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

    public static void deleteLager(Lager lager){
        //TODO: få den til at gøre noget ved alle de ting som har lageret associeret?
        Storage.getLagerArrayList().remove(lager);
    }
    public static Reol createReol(Lager lager, int antalHylder, int antalHyldepladser){
        Reol reol = new Reol(lager.getReoler().size()+1, lager);

        //create hylder med pladser
        for(int i = 0; i < antalHylder; i++){
            Hylde hylde = new Hylde(i+1, reol);
            for(int j = 0; j < antalHyldepladser; j++){
                Hyldeplads hyldeplads = new Hyldeplads(j+1, hylde);
                hyldeplads.setOptaget(false);
                hylde.addHyldePlads(hyldeplads);
            }
            reol.addHylde(hylde);
        }
        lager.addReol(reol);
        return reol;
    }

    /**
     * Finder en tom hyldeplads på en reol, hvis det kan lade sig gøre
     * @param reol
     * @return hyldeplads, hvis en er ledig
     */
    public static Hyldeplads findTomHyldeplads(Reol reol){

        for(int i = 0; i < reol.getHylder().size(); i++){
            Hylde hylde = reol.getHylder().get(i);
            for(int j = 0; j < hylde.getHyldepladser().size(); j++){
                if(!hylde.getHyldepladser().get(j).optaget){
                    return hylde.getHyldepladser().get(j);
                }
            }
        }
        return null;
    }
    //endregion

    //region Destillat
    //TODO: i stedet for at have 2 forskellige constructere, kunne man vel bare have én, hvor den uden indeholder en tom streng?
    public static Destillat createDestillat(String medarbejder, int liter, double alkoholProcent, LocalDate startDato, String kornSort, String beskrivelse){
        if (liter <= 0 || alkoholProcent < 0 ){
            throw new IllegalArgumentException("Ugyldig data");
        } else {
            Destillat destillat = new Destillat(medarbejder, liter, alkoholProcent, startDato, kornSort, beskrivelse);
            destillat.setDestillatNr(Storage.getDestillatTæller() + 1);
            Storage.addDestillat(destillat);
            return destillat;
        }
    }
    public static Destillat createDestillatRøg(String medarbejder, int liter, double alkoholProcent, LocalDate startDato, String kornSort, String rygeMateriale, String beskrivelse){
        if (liter <= 0 || alkoholProcent < 0) {
            throw new IllegalArgumentException("Ugyldig data");
        } else {
            Destillat destillat = new Destillat(medarbejder, liter, alkoholProcent, startDato, kornSort, rygeMateriale, beskrivelse);
            destillat.setDestillatNr(Storage.getDestillatTæller() + 1);
            Storage.addDestillat(destillat);
            return destillat;
        }
    }
    public static ArrayList<Destillat> getDestillat(){return Storage.getDestillatArrayList();}
    public static ArrayList<Fad> getDestillatFade(Destillat destillat){return destillat.getFade();}
    public static void redigerDestillat(Destillat destillat, String medarbejder, int liter, double alkoholProcent, LocalDate startDato, String kornSort, String beskrivelse){
        destillat.setMedarbejder(medarbejder);
        destillat.setLiter(liter);
        destillat.setAlkoholProcent(alkoholProcent);
        destillat.setDatoForPåhldningPåFad(startDato);
        destillat.setKornSort(kornSort);
        destillat.setBeskrivelse(beskrivelse);
    }
    public static void redigerDestillatRøg(Destillat destillat, String medarbejder, int liter, double alkoholProcent, LocalDate startDato, String kornSort, String rygeMateriale,  String beskrivelse){
        destillat.setMedarbejder(medarbejder);
        destillat.setLiter(liter);
        destillat.setAlkoholProcent(alkoholProcent);
        destillat.setDatoForPåhldningPåFad(startDato);
        destillat.setKornSort(kornSort);
        destillat.setRygeMateriale(rygeMateriale);
        destillat.setBeskrivelse(beskrivelse);
    }
    public static void deleteDestillat(Destillat destillat){
        Storage.deleteDestillat(destillat);
    }

    public static void convertToWhisky(Fad fad, String navn) {
        fad.convertToWhisky(navn);
        Whisky whisky = Controller.createWhisky(navn, fad);
        fad.removeDestillat();
    }
    //endregion

    //region Whisky
    public static Whisky createWhisky(String navn, Fad fad) {
        if (fad.getDestillat() != null) {
            Whisky whisky = fad.createWhisky(navn,fad);
            whisky.setFad(fad);
            Storage.addWhisky(whisky);
            return whisky;
        } else {
            throw new NullPointerException("Der er ikke knyttet et destillat til dette fad, så konvertering til whisky kan ikke lade sig gøre.");
        }
    }
    public static ArrayList<WhiskyPåFlaske> createWhiskyPåFlaske(Whisky whisky, int antal, double fortyndelseIML, Lager lager) {
        ArrayList<WhiskyPåFlaske> whiskyPåFlasker = whisky.hældWhiskyPåFlaskeRekursivHjælpemetode(antal, fortyndelseIML);
        for(WhiskyPåFlaske wpf : whiskyPåFlasker) {
            Storage.addWhiskyPåFlaske(wpf);
        }
        setWhiskyPåFlaskePåLager(whiskyPåFlasker, lager);
        System.out.println(whiskyPåFlasker);
        return whiskyPåFlasker;
    }

    public static void setWhiskyPåFlaskePåLager(ArrayList<WhiskyPåFlaske> flasker, Lager lager) {
        for (WhiskyPåFlaske wpf : flasker) {
            wpf.setLager(lager);
        }
    }
    //endregion

    //region Historik

    public static String getHistorik(WhiskyPåFlaske whiskyPåFlaske) {
        return whiskyPåFlaske.getHistorik();
    }

    //endregion


    public static void initStorage() {
        Lager sønderhøj = createLager(2, 3, 3, "Sønderhøj 30");
        Lager sørenFrichsVej = createLager(3, 2, 2, "Søren Frichs Vej");

        Reol reol = createReol(sønderhøj, 5, 8);
        Reol reol2 = createReol(sønderhøj, 4, 8);
        Reol reol3 = createReol(sørenFrichsVej, 6, 8);
        Reol reol4 = createReol(sørenFrichsVej, 3, 8);
        Reol reol5 = createReol(sørenFrichsVej, 7, 8);

        Hylde hylde = reol.getHylder().get(0);
        Hyldeplads hyldeplads1 = reol.getHylder().get(1).getHyldepladser().get(1); //new Hyldeplads(1,hylde);
        Hyldeplads hyldeplads2 = reol.getHylder().get(1).getHyldepladser().get(1); //new Hyldeplads(2,hylde);
        Hyldeplads hyldeplads3 = reol.getHylder().get(1).getHyldepladser().get(1); //new Hyldeplads(3,hylde);
        Hyldeplads hyldeplads4 = reol.getHylder().get(1).getHyldepladser().get(1); //new Hyldeplads(4,hylde);

        Fad fad = createFad("Sherry distilleri, Lissabon", "Sherry", 1, 64, hyldeplads1);
        Fad fad1 = createFad("Bourbon distilleri, Texas", "Bourbon", 1, 500,  hyldeplads2);
        Fad fad2 = createFad("Sherry distilleri, Madrid", "Sherry", 2, 2,  hyldeplads3);
        Fad fad3 = createFad("Rødvin farm, Paris", "Rødvin", 1, 54,  hyldeplads4);

        Destillat destillat = createDestillat("John Dillermand", 500, 80.0, LocalDate.of(2022, 5, 20), "Rug", "Bedste Whiskey ever");
        Destillat destillat2 = createDestillatRøg("Bingo Dorte", 600, 60, LocalDate.of(2017, 2, 14), "Byg", "Strå", "I can't believe its not whiskey");

        destillat.hældDestillatPåfad(fad1);

        Whisky whisky = Controller.createWhisky("Inaugural Release", fad1);
        ArrayList<WhiskyPåFlaske> whiskyPåFlasker = Controller.createWhiskyPåFlaske(whisky, 20, 0, sønderhøj);
    }
}
