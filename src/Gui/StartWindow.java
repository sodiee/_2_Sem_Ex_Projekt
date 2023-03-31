package Gui;

import Application.Controller.Controller;
import Application.Model.Fad;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartWindow extends Application {

    @Override
    public void init() {
        Controller.initStorage();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Sall Whisky");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.setHeight(650);
        stage.setWidth(600);
    }

    // ----------------------------------------------------

    private void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        //StartPane
        Tab tabStart = new Tab("Startside");
        tabPane.getTabs().add(tabStart);
        StartPane startPane = new StartPane();
        tabStart.setContent(startPane);
        tabStart.setOnSelectionChanged(event -> startPane.updateControls());

        //LagerPane
        Tab tabLager = new Tab("Lagerhuse");
        tabPane.getTabs().add(tabLager);
        LagerPane lagerPane = new LagerPane();
        tabLager.setContent(lagerPane);
        tabLager.setOnSelectionChanged(event -> lagerPane.updateControls());

        //DestillatPane
        Tab tabDestillat = new Tab("Destilleringer");
        tabPane.getTabs().add(tabDestillat);
        DestillatPane destillatPane = new DestillatPane();
        tabDestillat.setContent(destillatPane);
        tabDestillat.setOnSelectionChanged(event -> destillatPane.updateControls());

        //FadPane
        Tab tabFad = new Tab("Fade");
        tabPane.getTabs().add(tabFad);
        FadPane fadPane = new FadPane();
        tabFad.setContent(fadPane);
        tabFad.setOnSelectionChanged(event -> fadPane.updateControls());

        //WhiskyPane
        Tab tabWhisky = new Tab("Whisky");
        tabPane.getTabs().add(tabWhisky);
        WhiskeyPane whiskeyPane = new WhiskeyPane();
        tabWhisky.setContent(whiskeyPane);
        tabWhisky.setOnSelectionChanged(event -> whiskeyPane.updateControls());

        Tab tabWhiskyFlasker = new Tab("Whisky Flasker");
        tabPane.getTabs().add(tabWhiskyFlasker);
        WhiskeyPåFlaskePane whiskeyPåFlaskePane = new WhiskeyPåFlaskePane();
        tabWhiskyFlasker.setContent(whiskeyPåFlaskePane);

    }
}

