
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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.STYLESHEET_MODENA;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import static javafx.print.PrintColor.COLOR;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * Representation of the boardGUI where player can throw a dice and move the
 * token
 *
 * @author Anilz
 */
public class BoardGUI extends Application implements BoardGUIInterface {

    //Scene for preGame player selection, and game gameScene
    private Scene preGameScene;
    private Scene gameScene;

    //Used to Combine Board movements with Dice Image
    private HBox gameBox;
    //Pane will be used for Board
    private GridPane boardView;
    //Board and repsenetation of Board with Rectangles
    private Board board;

    //For Token with representation
    //private Token token;
    //IsGameRunning
    boolean isRunning = true;
    //Size of tiles
    public static final int TILE_SIZE = 20;
    //public static final int Token_Radius = 15;
    //Number of Rows and Column
    private final int columns = 28;
    private final int rows = 28;
    //DiceRoll object to step as much as dice Values
    private DiceRoller diceRoller;
    //To measure steps not surpassing value of dice

    //ImageView of Weapon
    private ArrayList<ImageView> weaponImageViews = new ArrayList<>();

    private Button showHandBtn;
    private Button endTurnBtn;
    private VBox controlsVbx;

    private HBox gameViewHbx;
    private VBox alertsVbx;
    private Text alertTxt;
    private Text counterTxt;
    // text for pregame guidence
    private Text preGameText;
    //NO of player SelectionBoxes
    private int playerSelectionBoxesNumber = 2;
    private ArrayList<PlayerSelectionBox> selectionBoxesList = new ArrayList<>();
    //Combobox Values
    private String characters[] = {"Miss Scarlett", "Colonel Mustard", "Mrs.White", "Mrs.Peacock", "Mr.Green", "Professor Plum"};
    //Buttons
    private Button startButton;
    private Button passageBtn;
    private Button suggestionBtn;
    private Button accusationBtn;
    private Button detectiveCardButton;

    //the suggestion Panel and stage
    private SuggestionPanel suggestionPanel;
    private Stage suggestionStage;
    //for acqusationPanel and Stage
    private AccusationPanel accusationPanel;
    private Stage accusationStage;

    //Fields to get current player image 
    Image currentPlayerImage;
    ImageView currentPlayerImageView;
    Text currentPlayerText;

    public VBox CreatePreGameContent() {
        VBox actualPreGame = new VBox();
        actualPreGame.setPrefSize(600, 280);
        VBox selectionBoxesView = new VBox();
        HBox characterSelectionViews = new HBox();
        for (int i = 0; i < playerSelectionBoxesNumber; i++) {
            final int nodeIndex = i;
            PlayerSelectionBox newSelectionBox = new PlayerSelectionBox();
            selectionBoxesList.add(newSelectionBox);
            selectionBoxesView.getChildren().add(newSelectionBox.selectionContent());
            characterSelectionViews.getChildren().add(selectionBoxesList.get(i).getSelectedCharacterView());
            newSelectionBox.getCharacterSelectionCombobox().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    newSelectionBox.getPlayerTextField().getText();
                    newSelectionBox.setSelectedCharacter((String) newSelectionBox.getCharacterSelectionCombobox().getValue());
                    System.out.println(newSelectionBox.getSelectedCharacter());
                    newSelectionBox.setSelectedCharacterImage(new Image("/CharacterCards/" + newSelectionBox.getSelectedCharacter() + ".jpg", 80, 200, false, false));
                    newSelectionBox.setSelectedCharacterView(new ImageView(newSelectionBox.getSelectedCharacterImage()));

                    Text playerNameDisplay = new Text(newSelectionBox.getPlayerTextField().getText());
                    VBox nameAndDisplay = new VBox(playerNameDisplay, newSelectionBox.getSelectedCharacterView());
                    characterSelectionViews.getChildren().remove(nodeIndex);
                    characterSelectionViews.getChildren().add(nodeIndex, nameAndDisplay);
                }
            });
        }

        //Adds player buttons
        Button addPlayerButton = new Button("+Add Player");
        addPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (playerSelectionBoxesNumber < 6) {
                    final int nodeIndex = playerSelectionBoxesNumber;
                    PlayerSelectionBox newSelectionBox = new PlayerSelectionBox();
                    selectionBoxesList.add(newSelectionBox);
                    selectionBoxesView.getChildren().add(newSelectionBox.selectionContent());
                    playerSelectionBoxesNumber++;
                    characterSelectionViews.getChildren().add(selectionBoxesList.get(selectionBoxesList.size() - 1).getSelectedCharacterView());
                    newSelectionBox.getCharacterSelectionCombobox().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            newSelectionBox.setSelectedCharacter((String) newSelectionBox.getCharacterSelectionCombobox().getValue());
                            System.out.println(newSelectionBox.getSelectedCharacter());
                            newSelectionBox.setSelectedCharacterImage(new Image("/CharacterCards/" + newSelectionBox.getSelectedCharacter() + ".jpg", 80, 200, false, false));
                            newSelectionBox.setSelectedCharacterView(new ImageView(newSelectionBox.getSelectedCharacterImage()));

                            Text playerNameDisplay = new Text(newSelectionBox.getPlayerTextField().getText());
                            VBox nameAndDisplay = new VBox(playerNameDisplay, newSelectionBox.getSelectedCharacterView());
                            characterSelectionViews.getChildren().remove(nodeIndex);
                            characterSelectionViews.getChildren().add(nodeIndex, nameAndDisplay);
                        }

                    });
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
                    characterSelectionViews.getChildren().remove(characterSelectionViews.getChildren().size() - 1);
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
        actualPreGame.getChildren().addAll(selectionBoxesView, preSetupButtons, characterSelectionViews, preGameTextPane);
        //Set up position of nodes 
        characterSelectionViews.setAlignment(Pos.CENTER);
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
    @Override
    public HBox setUpBoard() {
        gameBox = new HBox();
        //DiceRoller added to play with dice
        diceRoller = new DiceRoller();
        VBox diceRollerView = diceRoller.createContent();
        //Button to switch between Player and AI
        detectiveCardButton = new Button("Check Detective Card");
        detectiveCardButton.setTextAlignment(TextAlignment.CENTER);
        detectiveCardButton.setWrapText(true);
        detectiveCardButton.setPrefSize(100, 100);
        //Suggestion Button
        suggestionBtn = new Button("Make Suggestion");
        suggestionBtn.setTextAlignment(TextAlignment.CENTER);
        suggestionBtn.setWrapText(true);
        suggestionBtn.setPrefSize(100, 100);
        //Accusation Button
        accusationBtn = new Button("Make Accusation");
        accusationBtn.setTextAlignment(TextAlignment.CENTER);
        accusationBtn.setWrapText(true);
        accusationBtn.setPrefSize(100, 100);
        //Show Cards Button
        showHandBtn = new Button("Check Hand");
        showHandBtn.setTextAlignment(TextAlignment.CENTER);
        showHandBtn.setWrapText(true);
        showHandBtn.setPrefSize(100, 100);
        //End Turn Button
        endTurnBtn = new Button("End Turn");
        endTurnBtn.setTextAlignment(TextAlignment.CENTER);
        endTurnBtn.setWrapText(true);
        endTurnBtn.setPrefSize(100, 100);

        passageBtn = new Button("Take passage");
        passageBtn.setTextAlignment(TextAlignment.CENTER);
        passageBtn.setWrapText(true);
        passageBtn.setPrefSize(100, 100);
        passageBtn.setVisible(false);

        controlsVbx = new VBox();
        controlsVbx.setAlignment(Pos.CENTER);

        gameViewHbx = new HBox();
        alertsVbx = new VBox();
        alertsVbx.setMaxWidth(300);
        alertsVbx.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        alertsVbx.setAlignment(Pos.CENTER);

        //Some Stuff
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
            if (!board.getRooms().get(i).getRoomName().equals("Staircase")) {
                // Puts the weapon into room
                board.placeWeaponToRoom(board.getRooms().get(i), board.getWeapons().get(i));
                //System.out.println(board.getRooms().get(i).getRoomWeapon().getName() + "is in "+board.getRooms().get(i).getRoomName());
            }
        }

        ////////////////////////////////////////GRIDPANE//////////////////////////////////////
        //Establish array of rectangles
        boardView = new GridPane();
        //Set up the Image of Board
        for (int _r = 0; _r < rows; _r++) {
            for (int _c = 0; _c < columns; _c++) {
                //Temporarily,not fit yet
                board.getTileMap()[_c][_r].setWidth(TILE_SIZE);
                board.getTileMap()[_c][_r].setHeight(TILE_SIZE);
                try {
                    Image i = new Image("/tile_textures/" + _r + "/" + _c + ".png", 20, 20, false, false);
                    board.getTileMap()[_c][_r].setImage(i);
                    ImageView tileView = new ImageView(board.getTileMap()[_c][_r].getImage());
                    boardView.add(tileView, _c, _r);
                } catch (Exception e) {
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
                        weaponImageViews.add(new ImageView(weapon.getWeaponImage()));
                        boardView.add(weaponImageViews.get(weaponImageViews.size() - 1), _c, _r);
                    }
                }
            }
        }

        //Give players required stuff
        board.initialisePlayerDetectiveCards();
        //To test if they received detective cards
        for (Player p : board.getPlayerList()) {
            System.out.println(p.getDetectiveCard());
        }
        //Displays current players Image
        currentPlayerText = new Text(board.getCurrentPlayer().getName() + " : " + board.getCurrentPlayer().getToken().getName() + "'s turn!");
        currentPlayerText.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        currentPlayerText.setWrappingWidth(170);
        currentPlayerImage = new Image("/CharacterCards/" + board.getCurrentPlayer().getToken().getName() + ".jpg", 150, 250, false, false);
        currentPlayerImageView = new ImageView(currentPlayerImage);

        alertTxt.setFont(Font.font("Verdana", FontPosture.REGULAR, 13));
        alertTxt.setWrappingWidth(170);
        counterTxt.setFont(Font.font("Verdana", FontPosture.REGULAR, 13));

        counterTxt.setWrappingWidth(100);
        controlsVbx.getChildren().addAll(showHandBtn, detectiveCardButton, suggestionBtn, accusationBtn, endTurnBtn, passageBtn);
        alertsVbx.getChildren().addAll(currentPlayerText, currentPlayerImageView, alertTxt, diceRollerView, counterTxt);
        alertsVbx.setAlignment(Pos.TOP_CENTER);
        alertsVbx.setSpacing(10);
        gameViewHbx.getChildren().addAll(boardView, alertsVbx);
        gameBox.getChildren().addAll(controlsVbx, gameViewHbx);
        gameBox.setAlignment(Pos.CENTER);
        return gameBox;
    }

    /**
     * private method to actions after suggestion is done Shows card
     * automatically if responding player is agent, else opens a post suggestion
     * window for responding human player to choose which card to show
     */
    private void suggestionHelper() {
        //On click of submit button,suggestion takes place
        suggestionPanel.submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //if characterSelectionCombobox values are not empty start iterating
                if (suggestionPanel.getSuggestedSuspect() != null && suggestionPanel.getSuggestedWeapon() != null) {
                    //Set suggestion alertText as 
                    alertTxt.setText("Player " + board.getCurrentPlayer().getName() + " suggested " + suggestionPanel.getSuggestedSuspect()
                            + "\n" + " commited murder in " + suggestionPanel.getSuggestedRoom() + " with a " + suggestionPanel.getSuggestedWeapon());
                    //Call suggested token into room
                    for (Player p : board.getPlayerList()) {
                        if (p.getToken().getName().equals(suggestionPanel.getSuggestedSuspect())) {
                            for (Room r : board.getRooms()) {
                                if (r.getRoomName().equals(suggestionPanel.getSuggestedRoom())) {
                                    board.playerEntersRoom(p, r);
                                    updateView();
                                    break;
                                }
                            }
                        }
                    }
                    //Call suggested weapon into room
                    for (Room room : board.getRooms()) {
                        for (Weapon weapon : board.getWeapons()) {
                            //If room does not have already have suggested object
                            if (weapon.getName().equals(suggestionPanel.getSuggestedWeapon()) && room.getRoomName().equals(suggestionPanel.getSuggestedRoom())
                                    && !room.getRoomWeapons().contains(weapon)) {
                                board.placeWeaponToRoom(room, weapon);
                                for (ImageView weaponImageView : weaponImageViews) {
                                    if (weaponImageView.getImage().equals(weapon.getWeaponImage())) {
                                        boardView.getChildren().remove(weaponImageView);
                                        boardView.add(weaponImageView, weapon.getWeaponLocation().getColIndex(), weapon.getWeaponLocation().getRowIndex());
                                    }
                                }

                            }
                        }
                    }

                    //Close the suggestionStage
                    suggestionStage.close();
                    //set suggested card is not found at someones hand yet
                    boolean suggestedCardsFound = false;
                    //Loop to get hand of players starting from one player after the current player
                    for (int i = 0; i < board.getPlayerList().size(); i++) {
                        //pointer j is used to iterate through all other players until index hits the current player again  
                        int j = (i + board.getPlayerList().indexOf(board.getCurrentPlayer())) % board.getPlayerList().size();
                        // Gets the nearest player which is not a non-playing player ,and not a currentPlayer
                        if ((board.getPlayerList().get(j).getHand() != null) && !board.getPlayerList().get(j).equals(board.getCurrentPlayer())) {
                            //Shuffles players hand
                            Collections.shuffle(board.getPlayerList().get(j).getHand());
                            //If players hand include suggested cards,suggestedCardfound becomes true, and loop breaks
                            //the card found is shown to player through counterTxt
                            ArrayList<String> suggestedCardPossessions = new ArrayList<>();
                            //Let its first value be emtpty

                            for (Card card : board.getPlayerList().get(j).getHand()) {
                                if (card.getName().equals(suggestionPanel.getSuggestedSuspect())
                                        || card.getName().equals(suggestionPanel.getSuggestedWeapon())
                                        || card.getName().equals(suggestionPanel.getSuggestedRoom())) {
                                    //suggestedCardFound = true;
                                    suggestedCardPossessions.add(card.getName());
                                }
                            }
                            //If suggested card/s appear in players hand,break the loop
                            if (!suggestedCardPossessions.isEmpty()) {
                                suggestedCardsFound = true;
                                Alert postSuggestionAlert;
                                //If responding player is agent,automatically shows first value of arrayList
                                if (board.getPlayerList().get(j).isAgent()) {
                                    postSuggestionAlert = suggestionPanel.createPostSuggestionAlert(board.getPlayerList().get(j).getName(), suggestedCardPossessions.get(0));
                                    postSuggestionAlert.showAndWait();
                                } else {
                                    suggestionStage.close();
                                    ChoiceDialog responderChoiceBox = suggestionPanel.createSuggestionResponderContent(board.getPlayerList().get(j).getName(), suggestedCardPossessions);
                                    //responderChoiceBox.showAndWait();
                                    boolean validItemChosen = false;
                                    while (!validItemChosen) {
                                        responderChoiceBox.showAndWait();
                                        if (!responderChoiceBox.getSelectedItem().equals("")) {
                                            validItemChosen = true;
                                            System.out.println("Selected Item: " + responderChoiceBox.getSelectedItem());
                                            //counterTxt.setText(board.getPlayerList().get(j).getName() + " shows you " + responderChoiceBox.getSelectedItem() + " card");
                                            postSuggestionAlert = suggestionPanel.createPostSuggestionAlert(board.getPlayerList().get(j).getName(), (String) responderChoiceBox.getSelectedItem());
                                            postSuggestionAlert.showAndWait();
                                        }
                                    }
                                }
                                break;
                            } else {
                                suggestedCardsFound = false;
                            }
                        }
                    }
                    //If other players do not have the suggested cards,counterTxt gives a message
                    if (!suggestedCardsFound) {
                        counterTxt.setText("Other players do not have suggested cards");
                    }
                } else {
                    System.out.println("Please fill all suggestion boxes");
                }
            }
        }
        );
    }

    private void accusationHelper(CardDistributor cardDistributor) {
        //Set on Actions
        accusationPanel.submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if ((accusationPanel.getAccusedSuspectName() != null) && (accusationPanel.getAccusedRoomName() != null) && (accusationPanel.getAccusedWeaponName() != null)) {
                    //Put envelope card names to strng to display and compare with accusation cards
                    String envelopeCardCompare = "Murder cards are:\n"
                            + cardDistributor.getMurderer().getName() + ","
                            + cardDistributor.getMurderRoom().getName() + ","
                            + cardDistributor.getMurderWeapon().getName() + "\n"
                            + "Player's accusation was:\n"
                            + accusationPanel.getAccusedSuspectName() + ","
                            + accusationPanel.getAccusedRoomName() + ","
                            + accusationPanel.getAccusedWeaponName();

                    if (cardDistributor.getMurderRoom().getName().equals(accusationPanel.getAccusedRoomName())
                            && cardDistributor.getMurderWeapon().getName().equals(accusationPanel.getAccusedWeaponName())
                            && cardDistributor.getMurderer().getName().equals(accusationPanel.getAccusedSuspectName())) {
                        accusationStage.close();
                        String correctAccusationInfo = envelopeCardCompare + "\n\n" + "Player " + board.getCurrentPlayer().getName() + " solved the case and won the game!!!";
                        Alert correctAccusationAlert = accusationPanel.createCorrectAccusationContent(board.getCurrentPlayer().getName(), correctAccusationInfo);
                        correctAccusationAlert.showAndWait();
                        //Should restart the game on command 
                    } else {
                        accusationStage.close();
                        //Gets the number of active players
                        int activePlayerNumber = 0;
                        for (Player p : board.getPlayerList()) {
                            if (p.isPlaying) {
                                activePlayerNumber++;
                            }
                        }
                        //Gives text a value depends on whether the player whow failed was last active player
                        String failedAccusationInfo = envelopeCardCompare + "\n\n" + "Player " + board.getCurrentPlayer().getName() + " will not have any more turns!!!";
                        String failedAccusationInfoWithOnlyPlayer = envelopeCardCompare + "\n\n" + "No player wins the game!!!";
                        String choosenFailedAccusationText = null;
                        choosenFailedAccusationText = (activePlayerNumber > 1) ? failedAccusationInfo : failedAccusationInfoWithOnlyPlayer;
                        //Puts the player name and the chosen text into acqusationPanel
                        Alert falseAccusationAlert = accusationPanel.createFalseAccusationContent(board.getCurrentPlayer().getName(), choosenFailedAccusationText);
                        falseAccusationAlert.showAndWait();
                        if (!falseAccusationAlert.isShowing()) {
                            //If active player number is greater than 1,disables the player, else ends the game(not yet)
                            if (activePlayerNumber > 1) {
                                //Disables the player who made false acqusation
                                Player playerToBeDisabled = board.getCurrentPlayer();
                                endTurnBtn.fire();
                                playerToBeDisabled.setIsPlaying(false);
                                activePlayerNumber--;
                                if (activePlayerNumber == 1) {
                                    alertTxt.setText(board.getCurrentPlayer().getName() + " is only player left");
                                    endTurnBtn.setDisable(true);
                                }
                            } else {
                                //For now , disables the endturn button , but it has to end the game
                                endTurnBtn.setDisable(true);
                            }
                        }
                    }
                } else {
                    alertTxt.setText("Please fill all boxes to make accusation !");
                }
            }
        }
        );
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
     * Updates GUI by removing all the tokens from boardView then adding them
     * back again in their updated positions
     */
    @Override
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
    private boolean IsGameStarting(ActionEvent e) {
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
                preGameText.setText("Name of players cannot be same !!!");
                gameStarting = false;
                break;
            }
            //Checks for unfilled variables 
            if (playerselectionbox.getPlayerName().isEmpty() || !Arrays.asList(characters).contains(playerselectionbox.getPlayerCharacter()) || (!playerselectionbox.getAgentButton().isSelected() && !playerselectionbox.getHumanButton().isSelected())) {
                //In any errors, prevents initialisation of the game
                preGameText.setText("Please fill player details completely !!!");
                gameStarting = false;
                break;
            }
            //Checks for repetition of characters
            if (!characterRepetitionChecklist.contains(playerselectionbox.getPlayerCharacter())) {
                characterRepetitionChecklist.add(playerselectionbox.getPlayerCharacter());
            } else {
                preGameText.setText("A character cannot be chosen more than once !!!");
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
        preGameScene = new Scene(CreatePreGameContent(), 600, 500);
        primaryStage.setTitle("Please Choose Characters!");
        primaryStage.setScene(preGameScene);
        primaryStage.show();

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //when setup fullfils all requirements,then START GAME!!!
                if (IsGameStarting(e)) {
                    startButton.setDisable(true);
                    //For setting gameScene and showing labels
                    setUpBoard();
                    gameScene = new Scene(gameBox);
                    primaryStage.setTitle("Cluedo!!!");
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
                            currentPlayerText.setText(board.getCurrentPlayer().getName() + " : " + board.getCurrentPlayer().getToken().getName() + "'s turn!");
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
                            Stage detectiveCardStage = new Stage();
                            detectiveCardStage.initModality(Modality.APPLICATION_MODAL);
                            displayDetectiveCard(detectiveCardStage);
                        }
                    });
                    suggestionBtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            //Allows suggestion if player is in room, and human
                            if (board.getRoomOfPlayer(board.getCurrentPlayer()) != null && (!board.getCurrentPlayer().isAgent())) {
                                //Create new Stage for popup
                                suggestionStage = new Stage();
                                suggestionStage.initModality(Modality.APPLICATION_MODAL);
                                // Create new suggestion panel 
                                suggestionPanel = new SuggestionPanel();
                                // gets name of current players room as parameter to create content of suggestionPanel
                                String suggestionRoomName = board.getRoomOfPlayer(board.getCurrentPlayer()).getRoomName();
                                //Put suggested panel content into new postSuggestionScene and shows with popup suggestionStage
                                Scene suggestionScene = new Scene(suggestionPanel.createSuggestionContent(suggestionRoomName));
                                suggestionStage.setScene(suggestionScene);
                                suggestionStage.show();
                                //Calls private method to start submission suggestion process 
                                suggestionHelper();
                            } else {
                                alertTxt.setText("Cannot make suggestion outside of rooms");
                            }
                        }
                    }
                    );
                    accusationBtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            //SET ALERT AND COUNTER texts TO EMPTY TEXTS
                            alertTxt.setText("");
                            counterTxt.setText("");
                            //Get card distributor
                            CardDistributor cardDistributor = board.getCardDistributor();
                            if (!board.getCurrentPlayer().isAgent()) {
                                //Printing names for testing
                                System.out.println(cardDistributor.getMurderRoom().getName());
                                System.out.println(cardDistributor.getMurderWeapon().getName());
                                System.out.println(cardDistributor.getMurderer().getName());
                                //setting up accusation stage
                                accusationStage = new Stage();
                                accusationStage.initModality(Modality.APPLICATION_MODAL);
                                //Create new AcqusationPanel
                                accusationPanel = new AccusationPanel();
                                Scene accusationScene = new Scene(accusationPanel.createAccusationContent(board.getCurrentPlayer().getName()));
                                accusationStage.setScene(accusationScene);
                                accusationStage.show();
                                //Set on Actions
                                accusationHelper(cardDistributor);
                            }
                        }
                    });
                    if (board.getCurrentPlayer()
                            .isAgent() && board.getCurrentPlayer().getIsPlaying()) {
                        handleAgentTurn();
                    }
                }
            }
        }
        );
        //For Closing Window on "x" button
        primaryStage.setOnCloseRequest(
                new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t
            ) {
                Platform.exit();
                System.exit(0);
            }
        }
        );
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
            dialogVbox.getChildren().add(new Text(showHandtxt));
            HBox cardsDisplay = new HBox();
            for (Card c : board.getCurrentPlayer().getHand()) {
                ImageView cardImageView;
                try {
                    if(c.getType()==CardType.Person){
                        c.setCardImage(new Image("/CharacterCards/" + c.getName() + ".jpg", 100, 120, false, false));
                    }else if(c.getType()==CardType.Weapon){
                         c.setCardImage(new Image("/weaponCards/" + c.getName() + ".jpg", 100, 120, false, false));
                    }else{
                        c.setCardImage(new Image("/RoomCards/" + c.getName() + ".jpg", 100, 120, false, false));
                    }
                    System.out.println(c.getType() + ":" + c.getName());
                    cardImageView = new ImageView(c.getCardImage());
                    cardsDisplay.getChildren().add(cardImageView);
                } catch (Exception e) {
                    c.setCardImage(new Image("/CharacterCards/unknownCard.png", 100, 120, false, false));
                    cardImageView = new ImageView(c.getCardImage());
                    cardsDisplay.getChildren().add(cardImageView);
                    System.out.println(c.getType()+ ":" + c.getName());
                }
            }
            dialogVbox.getChildren().add(cardsDisplay);
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        } else {
            System.out.println("Agent Player Turn");
            alertTxt.setText("Agent Player Turn");
        }
    }

    public void displayDetectiveCard(Stage stage) {

        DetectiveCardPanel detectiveCardPanel = new DetectiveCardPanel();
        //Give values of Players data to detectiveCardPanel
        detectiveCardPanel.setDetectiveCard(board.getCurrentPlayer().getDetectiveCard());
        detectiveCardPanel.setDetectiveNotes(board.getCurrentPlayer().getDetectiveNotes());
        //Prints the data of players first
        System.out.println("Players checkList is: " + detectiveCardPanel.getDetectiveCard());
        System.out.println("Players current notes are : " + detectiveCardPanel.getDetectiveNotes());
        //Create content with given values
        Scene detectiveCardScene = new Scene(detectiveCardPanel.createContent());

        stage.setOnCloseRequest(e -> {
            //sets text areas value into detectiveNotes of detectiveCardsPanel
            detectiveCardPanel.setDetectiveNotes(detectiveCardPanel.getDetectiveNotesTextArea().getText());
            //Assigns detective Card and Notes values as players detective Card and notes
            board.getCurrentPlayer().setDetectiveCard(detectiveCardPanel.getDetectiveCard());
            board.getCurrentPlayer().setDetectiveNotes(detectiveCardPanel.getDetectiveNotes());
        });

        detectiveCardPanel.getCardUpdates();
        stage.setScene(detectiveCardScene);
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
                board.getCurrentPlayer().setDetectiveCard(detectiveCardPanel.getDetectiveCard());
                board.getCurrentPlayer().setDetectiveNotes(detectiveCardPanel.getDetectiveNotes());
                //Prints for cvalidation
                System.out.println(board.getCurrentPlayer().getDetectiveCard());
                System.out.println(board.getCurrentPlayer().getDetectiveNotes());
                //Close string
                stage.hide();
            }
        });
        //Updates detectiveCards when checkBox is selected

    }

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
                alertTxt.setText(board.getCurrentPlayer().getName() + " took passage to " + board.getRoomOfPlayer(board.getCurrentPlayer()).getPassageExit().getRoomName());
                passageBtn.setVisible(false);
            }
        });
    }

    private void enablePassageBtn() {
        Room currentPlayerRoom = board.getRoomOfPlayer(board.getCurrentPlayer());
        if (currentPlayerRoom.getPassageExit() != null) {
            passageBtn.setText("Move to " + board.getRoomOfPlayer(board.getCurrentPlayer()).getPassageExit().getRoomName());
            passageBtn.setVisible(true);
        }
    }

}
