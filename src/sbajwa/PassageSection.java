package sbajwa;

import dnd.models.Monster;
import dnd.die.Percentile;

/*
 * Name:Simrandeep Bajwa
 * Date: November 17, 2019
 */

public class PassageSection implements java.io.Serializable {

  /*Instance variables*/

  /**
  *An object of the Monster class.
  */
  private Monster aMonster;
  /**
  *An object of the Door class.
  */
  private Door aDoor;
  /**
  *A String to hold the passage section description.
  */
  private String passage;
  /**
  *A boolean to indicate if a monster exists.
  */
  private Boolean existMonster;
  /**
  *A boolean to indicate if a door exists.
  */
  private Boolean existDoor;

/**
*This constructor creates an object of the PassageSection class.
*/
public PassageSection() {
  //sets up the 10 foot section with default settings
  this.passage = "";
  existMonster = false;
  existDoor = false;
  aMonster = null;
  aDoor = null;
}

/**
*This constructor creates an object of the PassageSection class with a specified description.
*@param description is the description specified.
*/
public PassageSection(String description) {
  //sets up a specific passage based on the values sent in from
  this.passage = description;

  /*Get a door if there is a door*/
  this.checkForDoor(passage);
  /*Get a monster if there is a monster*/
  this.checkForMonster(passage);
}

/**
*Checks to see if the string entered has a door, and if so a door is generated.
*@param passageSection is a string containing the description of the passagesection.
*/
private void checkForDoor(String passageSection) {

  /*Get a door if there is a door*/
  if (passageSection.indexOf("door") != -1 || passageSection.indexOf("Door") != -1) {
    existDoor = true;
    aDoor = new Door();
    if (passageSection.contains("archway")) {
      aDoor.setArchway(true);
    }
  } else {
    existDoor = false;
    aDoor = null;
  }

}

/**
*Checks to see if the string entered has a monster, and if so a monster is generated.
*@param passageSection is a string containing the description of the passagesection.
*/
private void checkForMonster(String passageSection) {

  /*Get a monster if there is a monster*/
  if (passageSection.indexOf("Monster") != -1) {
    existMonster = true;
    aMonster = new Monster();
    aMonster.setType(this.getRoll());
  } else {
    existMonster = false;
    aMonster = null;
  }

}

/**
*This method is used to get a roll between 1 and 100.
*@return A roll between 1 and 100 is returned.
*/
private int getRoll() {
  /*Return a roll on a percentile dice*/
  Percentile die = new Percentile();
  int roll = die.roll();
  return roll;
}

/**
*This method is used to get a door from the passageSection.
*@return a Door in the passageSection.
*/
public Door getDoor() {
 return aDoor;
}
/**
*This method is used to get the monster from the passageSection.
*@return a Monster in the passageSection.
*/
public Monster getMonster() {
  //returns the monster that is in the passage section, if there is one
  return aMonster;

}
/**
*This method is used to get the description of the passageSection.
*@return a string description of the passageSection.
*/
public String getDescription() {
  return this.passage;
}

/**
*This method attaches a door to a passageSection.
*@param enteredDoor is the door that is to be attached to the passage section.
*/
public void setDoor(Door enteredDoor) {
  existDoor = true;
  this.aDoor = enteredDoor;
}

/**
*This method adds a monster to the passagesection.
*@param enteredMonster is the monster to be added to the passage section.
*/
public void setMonster(Monster enteredMonster) {
  existMonster = true;
  this.aMonster = enteredMonster;
}

/**
*This method is used to determine if there is a monster in the passage Section.
*@return true if there is a monster in the passage section.
*/
public Boolean isMonster() {
  return existMonster;
}

/**
*This method is used to determine if there is a door in the passage section.
*@return true if there is a door in the passage section.
*/
public Boolean isDoor() {
  return existDoor;
}

}
