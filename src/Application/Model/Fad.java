package Application.Model;

import java.time.LocalDate;
import java.util.HashMap;

public class Fad {
    private String leverandør;
    private String tidligereIndhold;
    private int antalGangeBrugt;
    private int nummer;
    private int størrelseLiter;
    private Lager lager;
    private int alder;
    private Destillat destillat;
    private HashMap<Integer, Destillat> tidligereDestillater;

    public Fad(String leverandør, String tidligereIndhold, int antalGangeBrugt, int nummer, int størrelseLiter, Lager lager) {
        this.leverandør = leverandør;
        this.tidligereIndhold = tidligereIndhold;
        this.antalGangeBrugt = antalGangeBrugt;
        this.nummer = nummer;
        this.størrelseLiter = størrelseLiter;
        this.tidligereDestillater = new HashMap<>();
        setLager(lager);
        alder = 0;
    }

    public double beregnAngelShare(Destillat destillat) {
        double angelShareProcent = 0;
        double angelShareDel = 0;
        if (alder == 0) {
            angelShareProcent = 0.02;
            angelShareDel += destillat.getLiterFraStart() * angelShareProcent;
            return angelShareDel;
        } else if (alder < 5) {
            angelShareProcent = 0.02;
            for (int i = 0; i < alder; i++) {
                angelShareDel += destillat.getLiterFraStart() * angelShareProcent;
            }
        } else if (alder >= 5 && alder < 8) {
            angelShareProcent = 0.03;
            for (int i = 0; i < alder; i++) {
                angelShareDel += destillat.getLiterFraStart() * angelShareProcent;
            }
        } else if (alder >= 8 && alder < 12) {
            angelShareProcent = 0.04;
            for (int i = 0; i < alder; i++) {
                angelShareDel += destillat.getLiterFraStart() * angelShareProcent;
            }
        } else if (alder >= 12) {
            angelShareProcent = 0.05;
            for (int i = 0; i < alder; i++) {
                angelShareDel += destillat.getLiterFraStart() * angelShareProcent;
            }
            return angelShareDel;
        }
        return angelShareDel;
    }

    public int getAlder() {
        return alder;
    }

    public void setAlder(int alder) {
        this.alder = alder;
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
            antalGangeBrugt++;
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
            alder = destillat.getSlutDato().getYear() - destillat.getStartDato().getYear();
        }
    }

    public int getStørrelseLiter() {
        return størrelseLiter;
    }

    public String toString() {
        return "Nr:" + nummer + " " + " fra " + leverandør;
    }
}
