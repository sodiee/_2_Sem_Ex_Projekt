package Application.Model;



import javafx.scene.control.Alert;

import java.util.ArrayList;

public class Fad {
    private String leverandør;
    private String tidligereIndhold;
    private int antalGangeBrugt;
    //TODO: nummereringen virker ikke
    private static int nummer;
    private int størrelseLiter;
    private int opfyldtLiter = 0;
    private Reol reol;
    private Hylde hylde;
    private Hyldeplads hyldeplads;
    private int alder;

    private Destillat destillat;
    private ArrayList<Destillat> tidligereDestillater;
    private Status status;



    public Fad(String leverandør, String tidligereIndhold, int antalGangeBrugt, int størrelseLiter, Hyldeplads hyldeplads) {
        this.leverandør = leverandør;
        this.tidligereIndhold = tidligereIndhold;
        this.antalGangeBrugt = antalGangeBrugt;
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

    public Whisky createWhisky(Fad fad) {
            if(destillat == null){
                Alert alertConfirmation = new Alert(Alert.AlertType.ERROR);
                alertConfirmation.setTitle("Null");
                alertConfirmation.setHeaderText("ERROR: destillat == null");
            }
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
        else {
            Alert alertConfirmation = new Alert(Alert.AlertType.ERROR);
            alertConfirmation.setTitle("Null");
            alertConfirmation.setHeaderText("ERROR: destillat == null");
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


    public int getOpfyldtLiter() {return opfyldtLiter;}

    public void setOpfyldtLiter(int opfyldtLiter) {this.opfyldtLiter = opfyldtLiter;}

    public Hyldeplads getHyldeplads() {
        return hyldeplads;
    }

    public String getLeverandør() {
        return leverandør;
    }

    public void setLeverandør(String leverandør) {
        this.leverandør = leverandør;
    }

    public String getTidligereIndhold() {
        return tidligereIndhold;
    }

    public void setTidligereIndhold(String tidligereIndhold) {
        this.tidligereIndhold = tidligereIndhold;
    }

    public int getAntalGangeBrugt() {
        return antalGangeBrugt;
    }

    public void setAntalGangeBrugt(int antalGangeBrugt) {
        this.antalGangeBrugt = antalGangeBrugt;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public void setStørrelseLiter(int størrelseLiter) {
        this.størrelseLiter = størrelseLiter;
    }

    public Reol getReol() {
        return reol;
    }

    public void setReol(Reol reol) {
        this.reol = reol;
    }

    public Hylde getHylde() {
        return hylde;
    }

    public void setHylde(Hylde hylde) {
        this.hylde = hylde;
    }

    public ArrayList<Destillat> getTidligereDestillater() {
        return tidligereDestillater;
    }

    public void setTidligereDestillater(ArrayList<Destillat> tidligereDestillater) {
        this.tidligereDestillater = tidligereDestillater;
    }

    public String toString() {
        return "#" + nummer + " " + " fra " + leverandør + "     " + opfyldtLiter + " / " + størrelseLiter + " L";
    }
}
