package Application.Model;

public class Whisky {

    private int nummer;
    private double alkoholProcent;
    private String beskrivelse;
    private int liter;
    private boolean fortyndet;
    private int fortyndelseIML;

    public Whisky(int nummer, double alkoholProcent, String beskrivelse, int liter, boolean fortyndet, int fortyndelseIML) {
        this.nummer = nummer;
        this.alkoholProcent = alkoholProcent;
        this.beskrivelse = beskrivelse;
        this.liter = liter;
        this.fortyndet = fortyndet;
        this.fortyndelseIML = fortyndelseIML;
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

    public int getLiter() {
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

    public int getFortyndelseIML() {
        return fortyndelseIML;
    }

    public void setFortyndelseIML(int fortyndelseIML) {
        this.fortyndelseIML = fortyndelseIML;
    }
}
