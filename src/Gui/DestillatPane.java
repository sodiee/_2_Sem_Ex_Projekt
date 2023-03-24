package Gui;

import Application.Controller.Controller;
import Application.Model.Destillat;
import Application.Model.Fad;
import Application.Model.Lager;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;
import java.util.Optional;

public class DestillatPane extends GridPane {

    private Label lblDestilleringer, lblNr, lblMedarbejder, lblLiter, lblAlkPro, lblStartDato, lblSlutDato, lblKornSort, lblRygemateriale, lblBeskrivelse, lblIsDone;
    private Label lblNr2, lblMedarbejder2, lblLiter2, lblAlkPro2, lblStartDato2, lblSlutDato2, lblKornSort2, lblRygemateriale2, lblBeskrivelse2, lblIsDone2;
    private ListView<Destillat> lvwDestilleringer;
    private Button btnOpretDestillering, btnRedigerDestillering, btnSletDestillering, btnDone;

    public DestillatPane() {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        initGUI();

    }
    private void initGUI(){
        lblDestilleringer = new Label("Destilleringer");
        this.add(lblDestilleringer, 0, 0);

        lvwDestilleringer = new ListView();
        this.add(lvwDestilleringer, 0, 1);
        ChangeListener<Destillat> listener = (ov, oldCompny, newCompany) -> this.selectedDestillatChanged();
        lvwDestilleringer.getSelectionModel().selectedItemProperty().addListener(listener);
        lvwDestilleringer.getItems().setAll(Controller.getDestillat());

        //Buttons
        btnOpretDestillering = new Button("Opret Ny");
        btnRedigerDestillering = new Button("Redigér");
        btnSletDestillering = new Button("Slet");
        btnDone = new Button("MFærdigør");
        btnOpretDestillering.setOnAction(event -> btnOpretAction());
        btnRedigerDestillering.setOnAction(event -> btnRedigerAction());
        btnSletDestillering.setOnAction(event -> btnSletAction());
        btnDone.setOnAction(event -> btnDoneAction());

        HBox hbxButtons = new HBox(5);
        this.add(hbxButtons, 0, 3);
        hbxButtons.getChildren().add(btnOpretDestillering);
        hbxButtons.getChildren().add(btnRedigerDestillering);
        hbxButtons.getChildren().add(btnSletDestillering);

        //Info VBox
        VBox vbxInfo = new VBox(5);
        this.add(vbxInfo, 1, 1);
        lblMedarbejder = new Label("Medarbejder");
        lblMedarbejder2 = new Label();
        lblLiter = new Label("Liter");
        lblLiter2 = new Label();
        lblAlkPro = new Label("Alkohol Procent");
        lblAlkPro2 = new Label();
        lblStartDato = new Label("Start Dato");
        lblStartDato2 = new Label();
        lblSlutDato = new Label("Slut Dato");
        lblSlutDato2 = new Label();
        lblKornSort = new Label("Korn Sort");
        lblKornSort2 = new Label();
        lblRygemateriale = new Label("Rygemateriale");
        lblRygemateriale2 = new Label();
        lblBeskrivelse = new Label("Beskrivelse");
        lblBeskrivelse2 = new Label();
        lblNr = new Label("ID Nummer");
        lblNr2 = new Label();
        lblIsDone = new Label("Færdiggjort");
        lblIsDone2 = new Label();
        vbxInfo.getChildren().add(lblMedarbejder);
        vbxInfo.getChildren().add(lblMedarbejder2);
        vbxInfo.getChildren().add(lblLiter);
        vbxInfo.getChildren().add(lblLiter2);
        vbxInfo.getChildren().add(lblAlkPro);
        vbxInfo.getChildren().add(lblAlkPro2);
        vbxInfo.getChildren().add(lblStartDato);
        vbxInfo.getChildren().add(lblStartDato2);
        vbxInfo.getChildren().add(lblSlutDato);
        vbxInfo.getChildren().add(lblSlutDato2);
        vbxInfo.getChildren().add(lblKornSort);
        vbxInfo.getChildren().add(lblKornSort2);
        vbxInfo.getChildren().add(lblRygemateriale);
        vbxInfo.getChildren().add(lblRygemateriale2);
        vbxInfo.getChildren().add(lblBeskrivelse);
        vbxInfo.getChildren().add(lblBeskrivelse2);
        vbxInfo.getChildren().add(lblNr);
        vbxInfo.getChildren().add(lblNr2);
        vbxInfo.getChildren().add(lblIsDone);
        vbxInfo.getChildren().add(lblIsDone2);

        lblMedarbejder.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblLiter.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblAlkPro.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblStartDato.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblSlutDato.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblKornSort.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblRygemateriale.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblBeskrivelse.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblNr.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblIsDone.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    }

    private void selectedDestillatChanged(){this.updateControls();}

    public void updateControls(){

        Destillat destillat = lvwDestilleringer.getSelectionModel().getSelectedItem();

        if (destillat != null){
            lblNr2.setText(String.valueOf(destillat.getDestillatNr()));
            lblMedarbejder2.setText(destillat.getMedarbejder());
            lblLiter2.setText(String.valueOf(destillat.getLiter()));
            lblAlkPro2.setText(String.valueOf(destillat.getAlkoholProcent()));
            lblStartDato2.setText(String.valueOf(destillat.getStartDato()));
            lblSlutDato2.setText(String.valueOf(destillat.getSlutDato()));
            lblKornSort2.setText(destillat.getKornSort());
            lblRygemateriale2.setText(destillat.getRygeMateriale());
            lblBeskrivelse2.setText(destillat.getBeskrivelse());
            lblIsDone2.setText(String.valueOf(destillat.isDone()));
        }
    }


    //buttonAction
    private void btnOpretAction(){

        DestillatOpretWindow destillatOpretWindow = new DestillatOpretWindow();
        destillatOpretWindow.showAndWait();
        lvwDestilleringer.getItems().setAll(Controller.getDestillat());
    }
    private void btnRedigerAction(){

        Destillat destillat = lvwDestilleringer.getSelectionModel().getSelectedItem();
        DestillatRedigerWindow destillatRedigerWindow = new DestillatRedigerWindow(destillat);
        destillatRedigerWindow.showAndWait();
        lvwDestilleringer.getItems().setAll(Controller.getDestillat());
    }
    private void btnSletAction(){
        Destillat destillat = lvwDestilleringer.getSelectionModel().getSelectedItem();

        Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmation.setTitle("Slettelse");
        alertConfirmation.setHeaderText("Er du sikker på at du vil slette destilleringen?");

        Optional<ButtonType> option = alertConfirmation.showAndWait();

        if (option.get() == null) {
            //no selection
        } else if (option.get() == ButtonType.OK) {
            Controller.deleteDestillat(destillat);
        } else if (option.get() == ButtonType.CANCEL) {
            //cancelled
        } else {

        }
        lvwDestilleringer.getItems().setAll(Controller.getDestillat());
    }

    private void btnDoneAction(){
        Destillat destillat = lvwDestilleringer.getSelectionModel().getSelectedItem();
        if(destillat.isDone()){
            destillat.setDone(false);
        } else if (!destillat.isDone()) {
            destillat.setDone(true);
        }
    }
}
