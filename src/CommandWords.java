import java.util.ArrayList;
import java.util.Arrays;
/**
 * This class is part of the "Haunted Restaurant" application. 
 * "Haunted Restaurant" is a very simple, text based adventure game.  Users 
 * must enter the restaurant either through the bar or the lobby, and make 
 * thier way through the restaurant while dodging ghosts. There is a secret
 * exit through the restroom in the back... if the player makes it that far. 
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes and Michael Le-Reiver
 * @version 2015.10.11
 * Modifications:
 * - added "look" and "drink" to String[] validCommands
 * - added showAll() method
 * - changed showAll() method to getCommandList() so it 
 *   returns a String instead of printing the results
 * - 
 * 
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
            "go", "quit", "help", "look", "drink",
        };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * @return Return all valid commands to a String.
     */
    public String getCommandList()
    {
        String command = Arrays.toString(validCommands);
        return command;
    }
}

