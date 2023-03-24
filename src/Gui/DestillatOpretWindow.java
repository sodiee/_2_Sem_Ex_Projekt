package Gui;

import Application.Controller.Controller;
import Application.Model.Destillat;
import Application.Model.Lager;
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
public class DestillatOpretWindow extends Stage{

    private Label lblMedarbejder, lblLiter, lblAlkPro, lblStartDato, lblSlutDato, lblKornSort, lblRygemateriale, lblBeskrivelse;
    private TextField txfMedarbejder, txfLiter, txfAlkPro, txfKornSort, txfRygemateriale, txfBeskrivelse;
    private DatePicker dpStartDato, dpSlutDato;
    private Button btnOpret, btnAnnuller;

    public DestillatOpretWindow(){
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle("Opret Destillat");
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        this.setScene(scene);
        //this.setHeight(300);
        this.initGUI(pane);
    }

    private void initGUI(GridPane pane){
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lblMedarbejder = new Label("Medarbejder");
        pane.add(lblMedarbejder,0,0);
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

        lblSlutDato = new Label("Slut Dato");
        pane.add(lblSlutDato, 0, 8);
        dpSlutDato = new DatePicker();
        pane.add(dpSlutDato, 0, 9);

        lblKornSort = new Label("Korn Sort");
        pane.add(lblKornSort, 0, 10);
        txfKornSort = new TextField();
        pane.add(txfKornSort, 0, 11);

        lblRygemateriale = new Label("Rygemateriale");
        pane.add(lblRygemateriale, 0, 12);
        txfRygemateriale = new TextField();
        pane.add(txfRygemateriale, 0, 13);

        lblBeskrivelse = new Label("Beskrivelse");
        pane.add(lblBeskrivelse, 0, 14);
        txfBeskrivelse = new TextField();
        pane.add(txfBeskrivelse, 0, 15);

        lblMedarbejder.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblLiter.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblAlkPro.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblStartDato.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblSlutDato.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblKornSort.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblRygemateriale.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        lblBeskrivelse.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        //Buttons
        HBox hbxButtons = new HBox(5);
        pane.add(hbxButtons, 0, 16);
        btnOpret = new Button("Godkend");
        btnOpret.setOnAction(event -> opretAction());
        hbxButtons.getChildren().add(btnOpret);
        btnAnnuller = new Button("Annuller");
        btnAnnuller.setOnAction(event -> annullerAction());

    }

    private void opretAction(){

        //Validering
        if(!Application.Controller.ValidationController.validateString(txfMedarbejder.getText(), "Medarbejder", true, false)) return;
        if(!Application.Controller.ValidationController.validateInt(txfLiter.getText(), "Liter")) return;
        if(!Application.Controller.ValidationController.validateDouble(txfAlkPro.getText(), "Alkohol Procent")) return;
        //TODO: Valider om dato er gyldig?
        if(!Application.Controller.ValidationController.validateString(txfKornSort.getText(), "Korn Sort", false, false)) return;
        if(txfRygemateriale.getLength() != 0){if(!Application.Controller.ValidationController.validateString(txfKornSort.getText(), "Korn Sort", false, false)) return;}
        if(!Application.Controller.ValidationController.validateString(txfBeskrivelse.getText(), "Beskrivelse", true, true)) return;

        //Hvis røg er angivet
        if(txfRygemateriale.getLength() != 0){
            Controller.createDestillatRøg(
                    txfMedarbejder.getText(),
                    Integer.parseInt(txfLiter.getText()),
                    Double.parseDouble(txfAlkPro.getText()),
                    dpStartDato.getValue(),
                    dpSlutDato.getValue(),
                    txfKornSort.getText(),
                    txfRygemateriale.getText(),
                    txfBeskrivelse.getText()
                    );

        }
        //Hvis røg ikke er angivet
        else if(txfRygemateriale.getLength() == 0){
            Controller.createDestillat(
                    txfMedarbejder.getText(),
                    Integer.parseInt(txfLiter.getText()),
                    Double.parseDouble(txfAlkPro.getText()),
                    dpStartDato.getValue(),
                    dpSlutDato.getValue(),
                    txfKornSort.getText(),
                    txfBeskrivelse.getText()
                    );
        }
        this.hide();
    }

    private void annullerAction(){
        this.hide();
    }
}
