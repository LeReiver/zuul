
/**
 * "Haunted Restaurant" is a very simple, text based adventure game. Users must
 * enter the restaurant either through the bar or the lobby, and make thier way
 * through the restaurant while dodging ghosts. There is a secret exit through
 * the restroom in the back... if the player makes it that far.
 * 
 * To play this game, create an instance of this class and call the "play"
 * method.
 * 
 * This Game class creates and initializes all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 * 
 * @author Michael Kölling and David J. Barnes and Michael Le-Reiver
 * @version 2015.12.04 Modifications: - Modified Game() to read from database
 * 
 */

public class Game {
	private Parser parser;
	private Room currentRoom;
	private Scenario scenario;

	/**
	 * Create the game and initialize its internal map. Connect to database. If
	 * no connection, then read hard coded Scenario
	 */
	public Game() {
			ZuulDB zdb = new ZuulDB();
			Scenario dbScenario = zdb.readScenario();
			currentRoom = scenario.getStartRoom();
		if (dbScenario == null) {
				Scenario scenario = new Scenario(true);
				currentRoom = scenario.getStartRoom();
				parser = new Parser();
		}
		
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		printWelcome();

		// Enter the main command loop. Here we repeatedly read commands and
		// execute them until the game is over.

		boolean finished = false;
		while (!finished) {
			Command command = parser.getCommand();
			finished = processCommand(command);
		}
		System.out.println("Thank you for playing.  Good bye.");
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		System.out.println();
		System.out.println("Welcome to the Haunted Restaurant!");
		System.out.println("Haunted Restaurant is a very simple, text based adventure game. ");
		System.out.println("You must enter the restaurant either through the bar or the lobby, ");
		System.out.println("and make your way through the restaurant while dodging ghosts.");
		System.out.println("Type 'help' if you need help.");

		printLocationInfo();
	}

	/**
	 * Print information about the current location. Includes a description of
	 * the current room and the exits available.
	 */
	private void printLocationInfo() {
		System.out.println(currentRoom.getLongDescription());
		currentRoom.getExitString();
	}

	/**
	 * Looks around room and prints long description of current room.
	 */
	private void look() {
		System.out.println(currentRoom.getLongDescription());
	}

	/**
	 * Player drinks a beer.
	 */
	private void drink() {
		System.out.println("You just had a drink!");
	}

	/**
	 * Given a command, process (that is: execute) the command.
	 * 
	 * @param command
	 *            The command to be processed.
	 * @return true If the command ends the game, false otherwise.
	 */
	private boolean processCommand(Command command) {
		boolean wantToQuit = false;

		if (command.isUnknown()) {
			System.out.println("I don't know what you mean...");
			return false;
		}

		String commandWord = command.getCommandWord();
		if (commandWord.equals("help")) {
			printHelp();
		} else if (commandWord.equals("go")) {
			goRoom(command);
		}

		else if (commandWord.equals("look")) {
			look();
		}

		else if (commandWord.equals("drink")) {
			drink();
		}

		else if (commandWord.equals("quit")) {
			wantToQuit = quit(command);
		}

		return wantToQuit;
	}

	// implementations of user commands:

	/**
	 * Print out some help information. Here we print some stupid, cryptic
	 * message and a list of the command words.
	 */
	private void printHelp() {
		System.out.println("You are lost. You are alone. You wander");
		System.out.println("around in the scary restaurant.");
		System.out.println();
		System.out.println("Your command words are: ");
		System.out.println(parser.showCommands());
	}

	/**
	 * Try to go in one direction. If there is an exit, enter the new room,
	 * otherwise print an error message.
	 */
	private void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("Go where?");
			return;
		}

		String direction = command.getSecondWord();

		Room nextRoom = currentRoom.getExit(direction);

		if (nextRoom == null) {
			System.out.println("There is no door!");
		} else {
			currentRoom = nextRoom;
		}

		printLocationInfo();

	}

	/**
	 * "Quit" was entered. Check the rest of the command to see whether we
	 * really quit the game.
	 * 
	 * @return true, if this command quits the game, false otherwise.
	 */
	private boolean quit(Command command) {
		if (command.hasSecondWord()) {
			System.out.println("Quit what?");
			return false;
		} else {
			return true; // signal that we want to quit
		}
	}
}
