package com.example.demo1;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;

public class Dice implements Runnable {

    int diceNo = 0;
    private ImageView diceImage;
    private Button roll;

    public Dice(ImageView diceImage, Button roll) {
        this.diceImage = diceImage;
        this.roll = roll;
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            for (int i = 0; i < 15; i++) {
                diceNo = (random.nextInt(6) + 1);
                String location = "src/main/resources/com/example/demo1/dice/dice" + diceNo + ".png";
                File file = new File(location);
                diceImage.setImage(new Image(file.toURI().toString()));
                Thread.sleep(80);

            }
            roll.setDisable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Thread ID: "+Thread.currentThread().getId());
            setDiceNo(diceNo);

        }
        //System.out.println("DICE NO: " + diceNo);
    }

    private void setDiceNo(int no) {
        this.diceNo = no;
    }

    public int getDiceNo() {
        return diceNo;
    }
}

// Animation Timer
/*public class Dice implements Initializable {
    @FXML
    Label lbli;
    private Service<Void> backgroundThread;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void clickedButton(ActionEvent event) {
        backgroundThread = new Service<>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        for (int i = 0; i <= 1000000; i++) {
                            //updateMessage("i: + i)
                        }
                        return null;
                    }
                };
            }
        };
        backgroundThread.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                System.out.println("Done!");
                lbli.setProperty().unbind();
            }
        });
        lbli.textProperty().bind(backgroundThread.messageProperty());
        backgroundThread.restart();
    }
}*/

// Initialize interface
/*public class Dice implements Initializable {

    int diceNo = 0;
    private ImageView diceImage;
    private Button roll;
    private Service<Void> backgroundThread;

    public Dice(ImageView diceImage, Button roll) {
        this.diceImage = diceImage;
        this.roll = roll;
    }

    private void setDiceNo(int no) {
        this.diceNo = no;
    }

    public int getDiceNo() {
        return diceNo;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void run() {
        backgroundThread = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        // Task
                        try {
                            Random random = new Random();
                            for (int i = 0; i < 15; i++) {
                                diceNo = (random.nextInt(6) + 1);
                                String location = "src/main/resources/com/example/demo1/dice/dice" + diceNo + ".png";
                                File file = new File(location);
                                diceImage.setImage(new Image(file.toURI().toString()));
                                Thread.sleep(80);

                            }
                            roll.setDisable(false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
            }
        };
        backgroundThread.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                System.out.println("Done!");
                System.out.println("Dice finally prints: "+getDiceNo());
                //lbli.setProperty().unbind();
            }
        });
        //lbli.textProperty().bind(backgroundThread.messageProperty());
        backgroundThread.restart();
    }
}*/

