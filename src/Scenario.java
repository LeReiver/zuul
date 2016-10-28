import java.util.ArrayList;

/**
 * "Haunted Restaurant" is a very simple, text based adventure game. Users must
 * enter the restaurant either through the bar or the lobby, and make their way
 * through the restaurant while dodging ghosts. There is a secret exit through
 * the restroom in the back... if the player makes it that far.
 * 
 * To play this game, create an instance of this class and call the "play"
 * method.
 * 
 * This class represents a game scenario including connected rooms and items
 * 
 * @author Tang and Michael Le-Reiver
 * @version 2015.11.30 Modifications: - removed random and transporter methods -
 *          removed Items and related
 * 
 * 
 */
public class Scenario {
	private ArrayList<Room> rooms;
	private Room startRoom;

	/**
	 * Constructor for objects of class Scenario - Create all the rooms and link rooms to their exits 
	 * using Zuul database. If database fails, use hard code scenario
	 */
	public Scenario(boolean notDB) {
		rooms = new ArrayList<Room>();
		
		if(notDB) {
			scenarioCode();
		}
	}
	
	/**
	 * Hard coded Scenario object if DB fails
	 */
	private void scenarioCode() {
		// Set up your rooms, exits, and items
		// Move code from Game.createRooms here

		Room outside, lobby, diningArea, kitchen, bar, banquetRoom, poolRoom, restroom, basement, secretExit;

		// create the rooms
		outside = new Room("outside the main entrance of the Haunted Restaurant", 101);
		lobby = new Room("in the lobby");
		diningArea = new Room("in the main dining area");
		kitchen = new Room("in the kitchen");
		bar = new Room("in the bar");
		banquetRoom = new Room("in the banquet room");
		poolRoom = new Room("in the pool room");
		restroom = new Room("in the restroom");
		basement = new Room("in the basement");
		secretExit = new Room("outside the secret exit. CONGRATULATIONS!!!! You made it out alive." + "\n"
				+ "Too bad you lost your keys and need to go back in");

		// initialize room exits
		outside.setExit("east", bar);
		outside.setExit("south", lobby);
		lobby.setExit("north", outside);
		lobby.setExit("south", diningArea);
		diningArea.setExit("north", lobby);
		diningArea.setExit("east", kitchen);
		kitchen.setExit("east", restroom);
		kitchen.setExit("west", diningArea);
		bar.setExit("east", poolRoom);
		bar.setExit("west", outside);
		banquetRoom.setExit("north", poolRoom);
		banquetRoom.setExit("south", restroom);
		poolRoom.setExit("south", banquetRoom);
		poolRoom.setExit("west", bar);
		restroom.setExit("north", banquetRoom);
		restroom.setExit("down", basement);
		restroom.setExit("west", kitchen);
		basement.setExit("south", secretExit);
		basement.setExit("up", restroom);
		secretExit.setExit("north", basement);

		// Set the start room
		startRoom = outside;

		// Create the rooms ArrayList and add all your rooms to it
		rooms = new ArrayList<>();

		rooms.add(outside);
		rooms.add(lobby);
		rooms.add(diningArea);
		rooms.add(kitchen);
		rooms.add(bar);
		rooms.add(poolRoom);
		rooms.add(banquetRoom);
		rooms.add(restroom);
		rooms.add(basement);
		rooms.add(secretExit);
	}
	
	

	/**
	 * @return Returns the start room for this scenario
	 */
	public Room getStartRoom() // complete this method
	{
		return startRoom;
	}
	
	/**
     * @param startRoom Sets the start room
     */
    public void setStartRoom(Room startRoom)
    {
    	this.startRoom = startRoom;
    }

}
