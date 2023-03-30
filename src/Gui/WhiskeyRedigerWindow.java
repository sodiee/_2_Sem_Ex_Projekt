package Gui;

import Application.Model.Whisky;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WhiskeyRedigerWindow extends Stage {

    private Label lblAlkPro, lblBeskrivelse, lblLiter;
    private TextField txfAlkPro, txfBeskrivelse, txfLiter;
    private Button btnGodkend, btnAnnuller;
    private Whisky whisky;

    public WhiskeyRedigerWindow(Whisky whisky){
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.setTitle("Redigér Whisky");
        this.whisky = whisky;
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        this.setScene(scene);
        this.initGUI(pane);
        this.initData();
    }

    private void initGUI(GridPane pane){
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        btnGodkend = new Button("Godkend");
        btnGodkend.setOnAction(event -> godkendAction());
        btnAnnuller = new Button("Annuller");
        btnAnnuller.setOnAction(event -> annullerAction());
        lblAlkPro = new Label("Alkohol Procent");
        lblBeskrivelse = new Label("Beskrivelse");
        lblLiter = new Label("Antal Liter");
        txfAlkPro = new TextField();
        txfBeskrivelse = new TextField();
        txfLiter = new TextField();

        lblAlkPro.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblBeskrivelse.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblLiter.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

        HBox hbx = new HBox(50);
        hbx.getChildren().addAll(btnGodkend, btnAnnuller);

        VBox vbx = new VBox(5);
        vbx.getChildren().addAll(lblAlkPro, txfAlkPro, lblLiter, txfLiter, lblBeskrivelse, txfBeskrivelse, hbx);
        pane.add(vbx, 0, 0);
    }
    private void initData(){

        txfLiter.setText(String.valueOf(whisky.getLiter()));
        txfBeskrivelse.setText(whisky.getBeskrivelse());
        txfAlkPro.setText(String.valueOf(whisky.getAlkoholProcent()));

    }
    private void godkendAction(){
        //TODO:
    }
    private void annullerAction(){
        this.hide();
    }

}
