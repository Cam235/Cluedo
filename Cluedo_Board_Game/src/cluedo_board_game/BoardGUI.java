
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
    private GridPane boardView;
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
    private DiceRoller diceRoller;
    //To measure steps not surpassing value of dice
    int counter = 0;
    //ImageView of Weapon

    private Button showHandBtn;
    private Button endTurnBtn;
    private HBox controlsHbx;

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
        //Button to switch between Player and AI

        showHandBtn = new Button("Show Hand");
        endTurnBtn = new Button("End Turn");
        controlsHbx = new HBox();
        controlsHbx.setAlignment(Pos.CENTER);

        //Establish Board
        board = new Board(columns, rows);

        //------------------------CREATES WEAPONS---------------------------//
        //DO not yet place anywhere
        // Dagger, candlestick, revolver, rope, lead piping and spanner.
        board.initializeWeapon("Dagger");
        board.initializeWeapon("Candlestick");
        board.initializeWeapon("Revolver");
        board.initializeWeapon("Rope");
        board.initializeWeapon("Leadpiping");
        board.initializeWeapon("Spanner");
        //Sets up Images of weapons
        for (Weapon weapon : board.getWeapons()) {
            weapon.setWeaponImage(new Image("/weaponImages/" + weapon.getName() + ".png", 20, 20, false, false));
        }

        //////////////////CREATES 9 ROOMS - BATHROOM, DININGROOM, KITCHEN, BALLROOM, CONSERVATORY, GAMESROOM, LIBRARY, OFFICE, HALLWAY////////////////////// 
        ArrayList<Tile> bathroomSpace = new ArrayList<Tile>();
        ArrayList<Tile> bathroomDoors = new ArrayList<Tile>();
        for (int i = 22; i < 27; i++) {
            for (int j = 22; j < 27; j++) {
                bathroomSpace.add(board.getTileMap()[i][j]);
            }
        }
        Tile bathroomDoor = board.getTileMap()[22][21];
        bathroomDoors.add(bathroomDoor);
        Room bathroom = board.initializeRoom("Bathroom", bathroomSpace, bathroomDoors);

        ArrayList<Tile> diningroomSpace = new ArrayList<Tile>();
        ArrayList<Tile> diningroomDoors = new ArrayList<Tile>();
        for (int i = 20; i < 27; i++) {
            for (int j = 12; j < 18; j++) {
                diningroomSpace.add(board.getTileMap()[i][j]);
            }
        }
        Tile diningroomDoor = board.getTileMap()[19][15];
        Tile diningroomDoor2 = board.getTileMap()[20][11];
        diningroomDoors.add(diningroomDoor);
        diningroomDoors.add(diningroomDoor2);
        Room diningroom = board.initializeRoom("Diningroom", diningroomSpace, diningroomDoors);

        ArrayList<Tile> kitchenSpace = new ArrayList<Tile>();
        ArrayList<Tile> kitchenDoors = new ArrayList<Tile>();
        for (int i = 21; i < 27; i++) {
            for (int j = 1; j < 7; j++) {
                kitchenSpace.add(board.getTileMap()[i][j]);
            }
        }
        Tile kitchenDoor = board.getTileMap()[21][7];
        kitchenDoors.add(kitchenDoor);
        Room kitchen = board.initializeRoom("kitchen", kitchenSpace, kitchenDoors);

        ArrayList<Tile> ballroomSpace = new ArrayList<Tile>();
        ArrayList<Tile> ballroomDoors = new ArrayList<Tile>();
        for (int i = 10; i < 18; i++) {
            for (int j = 22; j < 27; j++) {
                ballroomSpace.add(board.getTileMap()[i][j]);
            }
        }
        Tile ballroomDoor = board.getTileMap()[11][21];
        Tile ballroomDoor2 = board.getTileMap()[16][21];
        Tile ballroomDoor3 = board.getTileMap()[9][23];
        Tile ballroomDoor4 = board.getTileMap()[18][23];
        ballroomDoors.add(ballroomDoor);
        ballroomDoors.add(ballroomDoor2);
        ballroomDoors.add(ballroomDoor3);
        ballroomDoors.add(ballroomDoor4);
        Room ballroom = board.initializeRoom("ballroom", ballroomSpace, ballroomDoors);

        ArrayList<Tile> conservatorySpace = new ArrayList<Tile>();
        ArrayList<Tile> conservatoryDoors = new ArrayList<Tile>();
        for (int i = 1; i < 6; i++) {
            for (int j = 23; j < 27; j++) {
                conservatorySpace.add(board.getTileMap()[i][j]);
            }
        }
        Tile conservatoryDoor = board.getTileMap()[5][22];
        conservatoryDoors.add(conservatoryDoor);
        Room conservatory = board.initializeRoom("conservatory", conservatorySpace, conservatoryDoors);

        ArrayList<Tile> gamesroomSpace = new ArrayList<Tile>();
        ArrayList<Tile> gamesroomDoors = new ArrayList<Tile>();
        for (int i = 1; i < 6; i++) {
            for (int j = 15; j < 19; j++) {
                gamesroomSpace.add(board.getTileMap()[i][j]);
            }
        }
        Tile gamesroomDoor = board.getTileMap()[1][14];
        Tile gamesroomDoor2 = board.getTileMap()[6][18];
        gamesroomDoors.add(gamesroomDoor);
        gamesroomDoors.add(gamesroomDoor2);
        Room gamesroom = board.initializeRoom("gamesroom", gamesroomSpace, gamesroomDoors);

        ArrayList<Tile> librarySpace = new ArrayList<Tile>();
        ArrayList<Tile> libraryDoors = new ArrayList<Tile>();
        for (int i = 1; i < 7; i++) {
            for (int j = 8; j < 12; j++) {
                librarySpace.add(board.getTileMap()[i][j]);
            }
        }
        Tile libraryDoor = board.getTileMap()[3][12];
        Tile libraryDoor2 = board.getTileMap()[7][9];
        libraryDoors.add(libraryDoor);
        libraryDoors.add(libraryDoor2);
        Room library = board.initializeRoom("library", librarySpace, libraryDoors);

        // HALLWAY STUFF
        ArrayList<Tile> hallwaySpace = new ArrayList<Tile>();
        ArrayList<Tile> hallwayDoors = new ArrayList<Tile>();
        for (int i = 11; i < 17; i++) {
            for (int j = 1; j < 7; j++) {
                hallwaySpace.add(board.getTileMap()[i][j]);
            }
        }
        Tile hallwayDoor1 = board.getTileMap()[13][7];
        Tile hallwayDoor2 = board.getTileMap()[14][7];
        Tile hallwayDoor3 = board.getTileMap()[8][4];
        hallwayDoors.add(hallwayDoor1);
        hallwayDoors.add(hallwayDoor2);
        hallwayDoors.add(hallwayDoor3);
        board.initializeRoom("Hallway", hallwaySpace, hallwayDoors);

        //OFFICE
        ArrayList<Tile> officeSpace = new ArrayList<Tile>();
        ArrayList<Tile> officeDoors = new ArrayList<Tile>();
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 4; j++) {
                officeSpace.add(board.getTileMap()[i][j]);
            }
        }
        Tile officeDoor = board.getTileMap()[6][4];
        officeDoors.add(officeDoor);
        Room office = board.initializeRoom("Office", officeSpace, officeDoors);

        //---------------------------PLACE WEAPONS TO ROOMS RANDOMLY--------------------------------///
        //Shuffles weapons list,so in each game different weapons can be placed in different rooms       
        Collections.shuffle(board.getWeapons());
        Collections.shuffle(board.getRooms());
        for (int i = 0; i < board.getWeapons().size(); i++) {
            // Puts the weapon into room
            board.placeWeaponToRoom(board.getRooms().get(i), board.getWeapons().get(i));
            //System.out.println(board.getRooms().get(i).getRoomWeapon().getName() + "is in "+board.getRooms().get(i).getRoomName());
        }

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
        testPlayerNamesList.add("p2");
        List<Character> testPlayerTypesList = new ArrayList<>();
        testPlayerTypesList.add('h');
        testPlayerTypesList.add('a');
        board.addPlayers(testPlayerNamesList, testPlayerTypesList);
        board.setCurrentPlayer(board.getPlayerList().get(0));

        //-----just for testing, use card distributor in real implementation-----//
        ArrayList<Card> testCardList1 = new ArrayList<>();
        ArrayList<Card> testCardList2 = new ArrayList<>();
        testCardList1.add(new Card(CardType.Person, "Mrs.Scarlet"));
        testCardList2.add(new Card(CardType.Person, "Mr.Mustard"));
        testCardList1.add(new Card(CardType.Weapon, "Dagger"));
        testCardList2.add(new Card(CardType.Weapon, "Candlestick"));
        testCardList1.add(new Card(CardType.Weapon, "Revolver"));
        testCardList2.add(new Card(CardType.Weapon, "Rope"));
        testCardList1.add(new Card(CardType.Room, "Bathroom"));
        testCardList2.add(new Card(CardType.Room, "Diningroom"));
        board.getPlayerList().get(0).setHand(testCardList1);
        board.getPlayerList().get(1).setHand(testCardList2);

        board.initializePlayerToken(board.getPlayerList().get(0), "Mrs.Scarlet", 19, 0);
        board.initializePlayerToken(board.getPlayerList().get(1), "Mr.Mustard", 27, 9);
        //assigns one of the token to one of the player
        for (int _r = 0; _r < rows; _r++) {
            for (int _c = 0; _c < columns; _c++) {
                for (Player p : board.getPlayerList()) {
                    if (board.getTileMap()[_c][_r].IsOccupied() && p.getToken().getTokenLocation() == board.getTileMap()[_c][_r]) {
                        boardView.add(p.getToken(), _c, _r);
                    }
                }
                for (Weapon weapon : board.getWeapons()) {
                    //Gets the first placed weapons location
                    if (board.getTileMap()[_c][_r].equals(weapon.getWeaponLocation())) {
                        ImageView weaponImageView = new ImageView(weapon.getWeaponImage());
                        boardView.add(weaponImageView, _c, _r);
                    }
                }
            }
        }
        controlsHbx.getChildren().addAll(showHandBtn, endTurnBtn);
        //Combines diceRoller and Board
        gameBox.getChildren().addAll(diceRollerView, boardView, controlsHbx);
        gameBox.setAlignment(Pos.CENTER);
        return gameBox;
    }

    /**
     * Allows player to move token using WASD buttons
     */
    public void setUpControls() {
        scene.setOnKeyPressed((KeyEvent event) -> {

            if (!board.getCurrentPlayer().isAgent()) {
                switch (event.getCode()) {
                    case W://go up
                        movementHelper(board.getCurrentPlayer().getToken().getTokenLocation().getColIndex(), (board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex() - 1));
                        break;
                    case S:// go down
                        movementHelper(board.getCurrentPlayer().getToken().getTokenLocation().getColIndex(), (board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex() + 1));
                        break;
                    case A://go left
                        movementHelper((board.getCurrentPlayer().getToken().getTokenLocation().getColIndex() - 1), board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex());
                        break;
                    case D:// go right
                        movementHelper((board.getCurrentPlayer().getToken().getTokenLocation().getColIndex() + 1), board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex());
                        break;
                    default://Non valid Ket
                        System.out.println("NOT VALID");
                        break;
                }
                updateView();
            } else {
                System.out.println("Agent Players turn");
            }
        });

    }

    private void movementHelper(int x, int y) {
        Tile currentPlayerPos = board.getCurrentPlayer().getToken().getTokenLocation();
        if (diceRoller.isDiceRolled()) {
            if ((counter < diceRoller.getDiceTotal())) {
                board.positionUpdateCurrentPlayer(x, y);
                System.out.println(board.getCurrentPlayer().getToken().getTokenLocation().getColIndex() + "," + board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex());
                if (!currentPlayerPos.equals(board.getCurrentPlayer().getToken().getTokenLocation())) {
                    counter++;
                }
                if ((board.getRoomOfPlayer(board.getCurrentPlayer())) != null) {
                    System.out.println("TOKEN IS IN " + board.getRoomOfPlayer(board.getCurrentPlayer()).getRoomName());
                    counter = diceRoller.getDiceTotal();
                }
                if (!(counter < diceRoller.getDiceTotal())) {
                    //-------reset dice here just for testing-------//
                    if (!board.getCurrentPlayer().isAgent()) {
                        resetDice();
                    }
                }
            } else {
                System.out.println("Please End Turn");
            }
        } else {
            System.out.println("Please Roll the Dice");
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
        
        showHandBtn.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!board.getCurrentPlayer().isAgent()) {
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(primaryStage);
                    VBox dialogVbox = new VBox(20);
                    String showHandtxt = new String();
                    showHandtxt += "---Cards---\n";
                    for (Card c : board.getCurrentPlayer().getHand()) {
                        showHandtxt += c.getType().toString() + ": " + c.getName() + "\n";
                    }
                    dialogVbox.getChildren().add(new Text(showHandtxt));
                    Scene dialogScene = new Scene(dialogVbox, 300, 200);
                    dialog.setScene(dialogScene);
                    dialog.show();
                } else {
                    System.out.println("Agent Player Turn");
                }
            }
        });
        /*Increments the current player*/
        endTurnBtn.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                board.incrementCurrentPlayer();
                resetDice();
                //if current player is now ai handle their turn
                if (board.getCurrentPlayer().isAgent()) {
                    endTurnBtn.setDisable(true);
                    //-----------thread needed here for gui update during ai turn-------------//
                    //rolls the dice
                    diceRoller.getRollButton().fire();
                    
                    //use current agent to make sure thread doesn't try to move the next player 
                    Player currentAgent = board.getCurrentPlayer();
                    //Starts the thread
                    Thread thread = new Thread(() -> {
                        Runnable updater = () -> handleAgentMove(currentAgent);
                        while (counter < diceRoller.getDiceTotal()) {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException ex) {
                            }
                            //runs the handleAgentMove() on the Application thread
                            Platform.runLater(updater);
                        }
                        endTurnBtn.setDisable(false);
                        //automatically end turn
                        endTurnBtn.fire();
                    });
                    thread.start();
                }
            }
        });
        setUpControls();

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

    private void handleAgentMove(Player p) {
        if (counter < diceRoller.getDiceTotal() && (board.getCurrentPlayer() == p)) {
            Integer[] newCoords = board.getCurrentPlayer().getMove(board.getCurrentPlayer().getToken().getTokenLocation().getColIndex(), board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex());
            movementHelper(newCoords[0], newCoords[1]);
            updateView();
        }
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

    private void resetDice() {
        System.out.println("Please Roll the Dice");
        //Sets Counter to 0
        counter = 0;
        //Set Dice Rolled to false and Enables DiceRoller
        diceRoller.setDiceRolled(false);
        diceRoller.enableDiceRollerButton();
    }

}
