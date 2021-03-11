
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import java.util.ArrayList;
import java.util.List;
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
    //private Token token;
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
        Tile bathroomDoor2 = board.getTileMap()[20][14];
        bathroomDoors.add(bathroomDoor);
        bathroomDoors.add(bathroomDoor2);
        board.initializeRoom("Bathroom", bathroomSpace, bathroomDoors);

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
        // for testing Purposes
        //initialise one token and one player
        List<String> testPlayerNamesList = new ArrayList<>();
        testPlayerNamesList.add("p1");
        List<Character> testPlayerTypesList = new ArrayList<>();
        testPlayerTypesList.add('h');
        board.addPlayers(testPlayerNamesList, testPlayerTypesList);
        board.setCurrentPlayer(board.getPlayerList().get(0));
        /*
        initializePlayerToken(board.getPlayerList().get(0), "TestToken", 2, 3);
        for (int _r = 0; _r < rows; _r++) {
            for (int _c = 0; _c < columns; _c++) {
                if (board.getTileMap()[_c][_r].IsOccupied()) {
                    boardView.add(board.getCurrentPlayer().getToken(), _c, _r);
                }
            }
        }
         */
        //Initialize token on board,assign to token objects
        Token scarlett = board.initializeToken("Mrs.Scarlet", 2, 3);  
        Token mustard = board.initializeToken("Mr.Mustard", 7, 3);  
        //assigns one of the token to one of the player
        assignTokenToPlayer(board.getCurrentPlayer(), scarlett);
        for (int _r = 0; _r < rows; _r++) {
            for (int _c = 0; _c < columns; _c++) {
                for (Token token : board.getTokens()) {
                    if (board.getTileMap()[_c][_r].IsOccupied() && token.getTokenLocation()==board.getTileMap()[_c][_r] ) {
                        boardView.add(token, _c, _r);
                    }
                }
            }
        }
        
        

        //assignTokenToPlayer(board.getCurrentPlayer(),board.getTokens().get(0));
        //Combines diceRoller and Board
        gameBox.getChildren().addAll(switcherButton, diceRollerView, boardView);
        gameBox.setAlignment(Pos.CENTER);
        return gameBox;
    }
    

    /**
     * Makes random movements for AI token on the board
     *
     * public void positionUpdateAI() {//Bunu Implement Etmedim Daha if
     * (token.isAgent()) { Random random = new Random(); int movement =
     * random.nextInt(4); switch (movement) { case 0: moveToken(token,
     * (token.getTokenLocation().getColIndex()),
     * (token.getTokenLocation().getRowIndex() - 1));
     * System.out.println(token.getTokenLocation().getColIndex() + "," +
     * token.getTokenLocation().getRowIndex()); break; case 1: moveToken(token,
     * token.getTokenLocation().getColIndex(),
     * (token.getTokenLocation().getRowIndex() + 1));
     * System.out.println(token.getTokenLocation().getColIndex() + "," +
     * token.getTokenLocation().getRowIndex()); break; case 2: moveToken(token,
     * token.getTokenLocation().getColIndex() - 1,
     * (token.getTokenLocation().getRowIndex()));
     * System.out.println(token.getTokenLocation().getColIndex() + "," +
     * token.getTokenLocation().getRowIndex()); break; case 3: moveToken(token,
     * token.getTokenLocation().getColIndex() + 1,
     * (token.getTokenLocation().getRowIndex()));
     * System.out.println(token.getTokenLocation().getColIndex() + "," +
     * token.getTokenLocation().getRowIndex()); break; }
     * System.out.println(counter); updateView(); } }
     */
    /**
     * Allows player to move token using WASD buttons
     */
    public void setUpControls() {
        scene.setOnKeyPressed((KeyEvent event) -> {
            Tile currentPlayerPos = board.getCurrentPlayer().getToken().getTokenLocation();
            if (diceRoller.isDiceRolled() && (counter < diceRoller.getDiceTotal())) {
                switch (event.getCode()) {
                    case W://go up
                        board.positionUpdateCurrentPlayer((board.getCurrentPlayer().getToken().getTokenLocation().getColIndex()), (board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex() - 1));
                        movementControlsHelper(currentPlayerPos);
                        break;
                    case S:// go down
                        board.positionUpdateCurrentPlayer((board.getCurrentPlayer().getToken().getTokenLocation().getColIndex()), (board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex() + 1));
                        movementControlsHelper(currentPlayerPos);
                        break;
                    case A://go left
                        board.positionUpdateCurrentPlayer(board.getCurrentPlayer().getToken().getTokenLocation().getColIndex() - 1, (board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex()));
                        movementControlsHelper(currentPlayerPos);
                        break;
                    case D:// go right
                        board.positionUpdateCurrentPlayer(board.getCurrentPlayer().getToken().getTokenLocation().getColIndex() + 1, (board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex()));
                        movementControlsHelper(currentPlayerPos);
                        break;
                    default://Non valid Ket
                        System.out.println("NOT VALID");
                        break;
                }
                //If enters the room , player can no longer can move
                for (Room room : board.getRooms()) {
                    if (room.checkTileInRoom(board.getTileMap()[board.getCurrentPlayer().getToken().getTokenLocation().getColIndex()][board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex()])) {
                        System.out.println("TOKEN IS IN " + room.getRoomName());
                        counter = diceRoller.getDiceTotal();
                    }
                }
            } else {
                System.out.println("Please Roll the Dice");
                //Sets Counter to 0
                counter = 0;
                //Set Dice Rolled to false and Enables DiceRoller
                diceRoller.setDiceRolled(false);
                diceRoller.enableDiceRollerButton();
            }
            updateView();
        });

    }

    private void movementControlsHelper(Tile currentPlayerPos) {
        System.out.println(board.getCurrentPlayer().getToken().getTokenLocation().getColIndex() + "," + board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex());
        if (!currentPlayerPos.equals(board.getCurrentPlayer().getToken().getTokenLocation())) {
            counter++;
        }
    }

    /**
     * Updates Tokens view on the board by removing token image on previous and
     * adding token image on existing position
     */
    public void updateView() {
        for (Player p : board.getPlayerList()) {
            boardView.getChildren().remove(p.getToken());
            boardView.add(p.getToken(), p.getToken().getTokenLocation().getColIndex(), p.getToken().getTokenLocation().getRowIndex());
        }

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
                        setUpControls();
                    }
                }
            });
            thread.start();
        }*/
        //else {
        //If token is not AI, then allow player to make movements
        setUpControls();
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

    /*
     * Initialize token on specific locations
     *
     * @param tokenName
     * @param x
     * @param y
     * @return
     
    public Token initializePlayerToken(Player player, String tokenName, int x, int y) {
        try {
            player.setToken(new Token(tokenName));
            player.getToken().setTokenLocation(board.getTileMap()[x][y]);
            return player.getToken();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You can't initialize here ");
            return null;
        }
    }
     */
    
    /**
     * Assigns the existing token on board on some player
     *
     * @param player
     * @param token
     */
    public void assignTokenToPlayer(Player player, Token token) {
        try {
            player.setToken(token);
        } catch (Exception e) {
            System.out.println("Cannot take this Character");
        }
    }

}
