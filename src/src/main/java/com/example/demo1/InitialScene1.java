package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class InitialScene1 {

    public Button Computer;
    public Button Human;
    public Pane mainScene;
    private boolean ComputerPlaying = false;


    public void PlayWithComputer(ActionEvent actionEvent) throws IOException {
        System.out.println("Playing with computer");
        this.ComputerPlaying = true;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 580, 760);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void PlayWithHuman(ActionEvent actionEvent) throws IOException {
        System.out.println("Playing with human");
        this.ComputerPlaying = false;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 580, 760);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public boolean getComputerPlaying() {
        return this.ComputerPlaying;
    }
}
