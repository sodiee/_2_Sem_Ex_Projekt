package Application.Controller;

import Application.Model.*;
import Storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    //region Fad
    /**
     * Opretter et fad Objekt, som whisky lagres på
     * @param leverandør fadets oprindelse
     * @param tidligereIndhold det som før har været i fadet
     * @param antalGangeBrugt hvor mange gange der har været alkohol i fadet
     * @param størrelseLiter fadets kapacitet
     * @param hyldeplads fadets lokation
     * @return Fad objekt
     */
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

    /**
     * Søger efter hvilket lagerhus fadet ligger på
     * @param fad fadet der ønskes oprindelse af
     * @return Lager objekt if not null
     */
    public static Lager findLagerAfFad(Fad fad){
        //TODO: virker ikke, returns null
        for(int i = 0; i < Storage.getLagerArrayList().size(); i++){
            if(Storage.getLagerArrayList().get(i).getReoler().contains(fad.getReol())){
                return Storage.getLagerArrayList().get(i);
            }
        }
        return null;
    }
    //endregion

    //region Lager
    /**
     * Opretter et lager objekt, som Reoler > Hylder > Hyldepladser > Fade, kan ligge på
     * @param reoler Antal reoler lagerhuset skal have
     * @param hylder Antal hylder på hver reol
     * @param pladsPåHylde Antal pladser på en hylde
     * @param adresse Adressen på lagerhuset
     * @return Lager objektet
     */
    public static Lager createLager(int reoler, int hylder, int pladsPåHylde, String adresse) {
        Lager lager = new Lager(reoler, hylder, pladsPåHylde, adresse);
        Storage.addLager(lager);
        for (int i = 0; i < reoler; i++) {
            createReol(lager, hylder, pladsPåHylde);
        }
        return lager;
    }
    public static ArrayList<Lager> getLager(){
        return Storage.getLagerArrayList();
    }

    public static void deleteLager(Lager lager){
        //TODO: få den til at gøre noget ved alle de ting som har lageret associeret?
        Storage.getLagerArrayList().remove(lager);
    }

    /**
     * Opretter et Reol objekt, som ligger på lager-objekter, og indeholder hylder
     * @param lager lageret som hylderne oprettes på
     * @param antalHylder Antal af hylder til oprettelse for denne reol
     * @param antalHyldepladser Antal hyldepladser for hver hylde
     * @return Reol Objektet
     */
    public static Reol createReol(Lager lager, int antalHylder, int antalHyldepladser){
        Reol reol = new Reol(lager.getReoler().size()+1, lager);

        //create hylder med pladser
        for(int i = 0; i < antalHylder; i++){
            Hylde hylde = new Hylde(i+1, reol);
            for(int j = 0; j < antalHyldepladser; j++){
                Hyldeplads hyldeplads = new Hyldeplads(j + 1, hylde);
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
     * @param reol Reol objektet som der ønskes undersøgt
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

    /**
     * Opretter et destillat objekt, der kan lagres på fad indtil det kan konverteres til whisky
     * @param medarbejder Navnet på medarbjederen som producerer destillatet
     * @param liter Antal Liter der er produceret
     * @param alkoholProcent Alkohol koncentrationen
     * @param startDato Dagen destillatet blev oprettet på
     * @param kornSort Typen af korn alkohollen er oprettet af
     * @param beskrivelse Diverse beskrivelse af destilleringen
     * @param rygeMateriale Materialet brugt til at ryge destilleringen
     * @return Destillat objektet
     */
    public static Destillat createDestillat(String medarbejder, int liter, double alkoholProcent, LocalDate startDato, String kornSort, String beskrivelse, String rygeMateriale){
        if (liter <= 0 || alkoholProcent < 0 || startDato.isAfter(LocalDate.now().plusDays(1))) {
            throw new IllegalArgumentException("Ugyldig data");
        } else {
            Destillat destillat = new Destillat(medarbejder, liter, alkoholProcent, startDato, kornSort, beskrivelse, rygeMateriale);
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

    /**
     * Omdanner indholdet af et fad til at blive whisky, frem for destillat
     * @param fad fadet hvis indhold skal ændres
     * @param navn navnet på den nyoprettede whisky
     */
    public static void convertToWhisky(Fad fad, String navn) {
        fad.convertToWhisky(navn);
        Whisky whisky = Controller.createWhisky(navn, fad);
        fad.removeDestillat();
        fad.addDestilatTofad(whisky);
    }
    //endregion

    //region Whisky
    private static Whisky createWhisky(String navn, Fad fad) {
        if (fad.getDestillat() != null) {
            Whisky whisky = fad.createWhisky(navn,fad);
            whisky.setFad(fad);
            Storage.addWhisky(whisky);
            return whisky;
        } else {
            throw new NullPointerException("Der er ikke knyttet et destillat til dette fad, så konvertering til whisky kan ikke lade sig gøre.");
        }
    }
    public static void deleteWhisky(Whisky whisky){Storage.removeWhisky(whisky);}
    public static ArrayList<WhiskyPåFlaske> createWhiskyPåFlaske(Whisky whisky, int antal, double fortyndelseIML, Lager lager) {
        ArrayList<WhiskyPåFlaske> whiskyPåFlasker = whisky.hældWhiskyPåFlaskeRekursivMetode(antal, fortyndelseIML);
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

    /**
     * Opretter testdata til systemet
     */
    public static void initStorage() {
        Lager sønderhøj = createLager(0, 0, 0, "Sønderhøj 30");
        Lager sørenFrichsVej = createLager(3, 2, 2, "Søren Frichs Vej");

        Reol reol = createReol(sønderhøj, 5, 8);
        Reol reol2 = createReol(sønderhøj, 4, 8);
        Reol reol3 = createReol(sørenFrichsVej, 6, 8);
        Reol reol4 = createReol(sørenFrichsVej, 3, 8);
        Reol reol5 = createReol(sørenFrichsVej, 7, 8);

        Hylde hylde = reol.getHylder().get(0);
        Hyldeplads hyldeplads1 = reol.getHylder().get(0).getHyldepladser().get(1); //new Hyldeplads(1,hylde);
        Hyldeplads hyldeplads2 = reol.getHylder().get(1).getHyldepladser().get(2); //new Hyldeplads(2,hylde);
        Hyldeplads hyldeplads3 = reol.getHylder().get(2).getHyldepladser().get(3); //new Hyldeplads(3,hylde);
        Hyldeplads hyldeplads4 = reol.getHylder().get(3).getHyldepladser().get(4); //new Hyldeplads(4,hylde);

        Fad fad = createFad("Sherry distilleri, Lissabon", "Sherry", 1, 64, hyldeplads1);
        Fad fad1 = createFad("Bourbon distilleri, Texas", "Bourbon", 1, 500,  hyldeplads2);
        Fad fad2 = createFad("Sherry distilleri, Madrid", "Sherry", 2, 2,  hyldeplads3);
        Fad fad3 = createFad("Rødvin farm, Paris", "Rødvin", 1, 54,  hyldeplads4);

        Destillat destillat = createDestillat("John Dillermand", 500, 80.0, LocalDate.of(2022, 5, 20), "Rug", "Bedste Whiskey ever", null);
        Destillat destillat2 = createDestillat("Bingo Dorte", 600, 60, LocalDate.of(2017, 2, 14), "Byg",  "Benzin", "Strå");

        destillat.hældDestillatPåfad(fad1);

        Whisky whisky = Controller.createWhisky("Inaugural Release", fad1);
        ArrayList<WhiskyPåFlaske> whiskyPåFlasker = Controller.createWhiskyPåFlaske(whisky, 20, 0, sønderhøj);
    }
}
