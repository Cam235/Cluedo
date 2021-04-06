
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import static javafx.print.PrintColor.COLOR;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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

    //Scene for preGame player selection, and game gameScene
    Scene preGameScene;
    Scene gameScene;
    //Made Scene Global,for methods access

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
    //int counter = 0;
    //ImageView of Weapon

    private Button showHandBtn;
    private Button endTurnBtn;
    private HBox controlsHbx;

    private HBox gameViewHbx;
    private VBox alertsVbx;
    private Text alertTxt;
    private Text counterTxt;
    // text for pregame guidence
    private Text preGameText;

    //NO of player SelectionBoxes
    private int playerSelectionBoxesNumber = 2;
    ArrayList<PlayerSelectionBox> selectionBoxesList = new ArrayList<PlayerSelectionBox>();
    //Combobox Values
    private String characters[] = {"Miss Scarlett", "Colonel Mustard", "Mrs.White", "Mrs.Peacock", "Mr.Green", "Professor Plum"};
    //Boolean to declare wheter game started or not
    //private boolean gameStarted;
    //Button to Start Game
    private Button startButton;
//
    private Button suggestionBtn;

    public VBox CreatePreGameContent() {
        VBox actualPreGame = new VBox();
        actualPreGame.setPrefSize(600, 279);
        VBox selectionBoxesView = new VBox();
        for (int i = 0; i < playerSelectionBoxesNumber; i++) {
            PlayerSelectionBox newSelectionBox = new PlayerSelectionBox();
            selectionBoxesList.add(newSelectionBox);
            selectionBoxesView.getChildren().add(newSelectionBox.selectionContent());
        }

        //Adds player buttons
        Button addPlayerButton = new Button("+Add Player");
        addPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (playerSelectionBoxesNumber < 6) {
                    PlayerSelectionBox newSelectionBox = new PlayerSelectionBox();

                    selectionBoxesList.add(newSelectionBox);
                    selectionBoxesView.getChildren().add(newSelectionBox.selectionContent());
                    playerSelectionBoxesNumber++;
                    System.out.println(playerSelectionBoxesNumber);
                    System.out.println(selectionBoxesList.size());
                    System.out.println("List number:" + selectionBoxesView.getChildren().size());
                } else {
                    System.out.println("Too much mate!");
                }
            }
        });
        //Remove Player Select Box
        Button removePlayerButton = new Button("- Remove Player");
        removePlayerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (playerSelectionBoxesNumber > 2) {
                    selectionBoxesList.remove(selectionBoxesList.size() - 1);
                    selectionBoxesView.getChildren().remove(selectionBoxesView.getChildren().size() - 1);
                    playerSelectionBoxesNumber--;
                    System.out.println(playerSelectionBoxesNumber);
                    System.out.println(selectionBoxesList.size());
                    System.out.println("List no:" + selectionBoxesView.getChildren().size());
                } else {
                    System.out.println("Too few allready!");
                }
            }
        });
        startButton = new Button("Start Game");
        //Put player buttons into one
        HBox preSetupButtons = new HBox();
        preSetupButtons.getChildren().addAll(addPlayerButton, removePlayerButton, startButton);
        //Display a text for guidance 
        preGameText = new Text("Please fill player details");
        FlowPane preGameTextPane = new FlowPane(preGameText);

        actualPreGame.getChildren().addAll(selectionBoxesView, preSetupButtons, preGameTextPane);
        //Set up position of nodes 
        selectionBoxesView.setAlignment(Pos.CENTER);
        preSetupButtons.setAlignment(Pos.CENTER);
        preGameTextPane.setAlignment(Pos.CENTER);
        return actualPreGame;
    }

    private void setUpWeapons() {
        //------------------------CREATES WEAPONS---------------------------//
        //DO not yet place anywhere
        // Dagger, candlestick, revolver, rope, lead piping and spanner.
        board.initialiseWeapon("Dagger");
        board.initialiseWeapon("Candlestick");
        board.initialiseWeapon("Revolver");
        board.initialiseWeapon("Rope");
        board.initialiseWeapon("Leadpiping");
        board.initialiseWeapon("Spanner");
        //Sets up Images of weapons
        for (Weapon weapon : board.getWeapons()) {
            weapon.setWeaponImage(new Image("/weaponImages/" + weapon.getName() + ".png", 20, 20, false, false));
        }
    }

    private void setUpRooms() {
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
        Room bathroom = board.initialiseRoom("Bathroom", bathroomSpace, bathroomDoors);

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
        Room diningroom = board.initialiseRoom("Diningroom", diningroomSpace, diningroomDoors);

        ArrayList<Tile> kitchenSpace = new ArrayList<Tile>();
        ArrayList<Tile> kitchenDoors = new ArrayList<Tile>();
        for (int i = 21; i < 27; i++) {
            for (int j = 1; j < 7; j++) {
                kitchenSpace.add(board.getTileMap()[i][j]);
            }
        }
        Tile kitchenDoor = board.getTileMap()[21][7];
        kitchenDoors.add(kitchenDoor);
        Room kitchen = board.initialiseRoom("Kitchen", kitchenSpace, kitchenDoors);

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
        Room ballroom = board.initialiseRoom("Ballroom", ballroomSpace, ballroomDoors);

        ArrayList<Tile> conservatorySpace = new ArrayList<Tile>();
        ArrayList<Tile> conservatoryDoors = new ArrayList<Tile>();
        for (int i = 1; i < 6; i++) {
            for (int j = 23; j < 27; j++) {
                conservatorySpace.add(board.getTileMap()[i][j]);
            }
        }
        Tile conservatoryDoor = board.getTileMap()[5][22];
        conservatoryDoors.add(conservatoryDoor);
        Room conservatory = board.initialiseRoom("Conservatory", conservatorySpace, conservatoryDoors);

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
        Room gamesroom = board.initialiseRoom("Gamesroom", gamesroomSpace, gamesroomDoors);

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
        Room library = board.initialiseRoom("Library", librarySpace, libraryDoors);

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
        board.initialiseRoom("Hallway", hallwaySpace, hallwayDoors);

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
        Room office = board.initialiseRoom("Office", officeSpace, officeDoors);
    }

    /**
     * Creates Board, initialise Token at specified location and put in the
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
        suggestionBtn = new Button("Make Suggestion");
        showHandBtn = new Button("Show Hand");
        endTurnBtn = new Button("End Turn");
        controlsHbx = new HBox();
        controlsHbx.setAlignment(Pos.CENTER);

        gameViewHbx = new HBox();
        alertsVbx = new VBox();
        alertsVbx.setPrefSize(350, 300);
        alertsVbx.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        alertsVbx.setAlignment(Pos.CENTER);
        alertTxt = new Text();
        counterTxt = new Text();
        //Establish Board
        board = new Board(columns, rows);
        //Create Weapons on board
        setUpWeapons();
        //Create Rooms on board
        setUpRooms();
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

        //Initialise PlayerName List
        List<String> playerNamesList = new ArrayList<>();
        //Initialise Player Type list
        List<Character> playerTypesList = new ArrayList<>();
        //Iterate through total player number ( 6 )
        for (int i = 0; i < 6; i++) {
            //If player is defined in selection boxes , then receive selection boxes
            if (i < playerSelectionBoxesNumber) {
                playerNamesList.add(selectionBoxesList.get(i).getPlayerName());
                playerTypesList.add(selectionBoxesList.get(i).getPlayerType());
                System.out.println(playerNamesList.get(i) + "  is " + playerTypesList.get(i));
            } else {
                //If selection boxes are less than 6, create non-players,to fill up to 6
                playerNamesList.add("nonplayer");
                playerTypesList.add('a');
                System.out.println(playerNamesList.get(i) + " is " + playerTypesList.get(i));
            }
        }
        //Adds players and types ,creates the board
        board.addPlayers(playerNamesList, playerTypesList);

        ArrayList<String> tempCharacterNames = new ArrayList<>();

        tempCharacterNames.addAll(Arrays.asList(characters));

        //always create 6 players, create non playing players after playing players
        for (int i = 0; i < 6; i++) {
            if (i < playerSelectionBoxesNumber) {
                board.initialisePlayerToken(board.getPlayerList().get(i), selectionBoxesList.get(i).getPlayerCharacter());
                tempCharacterNames.remove(selectionBoxesList.get(i).getPlayerCharacter());
            } else {
                board.initialisePlayerToken(board.getPlayerList().get(i), tempCharacterNames.remove(0));
                board.getPlayerList().get(i).setIsPlaying(false);

            }
        }

        board.distributeCards();
        board.orderPlayerList();
        //Sets the current player to last player, then increments to start from beginning
        board.setCurrentPlayer(board.getPlayerList().get(board.getPlayerList().size() - 1));
        board.incrementCurrentPlayer();

        /////////////DISPLAY_OF_PLAYER_AND_TOKENS///////////////////
        //Sets up initial Player Tokens Positions and Colors 
        for (Player player : board.getPlayerList()) {
            switch (player.getToken().getName()) {
                case "Miss Scarlett": // Top Right
                    player.getToken().setFill(Color.CRIMSON);
                    player.getToken().setTokenLocation(board.getTileMap()[19][0]);
                    break;
                case "Colonel Mustard": // Right Top
                    player.getToken().setFill(Color.DARKORANGE);
                    player.getToken().setTokenLocation(board.getTileMap()[27][9]);
                    break;
                case "Mrs.White": // Bottom right
                    player.getToken().setFill(Color.WHITE);
                    player.getToken().setTokenLocation(board.getTileMap()[19][27]);
                    break;
                case "Mr.Green": //Bottom Left
                    player.getToken().setFill(Color.GREEN);
                    player.getToken().setTokenLocation(board.getTileMap()[7][27]);
                    break;
                case "Mrs.Peacock": // Left Bottom
                    player.getToken().setFill(Color.BLUEVIOLET);
                    player.getToken().setTokenLocation(board.getTileMap()[0][20]);
                    break;
                case "Professor Plum": // Left Top
                    player.getToken().setFill(Color.PLUM);
                    player.getToken().setTokenLocation(board.getTileMap()[0][5]);
                    break;
                default: //If error
                    player.getToken().setFill(Color.BLACK);
                    player.getToken().setTokenLocation(board.getTileMap()[10][0]);
                    break;
            }
        }

        //Sets up display of playerTokens and weapons in board
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
        //Combines Gui elements
        controlsHbx.getChildren().addAll(showHandBtn, suggestionBtn, endTurnBtn);
        alertsVbx.getChildren().addAll(alertTxt, counterTxt);
        gameViewHbx.getChildren().addAll(boardView, alertsVbx);
        gameBox.getChildren().addAll(diceRollerView, gameViewHbx, controlsHbx);
        gameBox.setAlignment(Pos.CENTER);
        return gameBox;
    }

    /**
     * sets up handlers for key presses during game play, including human player
     * movement controls
     */
    @Override
    public void setUpControls() {
        gameScene.setOnKeyPressed((KeyEvent event) -> {
            if (!board.getCurrentPlayer().isAgent()) {
                switch (event.getCode()) {
                    case W://go up
                        board.moveCurrentPlayer(board.getCurrentPlayer().getToken().getTokenLocation().getColIndex(),
                                (board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex() - 1), diceRoller.isDiceRolled(), diceRoller.getDiceTotal());
                        updateMovementAlerts();
                        break;
                    case S:// go down
                        board.moveCurrentPlayer(board.getCurrentPlayer().getToken().getTokenLocation().getColIndex(),
                                (board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex() + 1), diceRoller.isDiceRolled(), diceRoller.getDiceTotal());
                        updateMovementAlerts();
                        break;
                    case A://go left
                        board.moveCurrentPlayer((board.getCurrentPlayer().getToken().getTokenLocation().getColIndex() - 1),
                                board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex(), diceRoller.isDiceRolled(), diceRoller.getDiceTotal());
                        updateMovementAlerts();
                        break;
                    case D:// go right
                        board.moveCurrentPlayer((board.getCurrentPlayer().getToken().getTokenLocation().getColIndex() + 1),
                                board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex(), diceRoller.isDiceRolled(), diceRoller.getDiceTotal());
                        updateMovementAlerts();
                        break;
                    default://Non valid Ket
                        alertTxt.setText("Not Valid Key");
                        System.out.println("Not Valid Key");
                        break;
                }
                //--------------reset dice here just for testing -------------//
                if (!(board.getCounter() < diceRoller.getDiceTotal())) {
                    resetDice();
                }
                updateView();
            } else {
                System.out.println("Agent Players turn");
                alertTxt.setText("Agent Players Turn");
            }
        });

    }

    /**
     * Updates GUI by removing all the tokens and tile text from boardView then adding them back
     * again in their updated positions/states
     */
    @Override
    public void updateView() {
        for (Player p : board.getPlayerList()) {
            boardView.getChildren().remove(p.getToken());
            boardView.add(p.getToken(), p.getToken().getTokenLocation().getColIndex(), p.getToken().getTokenLocation().getRowIndex());
        }
        for (Room r: board.getRooms()){
            for (Tile t: r.getRoomDoors()){
                boardView.getChildren().remove(t.getText());
                boardView.add(t.getText(), t.getColIndex(), t.getRowIndex());
            }
        }
    }

    /**
     * Starts the prototype GUI
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        preGameScene = new Scene(CreatePreGameContent(), 600, 300);
        primaryStage.setTitle("Please Choose Characters!");
        primaryStage.setScene(preGameScene);
        primaryStage.show();

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                boolean IsInitialisable = true;
                ArrayList<String> characterRepetitionchecklist = new ArrayList<String>();
                //Checks if any variable is missing
                for (PlayerSelectionBox playerselectionbox : selectionBoxesList) {
                    playerselectionbox.playerTextField.fireEvent(e); // Gets value of textField
                    //Checks unfilled variables 
                    if (playerselectionbox.getPlayerName().isEmpty() || !Arrays.asList(characters).contains(playerselectionbox.getPlayerCharacter()) || (!playerselectionbox.agentButton.isSelected() && !playerselectionbox.humanButton.isSelected())) {
                        //In any errors, prevents initialisation of the game
                        preGameText.setText("Please fill player details completely !!!");
                        IsInitialisable = false;
                        break;
                    }
                    //Checks for repetition
                    if (!characterRepetitionchecklist.contains(playerselectionbox.getPlayerCharacter())) {
                        characterRepetitionchecklist.add(playerselectionbox.getPlayerCharacter());
                    } else {
                        preGameText.setText("A character cannot be chosen more than once !!!");
                        IsInitialisable = false;
                    }
                }
                //when setup fullfils all requirements, game can be started
                if (IsInitialisable) {
                    startButton.setDisable(true);
                    //For setting gameScene and showing labels
                    setUpBoard();
                    gameScene = new Scene(gameBox);
                    primaryStage.setTitle("Cluedo!!!");
                    primaryStage.setScene(gameScene);
                    setUpControls();
                    //Starts the game
                    /*Increments the current player*/
                    endTurnBtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            board.incrementCurrentPlayer();
                            resetDice();
                            alertTxt.setText("Current Player: " + board.getCurrentPlayer().getName());
                            counterTxt.setText("Please Roll The Dice");
                            
                            for(Room r: board.getRooms()){
                                for(int i = 0; i < r.getRoomDoors().size(); i++){
                                    if(board.getRoomOfPlayer(board.getCurrentPlayer()) != r){
                                        r.getRoomDoors().get(i).getText().setText("");
                                    }
                                    else{
                                        r.getRoomDoors().get(i).getText().setText("" + (i+1));
                                    }
                                }
                            }
                            //if current player is now ai handle their turn
                            if (board.getCurrentPlayer().isAgent()) {
                                handleAgentTurn();
                            }
                        }
                    });
                    //Shows Your hand
                    showHandBtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            displayCardList(primaryStage);
                        }
                    });

                    suggestionBtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            //Create new Stage for popup
                            Stage suggestionStage = new Stage();
                            suggestionStage.initModality(Modality.APPLICATION_MODAL);
                            // Create new suggestionpanel put panel into new scene
                            SuggestionPanel suggestionPanel = new SuggestionPanel();
                            Scene suggestionScene = new Scene(suggestionPanel.createSuggestionContent());
                            suggestionStage.setScene(suggestionScene);
                            suggestionStage.show();
                            //On click of submit button,suggestion takes place
                            suggestionPanel.submitButton.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    if(suggestionPanel.getSuggestedSuspect()!=null && suggestionPanel.getSuggestedRoom()!=null && suggestionPanel.getSuggestedWeapon()!=null){
                                        String currentplayerName = board.getCurrentPlayer().getName();
                                        alertTxt.setText("Player "+currentplayerName+ " suggested "+ suggestionPanel.getSuggestedSuspect() 
                                                + " murdered in " + suggestionPanel.getSuggestedRoom() + "with a" +suggestionPanel.getSuggestedWeapon());
                                        suggestionStage.close();
                                        
                                    } else{
                                        System.out.println("Please fill all suggestion boxes");
                                    }
 
                                }
                            });

                        }
                    });

                    if (board.getCurrentPlayer().isAgent() && board.getCurrentPlayer().getIsPlaying()) {
                        handleAgentTurn();
                    }
                }
            }
        });
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

    /**
     * controls the actions of an agent player during its turn
     */
    private void handleAgentTurn() {
        //----agent just moves for now----//
        endTurnBtn.setDisable(true);
        //rolls the dice
        diceRoller.getRollButton().fire();

        //use current agent to make sure thread doesn't try to move the next player 
        Player currentAgent = board.getCurrentPlayer();
        //Starts the thread
        Thread thread = new Thread(() -> {
            Runnable updater = () -> handleAgentMove(currentAgent);
            while (board.getCounter() < diceRoller.getDiceTotal()) {
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

    /**
     * handles movement of agent player p, calls updateView() after each move to
     * see agent moves in real time on the board
     *
     * @param p
     */
    private void handleAgentMove(Player p) {
        if (board.getCounter() < diceRoller.getDiceTotal() && (board.getCurrentPlayer() == p)) {
            Integer[] newCoords = board.getCurrentPlayer().getMove(board.getCurrentPlayer().getToken().getTokenLocation().getColIndex(), board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex());
            board.moveCurrentPlayer(newCoords[0], newCoords[1], diceRoller.isDiceRolled(), diceRoller.getDiceTotal());
            counterTxt.setText("Moves Left:" + (diceRoller.getDiceTotal() - board.getCounter()));
            updateView();
        }
    }

    @Override
    public void selectCharacters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * opens a dialog on the stage pStage to show a list of player cards
     *
     * @param pStage
     */
    @Override
    public void displayCardList(Stage pStage) {
        if (!board.getCurrentPlayer().isAgent()) {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(pStage);
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
            alertTxt.setText("Agent Player Turn");
        }
    }

    /**
     * Assigns the existing token on board on some player
     *
     * @param player
     * @param token
     *
     * public void assignTokenToPlayer(Player player, Token token) { try {
     * player.setToken(token); } catch (Exception e) {
     * System.out.println("Cannot take this Character"); } }
     *
     */
    /**
     * resets the dice and counter
     */
    private void resetDice() {
        //Sets Counter to 0
        board.setCounter(0);
        //Set Dice Rolled to false and Enables DiceRoller
        diceRoller.setDiceRolled(false);
        diceRoller.enableDiceRollerButton();
    }

    private void updateMovementAlerts() {
        if (diceRoller.isDiceRolled()) {
            counterTxt.setText("Moves Left:" + (diceRoller.getDiceTotal() - board.getCounter()));
        }
        alertTxt.setText(board.getAlertMsg());
    }

}
