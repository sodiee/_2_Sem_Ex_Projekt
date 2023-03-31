package Gui;

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

    private ComboBox<Whisky> cbxWhisky;
    private TextField txfSearch;
    private ListView<FlaskeListCell> lvwFlasker = new ListView<>();
    public Font font = Font.font("Verdana", FontWeight.BOLD, 12);;
    private Label lblNavn,lblNummer, lblAlkPro, lblBeskrivelse, lblLiter, lblFortyndelse, lblRygeMateriale, lblKornSort, lblFad, lblLiterDestil;
    private Label lblNavnVal,lblNummerVal, lblAlkProVal, lblBeskrivelseVal, lblLiterVal, lblFortyndelseVal, lblRygeMaterialeVal, lblKornSortVal, lblFadVal, lblLiterDestilVal;

    ImageView imageView = new ImageView();
    Image image;

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

        //TODO: Insæt flere elementer i listviewen, så det virker (CellFactory shit)
        //generate QR code med info om selve whiskien
        //knap til historik for valgte flaske
    }


    private void initData(){

        //MonthListCell[] listCell = Stream.of(Month.values()).map(MonthListCell::new).toArray(MonthListCell[]::new);

        //create new class element for each

        //cant stream wrong class
//        FlaskeListCell[] listCell = Stream.of(cbxWhisky.getSelectionModel().getSelectedItem().getFlasker()).toArray(FlaskeListCell[]::new);
//        ObservableList<FlaskeListCell> items = FXCollections.observableArrayList (listCell);
//
//        lvwFlasker.getItems().setAll(items);
//        lvwFlasker.getSelectionModel().selectFirst();
    }

   private void lvwFlaskerChanged(){
//        FlaskeListCell w = lvwFlasker.getSelectionModel().getSelectedItem();
//        if(w == null){return;}

//       lblNavnVal.setText(w.getWhisky().getNavn());
//       lblNummerVal.setText(String.valueOf(w.getNummer()));
//       lblAlkProVal.setText(String.valueOf(Math.floor(w.getWhisky().getAlkoholprocentDestillat())));
//       lblBeskrivelseVal.setText(w.getWhisky().getBeskrivelse());
//       lblLiterVal.setText(String.valueOf(w.getLiter()));
//       lblFortyndelseVal.setText(String.valueOf(Math.floor(w.getFortyndelseIML())));
//       lblRygeMaterialeVal.setText(w.getWhisky().getRygeMateriale());
//       lblKornSortVal.setText(w.getWhisky().getKornsort());
//       lblFadVal.setText(w.getWhisky().getFade().toString());
//       lblLiterDestilVal.setText(String.valueOf(Math.floor(w.getWhisky().getLiter())));

    }



    private void  cbxWhiskyChanged(){
        if(cbxWhisky.getSelectionModel().getSelectedItem() == null){return;}
        if(lvwFlasker == null){return;}
        //TODO: gør så de kun viser flasker fra den rigtige whisky

        ArrayList<FlaskeListCell> nyeFlasker = new ArrayList<>();
        //ListViewHeader
        FlaskeListCell flaskeHeader = new FlaskeListCell("n", "a", "f", "s");
        nyeFlasker.add(flaskeHeader);

        for(WhiskyPåFlaske w : cbxWhisky.getSelectionModel().getSelectedItem().getFlasker()){
            Flaske flaske = new Flaske(w.getNummer(), w.getAlkoholProcent(), w.getFortyndelseIML(), w.getLiter());
            FlaskeListCell flaskeListCell = new FlaskeListCell(flaske);

            nyeFlasker.add(flaskeListCell);
        }
        ObservableList<FlaskeListCell> items = FXCollections.observableArrayList (nyeFlasker);

        lvwFlasker.getItems().setAll(items);


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

            //TODO: centrering virker ikke
            TilePane.setAlignment(node, Pos.BASELINE_CENTER);
            setGraphic(node);
        }

        /**
         * Opret Flaske-celle til at display en header i ListView
         * @param nr
         * @param alk
         * @param fortynd
         * @param stø
         */
        private FlaskeListCell(String nr, String alk, String fortynd, String stø){
            imageView = new ImageView(WhiskeyPåFlaskePane.this.image);
            lblNr = new Label(nr);
            lblAlkPro = new Label(alk);
            lblFortyndelse = new Label(fortynd);
            lblStørrelse = new Label(stø);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            imageView.visibleProperty().set(false);

            lblNr.setFont(WhiskeyPåFlaskePane.this.font);
            lblAlkPro.setFont(WhiskeyPåFlaskePane.this.font);
            lblFortyndelse.setFont(WhiskeyPåFlaskePane.this.font);
            lblStørrelse.setFont(WhiskeyPåFlaskePane.this.font);

            this.setStyle("-fx-background-color: transparent");

            TilePane node = new TilePane(Orientation.VERTICAL, 55, 5, imageView, lblNr, lblAlkPro, lblFortyndelse, lblStørrelse);

            //TODO: centrering virker ikke
            //TilePane.setAlignment(node, Pos.BASELINE_CENTER);
            setGraphic(node);
        }
//        @Override
//        public void updateItem(Flaske flaske, boolean empty) {
//            super.updateItem(flaske, empty);
//            if (empty) {
//                setText(null);
//                setGraphic(null);
//            } else {
//                imageView.setImage(new Image(flaske.getImage()));
//                lblNr.setText(month.name());
//            }
//
//    }


    }
}
