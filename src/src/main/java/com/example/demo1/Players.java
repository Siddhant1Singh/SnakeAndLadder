package com.example.demo1;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Players {
    public ImageView player1;
    public ImageView player2;
    public ImageView playerTag1;
    public ImageView playerTag2;
    private static String name1;
    private static String name2;

    private static int winner;
    private Button roll;
    public GridPane grid;
    public Pane bottomPane;

    private boolean player1Turn, player2Turn;       // For turns of each player
    private boolean First1, First2;     // For start check of player
    private int p1col, p1row;           // For coordinates of player1
    private int p2col, p2row;           // For coordinates of player2
    private static int counter1, counter2;     // For snakes and ladders
    private int[] scoreBoard1, scoreBoard2;     // loss, win

    Players(int noOfPlayers, ImageView player1, ImageView player2, ImageView playerTag1, ImageView playerTag2, GridPane grid, Button roll, Pane pane, String nam1, String nam2) {
        this.player1 = player1;
        this.player2 = player2;
        this.playerTag1 = playerTag1;
        this.playerTag2 = playerTag2;
        this.bottomPane = pane;

        this.roll = roll;
        this.grid = grid;

        name1 = nam1;
        name2 = nam2;

        this.scoreBoard1 = new int[2];
        this.scoreBoard2 = new int[2];

        this.player1Turn = true;
        this.player2Turn = false;

        this.p1col = 0; this.p1row = 9;
        this.p2col = 0; this.p2row = 9;

        counter1 = 0; counter2 = 0;
        this.First1 = false; this.First2 = false;
    }

    public void move2Players(int dice) {
        System.out.println("Dice gives: "+dice);
        if (player1Turn) {
            playerTag2.setOpacity(0.7);
            playerTag1.setOpacity(1.0);
            int start = CheckStarted(p1col, p1row, dice, First1);
            if (start == -1) {
                changeTurn();
                return;
            }
            if (start == 0) {
                grid.getChildren().add(player1);
                GridPane.setConstraints(player1, 0, 9);
                changeTurn();
                this.First1 = true;
                counter1 += 1;
                return;
            }
            counter1 += dice;
            if (outsideBoundary(counter1))
                counter1 -= dice;

            System.out.println("Player1 is at: "+counter1);

            int[] t = changePos(p1col, p1row, counter1);
            p1col = t[0]; p1row = t[1];
            if (t[3] > 0)
                counter1 = t[3];

            if (t[2] == 1) {        // Check win
                System.out.println(name1+" won!!");
                roll.setDisable(true);
            }

            GridPane.setConstraints(player1, p1col, p1row);
            changeTurn();
        }
        else if(player2Turn) {
            playerTag1.setOpacity(0.7);
            playerTag2.setOpacity(1.0);
            int start = CheckStarted(p2col, p2row, dice, First2);
            if (start == -1) {
                changeTurn();
                return;
            }
            if (start == 0) {
                grid.getChildren().add(player2);
                GridPane.setConstraints(player2, 0, 9);
                changeTurn();
                this.First2 = true;
                counter2 += 1;
                return;
            }
            counter2 += dice;
            if (outsideBoundary(counter2))
                counter2 -= dice;

            System.out.println("Player2 is at: "+counter2);

            int[] t = changePos(p2col, p2row, counter2);
            p2col = t[0]; p2row = t[1];
            if (t[3] > 0)
                counter2 = t[3];

            if (t[2] == 1) {    // Check win
                System.out.println(name2+" won!");
                roll.setDisable(true);
            }

            GridPane.setConstraints(player2, p2col, p2row);
            changeTurn();
        }
    }

    private int CheckStarted(int col, int row, int dice, boolean first) {
        if (col == 0 && row == 9 && dice != 1 && !first)        // Not started
            return -1;
        if (col == 0 && row == 9 && !first)                     // Started. At initial position
            return 0;
        return 1;                                               // Moving ahead in game
    }

    private boolean checkWin(int col, int row) {
        return col == 0 && row == 0;
    }

    private int[] changePos(int col, int row, int count) {
        int[] temp = new int[4];    // col, row, win= T(1) or F(0), counter
        temp[0] = col;
        temp[1] = row;
        Board b = new Board();

        int[] core = b.getCoordinate(count);
        col = core[0];
        row = core[1];
        System.out.println("Initial Coordinates are: "+col+", "+row);

        int snake = b.getSnakesTail(count);
        int ladder = b.getLaddersHead(count);
        if (snake > 0) {
            int[] cord = b.getCoordinate(snake);
            col = cord[0];
            row = cord[1];
            temp[3] = snake;
            System.out.println("Final Coordinates are: "+col+", "+row);
        }

        else if (ladder > 0) {
            int[] cord = b.getCoordinate(ladder);
            col = cord[0];
            row = cord[1];
            temp[3] = ladder;
            System.out.println("Final Coordinates are: "+col+", "+row);
        }

        if (outsideBoundary(count)) {
            col = temp[0];
            row = temp[1];
        }

        if (checkWin(col, row))
            temp[2] = 1;

        temp[0] = col;
        temp[1] = row;

        return temp;
    }

    private boolean outsideBoundary(int count) {
        return count>100;
    }

    private void changeTurn() {
        if (player1Turn) {
            player1Turn = false;
            player2Turn = true;
        }
        else if (player2Turn) {
            player1Turn = true;
            player2Turn = false;
        }
    }

    public static String getWinner() {
        if (counter1 == 100)
            return name1;
        if (counter2 == 100)
            return name2;
        return "null";
    }

    public void updateWinnerScore(String win) {
        if (win.equals(name1)) {
            this.scoreBoard1[1] += 1;   //won
            this.scoreBoard2[0] += 1;   //lost
        }
        else if (win.equals(name2)) {
            this.scoreBoard2[1] += 1;   //won
            this.scoreBoard1[0] += 1;   //lost
        }
    }

    public int[] getWinnerScore() {
        String win = getWinner();

        if (win.equals(name1)) {
            return this.scoreBoard1;
        }

        else if (win.equals(name2)) {
            return this.scoreBoard2;
        }

        return new int[]{0, 0};
    }

    public static String getLoser() {
        if (counter1 == 100)
            return name2;
        if (counter2 == 100)
            return name1;
        return "null";
    }

    public void Restart() {
        roll.setDisable(false);

        this.player1Turn = true;
        this.player2Turn = false;

        this.p1col = 0; this.p1row = 9;
        this.p2col = 0; this.p2row = 9;

        counter1 = 0; counter2 = 0;
        this.First1 = false; this.First2 = false;

        GridPane.setConstraints(player1, 0, 9);
        GridPane.setConstraints(player2, 0, 9);
        grid.getChildren().remove(player1);
        grid.getChildren().remove(player2);

        bottomPane.getChildren().add(player1);
        player1.setLayoutX(19);
        player1.setLayoutY(-2);
        player1.setTranslateX(15);
        player1.setTranslateY(-10);

        bottomPane.getChildren().add(player2);
        player2.setLayoutX(-14);
        player2.setLayoutY(-1);
        player2.setTranslateX(28);
        player2.setTranslateY(-10);

        player1.setLayoutX(19);

        playerTag2.setOpacity(1.0);
        playerTag1.setOpacity(1.0);
    }
}

