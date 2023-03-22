package Gui;

import Application.Controller.Controller;
import Application.Model.Fad;
import Application.Model.Lager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class regFadWindow extends Stage {

    private Fad fad;

    private TextField txfLev, txfTidligere, txfBrugt, txfNr, txfLiter, txfLager;

    private Label lblLev, lblTidligere, lblBrugt, lblNr, lblLiter, lblLager;

    private Button okBut, canBut;

    private ComboBox<Lager> cbbLager;
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

        lblLiter= new Label("Antal Liter");
        pane.add(lblNr,4,0);

        txfLiter = new TextField();
        pane.add(txfNr,1,4);

        lblLager = new Label("Lager");
        pane.add(lblLager,5,0);

        txfLager = new TextField();
        pane.add(txfLager,1,5);

        okBut = new Button("Ok");
        pane.add(okBut,0,6);
        okBut.setOnAction(event -> this.okAction());


        canBut = new Button("Cancel");
        pane.add(canBut,1,6);
        canBut.setOnAction(event -> this.canAction());

        cbbLager = new ComboBox<>();
        pane.add(cbbLager,1,5);
        cbbLager.getItems().addAll(Controller.getLager());


    }

    private void canAction(){
        this.hide();
    }

    private void okAction(){
        String leverandør = txfLev.getText().trim();
        String tidligere = txfTidligere.getText().trim();
        String brugt = txfBrugt.getText().trim();
        String nr = txfNr.getText().trim();
        String liter = txfNr.getText().trim();
        Lager lager = cbbLager.getSelectionModel().getSelectedItem();
        if(leverandør.length() == 0 || tidligere.length() == 0 || brugt.length() == 0 || nr.length() == 0 || liter.length() == 0){
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Error");
            dialog.setHeaderText("Angiv noget i alle felter");
            dialog.showAndWait();
        }

        //Controller.createFad(leverandør,tidligere,brugt,nr,liter,)

    }





}

