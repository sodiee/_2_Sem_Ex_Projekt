package Application.Controller;

import Application.Model.Fad;
import Application.Model.Lager;

public class Controller {
    public void createFad(String leverandør, String tidligereIndhold, int antalGangeBrugt, int nummer, int størrelseLiter, Lager lager) {
        Fad fad = new Fad(leverandør, tidligereIndhold, antalGangeBrugt, nummer, størrelseLiter, lager);

    }
}
