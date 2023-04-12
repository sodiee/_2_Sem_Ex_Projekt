package Application.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Whisky extends Destillat {
    private String navn;
    private int nummer;
    private static int whiskyNummer = 0;
    private double alkoholProcent;
    private String beskrivelse;
    private double liter;
    private Fad fad;
    private ArrayList<WhiskyPåFlaske> flasker;

    public Whisky(String medarbejder, double liter, double alkoholProcent, LocalDate startDato, String kornSort, String beskrivelse, String rygeMateriale, String navn) {
        super(medarbejder, liter, alkoholProcent, startDato, kornSort, beskrivelse, rygeMateriale);
        this.fad = super.getAktuelFad();
        this.navn = navn;
        flasker = new ArrayList<>();
        nummer++;
        whiskyNummer = nummer;
        this.alkoholProcent = getAlkoholprocentDestillat();
        this.beskrivelse = getBeskrivelse();
        this.fad = getAktuelFad();
    }

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
                this.setLiter((int) (this.getLiter() - (påfyldningPerFlaske / 1000)));
            }
        }
        return flasker;
    }

    //region Rekursiv-metode
    public ArrayList<WhiskyPåFlaske> hældWhiskyPåFlaskeRekursivMetode(int antal, double fortyndelseIMl) {
        double flaskeStørrelseML = 700;
        double muligtAntal = (((this.getLiter() * 1000) + (antal * fortyndelseIMl)) / flaskeStørrelseML);

        if (antal > muligtAntal) {
            throw new IllegalArgumentException("Du har angivet for mange flasker i antal, i forhold til hvor meget der kan produceres");
        }

        return hældWhiskyPåFlaskeRekursivHjælpemetode(antal, fortyndelseIMl, flaskeStørrelseML, flasker, 1);
    }

    private ArrayList<WhiskyPåFlaske> hældWhiskyPåFlaskeRekursivHjælpemetode(int antal, double fortyndelseIMl, double flaskeStørrelseML,
                                                                             ArrayList<WhiskyPåFlaske> flasker, int i) {
        if (i > antal) {
            return flasker;
        }

        WhiskyPåFlaske whiskyPåFlaske = new WhiskyPåFlaske(i, antal, fortyndelseIMl, this);
        flasker.add(whiskyPåFlaske);
        this.setLiter(this.getLiter() - ((flaskeStørrelseML - fortyndelseIMl) / 1000));

        return hældWhiskyPåFlaskeRekursivHjælpemetode(antal, fortyndelseIMl, flaskeStørrelseML, flasker, i + 1);
    }
    //endregion

    public double getMuligeAntalFlasker(Double flaskestørrelse, double fortyndelse){
        return getLiter() / (flaskestørrelse - fortyndelse);}

    public String getMedarbejder() {
        return super.getMedarbejder();
    }

    public double getLiter() {
        return super.getLiter();
    }

    public double getAlkoholprocentDestillat() {
        return super.getAlkoholProcent();
    }

    public LocalDate getStartDato() {
        return super.getDatoForPåhldningPåFad();
    }

    public LocalDate getSlutDato() {
        return super.getDatoForPåhldningPåFad();
    }

    public String getKornsort() {
        return super.getKornSort();
    }

    public String getRygeMateriale() {
        return super.getRygeMateriale();
    }

    public String getNavn() {
        return navn;
    }

    public ArrayList<Fad> getFade(int index) {
        return super.getFade();
    }

    public Fad getAktuelFad() {
        return fad;
    }

    public int getObjectNummer() {
        return super.getObjectNummer();
    }

    public double getAlkoholProcentWhisky() {
        return this.alkoholProcent;
    }

    public ArrayList<WhiskyPåFlaske> getFlasker() {
        return flasker;
    }

    public double getSuperLiter() {
        return super.getLiter();
    }

    public void setFad(Fad fad) {
        this.fad = fad;
    }

    public Fad getFad() {
        return this.fad;
    }

    public double getWhiskyLiter() {
        return this.liter;
    }

    public String getBeskrivelse() {
        return super.getBeskrivelse();
    }

   /* public Destillat getDestillat() {
        return fad.getTidligereDestillater().get(fad.getTidligereDestillater().size() - 1);
    }*/

    @Override
    public String toString(){return "#" + nummer + " " + navn + " " + alkoholProcent + "%";}

}
