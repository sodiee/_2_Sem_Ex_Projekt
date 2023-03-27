package Gui;

import Application.Controller.Controller;
import Application.Model.Lager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class LagerOpretWindow extends Stage{

    Button btnOk, btnCancel;

    public LagerOpretWindow(){
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.setTitle("Opret Lager");
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
        //TODO: Hvordan skal reol og hyldesystemet virke?

        btnOk = new Button("OK");
        pane.add(btnOk, 0, 0);
        btnOk.setOnAction(event -> btnOkAction());

        btnCancel = new Button();
        pane.add(btnCancel, 1, 0);
        btnCancel.setOnAction(event -> btnCancelAction());
    }
    private void btnOkAction(){
        this.hide();
    }
    private void btnCancelAction(){
        this.hide();
    }
}
