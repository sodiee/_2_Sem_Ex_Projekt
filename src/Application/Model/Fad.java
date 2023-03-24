package Application.Model;

import java.util.HashMap;

public class Fad {
    private String leverandør;
    private String tidligereIndhold;
    private int antalGangeBrugt;
    private int nummer;
    private int størrelseLiter;
    private Lager lager;
    private int age;
    private Destillat destillat;
    private HashMap<Integer, Destillat> tidligereDestillater;

    public Fad(String leverandør, String tidligereIndhold, int antalGangeBrugt, int nummer, int størrelseLiter, Lager lager) {
        this.leverandør = leverandør;
        this.tidligereIndhold = tidligereIndhold;
        this.antalGangeBrugt = antalGangeBrugt;
        this.nummer = nummer;
        this.størrelseLiter = størrelseLiter;
        setLager(lager);
        age = 0;
    }

    public void setLager(Lager lager) {
        this.lager = lager;
        lager.addToList(this);
    }

    public void setDestillat(Destillat destillat) {
        this.destillat = destillat;
        destillat.addFad(this);
    }

    public void addDestilatTofad(Destillat destillat) {
        if (!(destillat == null)) {
            setDestillat(destillat);
        } else {
            System.out.println("Der er ikke nok plads på fadet");
        }
    }

    public Destillat getDestillat() {
        return destillat;
    }

    public void removeDestillat(Destillat destillat, int nr) {
        if (this.getDestillat() == destillat) {
            tidligereDestillater.put(nr, destillat);
            this.destillat = null;
        }
    }

    public int getStørrelseLiter() {
        return størrelseLiter;
    }

    public String toString() {
        return "Nr:" + nummer + " " + " fra " + leverandør;
    }
}
