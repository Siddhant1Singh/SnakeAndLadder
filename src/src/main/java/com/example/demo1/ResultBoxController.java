package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultBoxController implements Initializable {
    public Label matchesWon;
    public Label matchesLost;

    private int lost = 0;
    private int won = 0;

    public Button MainMenu;
    public Button Back;

    public static int ResultBoxChooses;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        matchesLost.setText(Players.getLoser());
        matchesWon.setText(Players.getWinner());
        ResultBoxChooses = 0;
    }

    public void MainMenuButtonClicked() {
        Stage curr = (Stage)Back.getScene().getWindow();
        curr.close();
        ResultBoxChooses = 1;
    }

    public void BackButtonClicked() {
        Stage curr = (Stage)Back.getScene().getWindow();
        curr.close();
        ResultBoxChooses = 2;
    }

    public void setScore(int lost, int won) {
        this.lost = lost;
        this.won = won;
    }

    public void setName(String win, String lose) {
        System.out.println("In setName function");
        matchesWon.setText(win);
        matchesLost.setText(lose);
    }
}
