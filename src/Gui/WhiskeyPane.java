package Gui;

import Application.Model.Whisky;
import Application.Model.WhiskyPåFlaske;
import Storage.Storage;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class WhiskeyPane extends BorderPane {
    //region Global Variables
    private ComboBox<Whisky> cbxWhisky;
    private Image image1, image2, image3, image4, image5;
    private ImageView imageView;
    private Label lblResterende, lblTotalNummer, lblFortyndelse, lblLiter;
    private Label lblResterendeValue, lblTotalNummerValue, lblFortyndelseValue, lblLiterValue;
    private Label lblWhisky, lblAlkPro, lblBeskrivelse, lblLager;
    private Label lblWhiskyValue, lblAlkProValue, lblBeskrivelseValue, lblLagerValue;
    private VBox vbxLeft, vbxRight, vbxCenter, vbxCombine;
    private HBox hbxInfo, hbxButtons;
    private Button btnAftap, btnRediger, btnSlet;
    //endregion
    public WhiskeyPane(){
        this.setPadding(new Insets(10));
        this.initGUI();
        this.initData();
    }
    private void initGUI(){
        //region Image & ComboBox
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
        imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);
        cbxWhisky = new ComboBox();
        ChangeListener<Whisky> listener = (ov, oldCompany, newCompany) -> this.selectedWhiskyChanged();
        cbxWhisky.getSelectionModel().selectedItemProperty().addListener(listener);
        vbxCenter = new VBox(10);
        vbxCenter.getChildren().addAll(imageView, cbxWhisky);
        this.setCenter(vbxCenter);
        vbxCenter.setAlignment(Pos.CENTER);
        //endregion
        //region Label
        lblResterende = new Label("Liter Tilbage");
        lblTotalNummer = new Label("Antal Flasker");
        lblFortyndelse = new Label("Fortyndelse i ml");
        lblLiter = new Label("Flaske Liter");
        lblResterendeValue = new Label();
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

        lblResterende.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblTotalNummer.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblFortyndelse.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblLiter.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblWhisky.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblAlkPro.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblBeskrivelse.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblLager.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        //endregion
        //region Buttons
        btnAftap = new Button("Aftap Whisky");
        btnAftap.setOnAction(event -> aftapAction());
        btnRediger = new Button("Redigér Whisky");
        btnRediger.setOnAction(event -> redigerAction());
        btnSlet = new Button("Slet Whisky");
        btnSlet.setOnAction(event -> sletAction());

        hbxButtons = new HBox(10);
        hbxButtons.getChildren().addAll(btnAftap, btnRediger, btnSlet);
        //endregion
        //region Vbox & Hbox
        vbxLeft = new VBox(5);
        vbxLeft.getChildren().addAll(lblResterende, lblResterendeValue, lblTotalNummer, lblTotalNummerValue, lblFortyndelse, lblFortyndelseValue, lblLiter, lblLiterValue);
        vbxRight = new VBox(5);
        vbxRight.getChildren().addAll(lblWhisky, lblWhiskyValue, lblAlkPro, lblAlkProValue, lblBeskrivelse, lblBeskrivelseValue, lblLager, lblLagerValue);
        hbxInfo = new HBox(150);
        hbxInfo.getChildren().addAll(vbxLeft, vbxRight);
        vbxCombine = new VBox(10);
        vbxCombine.getChildren().addAll(hbxInfo, hbxButtons);
        BorderPane.setAlignment(vbxCombine, Pos.CENTER);
        vbxCombine.setAlignment(Pos.CENTER);
        hbxButtons.setAlignment(Pos.CENTER);
        hbxInfo.setAlignment(Pos.CENTER);
        this.setBottom(vbxCombine);
        //endregion
    }
    private void initData(){

        cbxWhisky.getItems().setAll(Storage.getWhiskyArrayList());
        cbxWhisky.getSelectionModel().selectFirst();

        //tag whiskiens nummer 1-5, derefter reset int og tag billeder 1-5 igen
        ObservableList<Image> images = FXCollections.observableArrayList(image1, image2, image3, image4, image5);

        int imgselect = cbxWhisky.getSelectionModel().getSelectedIndex();
        imageView.setImage(images.get(imgselect));
        cbxWhisky.getSelectionModel().selectFirst();

        WhiskyPåFlaske whiskyPåFlaske = cbxWhisky.getSelectionModel().getSelectedItem().getFlasker().get(0);
        lblResterendeValue.setText("0 / " + String.valueOf(Math.floor(cbxWhisky.getSelectionModel().getSelectedItem().getLiter())));
        lblTotalNummerValue.setText(String.valueOf(whiskyPåFlaske.getTotalNummer()));
        lblFortyndelseValue.setText(String.valueOf(whiskyPåFlaske.getFortyndelseIML()));
        lblLiterValue.setText(String.valueOf(whiskyPåFlaske.getLiter()));
        lblWhiskyValue.setText(whiskyPåFlaske.getWhisky().toString());
        lblAlkProValue.setText(String.valueOf(whiskyPåFlaske.getWhisky().getAlkoholProcent()));
        lblBeskrivelseValue.setText(whiskyPåFlaske.getWhisky().getBeskrivelse());
        if(whiskyPåFlaske.getLager() != null){lblLagerValue.setText(whiskyPåFlaske.getLager().toString());}

    }
    private void selectedWhiskyChanged(){
        clearFields();
        if(cbxWhisky.getSelectionModel().getSelectedItem() == null){return;}
        Whisky whisky = cbxWhisky.getSelectionModel().getSelectedItem();
        if(!whisky.getFlasker().isEmpty()){
            WhiskyPåFlaske whiskyPåFlaske = cbxWhisky.getSelectionModel().getSelectedItem().getFlasker().get(0);
            lblResterendeValue.setText("0 / " + String.valueOf(Math.floor(cbxWhisky.getSelectionModel().getSelectedItem().getLiter())));
            lblTotalNummerValue.setText(String.valueOf(whiskyPåFlaske.getTotalNummer()));
            lblFortyndelseValue.setText(String.valueOf(whiskyPåFlaske.getFortyndelseIML()));
            lblLiterValue.setText(String.valueOf(whiskyPåFlaske.getLiter()));
            lblWhiskyValue.setText(whiskyPåFlaske.getWhisky().toString());
            lblAlkProValue.setText(String.valueOf(whiskyPåFlaske.getWhisky().getAlkoholProcent()));
            lblBeskrivelseValue.setText(whiskyPåFlaske.getWhisky().getBeskrivelse());
            if(whiskyPåFlaske.getLager() != null){lblLagerValue.setText(whiskyPåFlaske.getLager().toString());}
        }
        else{
            //hvis hvis der ikke er nogen flasker oprettet af whiskien
        }
    }
    private void clearFields(){
        lblResterendeValue.setText("");
        lblTotalNummerValue.setText("");
        lblFortyndelseValue.setText("");
        lblLiterValue.setText("");
        lblWhiskyValue.setText("");
        lblAlkProValue.setText("");
        lblBeskrivelseValue.setText("");
    }

    private void aftapAction(){
        WhiskeyPåFlaskeOpretWindow whiskeyPåFlaskeOpretWindow = new WhiskeyPåFlaskeOpretWindow();
        whiskeyPåFlaskeOpretWindow.showAndWait();
    }
    private void redigerAction(){
        if(cbxWhisky.getSelectionModel().getSelectedItem() == null){return;}
        Whisky whisky = cbxWhisky.getSelectionModel().getSelectedItem();
        WhiskeyRedigerWindow whiskeyRedigerWindow = new WhiskeyRedigerWindow(whisky);
        whiskeyRedigerWindow.showAndWait();
    }
    private void sletAction(){
        //TODO:
    }
    public void updateControls(){
        cbxWhisky.getItems().setAll(Storage.getWhiskyArrayList().toArray(new Whisky[0]));
        cbxWhisky.getSelectionModel().selectFirst();
    }
}
