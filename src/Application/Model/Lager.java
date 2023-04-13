package Application.Model;

import java.util.ArrayList;

public class Lager {
    //region Global Variables
    private int hylder;
    private int pladsPåHylde;
    private int reole;
    private String adresse;
    private ArrayList<Reol> reoler;
    private ArrayList<WhiskyPåFlaske> flasker;
    //endregion

    /**
     * Opretter et Lager mode-objekt ud fra givne parametre. Skal oprettes igennem controlleren,
     * da constructeren ikke indeholder reol/hylde/hyldeplads-oprettelse
     * @param reol antal reoler der ønskes opretet
     * @param hylde antal hylder der ønskes oprettet
     * @param pladsPåHylde antal pladser på hylder
     * @param adresse lagerets adresse
     */
    public Lager(int reol, int hylde, int pladsPåHylde, String adresse) {
        this.reole = reol;
        this.hylder = hylde;
        this.pladsPåHylde = pladsPåHylde;
        this.adresse = adresse;
        this.reoler = new ArrayList<>();
        this.flasker = new ArrayList<>();
    }

    public void addFlaske(WhiskyPåFlaske whiskyPåFlaske) {
        if (!flasker.contains(whiskyPåFlaske)) {
            flasker.add(whiskyPåFlaske);
            whiskyPåFlaske.setLager(this);
        }
    }

    public ArrayList<WhiskyPåFlaske> getFlasker() {
        return flasker;
    }
    public ArrayList<Reol> getReoler(){return reoler;}

    public void addReol(Reol reol){
        if(!reoler.contains(reol)){
            reoler.add(reol);
            reol.setLager(this);
        }
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     *
     * @return Adressen
     */
    @Override
    public String toString() {return adresse;}
}
