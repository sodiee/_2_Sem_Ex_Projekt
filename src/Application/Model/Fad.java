package Application.Model;


import java.util.ArrayList;

public class Fad {
    private String leverandør;
    private String tidligereIndhold;
    private int antalGangeBrugt;
    private boolean iBrug = false;
    private int nummer;
    private int størrelseLiter;
    private int opfyldtLiter = 0;
    private int alder;

    private Hyldeplads hyldeplads;

    private ArrayList<Destillat> tidligereDestillater;
    private Destillat destillat;

    private Status status;



    public Fad(String leverandør, String tidligereIndhold, int antalGangeBrugt, int nummer, int størrelseLiter, Hyldeplads hyldeplads) {
        this.leverandør = leverandør;
        this.tidligereIndhold = tidligereIndhold;
        this.antalGangeBrugt = antalGangeBrugt;
        this.nummer = nummer;
        this.størrelseLiter = størrelseLiter;
        this.tidligereDestillater = new ArrayList<>();
        setHyldeplads(hyldeplads);
        alder = 0;
        status = Status.TOM;
    }


    public double beregnAngelShare(Destillat destillat) {
        double tempLiter = destillat.getLiterFraStart();
        double angelShareProcent;
        double angelShareDelTotal = 0;

        if (alder < 0) {
            throw new IllegalArgumentException("Ugyldig alder på fad");
        }
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
            double antalLiter = fad.opfyldtLiter - fad.beregnAngelShare(fad.getDestillat());
            Whisky whisky = new Whisky((int) antalLiter, destillat.getBeskrivelse(), this);
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

    public void setHyldeplads(Hyldeplads hyldeplads){
        this.hyldeplads = hyldeplads;
        hyldeplads.setFad(this);
    }

    public void setDestillat(Destillat destillat) {
        this.destillat = destillat;
        destillat.addFad(this);
    }

    public void addDestilatTofad(Destillat destillat) {
        if (destillat != null) {
            setDestillat(destillat);
            destillat.addFad(this);
            antalGangeBrugt++;
        } else {
            System.out.println("Der er ikke nok plads på fadet");
        }
    }

    public void removeDestillat() {
        if (destillat != null) {
            tidligereDestillater.add(destillat);
            alder += destillat.getSlutDato().getYear() - destillat.getStartDato().getYear();
            this.destillat = null;
            convertToWhisky();
        }
    }

    public void convertToWhisky(){
        this.setStatus(Status.WHISKY);
        createWhisky(this);
    }
    public Destillat getDestillat() {
        return destillat;
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

    public Hyldeplads getHyldeplads() {
        return hyldeplads;
    }
    public String toString() {
        return "Nr:" + nummer + " " + " fra " + leverandør + "     " + opfyldtLiter + " / " + størrelseLiter + " L";
    }
}
