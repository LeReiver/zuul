
/**
 * This class is part of the "Haunted Restaurant" application. 
 * "Haunted Restaurant" is a very simple, text based adventure game.  Users 
 * must enter the restaurant either through the bar or the lobby, and make 
 * thier way through the restaurant while dodging ghosts. There is a secret
 * exit through the restroom in the back... if the player makes it that far. 
 * 
 * This class sets and returns information about items in the rooms.
 * 
 * @author Michael Le-Reiver 
 * @version 2015.10.13
 */
public class Item
{
    private String itemName;
    private int itemWeight;

    /**
     * Constructor for objects of class Item.
     * @param itemName
     * @param itemWeight
     */
    public Item(String itemName, int itemWeight)
    {
        this.itemName = itemName;
        this.itemWeight = itemWeight;
    }

    /**
     * @return Return item name.
     */
    public String getItemName()
    {
        return itemName;
    }

    /**
     * @return Return item weight.
     */
    public int getItemWeight()
    {
        return itemWeight;
    }

}
