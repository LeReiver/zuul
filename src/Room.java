
import java.util.HashMap;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Haunted Restaurant" application.
 * "Haunted Restaurant" is a very simple, text based adventure game. Users must
 * enter the restaurant either through the bar or the lobby, and make thier way
 * through the restaurant while dodging ghosts. There is a secret exit through
 * the restroom in the back... if the player makes it that far.
 *
 * A "Room" represents one location in the scenery of the game. It is connected
 * to other rooms via exits. The exits are labelled north, east, south, west.
 * For each direction, the room stores a reference to the neighboring room, or
 * null if there is no exit in that direction.
 * 
 * @author Michael Kölling and David J. Barnes and Michael Le-Reiver
 * @version 2015.11.30 MODIFICATIONS: - added field for roomID - added roomID to
 *          constructor - added getter for roomID - changed methods used for
 *          TransporterRoom from protected back to public - removed Items and
 *          related methods
 */
public class Room {
	private String description;
	private HashMap<String, Room> exits;
	private int id;

	/**
	 * Create a room described "description". Initially, it has no exits.
	 * "description" is something like "a kitchen" or "an open court yard".
	 * 
	 * @param description
	 *            The room's description.
	 */
	public Room(String description) {
		this.description = description;
		exits = new HashMap<String, Room>();
	}

	/**
	 * Create a room described "description". Initially, it has no exits.
	 * "description" is something like "a kitchen" or "an open court yard".
	 * 
	 * @param description
	 *            The room's description.
	 * @param roomID
	 *            The room's id.
	 */
	public Room(String description, int id) {
		this.description = description;
		exits = new HashMap<String, Room>();
		this.id = id;
	}

	/**
	 * Define the exits of this room. Every direction either leads to another
	 * room or is null (no exit there).
	 * 
	 * @param direction
	 *            The direction of the exit
	 * @param neighbor
	 *            The room that the exit leads to
	 */
	public void setExit(String direction, Room neighbor) {
		exits.put(direction, neighbor);
	}

	/**
	 * @return The description of the room.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return The id of the room.
	 */
	public int getRoomID() {
		return id;
	}

	/**
	 * Return the room that is reached if we go from this room in direction
	 * "direction " If there is no room in that direction, return null.
	 */
	public Room getExit(String direction) {
		return exits.get(direction);
	}

	/**
	 * @return Return a description of the room's exits, for example,
	 *         "Exits: north west".
	 */
	public String getExitString() {
		String returnString = "Exits:";
		Set<String> keys = exits.keySet();
		for (String exit : keys) {
			returnString += " " + exit;
		}
		return returnString;
	}

	/**
	 * @return Return a long description of this room including the exits, in
	 *         the form: You are in the Kitchen. Exits: north west
	 */
	public String getLongDescription() {
		return "You are " + description + ".\n" + getExitString();
	}
}
