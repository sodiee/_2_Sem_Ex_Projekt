package Application.Model;

import java.util.ArrayList;

public class WhiskyPåFlaske {
    private int nummer;

    private int totalNummer;
    private double fortyndelseIML;

    private double liter;

    private Whisky whisky;

    private Lager lager;

    public WhiskyPåFlaske(int nummer, int totalNummer, double fortyndelseIML, Whisky whisky, Lager lager) {
        this.nummer = nummer;
        this.totalNummer = totalNummer;
        this.fortyndelseIML = fortyndelseIML;
        this.liter = 0.7;
        this.whisky = whisky;
        this.lager = lager;
    }

    @Override
    public String toString() {
        return "Flaske nummer " + nummer + "/" + totalNummer;
    }
}
