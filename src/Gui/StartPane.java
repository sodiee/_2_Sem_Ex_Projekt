package Gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StartPane extends GridPane {

    private Image image;
    private Image image2;
    public StartPane() {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        this.setAlignment(Pos.CENTER);
        try{
            InputStream stream = new FileInputStream("resources/LOGO.png");
            image = new Image(stream);
            InputStream stream2 = new FileInputStream("resources/text.png");
            image2 = new Image(stream2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setX(10);
        imageView.setY(10);
        imageView.setFitWidth(575);
        imageView.setPreserveRatio(true);

        ImageView imageView2 = new ImageView();
        imageView2.setImage(image2);
        imageView2.setX(0);
        imageView2.setY(0);
        imageView2.setFitWidth(230);
        imageView2.setScaleY(1);
        imageView2.setPreserveRatio(true);
        GridPane.setHalignment(imageView2, HPos.CENTER);
        GridPane.setValignment(imageView2, VPos.TOP);

        this.add(imageView, 0, 0);
        this.add(imageView2, 0, 1);
    }

    public void updateControls() {

    }

}
