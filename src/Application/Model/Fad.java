package Application.Model;

import javafx.scene.control.Alert;
import java.util.ArrayList;

public class Fad {

    //region Global Variables
    private String leverandør;
    private String tidligereIndhold;
    private int antalGangeBrugt;
    private static int lastNummer;
    private int tøndeNummer;
    private int størrelseLiter;
    private int opfyldtLiter = 0;
    private Reol reol;
    private Hylde hylde;
    private Hyldeplads hyldeplads;
    private int alder;
    private Destillat destillat;
    private ArrayList<Destillat> tidligereDestillater;
    private Status status;
    //endregion

    /**
     * Opretter et Fad Model-Objekt, ud fra de givende parametre
     * @param leverandør Hvilken udbyder fadet kommer fra
     * @param tidligereIndhold Tidligere destillater/whisky på fadet
     * @param antalGangeBrugt Tidligere antal gange brugt
     * @param størrelseLiter Fadets størrelse i Liter
     * @param hyldeplads Fadets associerede hyldeplads på et lager
     */
    public Fad(String leverandør, String tidligereIndhold, int antalGangeBrugt, int størrelseLiter, Hyldeplads hyldeplads) {
        this.leverandør = leverandør;
        this.tidligereIndhold = tidligereIndhold;
        this.antalGangeBrugt = antalGangeBrugt;
        this.størrelseLiter = størrelseLiter;
        this.tidligereDestillater = new ArrayList<>();
        setHyldeplads(hyldeplads);
        alder = 0;
        status = Status.TOM;
        lastNummer++;
        tøndeNummer = lastNummer;
    }

    /**
     * Beregner hvor meget forsvinder hvert år af væsken
     * @param destillat destillatet som ønskes beregnet på
     * @return værdien af henfaldet
     */
    public double beregnAngelShare(Destillat destillat) {
        double tempLiter = destillat.getLiterFraStart();
        double angelShareProcent;
        double angelShareDelTotal = 0;

        if (alder < 0) {
            throw new IllegalArgumentException("Ugyldig alder på fad");
        } if (alder == 0) {
            angelShareProcent = 0.02;
            return destillat.getLiterFraStart() * angelShareProcent;
        } else {
            if (alder < 5) {
                angelShareProcent = 0.02;
            } else if (alder < 8) {
                angelShareProcent = 0.03;
            } else if (alder < 12) {
                angelShareProcent = 0.04;
            } else {
                angelShareProcent = 0.05;
            }

            double angelShareDelTemp = 0;
            double tempAS = 0;

            for (int i = 0; i < 3; i++) {
                angelShareDelTemp = tempLiter * angelShareProcent;
                angelShareDelTotal += angelShareDelTemp;
                tempAS = angelShareDelTemp;
                tempLiter = tempLiter - tempAS;
            }
        }

        return angelShareDelTotal;
    }

    /**
     * Opretter et whisky objekt fra et fads indhold, med udregningen vedrørende naturligt henfald (Angel Share)
     * @param navn Whiskiens produkt-navn
     * @param fad fadet hvis indhold ønskes fuldent
     * @return whisky objektet
     */
    public Whisky createWhisky(String navn, Fad fad) {
            double antalLiter = fad.opfyldtLiter - fad.beregnAngelShare(fad.getDestillat());
            Whisky whisky = new Whisky(destillat.getMedarbejder(), antalLiter, destillat.getAlkoholProcent(), destillat.getDatoForPåhldningPåFad(), destillat.getKornSort(), destillat.getBeskrivelse(), destillat.getRygeMateriale(), navn);
            return whisky;
    }

    /**
     * Tilføjer associeringen mellem fad og destillat, og optæller antal gange brugt
     * @param destillat
     */
    public void addDestilatTofad(Destillat destillat) {
        if (destillat != null) {
            setDestillat(destillat);
            destillat.addFad(this);
            antalGangeBrugt++;
        } else {
            System.out.println("Der er ikke nok plads på fadet");
        }
    }

    public void removeDestillat() { //String navn
        if (destillat != null) {
            tidligereDestillater.add(destillat);
            alder += destillat.getDestillatAge();
            this.destillat = null;
        }
    }

    /**
     * Angiver status-enum til denne whisky
     */
    public void convertToWhisky(){this.setStatus(Status.WHISKY);}

    //region Getters
    public Destillat getDestillat() {
        return destillat;
    }
    public int getStørrelseLiter() {
        return størrelseLiter;
    }
    public int getOpfyldtLiter() {return opfyldtLiter;}
    public String getLeverandør() {
        return leverandør;
    }
    public String getTidligereIndhold() {
        return tidligereIndhold;
    }
    public int getAntalGangeBrugt() {
        return antalGangeBrugt;
    }
    public Reol getReol() {
        return reol;
    }
    public ArrayList<Destillat> getTidligereDestillater() {
        return tidligereDestillater;
    }
    public Status getStatus() {
        return status;
    }
    public int getAlder() {
        return alder;
    }
    //endregion

    //region Setters
    public void setOpfyldtLiter(int opfyldtLiter) {this.opfyldtLiter = opfyldtLiter;}
    public void setLeverandør(String leverandør) {
        this.leverandør = leverandør;
    }
    public void setTidligereIndhold(String tidligereIndhold) {
        this.tidligereIndhold = tidligereIndhold;
    }
    public void setAntalGangeBrugt(int antalGangeBrugt) {
        this.antalGangeBrugt = antalGangeBrugt;
    }
    public void setReol(Reol reol) {
        this.reol = reol;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public void setAlder(int alder) {
        this.alder = alder;
    }
    public void setHyldeplads(Hyldeplads hyldeplads){
        this.hyldeplads = hyldeplads;
        hyldeplads.setFad(this);
    }
    public void setDestillat(Destillat destillat) {
        this.destillat = destillat;
        destillat.addFad(this);
    }
    //endregion

    /**
     * Brugt som objekt-identifikation i GUI
     * @return returnerer i følgende format: "#1 fra Leverandør 0 / 0L"
     */
    public String toString() {
        return "#" + tøndeNummer + " " + " fra " + leverandør + "     " + opfyldtLiter + " / " + størrelseLiter + " L";
    }

}
