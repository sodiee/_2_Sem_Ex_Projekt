package Gui;

import Application.Controller.Controller;
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
        Tab tabStart = new Tab("Lager Status");
        tabPane.getTabs().add(tabStart);
        StartPane startPane = new StartPane();
        tabStart.setContent(startPane);
        tabStart.setOnSelectionChanged(event -> startPane.updateControls());

        //DestillatPane
        Tab tabDestillat = new Tab("Destilleringer");
        tabPane.getTabs().add(tabDestillat);
        DestillatPane destillatPane = new DestillatPane();
        tabDestillat.setContent(destillatPane);
        tabDestillat.setOnSelectionChanged(event -> destillatPane.updateControls());


    }
}

