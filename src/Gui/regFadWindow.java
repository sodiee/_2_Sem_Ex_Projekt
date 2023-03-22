package Gui;

import Application.Model.Fad;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class regFadWindow extends Stage {

    private Fad fad;

    private TextField txfLev, txfTidligere, txfBrugt, txfNr, txfLiter, txfLager;

    private Label lblLev, lblTidligere, lblBrugt, lblNr, lblLiter, lblLager;
    public regFadWindow(String title, Fad fad) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.fad = fad;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);

        this.setHeight(260);
    }

    public regFadWindow(String title) {
        this(title, null);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lblLev = new Label("Leverandør");
        pane.add(lblLev,0,0);

        txfLev = new TextField();
        pane.add(txfLev,1,0);

        lblTidligere = new Label("Tidligere indhold");
        pane.add(lblTidligere,1,0);

        txfTidligere = new TextField();
        pane.add(txfTidligere,1,1);

        lblBrugt = new Label("Antal gange brugt");
        pane.add(lblBrugt,2,0);

        txfBrugt = new TextField();
        pane.add(txfBrugt,1,2);

        lblNr = new Label("Nummer");
        pane.add(lblNr,3,0);

        txfNr = new TextField();
        pane.add(txfNr,1,3);



    }



}

