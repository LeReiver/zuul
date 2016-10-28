/**
 * This class is the main class of the "Haunted Restaurant" application. 
 *  "Haunted Restaurant" is a very simple, text based adventure game.  Users 
 *  must enter the restaurant either through the bar or the lobby, and make 
 *  their way through the restaurant while dodging ghosts. There is a secret
 *  exit through the restroom in the back... if the player makes it that far.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 * 
 * @author Mike Le-Reiver
 * @version 2015.11.10
 */
public class Main {

	public static void main(String[] args) {
		Game  game = new Game();
		game.play();
	}

}
