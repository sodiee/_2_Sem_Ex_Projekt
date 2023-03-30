package Gui;

import Application.Controller.Controller;
import Application.Model.*;
import Storage.Storage;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Optional;

public class LagerPane extends GridPane {

    private ComboBox<Lager> cbxLager;
    private Label lblLager, lblReol, lblHylde, lblHyldeplads, lblFad, lblFadInfo;
    private ListView<Reol> lvwReol;
    private ListView<Hylde> lvwHylde;
    private ListView<Hyldeplads> lvwHyldeplads;
    private HBox hbxButtons;
    private Button btnOpret, btnRediger, btnSlet;

    public LagerPane(){
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        initGUI();
    }
    private void initGUI(){

        cbxLager = new ComboBox();
        lblLager = new Label("Lager");
        lblReol = new Label("Reol");
        lblHylde = new Label("Hylde");
        lblHyldeplads = new Label("Hyldeplads");
        lblFad = new Label("Fad");
        lvwReol = new ListView();
        lvwHylde = new ListView();
        lvwHyldeplads = new ListView();
        lblFadInfo = new Label();
        hbxButtons = new HBox(10);
        btnOpret = new Button("Opret Lagerhus");
        btnRediger = new Button("Redigér Lagerhus");
        btnSlet = new Button("Slet Lagerhus");

        //this.add(lblLager, 0, 0);
        this.add(cbxLager, 0, 1);
        this.add(lblReol, 0, 2);
        this.add(lvwReol, 0, 3);
        this.add(lblHylde, 1, 2);
        this.add(lvwHylde, 1, 3);
        this.add(lblHyldeplads, 0, 4);
        this.add(lvwHyldeplads, 0, 5);
        this.add(lblFad, 1, 4);
        this.add(lblFadInfo, 1, 5);
        this.add(hbxButtons, 0, 6);

        btnOpret.setOnAction(event -> btnOpretLagerAction());
        btnRediger.setOnAction(event -> btnRedigerLagerAction());
        btnSlet.setOnAction(event -> btnSletLagerAction());
        hbxButtons.getChildren().addAll(btnOpret, btnRediger, btnSlet);

        ChangeListener<Reol> listener1 = (ov1, oldCompny, newCompany) -> this.selectedReolChanged();
        ChangeListener<Hylde> listener2 = (ov2, oldCompny, newCompany) -> this.selectedHyldeChanged();
        ChangeListener<Hyldeplads> listener3 = (ov3, oldCompny, newCompany) -> this.selectedHyldepladsChanged();
        ChangeListener<Lager> listener4 = (ov4, oldCompny, newCompany) -> this.selectedLagerChanged();
        lvwReol.getSelectionModel().selectedItemProperty().addListener(listener1);
        lvwHylde.getSelectionModel().selectedItemProperty().addListener(listener2);
        lvwHyldeplads.getSelectionModel().selectedItemProperty().addListener(listener3);
        cbxLager.getSelectionModel().selectedItemProperty().addListener(listener4);


        lvwHyldeplads.setPrefHeight(200);
        lvwHylde.setPrefHeight(200);
        lvwReol.setPrefHeight(200);

        cbxLager.getItems().addAll(Storage.getLagerArrayList());
        cbxLager.getSelectionModel().selectFirst();

        lblFad.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblLager.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblHylde.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblFad.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblHyldeplads.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblReol.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    }
    public void updateControls(){

    }
    public void btnOpretLagerAction(){
        LagerOpretWindow lagerOpretWindow = new LagerOpretWindow();
        lagerOpretWindow.showAndWait();
        cbxLager.getItems().setAll(Storage.getLagerArrayList());
        cbxLager.getSelectionModel().selectLast();
    }
    public void btnRedigerLagerAction(){
        if(cbxLager.getSelectionModel().getSelectedItem() == null){return;}
        LagerRedigerWindow lagerRedigerWindow = new LagerRedigerWindow(cbxLager.getSelectionModel().getSelectedItem());
        lagerRedigerWindow.showAndWait();
        cbxLager.getItems().setAll(Storage.getLagerArrayList());
        //TODO: sæt den til at vælge den man lige har redigeret
        cbxLager.getSelectionModel().selectLast();
    }
    public void btnSletLagerAction(){
        Lager lager = cbxLager.getSelectionModel().getSelectedItem();

        if(lager == null){return;}
        Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmation.setTitle("Slettelse");
        alertConfirmation.setHeaderText("Er du sikker på at du vil slette lageret?");
        Optional<ButtonType> option = alertConfirmation.showAndWait();

        if (option.get() == null) {
            //no selection
        } else if (option.get() == ButtonType.OK) {
            Controller.deleteLager(lager);
        } else if (option.get() == ButtonType.CANCEL) {
            //cancelled
        } else {

        }

        cbxLager.getItems().setAll(Storage.getLagerArrayList());
        cbxLager.getSelectionModel().selectLast();
    }
    private void selectedReolChanged(){
        lvwHyldeplads.getSelectionModel().clearSelection();
        lvwHyldeplads.getItems().clear();
        lvwHylde.getSelectionModel().clearSelection();
        lvwHylde.getItems().clear();

        Reol reol = lvwReol.getSelectionModel().getSelectedItem();
        lvwHylde.getItems().setAll(reol.getHylder());
    }
    private void selectedHyldeChanged(){

        Hylde hylde = lvwHylde.getSelectionModel().getSelectedItem();
        if (hylde != null) {
            lvwHyldeplads.getItems().setAll(hylde.getHyldepladser());
        }

    }
    private void selectedHyldepladsChanged(){
        //TODO: der opstår fejl fordi den her kører ved deselect, i stedet for kun select
        Hyldeplads hyldeplads = lvwHyldeplads.getSelectionModel().getSelectedItem();
        if(hyldeplads !=  null && hyldeplads.getFad() != null ){

            lblFadInfo.setText(hyldeplads.getFad().getLeverandør());
        }
        else{
            lblFadInfo.setText("Pladsen er tom");
        }
    }
    private void selectedLagerChanged(){
        Lager lager = cbxLager.getSelectionModel().getSelectedItem();
        if(lager != null) {
            lvwReol.getItems().setAll(lager.getReoler());
        }
    }
}
