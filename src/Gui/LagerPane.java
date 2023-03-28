package Gui;

import Application.Model.*;
import Storage.Storage;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class LagerPane extends GridPane {

    private ComboBox<Lager> cbxLager;
    private Label lblLager, lblReol, lblHylde, lblHyldeplads, lblFad, lblFadInfo;
    private ListView<Reol> lvwReol;
    private ListView<Hylde> lvwHylde;
    private ListView<Hyldeplads> lvwHyldeplads;


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

        this.add(lblLager, 0, 0);
        this.add(cbxLager, 0, 1);
        this.add(lblReol, 0, 2);
        this.add(lvwReol, 0, 3);
        this.add(lblHylde, 0, 4);
        this.add(lvwHylde, 0, 5);
        this.add(lblHyldeplads, 1, 3);
        this.add(lvwHyldeplads, 1, 4);
        this.add(lblFad, 1, 5);
        this.add(lblFadInfo, 1, 6);

        ChangeListener<Reol> listener1 = (ov, oldCompny, newCompany) -> this.selectedReolChanged();
        ChangeListener<Hylde> listener2 = (ov, oldCompny, newCompany) -> this.selectedHyldeChanged();
        ChangeListener<Hyldeplads> listener3 = (ov, oldCompny, newCompany) -> this.selectedHyldepladsChanged();
        lvwReol.getSelectionModel().selectedItemProperty().addListener(listener1);
        lvwHylde.getSelectionModel().selectedItemProperty().addListener(listener2);
        lvwHyldeplads.getSelectionModel().selectedItemProperty().addListener(listener3);

        cbxLager.getItems().addAll(Storage.getLagerArrayList());
    }
    public void updateControls(){

        Lager lager = cbxLager.getSelectionModel().getSelectedItem();
        lvwReol.getItems().setAll(lager.getReoler());

    }
    private void selectedReolChanged(){
        Reol reol = lvwReol.getSelectionModel().getSelectedItem();
        lvwHylde.getItems().setAll(reol.getHylder());
    }
    private void selectedHyldeChanged(){
        Hylde hylde = lvwHylde.getSelectionModel().getSelectedItem();
        lvwHyldeplads.getItems().setAll(hylde.getHyldepladser());

    }
    private void selectedHyldepladsChanged(){
        Hyldeplads hyldeplads = lvwHyldeplads.getSelectionModel().getSelectedItem();
        lblFadInfo.setText(hyldeplads.getFad().toString());
    }
}
