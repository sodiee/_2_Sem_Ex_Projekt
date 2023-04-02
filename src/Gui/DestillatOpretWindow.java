package Gui;

import Application.Controller.Controller;
import Application.Controller.ValidationController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DestillatOpretWindow extends Stage {

    private Label lblMedarbejder, lblLiter, lblAlkPro, lblStartDato, lblKornSort, lblRygemateriale, lblBeskrivelse;
    private TextField txfMedarbejder, txfLiter, txfAlkPro, txfKornSort, txfRygemateriale, txfBeskrivelse;
    private DatePicker dpStartDato;
    private Button btnOpret, btnAnnuller;

    public DestillatOpretWindow() {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle("Opret Destillat");
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        this.setScene(scene);
        this.setHeight(520);
        this.initGUI(pane);
    }

    private void initGUI(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        txfBeskrivelse = new TextField();
        pane.add(txfBeskrivelse, 0, 13);

        //region Label
        lblMedarbejder = new Label("Medarbejder");
        pane.add(lblMedarbejder, 0, 0);
        txfMedarbejder = new TextField();
        pane.add(txfMedarbejder, 0, 1);

        lblLiter = new Label("Liter");
        pane.add(lblLiter, 0, 2);
        txfLiter = new TextField();
        pane.add(txfLiter, 0, 3);

        lblAlkPro = new Label("Alkohol Procent");
        pane.add(lblAlkPro, 0, 4);
        txfAlkPro = new TextField();
        pane.add(txfAlkPro, 0, 5);

        lblStartDato = new Label("Start Dato");
        pane.add(lblStartDato, 0, 6);
        dpStartDato = new DatePicker();
        pane.add(dpStartDato, 0, 7);

        lblKornSort = new Label("Korn Sort");
        pane.add(lblKornSort, 0, 8);
        txfKornSort = new TextField();
        pane.add(txfKornSort, 0, 9);

        lblRygemateriale = new Label("Rygemateriale");
        pane.add(lblRygemateriale, 0, 10);
        txfRygemateriale = new TextField();
        pane.add(txfRygemateriale, 0, 11);

        lblBeskrivelse = new Label("Beskrivelse");
        pane.add(lblBeskrivelse, 0, 12);

        lblMedarbejder.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblLiter.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblAlkPro.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblStartDato.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblKornSort.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblRygemateriale.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblBeskrivelse.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        //endregion

        //region Button
        HBox hbxButtons = new HBox(5);
        pane.add(hbxButtons, 0, 16);
        btnOpret = new Button("Godkend");
        btnOpret.setOnAction(event -> opretAction());
        hbxButtons.getChildren().add(btnOpret);
        btnAnnuller = new Button("Annuller");
        btnAnnuller.setOnAction(event -> annullerAction());
        //endregion

    }

    private void opretAction() {
        String stringValue = txfMedarbejder.getText();
        String fieldName = lblMedarbejder.getText();
        Boolean allowSpace = true;
        Boolean allowSymbol = false;
        Boolean exceptionCatched = false;

        try {
            Application.Controller.ValidationController.validateStringException(stringValue, fieldName, allowSpace, allowSymbol);
        } catch (Exception e) {
            validateString(stringValue, fieldName, allowSpace, allowSymbol);
            exceptionCatched = true;
        }

        stringValue = txfLiter.getText();
        fieldName = lblLiter.getText();

        try {
            Application.Controller.ValidationController.validateIntException(stringValue, fieldName);
        } catch (Exception e) {
            validateInt(stringValue, fieldName);
            exceptionCatched = true;
        }

        stringValue = txfAlkPro.getText();
        fieldName = lblAlkPro.getText();

        try {
            Application.Controller.ValidationController.validateDoubleException(stringValue, fieldName);
        } catch (Exception e) {
            validateDouble(stringValue, fieldName);
            exceptionCatched = true;
        }

        stringValue = txfKornSort.getText();
        fieldName = lblKornSort.getText();
        allowSpace = false;
        allowSymbol = false;

        try {
            Application.Controller.ValidationController.validateStringException(stringValue, fieldName, allowSpace, allowSymbol);
        } catch (Exception e) {
            validateString(stringValue, fieldName, allowSpace, allowSymbol);
            exceptionCatched = true;
        }
        stringValue = txfRygemateriale.getText();
        fieldName = lblRygemateriale.getText();
        allowSpace = false;
        allowSymbol = false;

        if (txfRygemateriale.getLength() != 0) {
            try {
                Application.Controller.ValidationController.validateStringException(stringValue, fieldName, allowSpace, allowSymbol);
            } catch (Exception e) {
                validateString(stringValue, fieldName, allowSpace, allowSymbol);
                exceptionCatched = true;
            }
            stringValue = txfBeskrivelse.getText();
            fieldName = lblBeskrivelse.getText();
            allowSpace = true;
            allowSymbol = true;

            try {
                Application.Controller.ValidationController.validateStringException(stringValue, fieldName, allowSpace, allowSymbol);
            } catch (IllegalArgumentException e) {
                validateString(stringValue, fieldName, allowSpace, allowSymbol);
                exceptionCatched = true;
            }

            if (!exceptionCatched) {
                //Hvis røg er angivet
                if (txfRygemateriale.getLength() != 0) {
                    Controller.createDestillat(
                            txfMedarbejder.getText(),
                            Integer.parseInt(txfLiter.getText()),
                            Double.parseDouble(txfAlkPro.getText()),
                            dpStartDato.getValue(),
                            txfKornSort.getText(),
                            txfRygemateriale.getText(),
                            txfBeskrivelse.getText());
                }
                //Hvis røg ikke er angivet
                else if (txfRygemateriale.getLength() == 0) {
                    Controller.createDestillat(
                            txfMedarbejder.getText(),
                            Integer.parseInt(txfLiter.getText()),
                            Double.parseDouble(txfAlkPro.getText()),
                            dpStartDato.getValue(),
                            txfKornSort.getText(),
                            txfBeskrivelse.getText(),
                            txfRygemateriale.getText());
                }
                this.hide();
            }
        }

    }

    private void annullerAction() {
        this.hide();
    }

    private static void validateString(String stringValue, String fieldName, Boolean allowSpace, Boolean allowSymbol) {
        //Hvis strengen er tom
        if (stringValue.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Tomt felt");
            alert.setHeaderText(fieldName + " er tom!");
            alert.showAndWait();
        } else {
            char[] chars = stringValue.toCharArray();
            for (char c : chars) {
                //Hvis strengen indeholder et tal
                if (Character.isDigit(c)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ikke Gyldigt Input");
                    alert.setHeaderText(fieldName + " må kun indeholde bogstaver");
                    alert.showAndWait();
                    return;
                }
                //Hvis strengen indeholder et mellemrum
                if (c == ' ' && allowSpace == false) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ikke Gyldigt Input");
                    alert.setHeaderText(fieldName + " må ikke indeholde mellemrum");
                    alert.showAndWait();
                    return;
                }
                //Hvis strengen indeholder et symbol/tegn
                if (!stringValue.matches("^[A-Za-z0-9 ]*$") && allowSymbol == false) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ikke Gyldigt Input");
                    alert.setHeaderText(fieldName + " må ikke indeholde symboler/tegn");
                    alert.showAndWait();
                    return;
                }
            }
        }
    }

    public static void validateInt(String stringValue, String fieldName) {

        //Hvis tallet er tomt
        if (stringValue.length() == 0) {
            Alert alertIntEmpty = new Alert(Alert.AlertType.ERROR);
            alertIntEmpty.setTitle("Empty Field");
            alertIntEmpty.setHeaderText(fieldName + " is Empty!");
            alertIntEmpty.showAndWait();
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
                return;
            }
            //Hvis det er et negativt tal
            if (value < 0) {
                Alert alertIntNegative = new Alert(Alert.AlertType.ERROR);
                alertIntNegative.setTitle("Negative Number");
                alertIntNegative.setHeaderText(fieldName + " cannot be a negative number");
                alertIntNegative.showAndWait();
            } else {

            }

        }
    }

    public static void validateDouble(String stringValue, String fieldName) {

        if (stringValue.length() == 0) {
            Alert alertIntEmpty = new Alert(Alert.AlertType.ERROR);
            alertIntEmpty.setTitle("Empty Field");
            alertIntEmpty.setHeaderText(fieldName + " is Empty!");
            alertIntEmpty.showAndWait();
        } else {
            double value = -1;
            try {
                value = Double.parseDouble(stringValue.trim());
            } catch (NumberFormatException exception) {
                Alert alertDoubleNotNr = new Alert(Alert.AlertType.ERROR);
                alertDoubleNotNr.setTitle("Not a number");
                alertDoubleNotNr.setHeaderText(fieldName + " is not a number");
                alertDoubleNotNr.showAndWait();
                return;
            }
            if (value < 0) {
                Alert alertDoubleNegative = new Alert(Alert.AlertType.ERROR);
                alertDoubleNegative.setTitle("Negative Number");
                alertDoubleNegative.setHeaderText(fieldName + " cannot be a negative number");
                alertDoubleNegative.showAndWait();
            } else {

            }

        }
    }
}
