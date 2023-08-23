package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EndBoxController implements Initializable {
    @FXML
    private Pane endBox;
    @FXML
    private Button Back;
    @FXML
    private Button MainMenu;

    public static int EndBoxChooses;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EndBoxChooses = 0;
    }

    @FXML
    private void BackButtonClicked() {
        // Go back in game
        Stage curr = (Stage)Back.getScene().getWindow();
        curr.close();
        EndBoxChooses = 2;
    }
    @FXML
    private void MainMenuButtonClicked() {
        // Goto Main Menu
        Stage curr = (Stage)Back.getScene().getWindow();
        curr.close();
        EndBoxChooses = 1;
    }
}

// For progress bar
/*
import java.net.URL;

class DoWork extends Task<Integer> {
    @Override
    protected Integer call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + 1);
            updateProgress(i + 1, 10);
            Thread.sleep(500);
            if (isCancelled()) {
                return i;
            }
        }
        return 10;
    }
    @Override
    public boolean cancel(boolean may InterruptIfRunning) {
        updateMessage("Cancelled!");
        return super.cancel(mayInterruptIfRunning);
    }
    @Override
    protected void updateProgress (double workDone, double max) {
        updateMessage("progress!" + workDone);
        super.updateProgress(workDone, max);
    }
}

public class FirstController implements Initializable {
    @FXML
    ProgressBar bar;
    @FXML
    Progress Indicator indicator;
    @FXML
    Label status;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DoWork task = new DoWork();
        bar.progressProperty().bind(task.progress Property());
        indicator.progress Property().bind(task.progress Property());
        status.textProperty().bind(task.messageProperty());
    }
    new Thread(task).start();
}
 */