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


public class RegFadWindow extends Stage {

    private TextField txfLev, txfTidligere, txfBrugt, txfNr, txfLiter;

    private Label lblLev, lblTidligere, lblBrugt, lblNr, lblLiter, lblLager;

    private Button okBut, canBut;

    private ComboBox<Lager> cbbLager;

    public RegFadWindow(String title) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);


        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);

        this.setHeight(300);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lblLev = new Label("Leverandør");
        pane.add(lblLev, 0, 0);

        txfLev = new TextField();
        pane.add(txfLev, 1, 0);

        lblTidligere = new Label("Tidligere indhold");
        pane.add(lblTidligere, 0, 1);

        txfTidligere = new TextField();
        pane.add(txfTidligere, 1, 1);

        lblBrugt = new Label("Antal gange brugt");
        pane.add(lblBrugt, 0, 2);

        txfBrugt = new TextField();
        pane.add(txfBrugt, 1, 2);

        lblNr = new Label("Nummer");
        pane.add(lblNr, 0, 3);

        txfNr = new TextField();
        pane.add(txfNr, 1, 3);

        lblLiter = new Label("Antal Liter");
        pane.add(lblLiter, 0, 4);

        txfLiter = new TextField();
        pane.add(txfLiter, 1, 4);

        lblLager = new Label("Lager");
        pane.add(lblLager, 0, 5);

        okBut = new Button("Ok");
        pane.add(okBut, 0, 6);
        okBut.setOnAction(event -> this.okAction());


        canBut = new Button("Cancel");
        pane.add(canBut, 1, 6);
        canBut.setOnAction(event -> this.canAction());

        cbbLager = new ComboBox<>();
        pane.add(cbbLager, 1, 5);
        cbbLager.getItems().addAll(Controller.getLager());


    }

    private void canAction() {
        this.hide();
    }

    private void okAction() {
        String leverandør = txfLev.getText().trim();
        String tidligere = txfTidligere.getText().trim();
        int brugt = Integer.parseInt(txfBrugt.getText());
        int nr = Integer.parseInt(txfNr.getText());
        int liter = Integer.parseInt(txfLiter.getText());
        Lager lager = cbbLager.getSelectionModel().getSelectedItem();
        if (leverandør.length() == 0 || tidligere.length() == 0 || txfBrugt.getText().length() == 0 || txfNr.getText().length() == 0 || txfLiter.getText().length() ==0) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Error");
            dialog.setHeaderText("Angiv noget i alle felter");
            dialog.showAndWait();
        }else if (brugt == 0 || nr == 0 || liter == 0) {
                Alert dialog1 = new Alert(Alert.AlertType.INFORMATION);
                dialog1.setTitle("Error");
                dialog1.setHeaderText("Brugt, nummer og liter må ikke være 0");
                dialog1.showAndWait();
            }
        else{
            Controller.createFad(leverandør,tidligere,brugt,nr,liter,lager);
        }

    }
}

