package org.example;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class App extends Application {

    private double dragX, dragY;

    @Override
    public void start(Stage stage) throws IOException {
            URL fxmlURL = getClass().getClassLoader().getResource("InternetPackages.fxml");
            if (fxmlURL != null) {
                GridPane root = FXMLLoader.load(fxmlURL);
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.UNDECORATED);

                scene.getStylesheets().add("style.css");

                stage.setScene(scene);
                stage.show();

                scene.setOnMousePressed(mouseEvent -> {
                    dragX = mouseEvent.getScreenX() - stage.getX();
                    dragY = mouseEvent.getScreenY() - stage.getY();
                });
                scene.setOnMouseDragged(mouseEvent -> {
                    stage.setX(mouseEvent.getScreenX() - dragX);
                    stage.setY(mouseEvent.getScreenY() - dragY);
                });
            }
    }

    public static void main(String[] args) {
        launch();
    }

}