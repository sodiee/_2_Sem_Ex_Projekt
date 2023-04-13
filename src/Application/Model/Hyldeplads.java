package Application.Model;

import java.util.ArrayList;

public class Hyldeplads {

    //region Global Variables
    private int hyldepladsNr;
    private Hylde hylde;
    private Fad fad;
    public boolean optaget;
    //endregion

    /**
     * Opretter et hyldeplads model-objekt
     * @param hyldepladsNr nummeret på hyldepladsen
     * @param hylde hylden som pladsen er på
     */
    public Hyldeplads(int hyldepladsNr, Hylde hylde){
        this.hyldepladsNr = hyldepladsNr;
        this.hylde = hylde;
        this.optaget = false;
    }

    //region Getters
    public int getHyldepladsNr() {
        return hyldepladsNr;
    }
    public Fad getFad() {
        return fad;
    }
    public boolean isOptaget() {
        return optaget;
    }
    //endregion

    //region Setters
    public void setFad(Fad fad) {
        this.fad = fad;
    }
    public void setHylde(Hylde hylde) {
        this.hylde = hylde;
    }
    public void setOptaget(boolean optaget) {
        this.optaget = optaget;
    }
    //endregion

    /**
     *
     * @return "# + hyldepladsnummer"
     */
    @Override
    public String toString(){
        return "#" + hyldepladsNr;
    }
}
