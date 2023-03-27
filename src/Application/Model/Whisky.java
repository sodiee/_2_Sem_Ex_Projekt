package Application.Model;

import java.util.ArrayList;

public class Whisky {
    private static int nummer;
    private double alkoholProcent;
    private String beskrivelse;
    private double liter;
    private Fad fad;
    private ArrayList<WhiskyPåFlaske> flasker;

    public Whisky(int liter, String beskrivelse, Fad fad) {
        this.liter = liter;
        this.nummer = nummer + 1;
        this.alkoholProcent = fad.getDestillat().getAlkoholProcent();
        this.beskrivelse = beskrivelse;
        this.fad = fad;
        this.flasker = new ArrayList<>();
    }

    public ArrayList<WhiskyPåFlaske> hældWhiskyPåFlaske(int antal, double fortyndelseIMl) {
        double flaskeStørrelseLiter = 0.7;
        int muligtAntal = (int) (this.getLiter() / flaskeStørrelseLiter);

        if (antal > muligtAntal) {
            throw new IllegalArgumentException("Du har angivet for mange flasker i antal");
        } else {
            for (int i = 1; i < antal; i++) {
                WhiskyPåFlaske whiskyPåFlaske = new WhiskyPåFlaske(i, muligtAntal, fortyndelseIMl,this, fad.getLager());
                flasker.add(whiskyPåFlaske);
            }
            return flasker;
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

}
