package sbajwa;

/*
 * Name:Simrandeep Bajwa
 * Date: November 17, 2019
 * Student Number: 1040216
 * Email: sbajwa05@uoguelph.ca
 */

public abstract class Space implements java.io.Serializable {

/**
*@return A string description of either a chamber or a passage.
*/
public abstract  String getDescription();
/**
*@param theDoor this is an object of the Door class which gets a passage or chamber attached to it.
*/
public abstract void setDoor(Door theDoor);

}
