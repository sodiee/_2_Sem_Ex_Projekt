package Application.Model;

import java.util.ArrayList;

public class Whisky {
    private static int nummer;
    private double alkoholProcent;
    private String beskrivelse;
    private double liter;
    private Fad fad;
    private ArrayList<WhiskyPåFlaske> flasker;

    public Whisky(double liter, String beskrivelse, Fad fad) {
        this.liter = liter;
        this.nummer = nummer + 1;
        this.alkoholProcent = fad.getDestillat().getAlkoholProcent();
        this.beskrivelse = beskrivelse;
        this.fad = fad;
        this.flasker = new ArrayList<>();
    }

    public ArrayList<WhiskyPåFlaske> hældWhiskyPåFlaske(int antal, double fortyndelseIMl) {
        double flaskeStørrelseML = 700;
        double muligtAntal = (((this.getLiter() * 1000) + (antal * fortyndelseIMl)) / flaskeStørrelseML);

        if (antal > muligtAntal) {
            throw new IllegalArgumentException("Du har angivet for mange flasker i antal, i forhold til hvor meget der kan produceres");
        }

        return hældWhiskyPåFlaskeRekursiv(antal, fortyndelseIMl, flaskeStørrelseML, muligtAntal, new ArrayList<WhiskyPåFlaske>(), 1);
    }

    private ArrayList<WhiskyPåFlaske> hældWhiskyPåFlaskeRekursiv(int antal, double fortyndelseIMl, double flaskeStørrelseML, double muligtAntal, ArrayList<WhiskyPåFlaske> flasker, int i) {
        if (i > antal) {
            return flasker;
        }

        WhiskyPåFlaske whiskyPåFlaske = new WhiskyPåFlaske(i, (int)muligtAntal, fortyndelseIMl, this, fad.getLager());
        flasker.add(whiskyPåFlaske);
        this.setLiter(this.getLiter() - ((flaskeStørrelseML - fortyndelseIMl) / 1000));

        return hældWhiskyPåFlaskeRekursiv(antal, fortyndelseIMl, flaskeStørrelseML, muligtAntal, flasker, i + 1);
    }
    /*public ArrayList<WhiskyPåFlaske> hældWhiskyPåFlaske(int antal, double fortyndelseIMl) {
        double flaskeStørrelseML = 700;
        double muligtAntal = (((this.getLiter() * 1000) + (antal * fortyndelseIMl)) / flaskeStørrelseML);

        if (antal > muligtAntal) {
            throw new IllegalArgumentException("Du har angivet for mange flasker i antal, i forhold til hvor meget der kan produceres");
        } else {
            double påfyldningPerFlaske = flaskeStørrelseML - fortyndelseIMl;
            for (int i = 1; i < antal + 1; i++) {
                WhiskyPåFlaske whiskyPåFlaske = new WhiskyPåFlaske(i, (int)muligtAntal, fortyndelseIMl, this, fad.getLager());
                flasker.add(whiskyPåFlaske);
                this.setLiter(this.getLiter() - (påfyldningPerFlaske / 1000));
            }
        }
        return flasker;
    }*/

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

    public void setLiter(double liter) {
        this.liter = liter;
    }

}
