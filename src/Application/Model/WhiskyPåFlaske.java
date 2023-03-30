package Application.Model;

import java.util.ArrayList;

public class WhiskyPåFlaske {
    private int nummer;

    private int totalNummer;
    private double fortyndelseIML;

    private double liter;

    private Whisky whisky;

    private Lager lager;

    public WhiskyPåFlaske(int nummer, int totalNummer, double fortyndelseIML, Whisky whisky) {
        this.nummer = nummer;
        this.totalNummer = totalNummer;
        this.fortyndelseIML = fortyndelseIML;
        this.liter = 0.7;
        this.whisky = whisky;
        this.lager = null;
    }

    public void setWhisky(Whisky whisky) {
        this.whisky = whisky;
    }

    public void setLager(Lager lager){
        this.lager = lager;
        lager.addFlaske(this);
    }

    public int getNummer() {
        return nummer;
    }

    public int getTotalNummer() {
        return totalNummer;
    }

    public double getFortyndelseIML() {
        return fortyndelseIML;
    }

    public double getLiter() {
        return liter;
    }

    public Whisky getWhisky() {
        return whisky;
    }

    public Lager getLager() {
        return lager;
    }

    @Override
    public String toString() {
        return "Flaske nummer " + nummer + "/" + totalNummer;
    }
}

