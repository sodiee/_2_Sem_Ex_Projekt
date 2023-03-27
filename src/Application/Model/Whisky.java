package Application.Model;

import java.util.ArrayList;

public class Whisky {
    private static int nummer;
    private double alkoholProcent;
    private String beskrivelse;
    private double liter;
    private boolean fortyndet;
    private int fortyndelseIL;

    private ArrayList<WhiskyPåFlaske> flasker;

    private Fad fad;

    public Whisky(int liter, double alkoholProcent, String beskrivelse, boolean fortyndet, int fortyndelseIL, Fad fad) {
        this.nummer = nummer + 1;
        this.alkoholProcent = alkoholProcent;
        this.beskrivelse = beskrivelse;
        this.fortyndet = fortyndet;
        this.fortyndelseIL = fortyndelseIL;
        this.fad = fad;
    }

    public void hældWhiskyPåFlaske(Destillat destillat, int antal) {
        double flaskeStørrelseLiter = 0.7;
        int muligtAntal = (int) (destillat.getLiterFraStart() / flaskeStørrelseLiter);
        if (antal > muligtAntal) {

        }
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public void setAlkoholProcent(double alkoholProcent) {
        this.alkoholProcent = alkoholProcent;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public double getLiter() {
        return liter;
    }

    public void setLiter(int liter) {
        this.liter = liter;
    }

    public boolean isFortyndet() {
        return fortyndet;
    }

    public void setFortyndet(boolean fortyndet) {
        this.fortyndet = fortyndet;
    }

    public int getFortyndelseIL() {
        return fortyndelseIL;
    }

    public void setFortyndelseIL(int fortyndelseIL) {
        this.fortyndelseIL = fortyndelseIL;
    }
}
