package cluedo_board_game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Represents an agent player in the board game.
 *
 * @author cwood
 * @version 1.0
 */
public class Agent extends Player {

    /**
     * Constructor for agent player which creates a agent player and assigns a
     * specified token to it.
     *
     * @param playerId
     * @param name
     */
    public Agent(int playerId, String name) {
        super(playerId, name);
        this.token = super.getToken();
    }

    /**
     * Sets the player as agent.
     *
     * @return true
     */
    @Override
    public Boolean isAgent() {
        return true;
    }

    /**
     * A method to move player token to specified tile.
     *
     * @param tile
     */
    @Override
    public void moveToken(Tile tile) {
        this.token.setTokenLocation(tile);
    }

    /**
     * Generates a random set of coordinates adjacent to the coordinates x y for
     * agent movement.
     *
     * @param x x axis coordinates
     * @param y y axis coordinates
     * @return random coordinates adjacent to x y
     */
    @Override
    public Integer[] getMove(int x, int y) {
        Integer[] coords = new Integer[2];
        Random random = new Random();
        int movement = random.nextInt(4);
        switch (movement) {
            case 0:
                coords[0] = x;
                coords[1] = y - 1;
                break;
            case 1:
                coords[0] = x;
                coords[1] = y + 1;
                break;
            case 2:
                coords[0] = x - 1;
                coords[1] = y;
                break;
            default:
                coords[0] = x + 1;
                coords[1] = y;
                break;
        }
        return coords;
    }

    /**
     * Returns an ArrayList of card names which are marked as unseen in the
     * players detective card.
     *
     * @return String ArrayList of card names
     */
    @Override
    public ArrayList<String> getUnseenCards() {
        ArrayList<String> unseenCards = new ArrayList<>();
        checklist.keySet().stream().filter((s) -> (!checklist.get(s))).forEachOrdered((s) -> {
            unseenCards.add(s);
        });
        return unseenCards;
    }

    /**
     * Marks the cards in the agents hand as seen in the agents detective card.
     */
    @Override
    public void markHandAsSeen() {
        hand.forEach((c) -> {
            checklist.put(c.getName(), true);
        });
    }

    /**
     * Given arrays of character, room and weapon names: return a character,
     * weapon and room to use in an accusation.
     *
     * @param characters array list of character names
     * @param rooms array list of room names
     * @param weapons array list of weapon names
     * @return string array of length 3
     */
    @Override
    public String[] getAccusation(String[] characters, String[] rooms, String[] weapons) {
        String[] suggestion = new String[3];
        getUnseenCards().forEach((s) -> {
            if (Arrays.asList(characters).contains(s)) {
                suggestion[0] = s;
            } else if (Arrays.asList(weapons).contains(s)) {
                suggestion[1] = s;
            } else if (Arrays.asList(rooms).contains(s)) {
                suggestion[2] = s;
            } else {
                System.err.println("Agent " + name + " has an invalid detective card");
            }
        });
        return suggestion;
    }

    /**
     * Given arrays of character, room and weapon names: return a character and
     * weapon name to use in a suggestion.
     *
     * @param characters array list of character names
     * @param rooms array list of room names
     * @param weapons array list of weapon names
     * @return string array of length 2
     */
    @Override
    public String[] getSuggestion(String[] characters, String[] rooms, String[] weapons) {
        String[] suggestion = new String[2];
        ArrayList<String> unseenCharacters = new ArrayList<>();
        ArrayList<String> unseenWeapons = new ArrayList<>();
        getUnseenCards().forEach((s) -> {
            if (Arrays.asList(characters).contains(s)) {
                unseenCharacters.add(s);
            } else if (Arrays.asList(weapons).contains(s)) {
                unseenWeapons.add(s);
            } else if (!Arrays.asList(rooms).contains(s)) {
                System.err.println("Agent " + name + " has an invalid detective card");
            }
        });
        int randCharacterIndex = (int) ((Math.random() * unseenCharacters.size()));
        int randWeapnIndex = (int) ((Math.random() * unseenWeapons.size()));
        suggestion[0] = unseenCharacters.remove(randCharacterIndex);
        suggestion[1] = unseenWeapons.remove(randWeapnIndex);
        return suggestion;
    }
}
