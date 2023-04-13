package Gui;

import Application.Controller.Controller;
import Application.Model.*;
import Storage.Storage;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FadRedigerWindow extends Stage {
    private TextField txfLev, txfTidligere, txfBrugt, txfLiter;
    private Label lblLev, lblTidligere, lblBrugt, lblLiter, lblLager, lblReol;
    private Button okBut, canBut;
    private ComboBox<Lager> cbbLager;
    private ComboBox<Reol> cbxReol;
    private Fad fad;

    public FadRedigerWindow(Fad fad) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.setTitle("Redigér Fad");
        GridPane pane = new GridPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);
        this.fad = fad;
        this.initData();
        //this.setHeight(300);
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

        lblLiter = new Label("Antal Liter");
        pane.add(lblLiter, 0, 3);

        txfLiter = new TextField();
        pane.add(txfLiter, 1, 3);

        lblLager = new Label("Lager");
        pane.add(lblLager, 0, 4);

        okBut = new Button("Ok");
        pane.add(okBut, 0, 6);
        okBut.setOnAction(event -> this.okAction());

        canBut = new Button("Cancel");
        pane.add(canBut, 1, 6);
        canBut.setOnAction(event -> this.canAction());

        cbbLager = new ComboBox<>();
        pane.add(cbbLager, 1, 4);
        cbbLager.getItems().addAll(Controller.getLager());
        ChangeListener<Lager> listener1 = (ov, oldCompny, newCompany) -> this.onLagerChanged();
        cbbLager.getSelectionModel().selectedItemProperty().addListener(listener1);

        lblReol = new Label("Reol");
        pane.add(lblReol, 0, 5);

        cbxReol = new ComboBox<>();
        pane.add(cbxReol, 1, 5);

    }
    private void initData(){
        txfLev.setText(fad.getLeverandør());
        txfTidligere.setText(fad.getTidligereIndhold());
        txfBrugt.setText(String.valueOf(fad.getAntalGangeBrugt()));
        txfLiter.setText(String.valueOf(fad.getStørrelseLiter()));
        cbbLager.getItems().setAll(Storage.getLagerArrayList());
    }


    private void onLagerChanged(){
        cbxReol.getItems().setAll(cbbLager.getSelectionModel().getSelectedItem().getReoler());
    }

    private void canAction() {
        this.hide();
    }

    private void okAction() {
        String leverandør = txfLev.getText().trim();
        String tidligere = txfTidligere.getText().trim();
        int brugt = Integer.parseInt(txfBrugt.getText());
        int liter = Integer.parseInt(txfLiter.getText());
        Hyldeplads hyldeplads = Controller.findTomHyldeplads(cbxReol.getSelectionModel().getSelectedItem());

            if (hyldeplads == null) {
                Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                dialog.setTitle("Error");
                dialog.setHeaderText("Hyldeplads er null");
                dialog.showAndWait();
            } else {
                Controller.createFad(leverandør, tidligere, brugt, liter, hyldeplads);
                fad.setLeverandør(txfLev.getText());
                fad.setTidligereIndhold(txfTidligere.getText());
                fad.setAntalGangeBrugt(Integer.parseInt(txfBrugt.getText()));
                hide();
            }

        }
    }

