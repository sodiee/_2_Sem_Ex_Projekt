package Gui;

import Application.Model.Fad;
import Application.Model.Status;
import Storage.Storage;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;

public class FadMixWindow extends Stage {
    //region Global Variables
    private Fad fad;
    private ListView<Fad> lvwFad;
    private Label lblResterende, lblResterendeValue, lblInput;
    private TextField txfInput;
    private Button btnGodkend;
    private double resterendeLiter;
    //endregion
    public FadMixWindow(Fad fad){
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.setTitle("Hæld Indhold på andet fad");
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        this.setScene(scene);

        this.fad = fad;
        this.resterendeLiter = fad.getOpfyldtLiter();
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

            lvwFad.getItems().add(fad);

        }

        ChangeListener<Fad> listener1 = (ov, oldCompny, newCompany) -> this.selectedFadChanged();
        ChangeListener<String> listener2 = (ov, oldCompny, newCompany) -> this.inputChanged();
        lvwFad.getSelectionModel().selectedItemProperty().addListener(listener1);
        lvwFad.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);



        lblInput = new Label("Antal Liter til overførelse");
        lblInput.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        txfInput = new TextField();
        txfInput.textProperty().addListener(listener2);
        VBox vbx = new VBox(5);
        vbx.getChildren().addAll(lblInput, txfInput);
        pane.add(vbx, 1, 2);

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
        pane.add(hbx1, 1, 3);
        hbx1.getChildren().add(vbx1);
        hbx1.getChildren().add(btnGodkend);

    }
    private void inputChanged(){
        if(!txfInput.getText().isEmpty()){
            resterendeLiter = fad.getOpfyldtLiter() - Double.parseDouble(txfInput.getText());
            lblResterendeValue.setText(String.valueOf(resterendeLiter));
        }
        btnGodkend.setDisable(false);
        if(resterendeLiter <= 0){
            btnGodkend.setDisable(true);
        }
    }

    private void selectedFadChanged(){

    }

    private void btnGodkendAction(){

        //TODO: Hæld whiskien over på et andet fad

            this.hide();

    }

}
