package Gui;

import Application.Controller.Controller;
import Application.Model.Fad;
import Application.Model.Status;
import Storage.Storage;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Optional;

public class FadPane extends GridPane {

    private ListView<Fad> lvwFad;
    private Label lblTidligereIndhold, lblAntalGangeBrugt, lblLager, lblAlder, lblDestillat, lblStatus, lblTidligereDestil;
    private Label LblTidligereIndholdValue, lblAntalGangeBrugtValue, lblLagerValue, lblAlderValue, lblDestillatValue, lblStatusValue;
    private ListView lvwTidligereDestil;
    private VBox vbxInfo;
    private HBox hbxButtons;
    private Button btnRegFad, btnSletFad, btnRedigérFad, btnOpretWhisky;
    private Fad selectedFad;

    public FadPane() {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        this.initGUI();
        this.initData();

    }

    private void initGUI() {
        //kun labels til ting ikke I toString()
        //labelLager = "Lager - reol hylde hyldeplads
        //listview kun så stor som antallet af tidligere destillater

        lvwFad = new ListView<>();
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

        lblStatus.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblTidligereIndhold.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblLager.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblAlder.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblDestillat.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblTidligereDestil.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

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

        lvwFad.setPrefWidth(300);

        this.add(lvwFad, 0, 0);
        this.add(vbxInfo, 1, 0);
        this.add(hbxButtons, 0, 1, 2, 1);
        lvwFad.getItems().addAll(Storage.getFadArrayList());
    }

    private void initData() {

        if (selectedFad == null) {
            return;
        }
        LblTidligereIndholdValue.setText(selectedFad.getTidligereIndhold());
        lblAntalGangeBrugtValue.setText(String.valueOf(selectedFad.getAntalGangeBrugt()));
        //lblLagerValue.setText(selectedFad.getLager().toString());
        lblAlderValue.setText(String.valueOf(selectedFad.getAlder()));
        if (selectedFad.getDestillat() != null) {
            lblDestillatValue.setText("Destillat #" + String.valueOf(selectedFad.getDestillat().getDestillatNr()));
        }
        lblStatusValue.setText(selectedFad.getStatus().name());
        if (Controller.findLagerAfFad(selectedFad) != null) {
            lblLager.setText(Controller.findLagerAfFad(selectedFad).toString());
        }

    }

    private void selectedFadChanged() {

        if (lvwFad.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        if (lvwFad.getSelectionModel().getSelectedItem().getStatus() == Status.DESTILLAT && lvwFad.getSelectionModel().getSelectedItem() != null) {
            btnOpretWhisky.setDisable(false);
        } else {
            btnOpretWhisky.setDisable(true);
        }

        if (lvwFad.getSelectionModel().getSelectedItem() != null) {
            btnRedigérFad.setDisable(false);
            btnSletFad.setDisable(false);

            selectedFad = lvwFad.getSelectionModel().getSelectedItem();
            initData();

        } else {
            btnRedigérFad.setDisable(true);
            btnSletFad.setDisable(true);
        }
    }

    public void updateControls() {

    }

    public void regFadAction() {
        FadOpretWindow fadOpretWindow = new FadOpretWindow("Opret Fad");
        fadOpretWindow.showAndWait();
        lvwFad.getItems().clear();
        lvwFad.getItems().addAll(Storage.getFadArrayList());
    }

    public void redigerAction() {
        if (lvwFad.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        FadRedigerWindow fadRedigerWindow = new FadRedigerWindow(lvwFad.getSelectionModel().getSelectedItem());
        fadRedigerWindow.showAndWait();
        lvwFad.getItems().clear();
        lvwFad.getItems().addAll(Storage.getFadArrayList());
    }

    public void sletAction() {
        if (lvwFad.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmation.setTitle("Slettelse");
        alertConfirmation.setHeaderText("Er du sikker på at du vil slette fadet?");
        Optional<ButtonType> option = alertConfirmation.showAndWait();

        if (option.get() == null) {

        } else if (option.get() == ButtonType.OK) {
            Controller.deleteFad(lvwFad.getSelectionModel().getSelectedItem());
            lvwFad.getItems().clear();
            lvwFad.getItems().addAll(Storage.getFadArrayList());
        } else if (option.get() == ButtonType.CANCEL) {

        }
    }

    public void opretWhiskyAction() {
        if (lvwFad.getSelectionModel().getSelectedItem().getStatus() != Status.DESTILLAT) {
            return;
        }

        if (lvwFad.getSelectionModel().getSelectedItem().getDestillat().getDestillatAge() >= 3) {
            Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
            alertConfirmation.setTitle("Slettelse");
            alertConfirmation.setHeaderText("Er du sikker på at du vil konvertere fadets indhold fra destillat til Whisky?");
            Optional<ButtonType> option = alertConfirmation.showAndWait();

            if (option.get() == null) {

            } else if (option.get() == ButtonType.OK) {
                lvwFad.getSelectionModel().getSelectedItem().removeDestillat();
                lvwFad.getItems().clear();
                lvwFad.getItems().addAll(Storage.getFadArrayList());
            } else if (option.get() == ButtonType.CANCEL) {

            }
        } else {
                Alert alertIkkeMuligt = new Alert(Alert.AlertType.ERROR);
                alertIkkeMuligt.setTitle("Destillat ikke gammel nok");
                alertIkkeMuligt.setHeaderText("Destillat er ikke 3 år eller over og må derfor ikke kaldes whisky.");
                Optional<ButtonType> optional = alertIkkeMuligt.showAndWait();
            }
        }
}
