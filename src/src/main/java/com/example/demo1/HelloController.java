package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class HelloController {

    public ImageView player1;
    public ImageView player2;
    public GridPane grid;
    public Button back;
    public ImageView tag1;
    public ImageView tag2;
    public Pane bottomPane;
    @FXML
    private Label welcomeText;
    @FXML
    private Button roll;
    @FXML
    private ImageView diceImage;

    private Dice d;// = new Dice(diceImage, roll);
    private Board b = new Board();
    private Players p;
    private boolean playersInitialized = false;
    private boolean playingComputer = true;


    @FXML
    protected void backButtonClicked(ActionEvent actionEvent) throws IOException {
        // Main method of EndBoxController Class
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("End-Box.fxml"));
        Stage finishBox = new Stage();
        finishBox.initStyle(StageStyle.UNDECORATED);
        finishBox.initModality(Modality.APPLICATION_MODAL); // Block input events
        Scene scene = new Scene(fxmlLoader.load(), 272, 167);
        finishBox.setTitle("Close");
        finishBox.setScene(scene);
        finishBox.showAndWait();

        if (EndBoxController.EndBoxChooses == 1) {    // Chooses main menu
            //Change Scene
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Initial-Scene1.fxml"));
            scene = new Scene(fxmlLoader.load(), 580, 760);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    protected int RollDice() throws InterruptedException {

        Random random = new Random();
        int dice = random.nextInt(6)+1;
        int diceNo = dice;
        String location = "src/main/resources/com/example/demo1/dice/dice" + diceNo + ".png";
        File file = new File(location);
        diceImage.setImage(new Image(file.toURI().toString()));
        return dice;
    }

    @FXML
    private void movePlayers(ActionEvent actionEvent) throws Exception {
        int i=1;
        if (!playersInitialized)
        {
            InitialScene1 init = new InitialScene1();
            if (init.getComputerPlaying())
                p = new Players(2, player1, player2, tag1, tag2, grid, roll, bottomPane, "Player1", "Computer");
            else
                p = new Players(2, player1, player2, tag1, tag2, grid, roll, bottomPane, "Player1", "Player2");

            playersInitialized = true;
        }
        while(playingComputer && i <= 1)
        {
            int die = RollDice();
            p.move2Players(die);

            String win = Players.getWinner();
            if (!win.equals("null"))
            {
                roll.setDisable(true);
                p.updateWinnerScore(win);
                showResult();

                // Main method of ResultBoxController class
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Result-Box.fxml"));
                Stage Box = new Stage();
                Box.initStyle(StageStyle.UNDECORATED);
                Box.initModality(Modality.APPLICATION_MODAL); // Block input events
                Scene scene = new Scene(fxmlLoader.load(), 288, 220);
                Box.setTitle("Result");
                Box.setScene(scene);
                Box.showAndWait();

                //Change Scene
                if (ResultBoxController.ResultBoxChooses == 1) {    // Chooses main menu
                    fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Initial-Scene1.fxml"));
                    scene = new Scene(fxmlLoader.load(), 580, 760);
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }
                else if (ResultBoxController.ResultBoxChooses == 2) {   // Chooses Restart
                    p.Restart();
                }
            }
            i+=1;
        }
    }

    private void showResult() {
        int[] arr = p.getWinnerScore();
        System.out.println(Players.getWinner()+"'s lost matches are: "+arr[0]);
        System.out.println(Players.getWinner()+"'s won matches are: "+arr[1]);
    }
}
