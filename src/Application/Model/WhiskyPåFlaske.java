package Application.Model;

public class WhiskyPåFlaske {
    private int nummer;

    private int totalNummer;
    private double fortyndelseIML;
    private double AlkoholProcent;

    private double liter;

    private Whisky whisky;

    private Lager lager;

    /**
     * Opretter et whisky model-objekt, ud fra de angivne parametre
     * @param nummer nummeret på flasken
     * @param totalNummer nummer af hvor mange flasker eksisterer
     * @param fortyndelseIML fortyndelse i ml
     * @param whisky whiskien der aftappes fra
     */
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

    public double getAlkoholProcent() {return AlkoholProcent;}

    /**
     * En metode som kan give historik og al information vedrørende en whiskyflaske
     * @return En streng af alle informationer. Meget lang
     */
    public String getHistorik() {
        String navn = whisky.getNavn() + " ";
        String nummer = String.valueOf(getNummer()) + "";
        String totalNummer = String.valueOf(getTotalNummer()) + " ";
        String alkoholProcent = String.valueOf(whisky.getAlkoholprocentDestillat()) + " ";
        String beskrivelse = whisky.getBeskrivelse() + " ";
        String liter = String.valueOf(this.getLiter()) + " ";
        String fortyndelseIML = String.valueOf(this.fortyndelseIML) + " ";
        String rygeMateriale = whisky.getRygeMateriale() + " ";
        String kornSort = whisky.getKornSort() + " ";
        String fad = "Tøndenummer: " + whisky.getAktuelFad() + " ";
        String leverandør = whisky.getFad().getLeverandør() + " ";
        String literPåOprindeligDestillat = whisky.getFad().getTidligereDestillater().get(whisky.getFad().getTidligereDestillater().size() - 1).getLiterFraStart() + "";

        return "****" + "\nNavn: " + navn + "\nNummer: " + nummer + "/" + totalNummer + "\nAlkoholprocent: " + alkoholProcent + "\nBeskrivelse: " + beskrivelse + "\nLiter: " + liter + "\nFortyndelse i ML: " + fortyndelseIML + "\nRygemateriale: " + rygeMateriale + "\nKornsort: " + kornSort + "\nFad: " + fad + "\nLeverandør: " + leverandør + "\n" + "LiterPåOprindeligDestillat: " + literPåOprindeligDestillat + "\n******";
    }

    /**
     *
     * @return "Flaske numer: + nummer + / + totalNummer
     */
    @Override
    public String toString() {
        return "Flaske nummer " + nummer + "/" + totalNummer;
    }
}

