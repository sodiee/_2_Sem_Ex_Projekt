package Gui;

import Application.Controller.Controller;
import Application.Model.Fad;
import Application.Model.Lager;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class StartPane extends GridPane {

    private Label lblLager, lblFad;

    private ListView<Lager> lvwLager;

    private ListView<Fad> lvwFad;

    private Button regFad;


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
        lvwFad.getItems().setAll(Controller.getFad());
        ChangeListener<Fad> listener1 = (ov, oldCompny, newCompany) -> this.selectedFadChanged();
        lvwFad.getSelectionModel().selectedItemProperty().addListener(listener1);

        regFad = new Button("Registrer Fad");
        this.add(regFad, 0, 2);
        regFad.setOnAction(event -> this.regFadAction());

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

    public void updateControls() {


    }

}
