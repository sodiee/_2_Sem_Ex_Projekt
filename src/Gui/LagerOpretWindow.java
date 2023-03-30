package Gui;

import Application.Controller.Controller;
import Application.Model.Lager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class LagerOpretWindow extends Stage{

    private Label lblAntalReoler, lblAntalHylderPrReol, lblAntalPladserPåHylde, lblAdresse;
    private TextField txfAntalReoler, txfAntalHylderPrReol, txfAntalPladserPåHylde, txfAdresse;
    private Button btnOk, btnCancel;

    public LagerOpretWindow(){
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.setTitle("Opret Lager");
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

        lblAntalReoler = new Label("Antal Reoler");
        lblAntalHylderPrReol = new Label("Antal Hylder pr. Reol");
        lblAntalPladserPåHylde = new Label("Antal Pladser på Hylder");
        lblAdresse = new Label("Adresse");
        txfAntalReoler = new TextField();
        txfAntalHylderPrReol = new TextField();
        txfAntalPladserPåHylde = new TextField();
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
        vbx.getChildren().addAll(lblAntalReoler,
                txfAntalReoler,
                lblAntalHylderPrReol,
                txfAntalHylderPrReol,
                lblAntalPladserPåHylde,
                txfAntalPladserPåHylde,
                lblAdresse,
                txfAdresse,
                hbx);

        pane.add(vbx, 0, 0);

    }
    private void btnOkAction(){
        //validate
        if(!Application.Controller.ValidationController.validateString(txfAdresse.getText(), "Adresse", true, true)) return;
        if(!Application.Controller.ValidationController.validateInt(txfAntalReoler.getText(), "Antal Reoler")) return;
        if(!Application.Controller.ValidationController.validateInt(txfAntalHylderPrReol.getText(), "Hylder pr. Reol")) return;
        if(!Application.Controller.ValidationController.validateInt(txfAntalPladserPåHylde.getText(), "Antal Hyldepladser")) return;

        Controller.createLager(
                Integer.valueOf(txfAntalReoler.getText()),
                Integer.valueOf(txfAntalHylderPrReol.getText()),
                Integer.valueOf(txfAntalPladserPåHylde.getText()),
                txfAdresse.getText());
        this.hide();

    }
    private void btnCancelAction(){
        this.hide();
    }
}
