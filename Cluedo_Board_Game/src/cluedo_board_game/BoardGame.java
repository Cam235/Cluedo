/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Representation of the boardgame where player can throw a dice and move the
 * pawn
 *
 * @author Anilz
 */
public class BoardGame extends Application {

    //Made Scene Global,for methods access
    Scene scene;
    //Used to Combine Board movements with Dice Image
    private VBox gameBox;
    //Pane will be used for Board
    GridPane boardView;
    //Board and repsenetation of Board with Rectangles
    private Board board;
    private Rectangle[][] tileImages;
    //For Pawn with representation
    private Pawn pawn;
    //IsGameRunning
    boolean isRunning = true;
    //Sizes
    public static final int Tile_Size = 20;
    public static final int Pawn_Radius = 15;
    //Number of Rows and Column
    private final int columns = 28;
    private final int rows = 28;
    //DiceRoll object to step as much as dice Values
    DiceRoller diceRoller;
    //To measure steps not surpassing value of dice
    int counter = 0;
    //Switch Between Player and AI
    Button switcherButton;

    /**
     * Creates Board, initialize Pawn at specified location and put in the
     * boardView Creates DiceRoller object Combines 2 different classes in VBox
     *
     * @return
     */
    public VBox CreateContent() {
        gameBox = new VBox();
        //DiceRoller added to play with dice
        diceRoller = new DiceRoller();
        VBox diceRollerView = diceRoller.CreateContent();
        //Button to swith between Player and AI
        switcherButton = new Button("AI/Player");
        switcherButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pawn.setIsAgent(!pawn.IsAgent());
            }
        });
        //Establish Board
        board = new Board(columns, rows);
        //Establish array of rectangles
        boardView = new GridPane();
        //Set up the Image of Board
        tileImages = new Rectangle[columns][rows];
        for (int _r = 0; _r < rows; _r++) {
            for (int _c = 0; _c < columns; _c++) {
                tileImages[_c][_r] = new Rectangle();
                tileImages[_c][_r].setWidth(Tile_Size);
                tileImages[_c][_r].setHeight(Tile_Size);
                tileImages[_c][_r].setFill(Color.YELLOW);
                tileImages[_c][_r].setStroke(Color.BLACK);
                boardView.add(tileImages[_c][_r], _c, _r);
            }
        }
        //Initialize the Pawn on specified location
        pawn = board.initializePawn("TestPawn", 0, 0);
        for (int _r = 0; _r < rows; _r++) {
            for (int _c = 0; _c < columns; _c++) {
                if (board.getTileMap()[_c][_r].IsOccupied()) {
                    boardView.add(pawn, _c, _r);
                }
            }
        }
        //Combines diceRoller and Board
        gameBox.getChildren().addAll(switcherButton, diceRollerView, boardView);
        gameBox.setAlignment(Pos.CENTER);
        return gameBox;
    }

    /**
     * Method to move pawn on specified location on tileMap Method can be
     * replicated as much as total dice value TO make it work, dice has to be
     * rethrown
     *
     * @param pawn
     * @param x
     * @param y
     */
    private void movePawn(Pawn pawn, int x, int y) {
        if (diceRoller.isDiceRolled() && (counter < diceRoller.getDiceTotal())) {
            try {
                pawn.getPawnLocation().setOccupied(false);
                pawn.setPawnLocation(board.tileMap[x][y]);
                counter++;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You cant go here");
            }
        } else {
            System.out.println("Please Roll the Dice");
            //Sets Counter to 0
            counter = 0;
            //Set Dice Rolled to false and Enables DiceRoller
            diceRoller.setDiceRolled(false);
            diceRoller.enableDiceRollerButton();
        }
    }

    /**
     * Makes random movements for AI pawn on the board
     */
    public void positionUpdateAI() {//Bunu Implement Etmedim Daha
        if (pawn.IsAgent()) {
            Random random = new Random();
            int movement = random.nextInt(4);
            switch (movement) {
                case 0:
                    movePawn(pawn, (pawn.getPawnLocation().getColIndex()), (pawn.getPawnLocation().getRowIndex() - 1));
                    System.out.println(pawn.getPawnLocation().getColIndex() + "," + pawn.getPawnLocation().getRowIndex());
                    break;
                case 1:
                    movePawn(pawn, pawn.getPawnLocation().getColIndex(), (pawn.getPawnLocation().getRowIndex() + 1));
                    System.out.println(pawn.getPawnLocation().getColIndex() + "," + pawn.getPawnLocation().getRowIndex());
                    break;
                case 2:
                    movePawn(pawn, pawn.getPawnLocation().getColIndex() - 1, (pawn.getPawnLocation().getRowIndex()));
                    System.out.println(pawn.getPawnLocation().getColIndex() + "," + pawn.getPawnLocation().getRowIndex());
                    break;
                case 3:
                    movePawn(pawn, pawn.getPawnLocation().getColIndex() + 1, (pawn.getPawnLocation().getRowIndex()));
                    System.out.println(pawn.getPawnLocation().getColIndex() + "," + pawn.getPawnLocation().getRowIndex());
                    break;
            }
            System.out.println(counter);
            updateView();
        }
    }

    /**
     * Allows player to move pawn using WASD buttons
     */
    public void positionUpdatePlayer() {
        if (!pawn.IsAgent()) {
            scene.setOnKeyPressed((KeyEvent event) -> {
                switch (event.getCode()) {
                    case W://go up
                        movePawn(pawn, (pawn.getPawnLocation().getColIndex()), (pawn.getPawnLocation().getRowIndex() - 1));
                        System.out.println(pawn.getPawnLocation().getColIndex() + "," + pawn.getPawnLocation().getRowIndex());
                        break;
                    case S:// go down
                        movePawn(pawn, pawn.getPawnLocation().getColIndex(), (pawn.getPawnLocation().getRowIndex() + 1));
                        System.out.println(pawn.getPawnLocation().getColIndex() + "," + pawn.getPawnLocation().getRowIndex());
                        break;
                    case A://go left
                        movePawn(pawn, pawn.getPawnLocation().getColIndex() - 1, (pawn.getPawnLocation().getRowIndex()));
                        System.out.println(pawn.getPawnLocation().getColIndex() + "," + pawn.getPawnLocation().getRowIndex());
                        break;
                    case D:// go right
                        movePawn(pawn, pawn.getPawnLocation().getColIndex() + 1, (pawn.getPawnLocation().getRowIndex()));
                        System.out.println(pawn.getPawnLocation().getColIndex() + "," + pawn.getPawnLocation().getRowIndex());
                        break;
                    default://Non valid Ket
                        System.out.println("NOT VALID");
                        break;
                }
                System.out.println(counter);
                updateView();
            });

        }
    }

    /**
     * Updates Pawns view on the board by removing pawn image on previous and
     * adding pawn image on existing position
     */
    public void updateView() {
        boardView.getChildren().remove(pawn);
        boardView.add(pawn, pawn.getPawnLocation().getColIndex(), pawn.getPawnLocation().getRowIndex());
    }

    /**
     * Starts the prototype GUI
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        CreateContent();
        //For setting scene and showing labels
        scene = new Scene(gameBox);
        primaryStage.setTitle("Play it Broo");
        primaryStage.setScene(scene);
        primaryStage.show();
        //Checks if pawn is agent or player,provides gameplay accordingly      
        if (pawn.IsAgent()) {
            //Use threads for with assigned method to make random movements
            Thread thread = new Thread(() -> {
                Runnable updater = () -> positionUpdateAI();
                while (isRunning) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                    }
                    // UI positionUpdateAI is run on the Application thread
                    Platform.runLater(updater);
                    if (!pawn.IsAgent()) {
                        positionUpdatePlayer();
                    }
                }
            });
            thread.start();
        } else {
            //If pawn is not AI, then allows player to make movements
            positionUpdatePlayer();
        }

        //For Closing Window on "x" button
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

}
