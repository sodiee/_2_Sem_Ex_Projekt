package Application.Model;

public class Fad {
    private String leverandør;
    private String tidligereIndhold;
    private int antalGangeBrugt;
    private int nummer;
    private int størrelseLiter;

    private int literOptaget;
    private Lager lager;

    public Fad(String leverandør, String tidligereIndhold, int antalGangeBrugt, int nummer, int størrelseLiter, Lager lager) {
        this.leverandør = leverandør;
        this.tidligereIndhold = tidligereIndhold;
        this.antalGangeBrugt = antalGangeBrugt;
        this.nummer = nummer;
        this.størrelseLiter = størrelseLiter;
        setLager(lager);
    }

    public void setLager(Lager lager) {
        this.lager = lager;
        lager.addToList(this);
    }

    public void addLiterTofad(int liter){
        if(this.literOptaget + liter > størrelseLiter){
            literOptaget += liter;
        }
        else{
            System.out.println("Der er ikke nok plads på fadet");
        }
    }

    public int getStørrelseLiter(){
        return størrelseLiter;
    }

    public String toString(){
        return "Nr:" + nummer + " " + " fra " + leverandør;
    }

}
