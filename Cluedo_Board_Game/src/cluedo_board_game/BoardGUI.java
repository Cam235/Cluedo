
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
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * Representation of the Cluedo game, where characters are chosen and game is
 * played
 *
 * @author Anilz
 * @version 4.0
 */
public class BoardGUI extends Application {

    //-------Most Basics---------//
    private Stage primaryStage; //A global stage which hold pregameScene, gameScene and facilitate restart,start new game
    private Board board; // Board object created to apply board functions and play the game
    boolean isRunning = true; //Field to start game after successful character selection
    //-----For the preGame Window-------//
    private Scene preGameScene; //pregame Scene
    //Setting of minimum player selection box number,and create arraylist with defined size
    private int minSelectionBoxes = 2;
    private ArrayList<PlayerSelectionBox> selectionBoxesList = new ArrayList<>();
    private Text preGameText; // text for pregame guidence    
    private Button startButton; //Pregame button to start the game

    //-----For the Game Window-------//
    private Scene gameScene; //Game scene    
    private VBox gameBox; //Contains the gameContent        
    private GridPane boardView; // GridPane for gameboard

    //Number of Rows and Column of boards tileMap(boardGameView), and Tile Size
    private static final int TILE_SIZE = 20;
    private final int columns = 28;
    private final int rows = 28;

    
    //Game Buttons(Left hand side of the board)
    private Button showHandBtn;
    private Button detectiveCardButton;
    private Button suggestionBtn;
    private Button accusationBtn;
    private Button endTurnBtn;
    private Button passageBtn;
    //Control box to include all buttons
    private VBox controlsVbx;

    //Profile,alert and diceroller items(On right hand side of board)
    Text currentPlayerNameText;
    Image currentPlayerImage;
    ImageView currentPlayerImageView;
    //Alert and counter texts
    private Text alertTxt;
    private Text counterTxt;
    //Diceroller object to play game
    private DiceRoller diceRoller; 
    //Profile and alertBox to include all from players name text to diceroller
    private VBox profileAndAlertVBox; 
    
    //Adding MenuBar and items for gameSettings( Top left corner of the game)
    MenuBar gameSettingsMenuBar;
    Menu gameSettingsMenu;
    MenuItem quitItem;//Button to quit game
    MenuItem newGameItem;//Button to start new game
    MenuItem restartItem;//Button to restart game
    
    //Weapon Image Views which are displayed in the room weapon belong to.
    private ArrayList<ImageView> weaponImageViews = new ArrayList<>();
    //Card name values for accusation,suggestions and Image setting functions
    private final String[] characterNames = {"Miss Scarlett", "Colonel Mustard", "Mrs.White", "Mrs.Peacock", "Mr.Green", "Professor Plum"};
    private final String[] weaponNames = {"Dagger", "Candlestick", "Revolver", "Rope", "Leadpiping", "Spanner"};
    private final String[] roomNames = {"Kitchen", "Diningroom", "Lounge", "Ballroom", "Conservatory", "Billiardroom", "Library", "Hall", "Study"};

    //-------Accusation and Suggestion panels------//
    //the suggestion Panel and stage
    private SuggestionPanel suggestionPanel;
    private Stage suggestionStage;
    //For acqusationPanel and Stage for accusation attempts
    private AccusationPanel accusationPanel;
    private Stage accusationStage;



    public VBox createPreGameContent() {

        VBox preGameBox = new VBox();
        preGameBox.setAlignment(Pos.TOP_CENTER);
        preGameBox.setPrefSize(650, 280);
        VBox selectionBoxesView = new VBox();
        HBox characterSelectionViews = new HBox();
        characterSelectionViews.setSpacing(5);
        for (int i = 0; i < minSelectionBoxes; i++) {
            final int nodeIndex = i;
            PlayerSelectionBox newSelectionBox = new PlayerSelectionBox();
            selectionBoxesList.add(newSelectionBox);
            selectionBoxesView.getChildren().add(newSelectionBox.selectionContent());
            VBox nameAndDisplay = new VBox(new Text(""), selectionBoxesList.get(i).getSelectedCharacterView());
            characterSelectionViews.getChildren().add(nameAndDisplay);
            newSelectionBox.getCharacterSelectionCombobox().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    newSelectionBox.setSelectedCharacter(newSelectionBox.getCharacterSelectionCombobox().getValue());
                    System.out.println(newSelectionBox.getSelectedCharacter());
                    newSelectionBox.setSelectedCharacterImage(new Image("/CharacterCards/" + newSelectionBox.getSelectedCharacter() + ".jpg", 130, 200, false, false));
                    newSelectionBox.setSelectedCharacterView(new ImageView(newSelectionBox.getSelectedCharacterImage()));

                    Text playerNameDisplay = new Text(newSelectionBox.getPlayerTextField().getText());
                    VBox nameAndDisplay = new VBox(playerNameDisplay, newSelectionBox.getSelectedCharacterView());
                    nameAndDisplay.setAlignment(Pos.CENTER);
                    characterSelectionViews.getChildren().remove(nodeIndex);
                    characterSelectionViews.getChildren().add(nodeIndex, nameAndDisplay);
                }
            });
            newSelectionBox.getPlayerTextField().setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent key) {
                    Text playerNameDisplay = new Text(newSelectionBox.getPlayerTextField().getText());
                    VBox nameAndDisplay = new VBox(playerNameDisplay, newSelectionBox.getSelectedCharacterView());
                    nameAndDisplay.setAlignment(Pos.CENTER);
                    characterSelectionViews.getChildren().remove(nodeIndex);
                    characterSelectionViews.getChildren().add(nodeIndex, nameAndDisplay);
                }
            });
            newSelectionBox.getPlayerTextField().setOnKeyTyped(event -> {
                int maxCharacters = 16;
                if (newSelectionBox.getPlayerTextField().getText().length() > maxCharacters) {
                    preGameText.setText("Maximum of 16 characters for player names");
                    event.consume();
                }
            });
        }

        //Adds player buttons
        Button addPlayerButton = new Button("+ Add Player");
        addPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (minSelectionBoxes < 6) {
                    final int nodeIndex = minSelectionBoxes;
                    PlayerSelectionBox newSelectionBox = new PlayerSelectionBox();
                    selectionBoxesList.add(newSelectionBox);
                    selectionBoxesView.getChildren().add(newSelectionBox.selectionContent());
                    minSelectionBoxes++;
                    VBox nameAndDisplay = new VBox(new Text(""), selectionBoxesList.get(selectionBoxesList.size() - 1).getSelectedCharacterView());
                    characterSelectionViews.getChildren().add(nameAndDisplay);
                    newSelectionBox.getCharacterSelectionCombobox().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            newSelectionBox.setSelectedCharacter((String) newSelectionBox.getCharacterSelectionCombobox().getValue());
                            System.out.println(newSelectionBox.getSelectedCharacter());
                            newSelectionBox.setSelectedCharacterImage(new Image("/CharacterCards/" + newSelectionBox.getSelectedCharacter() + ".jpg", 130, 200, false, false));
                            newSelectionBox.setSelectedCharacterView(new ImageView(newSelectionBox.getSelectedCharacterImage()));

                            Text playerNameDisplay = new Text(newSelectionBox.getPlayerTextField().getText());
                            VBox nameAndDisplay = new VBox(playerNameDisplay, newSelectionBox.getSelectedCharacterView());
                            nameAndDisplay.setAlignment(Pos.CENTER);
                            characterSelectionViews.getChildren().remove(nodeIndex);
                            characterSelectionViews.getChildren().add(nodeIndex, nameAndDisplay);
                        }

                    });
                    newSelectionBox.getPlayerTextField().setOnKeyReleased(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent key) {
                            Text playerNameDisplay = new Text(newSelectionBox.getPlayerTextField().getText());
                            VBox nameAndDisplay = new VBox(playerNameDisplay, newSelectionBox.getSelectedCharacterView());
                            nameAndDisplay.setAlignment(Pos.CENTER);
                            characterSelectionViews.getChildren().remove(nodeIndex);
                            characterSelectionViews.getChildren().add(nodeIndex, nameAndDisplay);
                        }
                    });
                    newSelectionBox.getPlayerTextField().setOnKeyTyped(e -> {
                        int maxCharacters = 16;
                        if (newSelectionBox.getPlayerTextField().getText().length() > maxCharacters) {
                            preGameText.setText("Maximum of 16 characters for player names");
                            e.consume();
                        }
                    });
                    System.out.println(minSelectionBoxes);
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
                if (minSelectionBoxes > 2) {
                    selectionBoxesList.remove(selectionBoxesList.size() - 1);
                    selectionBoxesView.getChildren().remove(selectionBoxesView.getChildren().size() - 1);
                    characterSelectionViews.getChildren().remove(characterSelectionViews.getChildren().size() - 1);
                    minSelectionBoxes--;
                    System.out.println(minSelectionBoxes);
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
        preGameText = new Text("Please fill in player details");
        FlowPane preGameTextPane = new FlowPane(preGameText);
        preGameBox.getChildren().addAll(selectionBoxesView, preSetupButtons, characterSelectionViews, preGameTextPane);
        //Set up position of nodes 
        characterSelectionViews.setAlignment(Pos.CENTER);
        selectionBoxesView.setAlignment(Pos.CENTER);
        preSetupButtons.setAlignment(Pos.CENTER);
        preGameTextPane.setAlignment(Pos.CENTER);

        //Create background
        Background bg = new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY));
        // set background
        preGameBox.setBackground(bg);

        // scene setting
        return preGameBox;
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
        //////////////////CREATES 9 ROOMS - LOUNGE, DININGROOM, KITCHEN, BALLROOM, CONSERVATORY, BILLIARDROOM, LIBRARY, STUDY, HALL////////////////////// 
        ArrayList<Tile> kitchenSpace = new ArrayList<>();
        ArrayList<Tile> kitchenDoors = new ArrayList<>();
        for (int i = 22; i < 27; i++) {
            for (int j = 22; j < 27; j++) {
                kitchenSpace.add(board.getTileMap()[i][j]);
            }
        }
        Tile kitchenDoor = board.getTileMap()[22][21];
        kitchenDoors.add(kitchenDoor);
        Room kitchen = board.initialiseRoom("Kitchen", kitchenSpace, kitchenDoors);

        ArrayList<Tile> diningroomSpace = new ArrayList<>();
        ArrayList<Tile> diningroomDoors = new ArrayList<>();
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

        ArrayList<Tile> loungeSpace = new ArrayList<>();
        ArrayList<Tile> loungeDoors = new ArrayList<>();
        for (int i = 21; i < 27; i++) {
            for (int j = 1; j < 7; j++) {
                loungeSpace.add(board.getTileMap()[i][j]);
            }
        }
        Tile loungeDoor = board.getTileMap()[21][7];
        loungeDoors.add(loungeDoor);
        Room lounge = board.initialiseRoom("Lounge", loungeSpace, loungeDoors);

        ArrayList<Tile> ballroomSpace = new ArrayList<>();
        ArrayList<Tile> ballroomDoors = new ArrayList<>();
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

        ArrayList<Tile> conservatorySpace = new ArrayList<>();
        ArrayList<Tile> conservatoryDoors = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            for (int j = 23; j < 27; j++) {
                conservatorySpace.add(board.getTileMap()[i][j]);
            }
        }
        Tile conservatoryDoor = board.getTileMap()[5][22];
        conservatoryDoors.add(conservatoryDoor);
        Room conservatory = board.initialiseRoom("Conservatory", conservatorySpace, conservatoryDoors);

        ArrayList<Tile> billiardroomSpace = new ArrayList<>();
        ArrayList<Tile> billiardroomDoors = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            for (int j = 15; j < 19; j++) {
                billiardroomSpace.add(board.getTileMap()[i][j]);
            }
        }
        Tile billiardroomDoor = board.getTileMap()[1][14];
        Tile billiardroomDoor2 = board.getTileMap()[6][18];
        billiardroomDoors.add(billiardroomDoor);
        billiardroomDoors.add(billiardroomDoor2);
        Room billiardroom = board.initialiseRoom("Billiardroom", billiardroomSpace, billiardroomDoors);

        ArrayList<Tile> librarySpace = new ArrayList<>();
        ArrayList<Tile> libraryDoors = new ArrayList<>();
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

        // HALL STUFF
        ArrayList<Tile> hallSpace = new ArrayList<>();
        ArrayList<Tile> hallDoors = new ArrayList<>();
        for (int i = 11; i < 17; i++) {
            for (int j = 1; j < 7; j++) {
                hallSpace.add(board.getTileMap()[i][j]);
            }
        }
        Tile hallDoor1 = board.getTileMap()[13][7];
        Tile hallDoor2 = board.getTileMap()[14][7];
        Tile hallDoor3 = board.getTileMap()[8][4];
        hallDoors.add(hallDoor1);
        hallDoors.add(hallDoor2);
        hallDoors.add(hallDoor3);
        board.initialiseRoom("Hall", hallSpace, hallDoors);

        //STUDY
        ArrayList<Tile> studySpace = new ArrayList<>();
        ArrayList<Tile> studyDoors = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 4; j++) {
                studySpace.add(board.getTileMap()[i][j]);
            }
        }
        Tile studyDoor = board.getTileMap()[6][4];
        studyDoors.add(studyDoor);
        Room study = board.initialiseRoom("Study", studySpace, studyDoors);

        study.setPassageExit(kitchen);
        kitchen.setPassageExit(study);
        lounge.setPassageExit(conservatory);
        conservatory.setPassageExit(lounge);

        //creates staircase
        ArrayList<Tile> staircaseSpace = new ArrayList<>();
        for (int i = 11; i < 16; i++) {
            for (int j = 11; j < 18; j++) {
                staircaseSpace.add(board.getTileMap()[i][j]);
            }
        }
        ArrayList<Tile> staircaseDoors = new ArrayList<>();
        board.initialiseRoom("Staircase", staircaseSpace, staircaseDoors);
    }

    /**
     * Creates Board, initialise Token at specified location and put in the
     * boardView Creates DiceRoller object Combines 2 different classes in VBox
     *
     * @return
     */
    public VBox setUpBoard() {
        gameBox = new VBox();
        HBox gameBoxWithoutSettings = new HBox();
        //DiceRoller added to play with dice
        diceRoller = new DiceRoller();
        VBox diceRollerView = diceRoller.createContent();
        //Button to switch between Player and AI
        detectiveCardButton = new Button("Check Detective Card");
        detectiveCardButton.setTextAlignment(TextAlignment.CENTER);
        detectiveCardButton.setWrapText(true);
        detectiveCardButton.setPrefSize(130, 100);
        detectiveCardButton.setStyle(" -fx-font-size: 15px; -fx-font-weight : bold ;");
        //Suggestion Button
        suggestionBtn = new Button("Make Suggestion");
        suggestionBtn.setTextAlignment(TextAlignment.CENTER);
        suggestionBtn.setWrapText(true);
        suggestionBtn.setPrefSize(130, 100);
        suggestionBtn.setStyle(" -fx-font-size: 15px; -fx-font-weight : bold ;");
        //Accusation Button
        accusationBtn = new Button("Make Accusation");
        accusationBtn.setTextAlignment(TextAlignment.CENTER);
        accusationBtn.setWrapText(true);
        accusationBtn.setPrefSize(130, 100);
        accusationBtn.setStyle(" -fx-font-size: 15px; -fx-font-weight : bold ;");
        //Show Cards Button
        showHandBtn = new Button("Check Hand");
        showHandBtn.setTextAlignment(TextAlignment.CENTER);
        showHandBtn.setWrapText(true);
        showHandBtn.setPrefSize(130, 100);
        showHandBtn.setStyle(" -fx-font-size: 15px; -fx-font-weight : bold ;");
        //End Turn Button
        endTurnBtn = new Button("End Turn");
        endTurnBtn.setTextAlignment(TextAlignment.CENTER);
        endTurnBtn.setWrapText(true);
        endTurnBtn.setPrefSize(130, 100);
        endTurnBtn.setStyle(" -fx-font-size: 15px; -fx-font-weight : bold ;");

        passageBtn = new Button("Take passage");
        passageBtn.setTextAlignment(TextAlignment.CENTER);
        passageBtn.setWrapText(true);
        passageBtn.setPrefSize(130, 100);
        passageBtn.setVisible(false);
        passageBtn.setStyle(" -fx-font-size: 15px; -fx-font-weight : bold ;");

        controlsVbx = new VBox();
        controlsVbx.setAlignment(Pos.CENTER);

        profileAndAlertVBox = new VBox();
        profileAndAlertVBox.setMaxWidth(300);
        profileAndAlertVBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        profileAndAlertVBox.setAlignment(Pos.CENTER);

        //Informative texts for user interface
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
            if (!board.getRooms().get(i).getName().equals("Staircase")) {
                // Puts the weapons into rooms
                board.moveWeaponToRoom(board.getRooms().get(i), board.getWeapons().get(i));
                //System.out.println(board.getRooms().get(i).getRoomWeapon().getName() + "is in "+board.getRooms().get(i).getRoomName());
            }
        }

        ////////////////////////////////////////GRIDPANE//////////////////////////////////////
        // create a background fill        
        //Establish array of rectangles
        boardView = new GridPane();
        //Set up the Image of Board
        for (int _r = 0; _r < rows; _r++) {
            for (int _c = 0; _c < columns; _c++) {

                board.getTileMap()[_c][_r].setWidth(TILE_SIZE);
                board.getTileMap()[_c][_r].setHeight(TILE_SIZE);
                try {
                    Image i = new Image("/tile_textures/" + _r + "/" + _c + ".png", 21, 21, false, false);
                    board.getTileMap()[_c][_r].setImage(i);
                    ImageView tileView = new ImageView(board.getTileMap()[_c][_r].getImage());
                    tileView.setSmooth(true);
                    boardView.add(tileView, _c, _r);

                } catch (Exception e) {
                    board.getTileMap()[_c][_r].setFill(Color.GOLD);
                    board.getTileMap()[_c][_r].setStroke(Color.BLACK);
                    boardView.add(board.getTileMap()[_c][_r], _c, _r);
                }

            }
        }
        //To place staircase
        for (int _r = 10; _r < 19; _r++) {
            for (int _c = 10; _c < 17; _c++) {
                int currentTextureRow = _r - 9;
                int currentTextureCol = _c - 9;
                Image stairCaseTileImage = new Image("/Staircase_textures/image_" + currentTextureRow + "_" + currentTextureCol + ".png", 21, 21, false, false);
                board.getTileMap()[_c][_r].setImage(stairCaseTileImage);
                ImageView tileView = new ImageView(board.getTileMap()[_c][_r].getImage());
                boardView.add(tileView, _c, _r);
            }
        }

        //add door text objects to board
        for (Room r : board.getRooms()) {
            for (Tile t : r.getRoomDoors()) {
                boardView.add(t.getText(), t.getColIndex(), t.getRowIndex());
                boardView.setHalignment(t.getText(), HPos.CENTER);
            }
        }
        setUpPassageBtn();

        //Initialise PlayerName List
        List<String> playerNamesList = new ArrayList<>();
        //Initialise Player Type list
        List<Character> playerTypesList = new ArrayList<>();
        //Iterate through total player number ( 6 )
        for (int i = 0; i < 6; i++) {
            //If player is defined in selection boxes , then receive selection boxes
            if (i < minSelectionBoxes) {
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

        tempCharacterNames.addAll(Arrays.asList(characterNames));

        //always create 6 players, create non playing players after playing players
        for (int i = 0; i < 6; i++) {
            if (i < minSelectionBoxes) {
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
                    player.getToken().setFill(Color.AQUA);
                    player.getToken().setTokenLocation(board.getTileMap()[0][20]);
                    break;
                case "Professor Plum": // Left Top
                    player.getToken().setFill(Color.PURPLE);
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
                    if (board.getTileMap()[_c][_r].isOccupied() && p.getToken().getTokenLocation() == board.getTileMap()[_c][_r]) {
                        boardView.add(p.getToken(), _c, _r);
                    }
                }
                for (Weapon weapon : board.getWeapons()) {
                    //Gets the first placed weapons location
                    if (board.getTileMap()[_c][_r].equals(weapon.getWeaponLocation())) {
                        weaponImageViews.add(new ImageView(weapon.getWeaponImage()));
                        boardView.add(weaponImageViews.get(weaponImageViews.size() - 1), _c, _r);
                    }
                }
            }
        }

        //Give players required stuff
        board.initialisePlayerDetectiveCards();
        //Displays current players Image
        currentPlayerNameText = new Text(board.getCurrentPlayer().getName() + " : " + board.getCurrentPlayer().getToken().getName() + "'s turn!");
        currentPlayerNameText.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        currentPlayerNameText.setWrappingWidth(170);
        currentPlayerImage = new Image("/CharacterCards/" + board.getCurrentPlayer().getToken().getName() + ".jpg", 160, 250, false, false);
        currentPlayerImageView = new ImageView(currentPlayerImage);

        alertTxt.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        alertTxt.setWrappingWidth(170);
        counterTxt.setFont(Font.font("Verdana", FontPosture.REGULAR, 13));

        counterTxt.setWrappingWidth(100);
        controlsVbx.getChildren().addAll(showHandBtn, detectiveCardButton, suggestionBtn, accusationBtn, endTurnBtn, passageBtn);
        controlsVbx.setAlignment(Pos.TOP_CENTER);
        //controlsVbx.setSpacing(5);
        profileAndAlertVBox.getChildren().addAll(currentPlayerNameText, currentPlayerImageView, counterTxt, diceRollerView, alertTxt);
        currentPlayerNameText.setTextAlignment(TextAlignment.CENTER);
        alertTxt.setTextAlignment(TextAlignment.CENTER);
        counterTxt.setTextAlignment(TextAlignment.CENTER);

        profileAndAlertVBox.setAlignment(Pos.TOP_CENTER);
        profileAndAlertVBox.setSpacing(10);

        gameBoxWithoutSettings.getChildren().addAll(controlsVbx, boardView, profileAndAlertVBox);
        gameBoxWithoutSettings.setAlignment(Pos.TOP_CENTER);

        //Create GameSettings MenuBAr
        gameSettingsMenu = new Menu("Game Settings");
        quitItem = new MenuItem("Quit");
        newGameItem = new MenuItem("Start New Game");
        restartItem = new MenuItem("Restart");
        gameSettingsMenu.getItems().addAll(quitItem, newGameItem, restartItem);
        MenuBar menuBar = new MenuBar(gameSettingsMenu);
        //Actions on menuItems
        quitItem.setOnAction(e -> Platform.exit());
        newGameItem.setOnAction(e -> startNewGame());
        restartItem.setOnAction(e -> playGame(primaryStage));

        //Create background
        Background bg = new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY));

        // set background
        gameBox.setBackground(bg);
        gameBox.getChildren().addAll(menuBar, gameBoxWithoutSettings);

        return gameBox;
    }

    /**
     * handles a suggestion with a given suspected character, weapon and rooms
     * name
     *
     * @param characterName
     * @param roomName
     * @param weaponName
     */
    private void handleSuggestion(String characterName, String roomName, String weaponName) {
        board.getCurrentPlayer().setMostRecentlySuggestedRoom(roomName);
        //Set suggestion alertText as 
        alertTxt.setText(board.getCurrentPlayer().getName() + " suggested " + characterName
                + "\n" + " commited murder in " + roomName + " with a " + weaponName);
        //Call suggested token into room
        Room suggestedRoom = board.getRoomFromName(roomName);
        for (Player p : board.getPlayerList()) {
            if (p.getToken().getName().equals(characterName)) {
                board.playerEntersRoom(p, suggestedRoom);
                updateView();
                break;
            }
        }
        //move suggested weapon into room
        for (Weapon weapon : board.getWeapons()) {
            //If room does not already contain suggested weapon
            if (weapon.getName().equals(weaponName)
                    && !suggestedRoom.getRoomWeapons().contains(weapon)) {
                board.moveWeaponToRoom(suggestedRoom, weapon);
                for (ImageView weaponImageView : weaponImageViews) {
                    if (weaponImageView.getImage().equals(weapon.getWeaponImage())) {
                        boardView.getChildren().remove(weaponImageView);
                        boardView.add(weaponImageView, weapon.getWeaponLocation().getColIndex(), weapon.getWeaponLocation().getRowIndex());
                    }
                }
            }
        }

        //represents whether the suggested cards have been found in another players hand
        boolean suggestedCardFound = false;
        //loop to get hand of players starting from one player after the current player
        for (int i = 0; i < board.getPlayerList().size(); i++) {
            //pointer j is used to iterate through all other players until index hits the current player again  
            int j = (i + board.getPlayerList().indexOf(board.getCurrentPlayer())) % board.getPlayerList().size();
            //get the next player that is not a non-playing player and not the currentPlayer
            if ((board.getPlayerList().get(j).getHand() != null) && !board.getPlayerList().get(j).equals(board.getCurrentPlayer())) {
                //if players hand includes suggested cards, suggestedCardfound becomes true and loop breaks
                ArrayList<String> foundCards = new ArrayList<>();
                for (Card card : board.getPlayerList().get(j).getHand()) {
                    if (card.getName().equals(characterName)
                            || card.getName().equals(weaponName)
                            || card.getName().equals(roomName)) {
                        foundCards.add(card.getName());
                    }
                }
                //if suggested cards appear in players hand, make the player show a card and stop searching
                if (!foundCards.isEmpty()) {
                    suggestedCardFound = true;
                    Alert postSuggestionAlert;
                    //if responding player is agent, shows first found card
                    if (board.getPlayerList().get(j).isAgent()) {
                        //if an agent is showing an agent a card do it in secret
                        if (board.getCurrentPlayer().isAgent()) {
                            suggestionPanel = new SuggestionPanel();
                            board.getCurrentPlayer().updateDetectiveChecklist(foundCards.get(0), true);
                            alertTxt.setText(alertTxt.getText() + " " + board.getPlayerList().get(j).getName() + " shows them a card");
                            postSuggestionAlert = suggestionPanel.createPostAgentSuggestionAlert(board.getCurrentPlayer().getName(),
                                    board.getPlayerList().get(j).getName(), characterName, roomName, weaponName);
                            postSuggestionAlert.showAndWait();
                        }//else show an alert to the person for which card is shown
                        else {
                            postSuggestionAlert = suggestionPanel.createPostHumanSuggestionAlert(board.getPlayerList().get(j).getName(), foundCards.get(0));
                            postSuggestionAlert.showAndWait();
                        }
                    } //otherwise make the player choose a card to show
                    else {
                        suggestionStage.close();
                        ChoiceDialog responderChoiceBox = suggestionPanel.createSuggestionResponderContent(board.getPlayerList().get(j).getName(), foundCards);
                        boolean validItemChosen = false;
                        while (!validItemChosen) {
                            responderChoiceBox.showAndWait();
                            if (!responderChoiceBox.getSelectedItem().equals("")) {
                                validItemChosen = true;
                                //if a human is showing an agent a card do it in secret
                                if (board.getCurrentPlayer().isAgent()) {
                                    board.getCurrentPlayer().updateDetectiveChecklist(foundCards.get(0), true);
                                    alertTxt.setText(alertTxt.getText() + " " + board.getPlayerList().get(j).getName() + " shows them a card");
                                    postSuggestionAlert = suggestionPanel.createPostAgentSuggestionAlert(board.getCurrentPlayer().getName(),
                                            board.getPlayerList().get(j).getName(), characterName, roomName, weaponName);
                                    postSuggestionAlert.showAndWait();
                                }//else show an alert to the person for which card is shown 
                                else {
                                    postSuggestionAlert = suggestionPanel.createPostHumanSuggestionAlert(board.getPlayerList().get(j).getName(), (String) responderChoiceBox.getSelectedItem());
                                    postSuggestionAlert.showAndWait();
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
        //if no other player has a suggested card, give a message
        if (!suggestedCardFound) {
            Alert noPlayerHasCardAlert = suggestionPanel.createCardNotFoundAlert(board.getCurrentPlayer().isAgent(),
                    board.getCurrentPlayer().getName(), characterName, roomName, weaponName);
            noPlayerHasCardAlert.showAndWait();
        }
    }

    private void handleAccusation(String characterName, String weaponName, String roomName) {
        //card distributor from board is used here for murder envlope functionality
        CardDistributor cardDistributor = board.getCardDistributor();
        //compare accusation with murder envelope, if accusation is correct
        if (cardDistributor.getMurderRoom().getName().equals(roomName)
                && cardDistributor.getMurderWeapon().getName().equals(weaponName)
                && cardDistributor.getMurderer().getName().equals(characterName)) {
            //if accuser is an agent set up cards display
            if (board.getCurrentPlayer().isAgent()) {
                accusationPanel.initialiseCardsDisplay(characterName, roomName, weaponName);
            }
            //create correct accusation alert
            Alert correctAccusationAlert = accusationPanel.createCorrectAccusationContent(board.getCurrentPlayer().getName(),
                    cardDistributor.getMurderer().getName(),
                    cardDistributor.getMurderRoom().getName(),
                    cardDistributor.getMurderWeapon().getName()
            );
            correctAccusationAlert.initStyle(StageStyle.UTILITY);
            //remove all default buttons                
            correctAccusationAlert.getButtonTypes().clear();
            //button for starting from character selection
            ButtonType startNewGameBtn = new ButtonType("Start New Game", ButtonData.YES);
            correctAccusationAlert.getButtonTypes().add(startNewGameBtn);
            //button for restarting the game with same characters
            ButtonType restartBtn = new ButtonType("Restart", ButtonData.YES);
            correctAccusationAlert.getButtonTypes().add(restartBtn);
            //button for quitting application
            ButtonType endGameBtn = new ButtonType("Quit", ButtonData.YES);
            correctAccusationAlert.getButtonTypes().add(endGameBtn);
            //wait for a user to click a button
            Optional<ButtonType> result = correctAccusationAlert.showAndWait();
            if (!result.isPresent()) {
                //re-open alert until something is chosen
                correctAccusationAlert.showAndWait();
            } else if (result.get() == startNewGameBtn) {
                //start new Game
                startNewGame();
            } else if (result.get() == restartBtn) {
                //start again with same characters
                playGame(primaryStage);
            } else if (result.get() == endGameBtn) {
                //quit application
                Platform.exit();
                System.exit(0);
            }
            //else, accusation is incorrect
        } else {
            //get the number of active players
            int activePlayers = 0;
            for (Player p : board.getPlayerList()) {
                if (p.isPlaying) {
                    activePlayers++;
                }
            }
            //create an Alert for incorrect accusation
            Alert falseAccusationAlert = accusationPanel.createFalseAccusationContent(board.getCurrentPlayer().getName(),
                    cardDistributor.getMurderer().getName(),
                    cardDistributor.getMurderRoom().getName(),
                    cardDistributor.getMurderWeapon().getName(),
                    board.getCurrentPlayer().isAgent()
            );
            falseAccusationAlert.initStyle(StageStyle.UTILITY);
            //false accusation alerts have different messages depending on current active players in game
            String compareAccusationAndMurderCards;
            if (board.getCurrentPlayer().isAgent()) {
                compareAccusationAndMurderCards = "Agent " + board.getCurrentPlayer().getName()
                        + " made an accusation and was incorrect\nTheir Accusation was "
                        + characterName + " in the "
                        + roomName + " with a "
                        + weaponName + "\n\n";
            } else {
                compareAccusationAndMurderCards = "It was " + accusationPanel.getEnvelopeSuspect() + " in the "
                        + accusationPanel.getEnvelopeRoom() + " with a "
                        + accusationPanel.getEnvelopeWeapon() + "\n"
                        + board.getCurrentPlayer().getName() + "'s accusation was "
                        + characterName + " in the "
                        + roomName + " with a "
                        + weaponName + "\n\n";
            }
            //if there are still other players
            if (activePlayers > 1) {
                //show alert and remove falsely accusing player from the game 
                String noMoreTurns = "*** " + board.getCurrentPlayer().getName() + " is out of the game! ***";
                falseAccusationAlert.setContentText(compareAccusationAndMurderCards + noMoreTurns);
                falseAccusationAlert.showAndWait();
                board.getCurrentPlayer().setIsPlaying(false);
                endTurnBtn.fire();
                activePlayers--;
                //if there is only one player left, inform that player
                if (activePlayers == 1) {
                    alertTxt.setText(board.getCurrentPlayer().getName() + " is the only player left");
                }
                //else, last remaining player has lost the game
            } else {
                falseAccusationAlert.setContentText(compareAccusationAndMurderCards + "*** Nobody won the game! ***");
                //remove all default buttons                           
                falseAccusationAlert.getButtonTypes().clear();
                //button for starting from character selection
                ButtonType startNewGameBtn = new ButtonType("Start New Game", ButtonData.YES);
                falseAccusationAlert.getButtonTypes().add(startNewGameBtn);
                //button for restarting the game with same characters
                ButtonType restartBtn = new ButtonType("Restart", ButtonData.YES);
                falseAccusationAlert.getButtonTypes().add(restartBtn);
                //button for quitting application
                ButtonType endGameBtn = new ButtonType("Quit", ButtonData.YES);
                falseAccusationAlert.getButtonTypes().add(endGameBtn);
                //wait for a user to click a button
                Optional<ButtonType> result = falseAccusationAlert.showAndWait();
                if (!result.isPresent()) {
                    //re-open alert until something is chosen
                    falseAccusationAlert.showAndWait();
                } else if (result.get() == startNewGameBtn) {
                    //start new Game
                    startNewGame();
                } else if (result.get() == restartBtn) {
                    //start again with same characters
                    playGame(primaryStage);
                } else if (result.get() == endGameBtn) {
                    //quit application
                    Platform.exit();
                    System.exit(0);
                }
            }
        }
    }

    /**
     * sets up handlers for key presses during game play, including human player
     * movement controls
     */
    public void setUpControls() {
        gameScene.setOnKeyPressed((KeyEvent event) -> {
            if (!board.getCurrentPlayer().isAgent()) {
                switch (event.getCode()) {
                    case W://go up
                        board.moveCurrentPlayer(board.getCurrentPlayer().getToken().getTokenLocation().getColIndex(),
                                (board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex() - 1), diceRoller.isDiceRolled(), diceRoller.getDiceTotal());
                        updateMovementAlerts();

                        break;
                    case S://go down
                        board.moveCurrentPlayer(board.getCurrentPlayer().getToken().getTokenLocation().getColIndex(),
                                (board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex() + 1), diceRoller.isDiceRolled(), diceRoller.getDiceTotal());
                        updateMovementAlerts();
                        break;
                    case A://go left
                        board.moveCurrentPlayer((board.getCurrentPlayer().getToken().getTokenLocation().getColIndex() - 1),
                                board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex(), diceRoller.isDiceRolled(), diceRoller.getDiceTotal());
                        updateMovementAlerts();
                        break;
                    case D://go right
                        board.moveCurrentPlayer((board.getCurrentPlayer().getToken().getTokenLocation().getColIndex() + 1),
                                board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex(), diceRoller.isDiceRolled(), diceRoller.getDiceTotal());
                        updateMovementAlerts();
                        break;
                    /*In exiting the room , enable suggestion button,which does not work outside room.
                     This will enforce players inside room to move outside */
                    case DIGIT1://exit door 1
                        board.currentPlayerExitsRoom(1, diceRoller.isDiceRolled(), diceRoller.getDiceTotal());
                        updateMovementAlerts();
                        break;
                    case DIGIT2://exit door 2
                        board.currentPlayerExitsRoom(2, diceRoller.isDiceRolled(), diceRoller.getDiceTotal());
                        updateMovementAlerts();
                        break;
                    case DIGIT3://exit door 3
                        board.currentPlayerExitsRoom(3, diceRoller.isDiceRolled(), diceRoller.getDiceTotal());
                        updateMovementAlerts();
                        break;
                    case DIGIT4://exit door 4
                        board.currentPlayerExitsRoom(4, diceRoller.isDiceRolled(), diceRoller.getDiceTotal());
                        updateMovementAlerts();
                        break;
                    default://non valid Ket
                        alertTxt.setText("Not Valid Key");
                        System.out.println("Not Valid Key");
                        break;
                }
                updateView();
            } else {
                System.out.println("Agent Players turn");
                alertTxt.setText("Agent Players Turn");
            }
        });

    }

    /**
     * Updates GUI by removing all the tokens from boardView then adding them
     * back again in their updated positions
     */
    public void updateView() {
        for (Player p : board.getPlayerList()) {
            boardView.getChildren().remove(p.getToken());
            boardView.add(p.getToken(), p.getToken().getTokenLocation().getColIndex(), p.getToken().getTokenLocation().getRowIndex());
        }
    }

    /**
     * Returns true when game starting criterias are obtained
     *
     * @param e
     * @return
     */
    private boolean isGameStarting(ActionEvent e) {
        boolean gameStarting = true;
        //List To check if characters are choosen twice or more
        ArrayList<String> characterRepetitionChecklist = new ArrayList<String>();
        //List to checks if player namefield contains same values
        ArrayList<String> playerNameRepetitionChecklist = new ArrayList<String>();
        //Checks if any variable is missing
        for (PlayerSelectionBox playerselectionbox : selectionBoxesList) {
            // Gets value of textField
            playerselectionbox.getPlayerTextField().fireEvent(e);
            // Check for name replications
            if (!playerNameRepetitionChecklist.contains(playerselectionbox.getPlayerTextField().getText())) {
                playerNameRepetitionChecklist.add(playerselectionbox.getPlayerTextField().getText());
            } else {
                preGameText.setText("Names of players cannot be same!");
                gameStarting = false;
                break;
            }
            //Checks for unfilled variables 
            if (playerselectionbox.getPlayerName().isEmpty() || !Arrays.asList(characterNames).contains(playerselectionbox.getPlayerCharacter()) || (!playerselectionbox.getAgentButton().isSelected() && !playerselectionbox.getHumanButton().isSelected())) {
                //In any errors, prevents initialisation of the game
                preGameText.setText("Please fill in player details completely!");
                gameStarting = false;
                break;
            }
            //Checks for repetition of characters
            if (!characterRepetitionChecklist.contains(playerselectionbox.getPlayerCharacter())) {
                characterRepetitionChecklist.add(playerselectionbox.getPlayerCharacter());
            } else {
                preGameText.setText("A character cannot be chosen more than once!");
                gameStarting = false;
                break;
            }
        }
        return gameStarting;
    }

    /**
     * Starts the prototype GUI
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        preGameScene = new Scene(createPreGameContent(), 800, 450);

        primaryStage.setTitle("Please Choose Your Characters!");
        primaryStage.getIcons().add(new Image("stageIcon/stageIcon.png"));
        primaryStage.setScene(preGameScene);
        primaryStage.setResizable(false);
        primaryStage.show();

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //when setup fullfils all requirements,then START GAME!!!
                if (isGameStarting(e)) {
                    //plays the game
                    playGame(primaryStage);
                }
            }
        }
        );
        //For Closing Window / Quitting game on "x" button
        primaryStage.setOnCloseRequest(
                new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        }
        );
    }

    private void playGame(Stage primaryStage) {
        this.primaryStage = primaryStage;
        //For setting gameScene and showing labels
        setUpBoard();
        gameScene = new Scene(gameBox);
        primaryStage.getIcons().add(new Image("stageIcon/stageIcon.png"));
        primaryStage.setTitle("Cluedo!");
        primaryStage.setScene(gameScene);
        setUpControls();
        /*Increments the current player*/
        endTurnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Player p = board.getCurrentPlayer();
                board.incrementCurrentPlayer();
                resetDice();
                alertTxt.setText("Current Player: " + board.getCurrentPlayer().getName());
                counterTxt.setText("Please Roll The Dice");
                //Displays current players Image
                currentPlayerNameText.setText(board.getCurrentPlayer().getName() + " : " + board.getCurrentPlayer().getToken().getName() + "'s turn!");
                currentPlayerImage = new Image("/CharacterCards/" + board.getCurrentPlayer().getToken().getName() + ".jpg", 150, 250, false, false);
                currentPlayerImageView.setImage(currentPlayerImage);

                for (Room r : board.getRooms()) {
                    for (int i = 0; i < r.getRoomDoors().size(); i++) {
                        r.getRoomDoors().get(i).getText().setText("");
                    }
                }

                Room currentPlayerRoom = board.getRoomOfPlayer(board.getCurrentPlayer());
                if (currentPlayerRoom != null) {
                    for (int i = 0; i < currentPlayerRoom.getRoomDoors().size(); i++) {
                        currentPlayerRoom.getRoomDoors().get(i).getText().setText("" + (i + 1));
                    }
                    if (!board.getCurrentPlayer().isAgent()) {
                        if (p.isAgent()) {
                            Runnable enablePassageRunnable = () -> enablePassageBtn();
                            Platform.runLater(enablePassageRunnable);
                        } else {
                            enablePassageBtn();
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
        //Shows DetectiveCards
        detectiveCardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!board.getCurrentPlayer().isAgent()) {
                    Stage detectiveCardStage = new Stage();
                    detectiveCardStage.getIcons().add(new Image("stageIcon/stageIcon.png"));
                    detectiveCardStage.initModality(Modality.APPLICATION_MODAL);
                    displayDetectiveCard(detectiveCardStage);
                } else {
                    alertTxt.setText("You cannot check agents detective card!");
                }
            }
        });

        suggestionBtn.setOnMouseReleased((MouseEvent event) -> {
            if (board.getCurrentPlayer().isAgent()) {
                alertTxt.setText("Can't suggest during agent's turn!");
            }
        });

        suggestionBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Allows suggestion if player is in room, and human
                if (board.getRoomOfPlayer(board.getCurrentPlayer()) != null && (!board.getCurrentPlayer().isAgent())) {
                    if (!board.getCurrentPlayer().getMostRecentlySuggestedRoom().equals(board.getRoomOfPlayer(board.getCurrentPlayer()).getName())) {
                        //Create new Stage for popup
                        suggestionStage = new Stage();
                        suggestionStage.getIcons().add(new Image("stageIcon/stageIcon.png"));
                        suggestionStage.initModality(Modality.APPLICATION_MODAL);
                        suggestionStage.setResizable(false);
                        //Create new suggestion panel 
                        suggestionPanel = new SuggestionPanel();
                        //gets name of current players room as parameter to create content of suggestionPanel
                        String suggestionRoomName = board.getRoomOfPlayer(board.getCurrentPlayer()).getName();
                        //Put suggested panel content into new postSuggestionScene and shows with popup suggestionStage
                        Scene suggestionScene = new Scene(suggestionPanel.createSuggestionContent(suggestionRoomName, board.getCurrentPlayer().getName()));
                        suggestionStage.setScene(suggestionScene);
                        suggestionStage.show();
                        //On click of submit button, suggestion takes place
                        suggestionPanel.getSubmitButton().setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                //if characterSelectionCombobox values are not empty allow suggestion
                                if (suggestionPanel.getSuggestedSuspectName() != null && suggestionPanel.getSuggestedWeaponName() != null) {
                                    //Close the suggestionStage
                                    suggestionStage.close();
                                    //calls private method to start submission suggestion process 
                                    handleSuggestion(suggestionPanel.getSuggestedSuspectName(), board.getRoomOfPlayer(board.getCurrentPlayer()).getName(),
                                            suggestionPanel.getSuggestedWeaponName());
                                } else {
                                    alertTxt.setText("Please fill all boxes to make suggestion!");
                                }
                            }
                        });
                    } else {
                        alertTxt.setText("Player cannot make successive suggestions in the same room");
                    }
                } else {
                    if (!board.getCurrentPlayer().isAgent()) {
                        alertTxt.setText("Player cannot make suggestion outside of rooms");
                    }
                }
            }
        }
        );
        accusationBtn.setOnMouseReleased((MouseEvent event) -> {
            if (board.getCurrentPlayer().isAgent()) {
                alertTxt.setText("Can't accuse during agent's turn!");
            }
        });
        accusationBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Get card distributor
                CardDistributor cardDistributor = board.getCardDistributor();
                if (!board.getCurrentPlayer().isAgent()) {
                    //Printing names for testing
                    System.out.println(cardDistributor.getMurderRoom().getName());
                    System.out.println(cardDistributor.getMurderWeapon().getName());
                    System.out.println(cardDistributor.getMurderer().getName());
                    //setting up accusation stage
                    accusationStage = new Stage();
                    accusationStage.getIcons().add(new Image("stageIcon/stageIcon.png"));
                    accusationStage.initModality(Modality.APPLICATION_MODAL);
                    accusationStage.setResizable(false);
                    //Create new AcqusationPanel
                    accusationPanel = new AccusationPanel();
                    Scene accusationScene = new Scene(accusationPanel.createAccusationContent(board.getCurrentPlayer().getName()));
                    accusationStage.setScene(accusationScene);
                    accusationStage.show();
                    //Set on Actions
                    accusationPanel.getSubmitButton().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if ((accusationPanel.getAccusedSuspectName() != null) && (accusationPanel.getAccusedRoomName() != null) && (accusationPanel.getAccusedWeaponName() != null)) {
                                accusationStage.close();
                                handleAccusation(accusationPanel.getAccusedSuspectName(), accusationPanel.getAccusedWeaponName(), accusationPanel.getAccusedRoomName());
                            } else {
                                alertTxt.setText("Please fill all boxes to make accusation!");
                            }
                        }
                    });
                }
            }
        });
        if (board.getCurrentPlayer().isAgent() && board.getCurrentPlayer().getIsPlaying()) {
            handleAgentTurn();
        }
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
        endTurnBtn.setDisable(true);
        switch (board.getAgentTurn()) {
            case "Move":
                //rolls the dice
                diceRoller.getRollButton().fire();
                //use current agent to make sure thread doesn't try to move the next player 
                Player currentAgent = board.getCurrentPlayer();
                currentAgent.setPreviousPath(new ArrayList<>());
                currentAgent.getPreviousPath().add(currentAgent.getToken().getTokenLocation());
                //create thread for agent move for showing individual movements
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
                    Runnable endTrunRunnalble = () -> {
                        endTurnBtn.setDisable(false);
                        //automatically end turn
                        endTurnBtn.fire();
                    };
                    Platform.runLater(endTrunRunnalble);

                });
                //Starts the thread
                thread.start();
                break;

            case "Accuse":
                //Create new AccusationPanel
                accusationPanel = new AccusationPanel();
                String[] agentAccusation = board.getCurrentPlayer().getAccusation(characterNames, roomNames, weaponNames);
                handleAccusation(agentAccusation[0], agentAccusation[1], agentAccusation[2]);
                endTurnBtn.setDisable(false);
                //automatically end turn
                endTurnBtn.fire();
                break;

            case "Suggest":
                String[] agentSuggestion = board.getCurrentPlayer().getSuggestion(characterNames, roomNames, weaponNames);
                handleSuggestion(agentSuggestion[0], board.getRoomOfPlayer(board.getCurrentPlayer()).getName(), agentSuggestion[1]);
                endTurnBtn.setDisable(false);
                //automatically end turn
                endTurnBtn.fire();
                break;

            case "Skip":
                String currentAgentName = board.getCurrentPlayer().getName();
                endTurnBtn.setDisable(false);
                //automatically end turn
                endTurnBtn.fire();
                alertTxt.setText("Agent " + currentAgentName + " skips their turn!");
                break;

            default:
                System.err.println("Unsupported agent trun requested, skipping turn");
                endTurnBtn.setDisable(false);
                //automatically end turn
                endTurnBtn.fire();
                break;
        }

    }

    /**
     * handles movement of agent player p, calls updateView() after each move to
     * see agent moves in real time on the board
     *
     * @param p
     */
    private void handleAgentMove(Player p) {
        if (board.getCounter() < diceRoller.getDiceTotal() && (board.getCurrentPlayer() == p)) {
            Integer[] newCoords;
            do {
                do {
                    newCoords = board.getCurrentPlayer().getMove(board.getCurrentPlayer().getToken().getTokenLocation().getColIndex(), board.getCurrentPlayer().getToken().getTokenLocation().getRowIndex());
                    //don't let agent try and move out of bounds, to an occupied tile or to a wall
                } while (newCoords[0] < 0 || newCoords[0] > 27 || newCoords[1] < 0 || newCoords[1] > 27
                        || board.getTileMap()[newCoords[0]][newCoords[1]].isOccupied() || board.getTileMap()[newCoords[0]][newCoords[1]].isWall());
                //don't let agent try and retrace a move unless it needs to
            } while (!board.isPlayerBlockedByPreviousPath(board.getCurrentPlayer()) && 
                    board.getCurrentPlayer().getPreviousPath().contains(board.getTileMap()[newCoords[0]][newCoords[1]]));
            //add next move to agents previous path
            board.getCurrentPlayer().getPreviousPath().add(board.getTileMap()[newCoords[0]][newCoords[1]]);
            board.moveCurrentPlayer(newCoords[0], newCoords[1], diceRoller.isDiceRolled(), diceRoller.getDiceTotal());
            counterTxt.setText("Moves Left:" + (diceRoller.getDiceTotal() - board.getCounter()));
            updateView();
        }
    }

    public void selectCharacters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * opens a dialog on the stage pStage to show a list of player cards
     *
     * @param pStage
     */
    public void displayCardList(Stage pStage) {
        if (!board.getCurrentPlayer().isAgent()) {
            final Stage dialog = new Stage();
            dialog.getIcons().add(new Image("stageIcon/stageIcon.png"));
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(pStage);
            VBox dialogVbox = new VBox(20);
            Text showHandtxt = new Text("--------Cards--------");
            showHandtxt.setFont(Font.font("Verdana", FontWeight.BLACK, FontPosture.REGULAR, 15));
            dialogVbox.getChildren().add(showHandtxt);
            TilePane displayedCards = new TilePane();
            for (Card c : board.getCurrentPlayer().getHand()) {
                ImageView cardImageView;
                try {
                    if (c.getType() == CardType.Person) {
                        c.setCardImage(new Image("/CharacterCards/" + c.getName() + ".jpg", 130, 200, false, false));
                    } else if (c.getType() == CardType.Weapon) {
                        c.setCardImage(new Image("/weaponCards/" + c.getName() + ".jpg", 130, 200, false, false));
                    } else {
                        c.setCardImage(new Image("/RoomCards/" + c.getName() + ".jpg", 130, 200, false, false));
                    }
                    System.out.println(c.getType() + ":" + c.getName());
                    cardImageView = new ImageView(c.getCardImage());
                    displayedCards.getChildren().add(cardImageView);
                } catch (Exception e) {
                    c.setCardImage(new Image("/CharacterCards/unknownCard.png", 130, 200, false, false));
                    cardImageView = new ImageView(c.getCardImage());
                    displayedCards.getChildren().add(cardImageView);
                    System.out.println(c.getType() + ":" + c.getName());
                }
            }
            //Sets card distances
            displayedCards.setPrefRows(2);
            displayedCards.setHgap(10);
            displayedCards.setVgap(10);
            /////////////////////////////////
            dialogVbox.getChildren().add(displayedCards);
            dialogVbox.setAlignment(Pos.TOP_CENTER);
            Scene dialogScene = new Scene(dialogVbox);
            dialog.setScene(dialogScene);
            dialog.setResizable(false);
            dialog.setTitle("---" + board.getCurrentPlayer().getName() + "'s Cards---");
            dialog.show();
        } else {

            alertTxt.setText("You cannot see agent player's hand !");
        }
    }

    public void displayDetectiveCard(Stage stage) {

        DetectiveCardPanel detectiveCardPanel = new DetectiveCardPanel();
        //Give values of Players data to detectiveCardPanel
        detectiveCardPanel.setDetectiveChecklist(board.getCurrentPlayer().getDetectiveChecklist());
        detectiveCardPanel.setDetectiveNotes(board.getCurrentPlayer().getDetectiveNotes());
        //Prints the data of players first
        System.out.println("Players checkList is: " + detectiveCardPanel.getDetectiveChecklist());
        System.out.println("Players current notes are : " + detectiveCardPanel.getDetectiveNotes());
        //Create content with given values
        Scene detectiveCardScene = new Scene(detectiveCardPanel.createContent());

        stage.setOnCloseRequest(e -> {
            //sets text areas value into detectiveNotes of detectiveCardsPanel
            detectiveCardPanel.setDetectiveNotes(detectiveCardPanel.getDetectiveNotesTextArea().getText());
            //Assigns detective Card and Notes values as players detective Card and notes
            board.getCurrentPlayer().setDetectiveChecklist(detectiveCardPanel.getDetectiveChecklist());
            board.getCurrentPlayer().setDetectiveNotes(detectiveCardPanel.getDetectiveNotes());
        });

        detectiveCardPanel.getCardUpdates();
        stage.setScene(detectiveCardScene);
        stage.getIcons().add(new Image("stageIcon/stageIcon.png"));
        stage.setResizable(false);
        stage.show();
        stage.setTitle(board.getCurrentPlayer().getName() + "'s Detective Card");
        //Saves the detective updates to data to open once again in the same player
        detectiveCardPanel.getSaveButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //sets text areas value into detectiveNotes of detectiveCardsPanel
                detectiveCardPanel.setDetectiveNotes(detectiveCardPanel.getDetectiveNotesTextArea().getText());
                //Assigns detective Card and Notes values as players detective Card and notes
                board.getCurrentPlayer().setDetectiveChecklist(detectiveCardPanel.getDetectiveChecklist());
                board.getCurrentPlayer().setDetectiveNotes(detectiveCardPanel.getDetectiveNotes());
                //Prints for cvalidation
                System.out.println(board.getCurrentPlayer().getDetectiveChecklist());
                System.out.println(board.getCurrentPlayer().getDetectiveNotes());
                //Close string
                stage.hide();
            }
        });
        //Updates detectiveCards when checkBox is selected

    }

    /**
     * Restarts the game from characterSelection
     */
    private void startNewGame() {
        // Stackoverflow says it is dirty way to do!
        System.out.println("Restarting app!");
        primaryStage.close();
        primaryStage = new Stage();
        Platform.runLater(() -> new BoardGUI().start(primaryStage));

    }

    /**
     * resets the dice and counter
     */
    private void resetDice() {
        //Sets Counter to 0
        board.setCounter(0);
        counterTxt.setText("");
        //Set Dice Rolled to false and Enables DiceRoller
        diceRoller.setDiceRolled(false);
        diceRoller.enableDiceRollerButton();
    }

    private void updateMovementAlerts() {
        if (diceRoller.isDiceRolled()) {
            counterTxt.setText("Moves Left:" + (diceRoller.getDiceTotal() - board.getCounter()));
        }
        alertTxt.setText(board.getAlertMsg());
        if (board.getRoomOfPlayer(board.getCurrentPlayer()) == null) {
            passageBtn.setVisible(false);
        }
    }

    private void setUpPassageBtn() {
        passageBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                diceRoller.getRollButton().fire();
                for (int i = 0; i < board.getRoomOfPlayer(board.getCurrentPlayer()).getRoomDoors().size(); i++) {
                    board.getRoomOfPlayer(board.getCurrentPlayer()).getRoomDoors().get(i).getText().setText("");
                }
                board.playerEntersRoom(board.getCurrentPlayer(), board.getRoomOfPlayer(board.getCurrentPlayer()).getPassageExit());
                board.setCounter(diceRoller.getDiceTotal());
                updateView();
                updateMovementAlerts();
                alertTxt.setText(board.getCurrentPlayer().getName() + " took passage to " + board.getRoomOfPlayer(board.getCurrentPlayer()).getPassageExit().getName());
                passageBtn.setVisible(false);
            }
        });
    }

    private void enablePassageBtn() {
        Room currentPlayerRoom = board.getRoomOfPlayer(board.getCurrentPlayer());
        if (currentPlayerRoom.getPassageExit() != null) {
            passageBtn.setText("Move to " + board.getRoomOfPlayer(board.getCurrentPlayer()).getPassageExit().getName());
            passageBtn.setVisible(true);
        }
    }

}
