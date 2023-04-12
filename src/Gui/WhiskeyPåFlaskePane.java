package Gui;

import Application.Model.Fad;
import Application.Model.Whisky;
import Application.Model.WhiskyPåFlaske;
import Storage.Storage;
import com.sun.tools.javac.Main;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.stream.Stream;

public class WhiskeyPåFlaskePane extends GridPane {
    //region Global Variables
    private ComboBox<Whisky> cbxWhisky;
    private TextField txfSearch;
    private ListView<FlaskeListCell> lvwFlasker = new ListView<>();
    public Font font = Font.font("Verdana", FontWeight.BOLD, 12);;
    private Label lblNavn,lblNummer, lblAlkPro, lblBeskrivelse, lblLiter, lblFortyndelse, lblRygeMateriale, lblKornSort, lblFad, lblLiterDestil;
    private Label lblNavnVal,lblNummerVal, lblAlkProVal, lblBeskrivelseVal, lblLiterVal, lblFortyndelseVal, lblRygeMaterialeVal, lblKornSortVal, lblFadVal, lblLiterDestilVal;
    private Image image;
    //endregion

    public WhiskeyPåFlaskePane(){
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        initGUI();
        initData();
    }
    private void initGUI() {
        try{
            InputStream stream = new FileInputStream("resources/WhiskyIcons/Whisky6.png");
            image = new Image(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        txfSearch = new TextField();

        this.add(txfSearch, 1, 0);

        //region ComboBox
        cbxWhisky = new ComboBox<>();
        this.add(cbxWhisky, 0, 0);
        cbxWhisky.getItems().setAll(Storage.getWhiskyArrayList());

        ChangeListener<Whisky> listener1 = (ov, old, newone) -> this.cbxWhiskyChanged();
        cbxWhisky.getSelectionModel().selectedItemProperty().addListener(listener1);
        cbxWhisky.getSelectionModel().selectFirst();
        //endregion

        //region listView
        ChangeListener<FlaskeListCell> listener2 = (ov, old, newone) -> this.lvwFlaskerChanged();
        lvwFlasker.getSelectionModel().selectedItemProperty().addListener(listener2);
        this.add(lvwFlasker, 0, 1, 2, 1);
        lvwFlasker.setPrefWidth(590);
        lvwFlasker.setFixedCellSize(55);

        //endregion

        //region LabelInit
        lblNavn = new Label("Navn:");
        lblNummer = new Label("Nummer:");
        lblAlkPro = new Label("Alk Pro:");
        lblBeskrivelse = new Label("Beskrivelse:");
        lblLiter = new Label("Liter:");
        lblFortyndelse = new Label("Fortyndelse:");
        lblRygeMateriale = new Label("Ryge Materiale:");
        lblKornSort = new Label("Korn Sort:");
        lblFad = new Label("Fad:");
        lblLiterDestil = new Label("Liter Destil:");

        lblNavnVal = new Label();
        lblNummerVal = new Label();
        lblAlkProVal = new Label();
        lblBeskrivelseVal = new Label();
        lblLiterVal = new Label();
        lblFortyndelseVal = new Label();
        lblRygeMaterialeVal = new Label();
        lblKornSortVal = new Label();
        lblFadVal = new Label();
        lblLiterDestilVal = new Label();
        //endregion

        //region Vbox & Hbox
        VBox vbxleft = new VBox(5);
        vbxleft.getChildren().addAll(lblNavn, lblNavnVal, lblNummer, lblNummerVal, lblAlkPro, lblAlkProVal, lblBeskrivelse, lblBeskrivelseVal, lblLiter, lblLiterVal);
        VBox vbxRight = new VBox(5);
        vbxRight.getChildren().addAll(lblFortyndelse, lblFortyndelseVal, lblRygeMateriale, lblRygeMaterialeVal, lblKornSort, lblKornSortVal, lblFad, lblFadVal, lblLiterDestil, lblLiterDestilVal);
        HBox hbx = new HBox(200);
        hbx.getChildren().addAll(vbxleft, vbxRight);
        this.add(hbx, 0, 2, 2, 1);

        vbxleft.setAlignment(Pos.BASELINE_CENTER);
        vbxRight.setAlignment(Pos.BASELINE_CENTER);
        hbx.setAlignment(Pos.BASELINE_CENTER);
        //endregion

        //region Font
        lblNavn.setFont(font);
        lblNummer.setFont(font);
        lblAlkPro.setFont(font);
        lblBeskrivelse.setFont(font);
        lblLiter.setFont(font);
        lblFortyndelse.setFont(font);
        lblRygeMateriale.setFont(font);
        lblKornSort.setFont(font);
        lblFad.setFont(font);
        lblLiterDestil.setFont(font);
        //endregion
    }
    private void initData(){

    }
    private void lvwFlaskerChanged(){
        FlaskeListCell f = lvwFlasker.getSelectionModel().getSelectedItem();
        Whisky w = cbxWhisky.getSelectionModel().getSelectedItem();
        if(w == null || f == null){
            lblNavnVal.setText("");
            lblNummerVal.setText("");
            lblAlkProVal.setText("");
            lblBeskrivelseVal.setText("");
            lblLiterVal.setText("");
            lblFortyndelseVal.setText("");
            lblRygeMaterialeVal.setText("");
            lblKornSortVal.setText("");
            lblFadVal.setText("");
            lblLiterDestilVal.setText("");
        }
        else{
            lblNavnVal.setText(w.getNavn());
            lblNummerVal.setText(String.valueOf(f.lblNr.getText()));
            lblAlkProVal.setText(String.valueOf(Math.floor(w.getAlkoholprocentDestillat())));
            lblBeskrivelseVal.setText(w.getBeskrivelse());
            lblLiterVal.setText(String.valueOf(Math.floor(w.getLiter())));
            lblFortyndelseVal.setText(String.valueOf(f.lblFortyndelse.getText()));
            lblRygeMaterialeVal.setText(w.getRygeMateriale());
            lblKornSortVal.setText(w.getKornsort());
            lblFadVal.setText(w.getAktuelFad().getLeverandør());
            lblLiterDestilVal.setText(String.valueOf(Math.floor(w.getLiter())));
        }
    }
    private void  cbxWhiskyChanged(){
        if(cbxWhisky.getSelectionModel().getSelectedItem() == null){return;}
        if(lvwFlasker == null){return;}
        //TODO: gør så de kun viser flasker fra den rigtige whisky

        ArrayList<FlaskeListCell> nyeFlasker = new ArrayList<>();

        for(WhiskyPåFlaske w : cbxWhisky.getSelectionModel().getSelectedItem().getFlasker()){
            Flaske flaske = new Flaske(w.getNummer(), w.getWhisky().getAlkoholProcent(), w.getFortyndelseIML(), w.getLiter());
            FlaskeListCell flaskeListCell = new FlaskeListCell(flaske);

            nyeFlasker.add(flaskeListCell);
        }
        ObservableList<FlaskeListCell> items = FXCollections.observableArrayList (nyeFlasker);
        lvwFlasker.getItems().setAll(items);
    }
    public void updateControls(){
        cbxWhisky.getItems().setAll(Storage.getWhiskyArrayList());
        cbxWhisky.getSelectionModel().selectFirst();
    }
    class Flaske{

        private Image image;
        private int flaskeNr;  //Nummer / Total
        private double alkoholProcent;
        private double fortyndelseML;
        private double flaskeStø;

        private Flaske(int flaskeNr, double AlkPro, double fortyndelse, double flaskeStr){

            this.flaskeNr = flaskeNr;
            this.alkoholProcent = AlkPro;
            this.fortyndelseML = fortyndelse;
            this.flaskeStø = flaskeStr;
            this.image = WhiskeyPåFlaskePane.this.image;
        }

        public Image getImage() {
            return image;
        }

        public int getFlaskeNr() {
            return flaskeNr;
        }

        public double getAlkoholProcent() {
            return alkoholProcent;
        }

        public double getFortyndelseML() {
            return fortyndelseML;
        }

        public double getFlaskeStø() {
            return flaskeStø;
        }
    }
    class FlaskeListCell extends ListCell<Flaske>{
        private final ImageView imageView;
        private final Label lblNr;
        private final Label lblAlkPro;
        private final Label lblFortyndelse;
        private final Label lblStørrelse;

        private FlaskeListCell(Flaske flaske){
            imageView = new ImageView(flaske.getImage());
            lblNr = new Label("#" + String.valueOf(flaske.getFlaskeNr()));
            lblAlkPro = new Label(String.valueOf(flaske.getAlkoholProcent()) + "% Vol");
            lblFortyndelse = new Label(String.valueOf(flaske.getFortyndelseML()) + "ml");
            lblStørrelse = new Label(String.valueOf(flaske.getFlaskeStø()) + "L");
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            this.setStyle("-fx-background-color: transparent");

            TilePane node = new TilePane(Orientation.VERTICAL, 55, 50, imageView, lblNr, lblAlkPro, lblFortyndelse, lblStørrelse);

            //TODO: centrering virker ikke (lav prioritet)
            TilePane.setAlignment(node, Pos.BASELINE_CENTER);
            setGraphic(node);
        }
    }
}
