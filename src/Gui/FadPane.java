package Gui;

import Application.Controller.Controller;
import Application.Model.Fad;
import Application.Model.Status;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class FadPane extends GridPane {

    private ListView<Fad> lvwFad;
    private Label lblToString, lblTidligereIndhold, lblAntalGangeBrugt, lblLager, lblAlder, lblDestillat, lblStatus, lblTidligereDestil;
    private Label LblTidligereIndholdValue, lblAntalGangeBrugtValue, lblLagerValue, lblAlderValue, lblDestillatValue, lblStatusValue;
    private ListView lvwTidligereDestil;
    private VBox vbxInfo;
    private HBox hbxButtons;
    private Button btnRegFad, btnSletFad, btnRedigérFad, btnOpretWhisky;

    public FadPane(){
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        this.initGUI();


    }

    private void initGUI(){
        //kun labels til ting ikke I toString()
        //labelLager = "Lager - reol hylde hyldeplads
        //listview kun så stor som antallet af tidligere destillater

        lvwFad = new ListView<>();
        lblToString = new Label();
        lblTidligereIndhold = new Label("Tidligere Indhold: ");
        lblAntalGangeBrugt = new Label();
        lblLager = new Label("Lagerplads: ");
        lblAlder = new Label("Alder: ");
        lblDestillat = new Label("Destillatindhold: ");
        lblStatus = new Label("Status: ");
        LblTidligereIndholdValue = new Label();
        lblAntalGangeBrugtValue = new Label();
        lblLagerValue = new Label();
        lblAlderValue = new Label();
        lblDestillatValue = new Label();
        lblStatusValue = new Label();
        lblTidligereDestil = new Label("Tidligere Destillater: ");
        lvwTidligereDestil = new ListView();
        vbxInfo = new VBox();
        hbxButtons = new HBox(10);
        btnRegFad = new Button("Opret Fad");
        btnOpretWhisky = new Button("Færdigør Modning");
        btnSletFad = new Button("Slet Fad");
        btnRedigérFad = new Button("Redigér Fad");

        vbxInfo.getChildren().addAll(
                lblToString,
                lblStatus,
                lblStatusValue,
                lblTidligereIndhold,
                LblTidligereIndholdValue,
                lblLager,
                lblLagerValue,
                lblAlder,
                lblAlderValue,
                lblDestillat,
                lblDestillatValue,
                lblTidligereDestil,
                lvwTidligereDestil);

        hbxButtons.getChildren().addAll(btnRegFad, btnRedigérFad, btnSletFad, btnOpretWhisky);

        btnRegFad.setOnAction(event -> regFadAction());
        btnRedigérFad.setOnAction(event -> redigerAction());
        btnSletFad.setOnAction(event -> sletAction());
        btnOpretWhisky.setOnAction(event -> opretWhiskyAction());

        btnRedigérFad.setDisable(true);
        btnOpretWhisky.setDisable(true);
        btnSletFad.setDisable(true);

        ChangeListener<Fad> listener1 = (ov, oldCompny, newCompany) -> this.selectedFadChanged();
        lvwFad.getSelectionModel().selectedItemProperty().addListener(listener1);

        this.add(lvwFad, 0, 0);
        this.add(vbxInfo, 1, 0);
        this.add(hbxButtons, 0, 1, 1, 2);
    }

    private void selectedFadChanged() {
        //TODO Ændre så den ikke giver fejl når man skifter fane mens et fad er markeret.
        if(lvwFad.getSelectionModel().getSelectedItem().getStatus() == Status.DESTILLAT && lvwFad.getSelectionModel().getSelectedItem().getStatus() != null){
            btnOpretWhisky.setDisable(false);
        }
        else{
            btnOpretWhisky.setDisable(true);
        }

        if(lvwFad.getSelectionModel().getSelectedItem() != null){
            btnRedigérFad.setDisable(false);
            btnSletFad.setDisable(false);
        }
        else{
            btnRedigérFad.setDisable(true);
            btnSletFad.setDisable(true);
        }

    }
    public void regFadAction() {
        RegFadWindow regFadWindow = new RegFadWindow("Opret Fad");
        regFadWindow.showAndWait();
    }
    public void redigerAction() {


    }
    public void sletAction() {
        Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmation.setTitle("Slettelse");
        alertConfirmation.setHeaderText("Er du sikker på at du vil slette fadet?");
        Optional<ButtonType> option = alertConfirmation.showAndWait();

        if (option.get() == null) {

        } else if (option.get() == ButtonType.OK) {
            Controller.deleteFad(lvwFad.getSelectionModel().getSelectedItem());
        } else if (option.get() == ButtonType.CANCEL) {

        }
    }
    public void opretWhiskyAction(){
        //TODO:
        //sæt if() ved markeringen, så knappen ikke kan trykkes
        //lav alert der bekræfter ændringen
        //sørg for man ikke kan trykke på knappen to gange
        if(lvwFad.getSelectionModel().getSelectedItem().getStatus() == Status.DESTILLAT){

        }

        Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmation.setTitle("Slettelse");
        alertConfirmation.setHeaderText("Er du sikker på at du vil konvertere fadets indhold fra destillat til Whisky?");
        Optional<ButtonType> option = alertConfirmation.showAndWait();

        if (option.get() == null) {

        } else if (option.get() == ButtonType.OK) {
            lvwFad.getSelectionModel().getSelectedItem().removeDestillat();
        } else if (option.get() == ButtonType.CANCEL) {

        }
    }

}
