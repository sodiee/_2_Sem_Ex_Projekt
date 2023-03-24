package Application.Controller;

import Application.Model.Destillat;
import Application.Model.Fad;
import Application.Model.Lager;
import Storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Controller {

    //Create

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

    public static Lager createLager(int reoler, int hylder, int pladsPåHylde, String adresse) {
        Lager lager = new Lager(reoler, hylder, pladsPåHylde, adresse);
        Storage.addLager(lager);
        return lager;
    }

    //TODO: i stedet for at have 2 forskellige constructere, kunne man vel bare have én, hvor den uden indeholder en tom streng?
    public static Destillat createDestillat(String medarbejder, int liter, double alkoholProcent, LocalDate startDato, LocalDate slutDato, String kornSort, String beskrivelse){
        Destillat destillat = new Destillat(medarbejder, liter, alkoholProcent, startDato, slutDato, kornSort, beskrivelse, isDone);
        destillat.setDestillatNr(Storage.getDestillatTæller() + 1);
        Storage.addDestillat(destillat);
        return destillat;
    }
    public static Destillat createDestillatRøg(String medarbejder, int liter, double alkoholProcent, LocalDate startDato, LocalDate slutDato, String kornSort, String rygeMateriale, String beskrivelse){
        Destillat destillat = new Destillat(medarbejder, liter, alkoholProcent, startDato, slutDato, kornSort, rygeMateriale, beskrivelse, isDone);
        destillat.setDestillatNr(Storage.getDestillatTæller() + 1);
        Storage.addDestillat(destillat);
        return destillat;
    }


    //Get

    public static ArrayList<Fad> getFad(){
        return Storage.getFadArrayList();
    }

    public static ArrayList<Lager> getLager(){
        return Storage.getLagerArrayList();
    }
    public static ArrayList<Destillat> getDestillat(){return Storage.getDestillatArrayList();}

    public static ArrayList<Fad> getDestillatFade(Destillat destillat){return destillat.getFade();}

    //Edit
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


    //TODO: Delete funktioner




    public static void initStorage() {
        Lager sønderhøj = createLager(2, 3, 3, "Sønderhøj 30");
        Lager sørenFrichsVej = createLager(3, 2, 2, "Søren Frichs Vej");

        Fad fad = createFad("Sherry distilleri, Lissabon", "Sherry", 1, 64, 130, sønderhøj);
        Fad fad1 = createFad("Bourbon distilleri, Texas", "Bourbon", 1, 326, 90, sønderhøj);
        Fad fad2 = createFad("Sherry distilleri, Madrid", "Sherry", 2, 2, 95, sønderhøj);
        Fad fad3 = createFad("Rødvin farm, Paris", "Rødvin", 1, 54, 50, sørenFrichsVej);

        Destillat destillat = createDestillat("John Dillermand", 500, 80.0, LocalDate.of(2022, 5, 20), LocalDate.of(2023, 4, 14), "Rug", "Bedste Whiskey ever", true);
        Destillat destillat2 = createDestillatRøg("Bingo Dorte", 600, 60, LocalDate.of(2021, 2, 14), LocalDate.of(2022, 1, 19), "Byg", "Strå", "I can't believe its not whiskey", false);
    }
}
