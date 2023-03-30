package Gui;

import Application.Model.Whisky;
import Application.Model.WhiskyPåFlaske;
import Storage.Storage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class WhiskeyPane extends BorderPane {

    private Label lblTitle;
    private ComboBox<Whisky> cbxProdukt;
    private Image image1, image2, image3, image4, image5;
    private ImageView imageView;

    //VenstreInfo
    private Label lblNummer, lblTotalNummer, lblFortyndelse, lblLiter;
    private Label lblNummerValue, lblTotalNummerValue, lblFortyndelseValue, lblLiterValue;
    //HøjreInfo
    private Label lblWhisky, lblAlkPro, lblBeskrivelse, lblLager;
    private Label lblWhiskyValue, lblAlkProValue, lblBeskrivelseValue, lblLagerValue;
    private VBox vbxLeft, vbxRight, vbxCenter, vbxCombine;
    private HBox hbxInfo, hbxButtons;
    private Button btnAftap, btnRediger, btnSlet, btnSeAlle;

    public WhiskeyPane(){
        this.setPadding(new Insets(10));

        this.initGUI();
        this.initData();
        //this.getCenter().prefHeight(400);
    }

    private void initGUI(){

        try{
            InputStream stream = new FileInputStream("resources/WhiskyIcons/Whisky1.png");
            image1 = new Image(stream);
            InputStream stream2 = new FileInputStream("resources/WhiskyIcons/Whisky2.png");
            image2 = new Image(stream2);
            InputStream stream3 = new FileInputStream("resources/WhiskyIcons/Whisky3.png");
            image3 = new Image(stream3);
            InputStream stream4 = new FileInputStream("resources/WhiskyIcons/Whisky4.png");
            image4 = new Image(stream4);
            InputStream stream5 = new FileInputStream("resources/WhiskyIcons/Whisky5.png");
            image5 = new Image(stream5);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
        lblTitle = new Label("Flaskens Navn?");
        this.setTop(lblTitle);
        BorderPane.setAlignment(lblTitle, Pos.CENTER);
        **/

        imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);

        cbxProdukt = new ComboBox();
        vbxCenter = new VBox(10);
        vbxCenter.getChildren().addAll(imageView, cbxProdukt);
        this.setCenter(vbxCenter);
        vbxCenter.setAlignment(Pos.CENTER);
        //vbxCenter.setPrefHeight(500);

        lblNummer = new Label("N/A");
        lblTotalNummer = new Label("Antal Flasker");
        lblFortyndelse = new Label("Fortyndelse i ml");
        lblLiter = new Label("Flaske Liter");
        lblNummerValue  = new Label();
        lblTotalNummerValue = new Label();
        lblFortyndelseValue = new Label();
        lblLiterValue = new Label();
        lblWhisky = new Label("Whisky / Fad?");
        lblWhiskyValue = new Label();
        lblAlkPro = new Label("Alkohol Procent");
        lblAlkProValue = new Label();
        lblBeskrivelse = new Label("Beskrivelse");
        lblBeskrivelseValue = new Label();
        lblLager = new Label("Lager");
        lblLagerValue = new Label();

        vbxLeft = new VBox(5);
        vbxLeft.getChildren().addAll(lblNummer, lblNummerValue, lblTotalNummer, lblTotalNummerValue, lblFortyndelse, lblFortyndelseValue, lblLiter, lblLiterValue);
        vbxRight = new VBox(5);
        vbxRight.getChildren().addAll(lblWhisky, lblWhiskyValue, lblAlkPro, lblAlkProValue, lblBeskrivelse, lblBeskrivelseValue, lblLager, lblLagerValue);
        hbxInfo = new HBox(150);
        hbxInfo.getChildren().addAll(vbxLeft, vbxRight);

        btnAftap = new Button("Aftap Whisky");
        btnAftap.setOnAction(event -> aftapAction());
        btnRediger = new Button("Redigér Whisky");
        btnRediger.setOnAction(event -> redigerAction());
        btnSlet = new Button("Slet Whisky");
        btnSlet.setOnAction(event -> sletAction());
        btnSeAlle = new Button("Flaskeoversigt");

        hbxButtons = new HBox(10);
        hbxButtons.getChildren().addAll(btnAftap, btnRediger, btnSlet);

        vbxCombine = new VBox(10);
        vbxCombine.getChildren().addAll(hbxInfo, hbxButtons);

        BorderPane.setAlignment(vbxCombine, Pos.CENTER);
        vbxCombine.setAlignment(Pos.CENTER);
        hbxButtons.setAlignment(Pos.CENTER);
        hbxInfo.setAlignment(Pos.CENTER);

        this.setBottom(vbxCombine);

        lblNummer.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblTotalNummer.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblFortyndelse.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblLiter.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

        lblWhisky.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblAlkPro.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblBeskrivelse.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblLager.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

    }
    private void initData(){

        cbxProdukt.getItems().setAll(Storage.getWhiskyArrayList());
        cbxProdukt.getSelectionModel().selectFirst();

        //tag whiskiens nummer 1-5, derefter reset int og tag billeder 1-5 igen
        ObservableList<Image> images = FXCollections.observableArrayList(image1, image2, image3, image4, image5);

        int imgselect = cbxProdukt.getSelectionModel().getSelectedIndex();
        imageView.setImage(images.get(imgselect));
        cbxProdukt.getSelectionModel().selectFirst();

        WhiskyPåFlaske whiskyPåFlaske = cbxProdukt.getSelectionModel().getSelectedItem().getFlasker().get(0);
        //lblNummerValue.setText(String.valueOf(whiskyPåFlaske.getNummer()));
        lblTotalNummerValue.setText(String.valueOf(whiskyPåFlaske.getTotalNummer()));
        lblFortyndelseValue.setText(String.valueOf(whiskyPåFlaske.getFortyndelseIML()));
        lblLiterValue.setText(String.valueOf(whiskyPåFlaske.getLiter()));
        lblWhiskyValue.setText(whiskyPåFlaske.getWhisky().toString());
        lblAlkProValue.setText(String.valueOf(whiskyPåFlaske.getWhisky().getAlkoholProcent()));
        lblBeskrivelseValue.setText(whiskyPåFlaske.getWhisky().getBeskrivelse());
        if(whiskyPåFlaske.getLager() != null){lblLagerValue.setText(whiskyPåFlaske.getLager().toString());}

    }
    private void aftapAction(){
        WhiskeyPåFlaskeOpretWindow whiskeyPåFlaskeOpretWindow = new WhiskeyPåFlaskeOpretWindow();
        whiskeyPåFlaskeOpretWindow.showAndWait();
    }
    private void redigerAction(){
        if(cbxProdukt.getSelectionModel().getSelectedItem() == null){return;}
        Whisky whisky = cbxProdukt.getSelectionModel().getSelectedItem();
        WhiskeyRedigerWindow whiskeyRedigerWindow = new WhiskeyRedigerWindow(whisky);
        whiskeyRedigerWindow.showAndWait();
    }
    private void sletAction(){
        //TODO:
    }

    private void seAlleAction(){

    }
    public void updateControls(){
        cbxProdukt.getItems().setAll(Storage.getWhiskyArrayList().toArray(new Whisky[0]));
    }
}
