package Gui;

import Application.Controller.Controller;
import Application.Model.Lager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class DestillatOpretWindow extends Stage{

    private Label lblNr, lblMedarbejder, lblLiter, lblAlkPro, lblStartDato, lblSlutDato, lblKornSort, lblRygemateriale, lblBeskrivelse, lblIsDone;
    private TextField txfNr, txfMedarbejder, txfLiter, txfAlkPro, txfKornSort, txfRygemateriale, txfBeskrivelse, txfIsDone;
    private DatePicker dpStartDato, dpSlutDato;
    private Button btnOpret, btnAnnuller;

    public DestillatOpretWindow(){
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle("Opret Destillat");
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        this.setScene(scene);
        //this.setHeight(300);
        this.initGUI(pane);
    }

    private void initGUI(GridPane pane){
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lblMedarbejder = new Label("Medarbejder");
        pane.add(lblMedarbejder,0,0);
        txfMedarbejder = new TextField();
        pane.add(txfMedarbejder, 0, 1);

        lblLiter = new Label("Liter");
        pane.add(lblLiter, 0, 2);
        txfLiter = new TextField();
        pane.add(txfLiter, 0, 3);

        lblAlkPro = new Label("Alkohol Procent");
        pane.add(lblAlkPro, 0, 4);
        txfAlkPro = new TextField();
        pane.add(txfAlkPro, 0, 5);

        lblStartDato = new Label("Start Dato");
        pane.add(lblStartDato, 0, 6);


    }
}
