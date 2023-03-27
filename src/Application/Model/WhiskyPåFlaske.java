package Application.Model;

import java.util.ArrayList;

public class WhiskyPåFlaske {
    private int nummer;

    private int liter;

    private Whisky whisky;

    private ArrayList<Lager> lagre;

    public WhiskyPåFlaske(int nummer, int liter, Whisky whisky, ArrayList<Lager> lagre) {
        this.nummer = nummer;
        this.liter = liter;
        this.whisky = whisky;
        this.lagre = lagre;
    }
}
