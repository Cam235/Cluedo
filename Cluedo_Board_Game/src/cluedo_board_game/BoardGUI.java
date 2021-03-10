
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import java.util.ArrayList;
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
 * Representation of the boardGUI where player can throw a dice and move the
 * token
 *
 * @author Anilz
 */
public class BoardGUI extends Application implements BoardGUIInterface {

    //Made Scene Global,for methods access
    Scene scene;
    //Used to Combine Board movements with Dice Image
    private VBox gameBox;
    //Pane will be used for Board
    GridPane boardView;
    //Board and repsenetation of Board with Rectangles
    private Board board;
    
    
    //For Token with representation
    private Token token;
    //IsGameRunning
    boolean isRunning = true;
    //Size of tiles
    public static final int Tile_Size = 20;
    //public static final int Token_Radius = 15;
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
     * Creates Board, initialize Token at specified location and put in the
     * boardView Creates DiceRoller object Combines 2 different classes in VBox
     *
     * @return
     */
    @Override
    public VBox setUpBoard() {
        gameBox = new VBox();
        //DiceRoller added to play with dice
        diceRoller = new DiceRoller();
        VBox diceRollerView = diceRoller.createContent();
        //Button to swith between Player and AI
        switcherButton = new Button("AI/Player");
        switcherButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //token.setAgent(!token.isAgent());
            }
        });
        //Establish Board
        board = new Board(columns, rows);
        
        /////////////////////////////////////////ADD ROOMS////////////////////// 
        ArrayList<Tile> bathroomSpace = new ArrayList<Tile>();
        ArrayList<Tile> bathroomDoors = new ArrayList<Tile>();
         for (int i = 15; i < 23; i++) {
            for (int j = 15; j < 23; j++) {
                bathroomSpace.add(board.getTileMap()[j][i]);
            }
        }
         Tile bathroomDoor = board.getTileMap()[14][22];
         bathroomDoors.add(bathroomDoor);
         board.initializeRoom("Bathroom",bathroomSpace ,bathroomDoors );
        /*
        //Adding Kitchen room
       
        ArrayList<Tile> kitchenSpace = new ArrayList<Tile>();
        ArrayList<Tile> kitchenDoors = new ArrayList<Tile>();
        for (int i = 21; i < 23; i++) {
            for (int j = 21; j < 23; j++) {
                kitchenSpace.add(board.getTileMap()[j][i]);
            }
        }
        Tile kitchenDoor = board.getTileMap()[20][21];
        kitchenDoors.add(kitchenDoor);
        board.initializeRoom("Kitchen", kitchenSpace, kitchenDoors);
        /*
        //-------------------------
        //Adding Bedroom
        ArrayList<Tile> bedroomSpace = new ArrayList<Tile>();
        ArrayList<Tile> bedroomDoors = new ArrayList<Tile>();
        for (int i = 10; i < 12; i++) {
            for (int j = 21; j < 23; j++) {
                bedroomSpace.add(board.getTileMap()[j][i]);
            }
        }
        Tile bedroomDoor = board.getTileMap()[9][10];
        bedroomDoors.add(bedroomDoor);
        board.initializeRoom("BedRoom", bedroomSpace, bedroomDoors);
        //-------------------------     
        //Adding Surprise Room :)
        ArrayList<Tile> surpriseSpace = new ArrayList<Tile>();
        ArrayList<Tile> surpriseDoor = new ArrayList<Tile>();
        surpriseSpace.add(board.getTileMap()[4][10]);
        surpriseSpace.add(board.getTileMap()[4][11]);
        surpriseSpace.add(board.getTileMap()[4][12]);
        surpriseSpace.add(board.getTileMap()[4][13]);
        surpriseSpace.add(board.getTileMap()[5][13]);
        surpriseSpace.add(board.getTileMap()[3][13]);
        surpriseSpace.add(board.getTileMap()[4][14]);
        surpriseSpace.add(board.getTileMap()[5][14]);
        surpriseSpace.add(board.getTileMap()[3][14]);
        surpriseDoor.add(board.getTileMap()[4][9]);
        board.initializeRoom("SurpriseRoom", surpriseSpace, surpriseDoor);
        ///////////////////////////////////////////////////////////////////////////////////////
        */
        //---------------------------------------------------------------------------------///
        ////////////////////////////////////////GRIDPANE//////////////////////////////////////
        //Establish array of rectangles
        boardView = new GridPane();
        //Set up the Image of Board
        for (int _r = 0; _r < rows; _r++) {
            for (int _c = 0; _c < columns; _c++) {
                board.getTileMap()[_c][_r].setWidth(Tile_Size);
                board.getTileMap()[_c][_r].setHeight(Tile_Size);
                if (board.getTileMap()[_c][_r].getIsWall()) {
                    board.getTileMap()[_c][_r].setFill(Color.BLUE);
                    board.getTileMap()[_c][_r].setStroke(Color.BLUE);
                } else if (board.getTileMap()[_c][_r].getIsDoor()) {
                    board.getTileMap()[_c][_r].setFill(Color.WHITE);
                } else {
                    board.getTileMap()[_c][_r].setFill(Color.YELLOW);
                    board.getTileMap()[_c][_r].setStroke(Color.BLACK);
                }
                for (Room room : board.getRooms()) {
                    if (room.checkTileInRoom(board.getTileMap()[_c][_r])) {
                        board.getTileMap()[_c][_r].setFill(Color.GRAY);
                        board.getTileMap()[_c][_r].setStroke(Color.GRAY);
                    }
                }
                boardView.add(board.getTileMap()[_c][_r], _c, _r);
            }
        }

        //Initialize the Token on specified location
        token = board.initializeToken("TestToken", 0, 0);
        for (int _r = 0; _r < rows; _r++) {
            for (int _c = 0; _c < columns; _c++) {
                if (board.getTileMap()[_c][_r].IsOccupied()) {
                    boardView.add(token, _c, _r);
                }
            }
        }
        //Combines diceRoller and Board
        gameBox.getChildren().addAll(switcherButton, diceRollerView, boardView);
        gameBox.setAlignment(Pos.CENTER);
        return gameBox;
    }
    
    @Override
    public void spawnTokenInRoom(Token token, Room room) {
        //When hits 
        int index = (int) (Math.random() * room.getRoomSpace().size());
        int newX = room.getRoomSpace().get(index).getColIndex();
        int newY = room.getRoomSpace().get(index).getRowIndex();
        moveToken(token, newX, newY);

    }

    /**
     * Method to move token on specified location on board Method can be
     * replicated as much as total dice value TO make it work, dice has to be
     * rethrown
     *
     * @param token
     * @param x
     * @param y
     */
    @Override
    public void moveToken(Token token, int x, int y) {
        if (diceRoller.isDiceRolled() && (counter < diceRoller.getDiceTotal())) {
            try {
                //If the tile to be moved is not Wall,confirm movement
                if (!board.getTileMap()[x][y].getIsWall()) {
                    //If the board tile to be moved is door then,loop through rooms
                    if (board.getTileMap()[x][y].getIsDoor()) {
                        for (Room room : board.getRooms()) {
                            //if room contains the door to be moved
                            if (room.getRoomDoors().contains(board.getTileMap()[x][y])) {
                                //And if the player has not in room yet
                                if (!room.getRoomSpace().contains(token.getTokenLocation())) {
                                    //Prints which room of entry
                                    System.out.println("You are at entering the " + room.getRoomName());
                                    //Hit the door
                                    token.getTokenLocation().setOccupied(false);
                                    token.setTokenLocation(board.getTileMap()[x][y]);
                                    //And spawn token outside the door
                                    spawnTokenInRoom(token, room);
                                    //Ends token movements
                                    counter = diceRoller.getDiceTotal();
                                } else {
                                    //If it is already in room, then do not spawn in room
                                    token.getTokenLocation().setOccupied(false);
                                    token.setTokenLocation(board.getTileMap()[x][y]);
                                }
                            }
                        }
                    } else {
                        //if neither wall nor door, make just a movement
                        token.getTokenLocation().setOccupied(false);
                        token.setTokenLocation(board.getTileMap()[x][y]);
                        counter++;
                    }
                    //if tile to be moved is wall , then cannot move    
                } else {
                    System.out.println("You cannot go through Wall");
                }
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
     * Makes random movements for AI token on the board
    
    public void positionUpdateAI() {//Bunu Implement Etmedim Daha
        if (token.isAgent()) {
            Random random = new Random();
            int movement = random.nextInt(4);
            switch (movement) {
                case 0:
                    moveToken(token, (token.getTokenLocation().getColIndex()), (token.getTokenLocation().getRowIndex() - 1));
                    System.out.println(token.getTokenLocation().getColIndex() + "," + token.getTokenLocation().getRowIndex());
                    break;
                case 1:
                    moveToken(token, token.getTokenLocation().getColIndex(), (token.getTokenLocation().getRowIndex() + 1));
                    System.out.println(token.getTokenLocation().getColIndex() + "," + token.getTokenLocation().getRowIndex());
                    break;
                case 2:
                    moveToken(token, token.getTokenLocation().getColIndex() - 1, (token.getTokenLocation().getRowIndex()));
                    System.out.println(token.getTokenLocation().getColIndex() + "," + token.getTokenLocation().getRowIndex());
                    break;
                case 3:
                    moveToken(token, token.getTokenLocation().getColIndex() + 1, (token.getTokenLocation().getRowIndex()));
                    System.out.println(token.getTokenLocation().getColIndex() + "," + token.getTokenLocation().getRowIndex());
                    break;
            }
            System.out.println(counter);
            updateView();
        }
    }
    */
    /**
     * Allows player to move token using WASD buttons
     */
    public void positionUpdatePlayer() {
        if (true/*!token.isAgent()*/) {
            scene.setOnKeyPressed((KeyEvent event) -> {
                switch (event.getCode()) {
                    case W://go up
                        board.getCurrentPlayer().moveToken(token, (token.getTokenLocation().getColIndex()), (token.getTokenLocation().getRowIndex() - 1));
                        System.out.println(token.getTokenLocation().getColIndex() + "," + token.getTokenLocation().getRowIndex());
                        counter++;
                        break;
                    case S:// go down
                        moveToken(token, token.getTokenLocation().getColIndex(), (token.getTokenLocation().getRowIndex() + 1));
                        System.out.println(token.getTokenLocation().getColIndex() + "," + token.getTokenLocation().getRowIndex());
                        break;
                    case A://go left
                        moveToken(token, token.getTokenLocation().getColIndex() - 1, (token.getTokenLocation().getRowIndex()));
                        System.out.println(token.getTokenLocation().getColIndex() + "," + token.getTokenLocation().getRowIndex());
                        break;
                    case D:// go right
                        moveToken(token, token.getTokenLocation().getColIndex() + 1, (token.getTokenLocation().getRowIndex()));
                        System.out.println(token.getTokenLocation().getColIndex() + "," + token.getTokenLocation().getRowIndex());
                        break;
                    default://Non valid Ket
                        System.out.println("NOT VALID");
                        break;
                }
                for (Room room : board.getRooms()) {
                    if (room.checkTileInRoom(board.getTileMap()[token.getTokenLocation().getColIndex()][token.getTokenLocation().getRowIndex()])) {
                        System.out.println("TOKEN IS IN " + room.getRoomName());
                    }
                }
                updateView();
            });

        }
    }

    /**
     * Updates Tokens view on the board by removing token image on previous and
     * adding token image on existing position
     */
    public void updateView() {
        boardView.getChildren().remove(token);
        boardView.add(token, token.getTokenLocation().getColIndex(), token.getTokenLocation().getRowIndex());
    }

    /**
     * Starts the prototype GUI
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        setUpBoard();
        //For setting scene and showing labels
        scene = new Scene(gameBox);
        primaryStage.setTitle("Play it Broo");
        primaryStage.setScene(scene);
        primaryStage.show();
        //Checks if token is agent or player,provides gameplay accordingly 
        /*
        if (token.isAgent()) {
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
                    if (!token.isAgent()) {
                        positionUpdatePlayer();
                    }
                }
            });
            thread.start();
        }*/ 
        //else {
            //If token is not AI, then allow player to make movements
        positionUpdatePlayer();
        //}
        
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

    @Override
    public void selectCharacters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void displayCardList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
