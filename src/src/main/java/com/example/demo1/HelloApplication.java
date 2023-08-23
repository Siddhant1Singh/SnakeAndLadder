package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        music();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Initial-Scene1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 580, 760);   // 320x240  //580x720
        stage.setTitle("Snakes and Ladders");
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.show();
    }

    MediaPlayer mediaPlayer;
    public void music() {
        String path = "src/main/resources/com/example/demo1/backgroundMusic.mp3";
        Media h = new Media(Paths.get(path).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setCycleCount(500);
        mediaPlayer.play();
    }

    public static void main(String[] args) {
        launch();
    }
}