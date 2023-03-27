package Application.Model;


import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;

public class Fad {
    private String leverandør;
    private String tidligereIndhold;
    private int antalGangeBrugt;
    private boolean iBrug = false;
    private int nummer;
    private int størrelseLiter;
    private int opfyldtLiter = 0;
    private Lager lager;
    private int alder;
    private Destillat destillat;
    private HashMap<Integer, Destillat> tidligereDestillater;

    private Status status;

    public Fad(String leverandør, String tidligereIndhold, int antalGangeBrugt, int nummer, int størrelseLiter, Lager lager) {
        this.leverandør = leverandør;
        this.tidligereIndhold = tidligereIndhold;
        this.antalGangeBrugt = antalGangeBrugt;
        this.nummer = nummer;
        this.størrelseLiter = størrelseLiter;
        this.tidligereDestillater = new HashMap<>();
        setLager(lager);
        alder = 0;
        this.status = Status.TOM;
    }

    /**
     * Metode udregnet for, at angelShare delen i whisky brygning stiger hvert år. :)
     * Altså efter startalderen, så bliver angelShareProcenten sat og udregnet i bunden af metoden i for-loopet.
     *
     * @param destillat
     * @return
     */
    public double beregnAngelShare2(Destillat destillat) {
        double tempLiter = destillat.getLiterFraStart();
        double angelShareProcent;
        double angelShareDelTotal = 0;

        if (alder < 0) {
            throw new IllegalArgumentException("Ugyldig alder på fad");
        } else if (destillat.getFade() != null) {
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

            for (int i = 0; i < 3; i++) {
                double angelShareDelTemp = tempLiter * angelShareProcent;
                angelShareDelTotal += angelShareDelTemp;
                tempLiter -= angelShareDelTemp;
            }


        }

        //angelShareDelTotal = angelShareDelTotal * 100;
        //angelShareDelTotal = Math.round(angelShareDelTotal);
        //angelShareDelTotal = angelShareDelTotal / 100;
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

            for (int i = 0; i < 3; i++) {
                angelShareDelTemp = tempLiter * angelShareProcent;
                angelShareDelTotal += angelShareDelTemp;
                tempAS = angelShareDelTemp;
                tempLiter = tempLiter - tempAS;
            }
        }

        return angelShareDelTotal;
    }

    public Whisky createWhisky(Fad fad) {
        double antalLiter = opfyldtLiter - fad.beregnAngelShare3(destillat);
        Whisky whisky = new Whisky(antalLiter, alkoholProcent, destillat.getBeskrivelse(), fortyndet, fortyndelseIL, this);
        return whisky;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
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
            alder += destillat.getSlutDato().getYear() - destillat.getStartDato().getYear();

        }
    }

    public int getStørrelseLiter() {
        return størrelseLiter;
    }

    public boolean isiBrug() {
        return iBrug;
    }

    public void setiBrug(boolean iBrug) {
        this.iBrug = iBrug;
    }

    public int getOpfyldtLiter() {return opfyldtLiter;}

    public void setOpfyldtLiter(int opfyldtLiter) {this.opfyldtLiter = opfyldtLiter;}

    public Lager getLager() {
        return lager;
    }
    public String toString() {
        return "Nr:" + nummer + " " + " fra " + leverandør + "     " + opfyldtLiter + " / " + størrelseLiter + " L";
    }
}
