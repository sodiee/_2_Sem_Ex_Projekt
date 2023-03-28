package Gui;

import Application.Controller.Controller;
import Application.Model.Destillat;
import Application.Model.Fad;
import Storage.Storage;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DestillatFærdiggørWindow extends Stage{

    private Destillat destillat;
    private ListView lvwFad;
    private Label lblResterende, lblResterendeValue;
    private Button btnGodkend;
    private ObservableList<Fad> selectedItems;
    private int resterendeLiter;
    private int fadeLiter = 0;

    public DestillatFærdiggørWindow(Destillat destillat){

        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.setTitle("Færdigør destillat nr: " + destillat.getDestillatNr());
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        this.setScene(scene);

        this.destillat = destillat;
        this.resterendeLiter = destillat.getLiter();
        this.initGUI(pane);
    }

    private void initGUI(GridPane pane){

        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lvwFad = new ListView<>();
        pane.add(lvwFad, 1, 1);

        for (Fad fad : Storage.getFadArrayList()){
            if(!fad.isiBrug()){
                lvwFad.getItems().add(fad);
            }
        }

        ChangeListener<Fad> listener1 = (ov, oldCompny, newCompany) -> this.selectedFadChanged();
        lvwFad.getSelectionModel().selectedItemProperty().addListener(listener1);
        lvwFad.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //TODO: klikker man bare på et element, eller skal man holde shift nede?

        lblResterende = new Label("Resterende Liter:");
        lblResterende.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblResterendeValue = new Label();
        lblResterendeValue.setText(String.valueOf(resterendeLiter));

        btnGodkend = new Button("Godkend");
        btnGodkend.setOnAction(event -> btnGodkendAction());
        btnGodkend.setDisable(true);

        VBox vbx1 = new VBox(5);
        vbx1.getChildren().add(lblResterende);
        vbx1.getChildren().add(lblResterendeValue);

        HBox hbx1 = new HBox(5);
        pane.add(hbx1, 1, 2);
        hbx1.getChildren().add(vbx1);
        hbx1.getChildren().add(btnGodkend);

    }

    private void selectedFadChanged(){
        //TODO: Kalder den korrekt, når der bliver valgt til eller fra?
        btnGodkend.setDisable(false);
        selectedItems = lvwFad.getSelectionModel().getSelectedItems();
        fadeLiter = 0;

        for (Fad fad : selectedItems){
            fadeLiter += fad.getStørrelseLiter();
        }

        lblResterendeValue.setText(String.valueOf(resterendeLiter-fadeLiter));
        if (resterendeLiter-fadeLiter < 0){
            lblResterendeValue.setStyle("-fx-text-fill: red;");
            btnGodkend.setDisable(true);
        }
        else if (resterendeLiter-fadeLiter > 0){
            lblResterendeValue.setStyle("-fx-text-fill: black;");
        }
    }
    private void btnGodkendAction(){

        Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmation.setTitle("Færdigør Destillat");
        alertConfirmation.setHeaderText("Er du sikker på at du vil færdigøre destilleringen med valgte fad, og " + (resterendeLiter-fadeLiter) + " Liter overflødigt?");

        Optional<ButtonType> option = alertConfirmation.showAndWait();
        if (resterendeLiter-fadeLiter < 0){return;}
        if (option.get() == null) {

        } else if (option.get() == ButtonType.OK) {

            for(Fad fad : selectedItems){
                fad.setiBrug(true);
                fad.setOpfyldtLiter(fad.getStørrelseLiter()); //PROBLEM HER, DEN BLIVER 0
                fad.setStatus(Status.DESTILLAT);
            }
            destillat.setDone(true);
            this.hide();

        } else if (option.get() == ButtonType.CANCEL) {

        } else {

        }

    }


}
