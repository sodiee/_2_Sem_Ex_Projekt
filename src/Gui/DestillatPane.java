package Gui;

import Application.Controller.Controller;
import Application.Model.Destillat;
import Application.Model.Fad;
import Application.Model.Lager;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private Label lblNr, lblMedarbejder, lblLiter, lblAlkPro, lblStartDato, lblKornSort, lblRygemateriale, lblBeskrivelse, lblIsDone;
    private Label lblNr2, lblMedarbejder2, lblLiter2, lblAlkPro2, lblStartDato2, lblKornSort2, lblRygemateriale2, lblBeskrivelse2, lblIsDone2;
    private ListView<Destillat> lvwDestilleringer;
    private Button btnOpretDestillering, btnRedigerDestillering, btnSletDestillering, btnDone;
    private ComboBox cbxStatus;
    private ObservableList<String> cbxValues = FXCollections.observableArrayList("Klar til Fadpåfyldning", "Historiske Destillater", "Alle");

    public DestillatPane() {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        initGUI();

    }

    //TODO: Gør således at beskrivelsesfeltet laver ny linje, i stedet for at køre uendeligt til højre
    private void initGUI(){

        cbxStatus = new ComboBox(cbxValues);
        this.add(cbxStatus, 0, 0);
        ChangeListener<String> listener = (ov, oldCompany, newCompany) -> this.selectedStateChanged();
        cbxStatus.getSelectionModel().selectedItemProperty().addListener(listener);


        lvwDestilleringer = new ListView();
        this.add(lvwDestilleringer, 0, 1);
        ChangeListener<Destillat> listener1 = (ov, oldCompny, newCompany) -> this.selectedDestillatChanged();
        lvwDestilleringer.getSelectionModel().selectedItemProperty().addListener(listener1);
        cbxStatus.getSelectionModel().selectFirst();

        //Buttons
        btnOpretDestillering = new Button("Opret Ny");
        btnRedigerDestillering = new Button("Redigér");
        btnSletDestillering = new Button("Slet");
        btnDone = new Button("Hæld på fad");
        btnOpretDestillering.setOnAction(event -> btnOpretAction());
        btnRedigerDestillering.setOnAction(event -> btnRedigerAction());
        btnSletDestillering.setOnAction(event -> btnSletAction());
        btnDone.setOnAction(event -> btnDoneAction());
        btnOpretDestillering.setDisable(true);
        btnRedigerDestillering.setDisable(true);
        btnSletDestillering.setDisable(true);
        btnDone.setDisable(false);

        HBox hbxButtons = new HBox(5);
        this.add(hbxButtons, 0, 3);
        hbxButtons.getChildren().add(btnOpretDestillering);
        hbxButtons.getChildren().add(btnRedigerDestillering);
        hbxButtons.getChildren().add(btnSletDestillering);
        hbxButtons.getChildren().add(btnDone);

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

        lblMedarbejder.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblLiter.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblAlkPro.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblStartDato.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblKornSort.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblRygemateriale.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblBeskrivelse.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblNr.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblIsDone.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

        lvwDestilleringer.getSelectionModel().selectFirst();
    }

    private void selectedDestillatChanged(){this.updateControls();}

    private void selectedStateChanged(){

        if(lvwDestilleringer != null){
            lvwDestilleringer.getItems().clear();
        }


        if(cbxStatus.getSelectionModel().getSelectedItem() == "Historiske Destillater"){
            for(Destillat destillat : Controller.getDestillat()){
                if(destillat.isDone()){
                    lvwDestilleringer.getItems().add(destillat);
                }
            }
        }
        if(cbxStatus.getSelectionModel().getSelectedItem() == "Klar til Fadpåfyldning"){
            for(Destillat destillat : Controller.getDestillat()){
                if(!destillat.isDone()){
                    lvwDestilleringer.getItems().add(destillat);
                }
            }
        }
        if(cbxStatus.getSelectionModel().getSelectedItem() == "Alle"){
            lvwDestilleringer.getItems().addAll(Controller.getDestillat());
        }
    }

    public void updateControls(){

        Destillat destillat = lvwDestilleringer.getSelectionModel().getSelectedItem();

        //indsæt information omkring valgte destillat
        if (destillat != null){
            lblNr2.setText(String.valueOf(destillat.getObjectNummer()));
            lblMedarbejder2.setText(destillat.getMedarbejder());
            lblLiter2.setText(String.valueOf(destillat.getLiter()));
            lblAlkPro2.setText(String.valueOf(destillat.getAlkoholProcent()));
            lblStartDato2.setText(String.valueOf(destillat.getDatoForPåhldningPåFad()));
            lblKornSort2.setText(destillat.getKornSort());
            if(destillat.getRygeMateriale() != null){
                lblRygemateriale2.setText(destillat.getRygeMateriale());
            }
            else{
                lblRygemateriale2.setText("...");
            }
            lblBeskrivelse2.setText(destillat.getBeskrivelse());
            lblIsDone2.setText(String.valueOf(destillat.isDone()));
        }

        //aktiver buttons, da de er disabled fra start
        btnSletDestillering.setDisable(false);
        btnRedigerDestillering.setDisable(false);
        btnOpretDestillering.setDisable(false);

        //Gør således man ikke kan trykke "Færdiggør" eller "Redigér" på et destillat der allerede er fordelt på fad
        //TODO: skal man ikke kunne slette færdiggørede destillater?
        /**if (!destillat.isDone() && destillat != null){
            btnDone.setDisable(true);
            btnRedigerDestillering.setDisable(true);
        }
        else if (destillat.isDone()){
            btnDone.setDisable(false);
            btnRedigerDestillering.setDisable(false);
        }**/
    }


    //buttonAction
    private void btnOpretAction(){

        DestillatOpretWindow destillatOpretWindow = new DestillatOpretWindow();
        destillatOpretWindow.showAndWait();
        lvwDestilleringer.getItems().setAll(Controller.getDestillat());
    }
    private void btnRedigerAction(){

        Destillat destillat = lvwDestilleringer.getSelectionModel().getSelectedItem();
        if(destillat == null){return;}
        DestillatRedigerWindow destillatRedigerWindow = new DestillatRedigerWindow(destillat);
        destillatRedigerWindow.showAndWait();
        lvwDestilleringer.getItems().setAll(Controller.getDestillat());
    }
    private void btnSletAction(){
        Destillat destillat = lvwDestilleringer.getSelectionModel().getSelectedItem();
        if(destillat == null){return;}
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
        //TODO: Evt gøre så "færdgjort bliver rettet uden at skulle markere igen
        Destillat destillat = lvwDestilleringer.getSelectionModel().getSelectedItem();
        if(destillat == null){return;}
        if(!destillat.isDone()){
            DestillatFærdiggørWindow destillatFærdiggørWindow = new DestillatFærdiggørWindow(destillat);
            destillatFærdiggørWindow.showAndWait();
            //lvwDestilleringer.getItems().setAll(Controller.getDestillat());
            updateControls();
            selectedStateChanged();
            lvwDestilleringer.getSelectionModel().selectFirst();

        } else if (destillat.isDone()) {
            Alert alertIntEmpty = new Alert(Alert.AlertType.ERROR);
            alertIntEmpty.setTitle("Allerede færdigjordt");
            alertIntEmpty.setHeaderText("Destillat #" + destillat.getDestillatNr() + " Er allerede færdigjordt og indeholder ikke spiritus");
            alertIntEmpty.showAndWait();
        }
    }
}
