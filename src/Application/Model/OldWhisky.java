package Application.Model;

import java.util.ArrayList;

public class OldWhisky {
    /** Private String navn;

    private static int nummer;
    private double alkoholProcent;
    private String beskrivelse;
    private double liter;
    private Fad fad;
    private ArrayList<WhiskyPåFlaske> flasker;

    public Whisky(String navn,double liter, String beskrivelse, Fad fad) {
        this.navn = navn;
        this.liter = liter;
        this.nummer = nummer + 1;
        this.alkoholProcent = fad.getDestillat().getAlkoholProcent();
        this.beskrivelse = beskrivelse;
        this.fad = fad;
        this.flasker = new ArrayList<>();
    }

    //region Loop-Metode
    public ArrayList<WhiskyPåFlaske> hældWhiskyPåFlaske(int antal, double fortyndelseIMl) {
        double flaskeStørrelseML = 700;
        double muligtAntal = (((this.getLiter() * 1000) + (antal * fortyndelseIMl)) / flaskeStørrelseML);

        if (antal > muligtAntal) {
            throw new IllegalArgumentException("Du har angivet for mange flasker i antal, i forhold til hvor meget der kan produceres");
        } else {
            double påfyldningPerFlaske = flaskeStørrelseML - fortyndelseIMl;
            for (int i = 1; i < antal + 1; i++) {
                WhiskyPåFlaske whiskyPåFlaske = new WhiskyPåFlaske(i, (int)muligtAntal, fortyndelseIMl, this);
                flasker.add(whiskyPåFlaske);
                this.setLiter(this.getLiter() - (påfyldningPerFlaske / 1000));
            }
        }
        return flasker;
    }
    //endregion

    //region Rekursiv-metode
    public ArrayList<WhiskyPåFlaske> hældWhiskyPåFlaskeRekursivHjælpemetode(int antal, double fortyndelseIMl) {
        double flaskeStørrelseML = 700;
        double muligtAntal = (((this.getLiter() * 1000) + (antal * fortyndelseIMl)) / flaskeStørrelseML);

        if (antal > muligtAntal) {
            throw new IllegalArgumentException("Du har angivet for mange flasker i antal, i forhold til hvor meget der kan produceres");
        }

        return hældWhiskyPåFlaskeRekursivHjælpemetode(antal, fortyndelseIMl, flaskeStørrelseML, flasker, 1);
    }

    private ArrayList<WhiskyPåFlaske> hældWhiskyPåFlaskeRekursivHjælpemetode(int antal, double fortyndelseIMl, double flaskeStørrelseML, ArrayList<WhiskyPåFlaske> flasker, int i) {
        if (i > antal) {
            return flasker;
        }

        WhiskyPåFlaske whiskyPåFlaske = new WhiskyPåFlaske(i, antal, fortyndelseIMl, this);
        flasker.add(whiskyPåFlaske);
        this.setLiter(this.getLiter() - ((flaskeStørrelseML - fortyndelseIMl) / 1000));

        return hældWhiskyPåFlaskeRekursivHjælpemetode(antal, fortyndelseIMl, flaskeStørrelseML, flasker, i + 1);
    }
    //endregion

    public double getMuligeAntalFlasker(Double flaskestørrelse){return liter/flaskestørrelse;}

    public String getNavn(){
        return navn;
    }

    public void setNavn(String navn){
        this.navn = navn;
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

    public void setLiter(double liter) {
        this.liter = liter;
    }

    public ArrayList<WhiskyPåFlaske> getFlasker() {
        return flasker;
    }

    @Override
    public String toString(){return "#" + nummer + " " + beskrivelse + " " + alkoholProcent + "%";}
    **/

}