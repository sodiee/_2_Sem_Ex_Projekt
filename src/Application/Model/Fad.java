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

    /**
     * Metode udregnet for, at angelShare delen i whisky brygning stiger hvert år. :)
     * @param destillat
     * @return
     */
    public double beregnAngelShare(Destillat destillat) {
        double tempLiter = destillat.getLiterFraStart();
        double tempAS = 0;
        double angelShareProcent = 0;
        double angelShareDelTemp = 0;
        double angelShareDelTotal = 0;
        if (alder == 0) {
            angelShareProcent = 0.02;
            angelShareDelTemp += destillat.getLiterFraStart() * angelShareProcent;
            return angelShareDelTemp;
        } else if (alder < 5) {
            angelShareProcent = 0.02;
            for (int i = 0; i < alder; i++) {
                angelShareDelTemp = tempLiter * angelShareProcent;
                angelShareDelTotal += angelShareDelTemp;
                tempAS = angelShareDelTemp;
                tempLiter = tempLiter - tempAS;
            }
        } else if (alder >= 5 && alder < 8) {
            angelShareProcent = 0.03;
            for (int i = 0; i < alder; i++) {
                angelShareDelTemp = tempLiter * angelShareProcent;
                angelShareDelTotal += angelShareDelTemp;
                tempAS = angelShareDelTemp;
                tempLiter = tempLiter - tempAS;
            }
        } else if (alder >= 8 && alder < 12) {
            angelShareProcent = 0.04;
            for (int i = 0; i < alder; i++) {
                angelShareDelTemp = tempLiter * angelShareProcent;
                angelShareDelTotal += angelShareDelTemp;
                tempAS = angelShareDelTemp;
                tempLiter = tempLiter - tempAS;
            }
        } else if (alder >= 12) {
            angelShareProcent = 0.05;
            for (int i = 0; i < alder; i++) {
                angelShareDelTemp = tempLiter * angelShareProcent;
                angelShareDelTotal += angelShareDelTemp;
                tempAS = angelShareDelTemp;
                tempLiter = tempLiter - tempAS;
            }
        }
        return angelShareDelTotal;
    }

    public double beregnAngelShare2(Destillat destillat) {
        double tempLiter = destillat.getLiterFraStart();
        double angelShareProcent;
        double angelShareDelTotal = 0;

        switch (alder) {
            case 0:
                return destillat.getLiterFraStart() * 0.02;
            case 1:
            case 2:
            case 3:
            case 4:
                angelShareProcent = 0.02;
                break;
            case 5:
            case 6:
            case 7:
                angelShareProcent = 0.03;
                break;
            case 8:
            case 9:
            case 10:
            case 11:
                angelShareProcent = 0.04;
                break;
            default:
                angelShareProcent = 0.05;
                break;
        }

        for (int i = 0; i < alder; i++) {
            double angelShareDelTemp = tempLiter * angelShareProcent;
            angelShareDelTotal += angelShareDelTemp;
            tempLiter -= angelShareDelTemp;
        }

        return angelShareDelTotal;
    }

    public double beregnAngelShare3(Destillat destillat) {
        double tempLiter = destillat.getLiterFraStart();
        double angelShareProcent = 0;
        double angelShareDelTotal = 0;

        if (alder == 0) {
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

            for (int i = 0; i < alder; i++) {
                angelShareDelTemp = tempLiter * angelShareProcent;
                angelShareDelTotal += angelShareDelTemp;
                tempAS = angelShareDelTemp;
                tempLiter = tempLiter - tempAS;
            }
        }

        return angelShareDelTotal;
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
