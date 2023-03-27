package Gui;

import Application.Controller.Controller;
import Application.Model.Fad;
import Application.Model.Lager;
import Application.Model.Status;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class StartPane extends GridPane {

    private Label lblLager, lblFad;

    private ListView<Lager> lvwLager;

    private ListView<Fad> lvwFad;

    private Button regFad, btnOpretLager, btnOpretWhisky;


    public StartPane() {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        lvwLager = new ListView<>();
        this.add(lvwLager, 0, 1);
        lvwLager.getItems().setAll(Controller.getLager());
        ChangeListener<Lager> listener = (ov, oldCompny, newCompany) -> this.selectedLagerChanged();
        lvwLager.getSelectionModel().selectedItemProperty().addListener(listener);

        lvwFad = new ListView<>();
        this.add(lvwFad, 1, 1);
        ChangeListener<Fad> listener1 = (ov, oldCompny, newCompany) -> this.selectedFadChanged();
        lvwFad.getSelectionModel().selectedItemProperty().addListener(listener1);

        btnOpretLager = new Button("Opret Lager");
        this.add(btnOpretLager, 0, 2);
        btnOpretLager.setAlignment(Pos.BASELINE_CENTER);
        btnOpretLager.setOnAction(event -> this.btnOpretLagerAction());

        regFad = new Button("Registrer Fad");
        this.add(regFad, 1, 2);
        regFad.setAlignment(Pos.BASELINE_CENTER);
        regFad.setOnAction(event -> this.regFadAction());

        btnOpretWhisky = new Button("Opret Whisky");
        btnOpretWhisky.setAlignment(Pos.BASELINE_RIGHT);
        btnOpretWhisky.setOnAction(event -> this.opretWhiskyAction());
        btnOpretWhisky.setDisable(true);

        HBox hbx1 = new HBox(10);
        this.add(hbx1, 1, 2);
        hbx1.getChildren().add(regFad);
        hbx1.getChildren().add(btnOpretWhisky);

        lblLager = new Label("Lager");
        this.add(lblLager,0,0);

        lblFad = new Label("Fad");
        this.add(lblFad,1,0);
    }

    private void selectedLagerChanged() {
        this.updateControls();
    }

    private void selectedFadChanged() {
        this.updateControls();
    }

    public void regFadAction() {
        RegFadWindow regFadWindow = new RegFadWindow("Opret Fad");
        regFadWindow.showAndWait();
    }

    public void btnOpretLagerAction(){
        LagerOpretWindow lagerOpretWindow = new LagerOpretWindow();
        lagerOpretWindow.showAndWait();
    }

    public void opretWhiskyAction(){

        if(lvwFad.getSelectionModel().getSelectedItem().getStatus() == Status.DESTILLAT){
            lvwFad.getSelectionModel().getSelectedItem().removeDestillat();
        }


    }

    public void updateControls() {

        Lager lager = lvwLager.getSelectionModel().getSelectedItem();
        if (lager != null){
            lvwFad.getItems().setAll(lager.getFade());
        }
        btnOpretWhisky.setDisable(true);
        if(lvwFad.getSelectionModel().getSelectedItem().getStatus() == Status.DESTILLAT){
            btnOpretWhisky.setDisable(false);
        }

    }

}
