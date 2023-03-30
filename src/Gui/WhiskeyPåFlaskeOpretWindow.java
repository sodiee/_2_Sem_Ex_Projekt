package Gui;


import Application.Model.Whisky;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WhiskeyPåFlaskeOpretWindow extends Stage {

    private Label lblNummer, lblNummerValue, lblTotalNummer, lblFortyndelse, lblLiter;
    private TextField  txfTotalNummerValue, txfFortyndelseValue, txfLiterValue;
    private ComboBox<Whisky> cbxWhisky;
    private VBox vbx;
    private Button btnGodkend, btnAnnuller;

    public WhiskeyPåFlaskeOpretWindow(){
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.setTitle("Aftap Whisky");
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        this.setScene(scene);
        this.initGUI(pane);
        this.initData();
    }

    private void initGUI(GridPane pane){
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lblNummer = new Label("Muligt Antal Flasker");
        lblTotalNummer = new Label("Antal Flasker");
        lblFortyndelse = new Label("Fortyndelse i ml");
        lblLiter = new Label("Flaske Liter");
        lblNummerValue = new Label("...");
        txfTotalNummerValue = new TextField();
        txfFortyndelseValue = new TextField();
        txfLiterValue = new TextField();
        cbxWhisky = new ComboBox();

        btnGodkend = new Button("Godkend");
        btnGodkend.setOnAction(event -> godkendAction());
        btnAnnuller = new Button("Annuller");
        btnAnnuller.setOnAction(event -> annullerAction());

        HBox hbx = new HBox(73);
        hbx.getChildren().addAll(btnGodkend, btnAnnuller);

        vbx = new VBox(5);
        vbx.getChildren().addAll(cbxWhisky, lblNummer, lblNummerValue, lblLiter, txfLiterValue, lblTotalNummer, txfTotalNummerValue, lblFortyndelse, txfFortyndelseValue, hbx);
        pane.add(vbx, 0, 0);

        lblNummer.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblTotalNummer.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblFortyndelse.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        lblLiter.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

        txfLiterValue.textProperty().addListener((observable, oldValue, newValue) -> literChanged());
    }
    private void initData(){
        cbxWhisky.getItems().setAll(Storage.Storage.getWhiskyArrayList());
        cbxWhisky.getSelectionModel().selectFirst();

    }

    private void literChanged(){

        try{Double.parseDouble(txfLiterValue.getText());
            lblNummerValue.setText(String.valueOf(
                    Math.floor(cbxWhisky.getSelectionModel().getSelectedItem().getMuligeAntalFlasker(Double.parseDouble(txfLiterValue.getText())))));
        }
        catch(NumberFormatException e){
            lblNummerValue.setText("...");
        }

    }
    private void godkendAction(){
        //TODO:
    }
    private void annullerAction(){
        this.hide();
    }

}
