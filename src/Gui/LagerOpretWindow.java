package Gui;

import Application.Controller.Controller;
import Application.Controller.ValidationController;
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

public class LagerOpretWindow extends Stage {

    private Label lblAntalReoler, lblAntalHylderPrReol, lblAntalPladserPåHylde, lblAdresse;
    private TextField txfAntalReoler, txfAntalHylderPrReol, txfAntalPladserPåHylde, txfAdresse;
    private Button btnOk, btnCancel;

    public LagerOpretWindow() {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.setTitle("Opret Lager");
        GridPane pane = new GridPane();
        this.initGUI(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public void initGUI(GridPane pane) {
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

    private void btnOkAction() {
        //validate
        String stringValue = txfAdresse.getText();
        String fieldName = lblAdresse.getText();
        Boolean allowSpace = true;
        Boolean allowSymbol = true;
        Boolean exceptionCatched = false;

        try {
            ValidationController.validateStringException(stringValue, fieldName, allowSpace, allowSymbol);
        } catch (Exception e) {
            validateString(stringValue, fieldName, allowSpace, allowSymbol);
            exceptionCatched = true;
        }
        stringValue = txfAntalReoler.getText();
        fieldName = lblAntalReoler.getText();

        try {
            Application.Controller.ValidationController.validateIntException(stringValue, fieldName);
        } catch (Exception e) {
            validateInt(stringValue, fieldName);
            exceptionCatched = true;
        }

        stringValue = txfAntalHylderPrReol.getText();
        fieldName = lblAntalHylderPrReol.getText();

        try {
            Application.Controller.ValidationController.validateIntException(stringValue, fieldName);
        } catch (Exception e) {
            validateInt(stringValue, fieldName);
            exceptionCatched = true;
        }

        stringValue = txfAntalPladserPåHylde.getText();
        fieldName = lblAntalPladserPåHylde.getText();

        try {
            Application.Controller.ValidationController.validateIntException(stringValue, fieldName);
        } catch (Exception e) {
            validateInt(stringValue, fieldName);
            exceptionCatched = true;
        }

        if (!exceptionCatched) {

           Lager lager =  Controller.createLager(
                    Integer.valueOf(txfAntalReoler.getText()),
                    Integer.valueOf(txfAntalHylderPrReol.getText()),
                    Integer.valueOf(txfAntalPladserPåHylde.getText()),
                    txfAdresse.getText());

            this.hide();
        }

    }

    private void btnCancelAction() {
        this.hide();
    }

    private static Boolean validateString(String stringValue, String fieldName, Boolean allowSpace, Boolean allowSymbol) {

        //Hvis strengen er tom
        if (stringValue.length() == 0) {
            Alert alertIntEmpty = new Alert(Alert.AlertType.ERROR);
            alertIntEmpty.setTitle("Tomt felt");
            alertIntEmpty.setHeaderText(fieldName + " er tom!");
            alertIntEmpty.showAndWait();
            return false;
        } else {

            char[] chars = stringValue.toCharArray();
            for (char c : chars) {
                //Hvis strengen indeholder et tal
                if (Character.isDigit(c)) {
                    Alert alertIntEmpty = new Alert(Alert.AlertType.ERROR);
                    alertIntEmpty.setTitle("Ikke Gyldigt Input");
                    alertIntEmpty.setHeaderText(fieldName + " må kun indeholde bogstaver");
                    alertIntEmpty.showAndWait();
                    return false;
                }
                //Hvis strengen indeholder et mellemrum
                if (c == ' ' && allowSpace == false) {
                    Alert alertIntEmpty = new Alert(Alert.AlertType.ERROR);
                    alertIntEmpty.setTitle("Ikke Gyldigt Input");
                    alertIntEmpty.setHeaderText(fieldName + " må ikke indeholde mellemrum");
                    alertIntEmpty.showAndWait();
                    return false;
                }
                //Hvis strengen indeholder et symbol/tegn
                if (!stringValue.matches("^[A-Za-z0-9 ]*$") && allowSymbol == false) {
                    Alert alertIntEmpty = new Alert(Alert.AlertType.ERROR);
                    alertIntEmpty.setTitle("Ikke Gyldigt Input");
                    alertIntEmpty.setHeaderText(fieldName + " må ikke indeholde symboler/tegn");
                    alertIntEmpty.showAndWait();
                    return false;
                }
            }
        }
        return true;
    }

    public static Boolean validateInt(String stringValue, String fieldName) {

        //Hvis tallet er tomt
        if (stringValue.length() == 0) {
            Alert alertIntEmpty = new Alert(Alert.AlertType.ERROR);
            alertIntEmpty.setTitle("Empty Field");
            alertIntEmpty.setHeaderText(fieldName + " is Empty!");
            alertIntEmpty.showAndWait();
            return false;
        } else {
            int value = -1;
            try {
                value = Integer.parseInt(stringValue.trim());
            }
            //Hvis det ikke er et tal
            catch (NumberFormatException exception) {
                Alert alertIntNotNr = new Alert(Alert.AlertType.ERROR);
                alertIntNotNr.setTitle("Not a number");
                alertIntNotNr.setHeaderText(fieldName + " is not a number");
                alertIntNotNr.showAndWait();
                return false;
            }
            //Hvis det er et negativt tal
            if (value < 0) {
                Alert alertIntNegative = new Alert(Alert.AlertType.ERROR);
                alertIntNegative.setTitle("Negative Number");
                alertIntNegative.setHeaderText(fieldName + " cannot be a negative number");
                alertIntNegative.showAndWait();
                return false;
            } else {

            }

        }
        return true;
    }
}
