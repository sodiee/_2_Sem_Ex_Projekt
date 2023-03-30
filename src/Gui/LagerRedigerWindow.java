package Gui;

import Application.Controller.Controller;
import Application.Model.Lager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LagerRedigerWindow extends Stage {
    private Label lblAdresse;
    private TextField txfAdresse;
    private Button btnOk, btnCancel;
    private Lager lager;

    public LagerRedigerWindow(Lager lager){
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.setTitle("Rediger Lager");
        this.lager = lager;
        GridPane pane = new GridPane();
        this.initGUI(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);

    }
    public void initGUI(GridPane pane){
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lblAdresse = new Label("Adresse");

        txfAdresse = new TextField();

        btnOk = new Button("Godkend");
        pane.add(btnOk, 0, 0);
        btnOk.setOnAction(event -> btnOkAction());

        btnCancel = new Button("Annuller");
        pane.add(btnCancel, 1, 0);
        btnCancel.setOnAction(event -> btnCancelAction());

        HBox hbx = new HBox(50);
        hbx.getChildren().addAll(btnOk, btnCancel);

        VBox vbx = new VBox(5);
        vbx.getChildren().addAll(lblAdresse, txfAdresse, hbx);

        pane.add(vbx, 0, 0);

    }
    private void btnOkAction(){
        //validate
        if(!Application.Controller.ValidationController.validateString(txfAdresse.getText(), "Adresse", true, true)) return;
        lager.setAdresse(txfAdresse.getText());

        this.hide();

    }
    private void btnCancelAction(){
        this.hide();
    }
}
