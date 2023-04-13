package Gui;

import Application.Controller.Controller;
import Application.Model.Lager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
        String stringValue = txfAdresse.getText();
        String fieldName = lblAdresse.getText();
        Boolean allowSpace = true;
        Boolean allowSymbol = true;

        Application.Controller.ValidationController.validateStringException(stringValue, fieldName, allowSpace, allowSymbol);
            validateString(stringValue, fieldName, allowSpace, allowSymbol);

        lager.setAdresse(txfAdresse.getText());

        this.hide();

    }
    private void btnCancelAction(){
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
}
