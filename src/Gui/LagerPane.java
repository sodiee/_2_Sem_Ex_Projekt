package Gui;

import Application.Controller.Controller;
import Application.Model.*;
import Storage.Storage;
import com.sun.source.tree.Tree;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class LagerPane extends GridPane {

    private ComboBox<Lager> cbxLager;
    private Image rootImage, reolImage, hyldeImage, fadEmptyImage, fadImage;
    private HBox hbxButtons;
    private Button btnOpret, btnRediger, btnSlet;
    TreeView<CustomUnit> treeView;


    public LagerPane(){
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        initGUI();
    }
    private void initGUI(){

        //region Images
        try{
            InputStream stream1 = new FileInputStream("resources/warehouse.png");
            rootImage = new Image(stream1);

            InputStream stream2 = new FileInputStream("resources/shelf.png");
            reolImage = new Image(stream2);

            InputStream stream3 = new FileInputStream("resources/shelf(hylde).png");
            hyldeImage = new Image(stream3);

            InputStream stream4 = new FileInputStream("resources/barrelEmpty.png");
            fadEmptyImage = new Image(stream4);

            InputStream stream5 = new FileInputStream("resources/barrel.png");
            fadImage = new Image(stream5);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //endregion

        //region Instantiate Nodes
        cbxLager = new ComboBox();
        hbxButtons = new HBox(10);
        btnOpret = new Button("Opret Lagerhus");
        btnRediger = new Button("Redigér Lagerhus");
        btnSlet = new Button("Slet Lagerhus");


        hbxButtons.getChildren().addAll(btnOpret, btnRediger, btnSlet);
        cbxLager.getItems().addAll(Storage.getLagerArrayList());
        //endregion

        //region Add nodes
        this.add(cbxLager, 0, 1);
        this.add(hbxButtons, 0, 6);
        //endregion

        //region Events & Listeners
        btnOpret.setOnAction(event -> btnOpretLagerAction());
        btnRediger.setOnAction(event -> btnRedigerLagerAction());
        btnSlet.setOnAction(event -> btnSletLagerAction());
        ChangeListener<Lager> listener4 = (ov4, oldCompny, newCompany) -> this.selectedLagerChanged();
        cbxLager.getSelectionModel().selectedItemProperty().addListener(listener4);
        cbxLager.getSelectionModel().selectFirst();

        ChangeListener<TreeItem> listenerTree = (ov, old, neww) -> this.selectedTreeItemChanged();
        treeView.getSelectionModel().selectedItemProperty().addListener(listenerTree);
        //endregion


    }
    private void selectedLagerChanged() {

        Lager lager = cbxLager.getSelectionModel().getSelectedItem();
        if (lager == null) {
            return;
        }
        ImageView rootImageView = new ImageView(rootImage);
        rootImageView.setFitWidth(25);
        rootImageView.setFitHeight(25);
        CustomUnit customUnit = new CustomUnit(lager.toString(), "lager", "none");
        TreeItem<CustomUnit> stockUnit = new TreeItem<>(customUnit, rootImageView);

            for (Reol reol : lager.getReoler()) {

                ImageView reolImageView = new ImageView(reolImage);
                reolImageView.setFitWidth(20);
                reolImageView.setFitHeight(20);
                CustomUnit customUnit1 = new CustomUnit("Reol " + String.valueOf(reol.getReolNr()), "Reol", lager.toString());
                TreeItem<CustomUnit> reolUnit = new TreeItem<>(customUnit1, reolImageView);
                stockUnit.getChildren().add(reolUnit);

                for (Hylde hylde : reol.getHylder()){

                     ImageView hyldeImageView = new ImageView(hyldeImage);
                     hyldeImageView.setFitWidth(20);
                     hyldeImageView.setFitHeight(20);
                     CustomUnit customUnitHylde = new CustomUnit("Hylde " + String.valueOf(hylde.getHyldeNr()), "Hylde", String.valueOf(reol.getReolNr()));
                     TreeItem<CustomUnit> hyldeUnit = new TreeItem<>(customUnitHylde, hyldeImageView);
                     reolUnit.getChildren().add(hyldeUnit);

                    for (Hyldeplads hyldeplads : hylde.getHyldepladser()){

                        ImageView fadImageView = new ImageView(fadImage);
                        ImageView fadEmptyImageView = new ImageView(fadEmptyImage);
                        fadEmptyImageView.setFitWidth(20);
                        fadEmptyImageView.setFitHeight(20);
                        fadImageView.setFitHeight(20);
                        fadImageView.setFitWidth(20);
                        CustomUnit customUnitHyldeplads = new CustomUnit("Plads " + String.valueOf(hyldeplads.getHyldepladsNr()), "Hyldeplads", String.valueOf(hylde.getHyldeNr()));

                        if(hyldeplads.getFad() != null){
                            TreeItem<CustomUnit> hyldeUnitplads = new TreeItem<>(customUnitHyldeplads, fadImageView);
                            hyldeUnit.getChildren().add(hyldeUnitplads);
                        }
                        else if(hyldeplads.getFad() == null){
                            TreeItem<CustomUnit> hyldeUnitplads = new TreeItem<>(customUnitHyldeplads, fadEmptyImageView);
                            hyldeUnit.getChildren().add(hyldeUnitplads);
                        }
                        customUnitHyldeplads.setFad(hyldeplads.getFad());
                    }
                }
            }
            stockUnit.setExpanded(true);
            treeView = new TreeView<CustomUnit>(stockUnit);
            this.add(treeView, 0, 3);
        }

    private void selectedTreeItemChanged(){
        CustomUnit customUnit = treeView.getSelectionModel().getSelectedItem().getValue();
        if(customUnit.type == "Hyldeplads"){
            System.out.println(customUnit.fad);
        }
        else{
            System.out.println("no fad");
        }
    }

    public void updateControls(){}
    public void btnOpretLagerAction(){
        LagerOpretWindow lagerOpretWindow = new LagerOpretWindow();
        lagerOpretWindow.showAndWait();

        cbxLager.getItems().setAll(Storage.getLagerArrayList());
        cbxLager.getSelectionModel().selectLast();
    }
    public void btnRedigerLagerAction(){
        if(cbxLager.getSelectionModel().getSelectedItem() == null){return;}
        LagerRedigerWindow lagerRedigerWindow = new LagerRedigerWindow(cbxLager.getSelectionModel().getSelectedItem());
        lagerRedigerWindow.showAndWait();
        cbxLager.getItems().setAll(Storage.getLagerArrayList());
        cbxLager.getSelectionModel().selectLast();
    }
    public void btnSletLagerAction(){
        Lager lager = cbxLager.getSelectionModel().getSelectedItem();

        if(lager == null){return;}
        Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmation.setTitle("Slettelse");
        alertConfirmation.setHeaderText("Er du sikker på at du vil slette lageret?");
        Optional<ButtonType> option = alertConfirmation.showAndWait();

        if (option.get() == null) {} else if (option.get() == ButtonType.OK) {Controller.deleteLager(lager);}

        cbxLager.getItems().setAll(Storage.getLagerArrayList());
        cbxLager.getSelectionModel().selectLast();
    }

    private class CustomUnit{
        String navn;
        String type;
        String parent;
        Fad fad;

        public CustomUnit(String navn, String type, String parent) {
            this.navn = navn;
            this.type = type;
            this.parent = parent;
        }

        public Fad getFad() {
            return fad;
        }

        public void setFad(Fad fad) {
            this.fad = fad;
        }

        @Override
        public String toString(){return navn;}
    }


}
