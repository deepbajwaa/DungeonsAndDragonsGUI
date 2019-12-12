package sbajwa;
import dnd.models.Exit;
import dnd.models.Trap;
import java.util.ArrayList;
import dnd.die.D20;
import dnd.die.D10;
import dnd.die.D6;

/*
 * Name:Simrandeep Bajwa
 * Date: October 25, 2019
 * Student Number: 1040216
 * Email: sbajwa05@uoguelph.ca
 */

public class Door implements java.io.Serializable {
 /*Instance Variables*/
 /**
 *A Boolean to set the door to trapped or not.
 */
 private Boolean isTrapped;
 /**
 *A Boolean to set the door to an archway or not.
 */
 private Boolean isArchway;
 /**
 *A Boolean to set the door to open or not.
 */
 private Boolean isOpen;
 /**
 *A String to hold to door's description.
 */
 private String description;
 /**
 *A String to hold to door's exit description.
 */
 private String exitDescription;
 /**
 *An object of the Trap class.
 */
 private Trap doorTrap;
 /**
 *An arrayList of spaces to see what it behind and in front a door.
 */
 private ArrayList<Space> spacesDoor;
 /**
  *A char representing the door's id.
  */
 private char doorId;

/**
*This constructor creates an object of the Door class, and initializes the appropriate variables.
*/
public Door() {
   /*Create Door objects*/
   spacesDoor = new ArrayList<Space>();
   doorTrap = new Trap();

   /*Initilize to boolean instance varibles appropriately*/
   this.isTrapped = false;
   this.isOpen = true;
   this.isArchway = false;

  /*Rolls dice to determine if doors should be set to trapped, open, or an Archway*/
  this.setupDoor();

  /*Exit description is empty*/
  this.exitDescription = "";

}

/**
*This constructor creates a Door object based on the exit that is passed in, and initializes the appropriate variables.
*@param theExit this is an object of the Exit class.
*/
public Door(Exit theExit) {
  //sets up the door based on the Exit from the tables

  /*Create Die objects*/
  spacesDoor = new ArrayList<Space>();
  doorTrap = new Trap();

  /*Initilize to boolean instance varibles appropriately*/
  this.isTrapped = false;
  this.isOpen = true;
  this.isArchway = false;

  /*Rolls dice to determine if doors should be set to trapped, open, or an Archway*/
  this.setupDoor();

  /*Add the location and direction of the door*/
  this.exitDescription = "";
  this.getExitDescription(theExit);
}

/**
*This method adds a trap to the door if the door is not an archway and the flag is set to true.
*@param flag this tells the method to set the trap to true or false.
*@param roll this is an optional parameter if the user wants to pass in a dice roll.
*/
public void setTrapped(boolean flag, int... roll) {

  /*Check to see if door is trapped*/
  if (flag && !this.isArchway) {
    this.isTrapped = true;
    if (roll.length < 1) {
      this.doorTrap.chooseTrap(this.rollD20());
    } else {
      this.doorTrap.chooseTrap(roll[0]);
    }
  } else {
    this.isTrapped = false;
  }
}

/**
*This method sets the door to open or not open.
*@param flag this tells the method to set the door to open or not open depending on if true or false is passed.
*/
public void setOpen(boolean flag) {
  /*Set to flag if the door is not an archway*/
  if (!this.isArchway) {
    this.isOpen = flag;
  } else {
    this.isOpen = true;
  }
}

/**
*This method is used to set the door to an archway.
*@param flag this tells the method to set the door to an archway or not depending on if true or false is passed.
*/
public void setArchway(boolean flag) {
  /*Sets the door to an archway depending on the flag that is passed in.*/
  if (flag) {
    this.isArchway = flag;
    this.isOpen = true;
    this.isTrapped = false;
  } else {
    this.isArchway = false;
  }
}

/**
*This method will indicate if the door is trapped.
*@return If the door is trapped or not.
*/
public boolean isTrapped() {
  return isTrapped;
}

/**
*This method will indicate if the door is open.
*@return If the door is open or not.
*/
public boolean isOpen() {
  return isOpen;
}

/**
*This method will indicate if the door is archway.
*@return If the door is an archway or not.
*/
public boolean isArchway() {
  return isArchway;
}

/**
*This method is used to get the description of the trap.
*@return A String description of the trap.
*/
public String getTrapDescription() {
  return doorTrap.getDescription();
}

/**
*This method is used to get all the spaces that the door has.
*@return The arraylist of spaces.
*/
public ArrayList<Space> getSpaces() {
  //returns the two spaces that are connected by the door
  return spacesDoor;
}

/**
*This method is used to set only one space of the door if needed so.
*@param spaceOne this is the chamber or passage that is attached to the door.
*/
public void setSpacesOne(Space spaceOne) {
  //identifies the two spaces with the door
  spacesDoor.add(spaceOne);
}

/**
*This method is used to set two spaces of the door if needed so.
*@param spaceOne this is the chamber or passage that is attached to the door.
*@param spaceTwo this is the chamber or passage that is attached to the door.
*/
public void setSpaces(Space spaceOne, Space spaceTwo) {
  //identifies the two spaces with the door
  spacesDoor.add(spaceOne);
  spacesDoor.add(spaceTwo);
}

/**
*This method is used to get the description of the door.
*@return A string description of the door.
*/
public String getDescription() {
  /*Tell the user the descirption of the door*/
  this.setDescription();
  return description;
}

/**
*This method is used to get a roll from the d20 die.
*@return a roll from on the d20 dice.
*/
private int rollD20() {
  /*Create a D20 object*/
  D20 die = new D20();

  /*Get a roll*/
  int roll = die.roll();

  /*Return the roll*/
  return roll;
}

/**
*This method is used to get a roll from the d10 die.
*@return a roll from on the d10 dice.
*/
private int rollD10() {
  /*Create a D20 object*/
  D10 die = new D10();

  /*Get a roll*/
  int roll = die.roll();

  /*Return the roll*/
  return roll;
}

/**
*This method is used to get a roll from the d6 die.
*@return a roll from on the d6 dice.
*/
private int rollD6() {
  /*Create a D20 object*/
  D6 die = new D6();

  /*Get a roll*/
  int roll = die.roll();

  /*Return the roll*/
  return roll;
}

/**
 *This method is used to roll a chance of a door being trapped, open, or an archway accordingly.
 */
private void setupDoor() {

  /*Set up the doors depending on the roll that is returned from the D20, D6, and D10 dice.*/
  /*1/20 chance a door is trapped*/
  if (this.rollD20() == 1) {
    this.isTrapped = true;
    doorTrap.chooseTrap(this.rollD20());
  }

  /*1/6 chance a door is locked*/
  if (this.rollD6() == 1) {
    this.isOpen = false;
  }

  /*1/10 chance a door is an archway*/
  if (this.rollD10() == 1) {
    this.isArchway = true;
  }
}

/**
*This method gets the description of an exit.
*@param theExit this is an object of the exit class used to initial the exit description.
*/
private void getExitDescription(Exit theExit) {
  /*Create the exit description*/
  this.exitDescription = exitDescription.concat("This door is " + theExit.getDirection() + " and is located at " + theExit.getLocation() + ".\n");
}

/**
This method sets the description of the door.
*/
private void setDescription() {
  /*Initalize variables*/
  int counter = 0;
  description = "";

  /*Set description based on flags*/
  this.setDescriptionFlags();
  description = description.concat(exitDescription);
}

/**
*This method will create the description of the door based on the isArchway, isTrapped, and isOpen flags.
*/
private void setDescriptionFlags() {
  /*Get the description of the door based on the flags.*/
  if (isArchway) {
    description = description.concat("The door is an archway!\n" + "The door is open!\n");
  } else {
    if (isTrapped) {
      description = description.concat("There is a trap on this door!" + doorTrap.getDescription() + "\n");
    } else {
      description = description.concat("The door is not trapped!\n");
    }

    if (isOpen) {
      description = description.concat("The door is open!\n");
    } else {
      description = description.concat("The door is closed!\n");
    }
  }

}

  /**
   *This method allows the user to set the door's ID.
   *@param id is a char that is passed as the door's id.
   */
  public void setDoorId(char id) {
    /*set door Id*/
    this.doorId = id;
  }

  /**
   *This method is used to get the chamber's ID.
   *@return A char which represents the chamber's ID.
   */
  public char getDoorId() {
    /*set door Id*/
    return doorId;
  }

}
