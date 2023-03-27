package Gui;

import Application.Model.Fad;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WhiskeyOpretWindow extends Stage {

    private Label lblAlkoholPro, lblBeskrivelse, lblFortyndet, lblFortyndelseIL;
    private TextField txfAlkoholPro, txfBeskrivelse, txfFortyndelseIL;
    private CheckBox cbxFortyndet;

    private Button btnGodkend, btnAnnuller;
    private Fad fad;
    public WhiskeyOpretWindow(Fad fad){
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.fad = fad;
        this.setTitle("Tap Fad: " + fad);
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        this.setScene(scene);
        //this.setHeight(300);
        this.initGUI(pane);
    }

    private void initGUI(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lblAlkoholPro = new Label("Alkohol Procent: ");
        txfAlkoholPro = new TextField();
        lblBeskrivelse = new Label("Beskrivelse: ");
        txfBeskrivelse = new TextField();
        lblFortyndet = new Label("Fortyndet: ");
        cbxFortyndet = new CheckBox();
        lblFortyndelseIL = new Label("Fortyndelsesvæske i Liter: ");
        txfFortyndelseIL = new TextField();
        btnAnnuller = new Button("Annuller");
        btnGodkend = new Button("Godkend");

        pane.add(lblAlkoholPro, 0, 1);
        pane.add(lblBeskrivelse, 0, 2);
        pane.add(lblFortyndet, 0, 3);
        pane.add(lblFortyndelseIL, 0, 4);
        pane.add(txfAlkoholPro, 1, 1);
        pane.add(txfBeskrivelse, 1, 2);
        pane.add(cbxFortyndet, 1, 3);
        pane.add(txfFortyndelseIL, 1, 4);
        pane.add(btnGodkend, 0, 5);
        pane.add(btnAnnuller, 1, 5);

        lblAlkoholPro.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblBeskrivelse.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblFortyndet.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblFortyndelseIL.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        btnGodkend.setOnAction(event -> btnGodkendAction());
        btnAnnuller.setOnAction(event -> btnAnnullerAction());


    }
    private void btnGodkendAction(){

    }
    private void btnAnnullerAction(){

    }
}
