package sbajwa;
import java.util.ArrayList;
import java.util.HashMap;
import dnd.die.D20;

/*
 * Name:Simrandeep Bajwa
 * Date: November 17, 2019
 * Student Number: 1040216
 * Email: sbajwa05@uoguelph.ca
 */
/*Create a method that adds the doors back to the chambers*/

public class Level implements java.io.Serializable {
/**
*An arrayList of all the chambers in the level.
*/
private ArrayList<Chamber> allChambers;
/**
*This hashmap is used to hold all the doors associated with a chambers.
*/
private HashMap<Chamber, ArrayList<Door>> connectionMap;
/**
*This hashmap is used to hold all the door to door connections.
*/
private HashMap<Door, ArrayList<Chamber>> finalLevel;
/**
*This passage is used to generate passages for the door connections.
*/
private Passage aPassage;
/**
*This is used to set an ID for the passage.
*/
private char passageChar;
/**
*This constructor creates an object of the Level class.
*/
public Level() {
  /*Creates initial 5 chambers*/
  this.createChambers();
  /*System.out.println(allChambers);*/
  this.createConnectionMap();
  finalLevel = new HashMap<>();
  passageChar = 'A';
  aPassage = null;
}

/**
*This method creates the initial 5 chambers and add its to the arrayList of chambers.
*/
public void createChambers() {

  /*Declare variables*/
  int counter;
  char id = 'A';

  /*Initialize arraylist*/
  allChambers = new ArrayList<Chamber>();

  /*Creates the initial 5 chambers*/
  for (counter = 0; counter < 5; counter++) {
     allChambers.add(new Chamber());

  }

  /*Set Id for all chambers*/
  for (counter = 0; counter < 5; counter++) {
     allChambers.get(counter).setChamberId(id);
     id++;
  }

}

  /**
   *Returns all the chambers in the Level.
   * @return an arraylist of all the chambers in the level.
   */
  public ArrayList<Chamber> getAllChambers() {
    return allChambers;
  }
  /**
   *Returns all the chambers in the Level.
   * @return an arraylist of all the chambers in the level.
   */
  public ArrayList<Passage> getAllPassages() {
    /*Create an arraylist of passages*/
    ArrayList<Passage> allPassages = new ArrayList<Passage>();
    Passage temp;

    /*Run through the passages and add all the passages*/
    for (Door aDoor: finalLevel.keySet()) {
      temp = (Passage) aDoor.getSpaces().get(2);
      if (!allPassages.contains(temp)) {
        allPassages.add(temp);
      }
    }

    return allPassages;
  }

  /**
   * This method adds a monster to a chamber that is specified.
   * @param monster this is the monster to be added to a chamber.
   * @param option this is a string containing what chamber a monster is to be added too.
   */
  public void addMonsterToChamber(String option, String monster) {
    /*Declare variables*/
    String check;

    /*Find the correct chamber and return*/
    for (Chamber temp: allChambers) {
      check = " " + temp.getChamberId();
      if (option.contains(check)) {
        temp.generateSpecificMonster(monster);
        break;
      }
    }

  }

/**
 * This method adds a treasure to a chamber that is specified.
 * @param treasure this is the treasure to be added to a chamber.
 * @param option this is a string containing what chamber a treasure is to be added too.
 */
public void addTreasureToChamber(String option, String treasure) {
  /*Declare variables*/
  String check;

  /*Find the correct chamber and return*/
  for (Chamber temp : allChambers) {
    check = " " + temp.getChamberId();
    if (option.contains(check)) {
      temp.generateSpecificTreasure(treasure);
      break;
    }
  }

}

/**
 * This method removes a monster to a chamber that is specified.
 * @param monster this is the monster to be removed from a chamber.
 * @param option this is a string containing what chamber a monster is to be removed from.
 */
public void removeMonsterFromChamber(String option, String monster) {
  /*Declare variables*/
  String check;

  /*Find the correct chamber and return*/
  for (Chamber temp: allChambers) {
    check = " " + temp.getChamberId();
    if (option.contains(check)) {
      temp.removeMonster(monster);
      break;
    }
  }
}

/**
 * This method removes a treasure to a chamber that is specified.
 * @param treasure this is the treasure to be removed from a chamber.
 * @param option this is a string containing what chamber a monster is to be removed from.
 */
public void removeTreasureFromChamber(String option, String treasure) {
  /*Declare variables*/
  String check;

  /*Find the correct chamber and return*/
  for (Chamber temp: allChambers) {
    check = " " + temp.getChamberId();
    if (option.contains(check)) {
      temp.removeTreasure(treasure);
      break;
    }
  }
}
/**
 * This method adds a monster to a chamber that is specified.
 * @param monster this is the monster to be added to a passage.
 * @param option this is a string containing what passage a monster is to be added too.
 */
public void addMonsterToPassage(String option, String monster) {
  /*Declare variables*/
  ArrayList<Passage> allPassages = this.getAllPassages();
  String check;

  /*Find the correct chamber and return*/
  for (Passage temp: allPassages) {
    check = " " + temp.getPassageId();
    if (option.contains(check)) {
      temp.generateSpecificMonster(monster);
      break;
    }
  }
}

/**
 * This method adds a treasure to a passage that is specified.
 * @param treasure this is the treasure to be added to a passage.
 * @param option this is a string containing what passage a treasure is to be added too.
 */
public void addTreasureToPassage(String option, String treasure) {
  /*Declare variables*/
  ArrayList<Passage> allPassages = this.getAllPassages();
  String check;

  /*Find the correct chamber and return*/
  for (Passage temp : allPassages) {
    check = " " + temp.getPassageId();
    if (option.contains(check)) {
      temp.generateSpecificTreasure(treasure);
      break;
    }
  }
}
/**
 * This method removes a monster from a passage that is specified.
 * @param monster this is the monster to be removed from a passage.
 * @param option this is a string containing what passage a monster is to be removed from.
 */
public void removeMonsterFromPassage(String option, String monster) {
  /*Declare variables*/
  ArrayList<Passage> allPassages = this.getAllPassages();
  String check;

  /*Find the correct chamber and return*/
  for (Passage temp: allPassages) {
    check = " " + temp.getPassageId();
    if (option.contains(check)) {
      temp.removeMonster(monster);
      break;
    }
  }
}

/**
 * This method removes a treasure to a passage that is specified.
 * @param treasure this is the treasure to be removed from a passage.
 * @param option this is a string containing what chamber a monster is to be removed from.
 */
public void removeTreasureFromPassage(String option, String treasure) {
  /*Declare variables*/
  ArrayList<Passage> allPassages = this.getAllPassages();
  String check;

  /*Find the correct chamber and return*/
  for (Passage temp: allPassages) {
    check = " " + temp.getPassageId();
    if (option.contains(check)) {
      temp.removeTreasure(treasure);
      break;
    }
  }
}

/**
 * This method is used to attach the doors back to all the chambers once they have been removed.
 */
public void reattachDoors() {
  /*Declare Variables*/
  Chamber temp;

  /*Run through the array list of chambers*/
  for (Chamber aChamber: allChambers) {
    for (Door aDoor: finalLevel.keySet()) {
       temp = (Chamber) aDoor.getSpaces().get(0);
        if (temp == aChamber) {
          aChamber.setDoor(aDoor);
        }
    }
  }
}

/**
*This method creates the initial connection HashMap with each chamber having a corresponding Door ArrayList.
*/
public void createConnectionMap() {
  /*Initalize hashmap*/
  connectionMap = new HashMap<>();

  /*Creates the connection hashmap*/
  for (Chamber aChamber: allChambers) {
    connectionMap.put(aChamber, aChamber.getDoors());
  }
}

/**
*This method is used to determine the chamber with the most doors in the connection Hashmap.
*@param anyChamberInHashmap this is a chamber that is passed in that is in the hashMap.
*@return the chamber with the greatest amount of exits in the hashMap is returned.
*/
public Chamber biggestChamber(Chamber anyChamberInHashmap) {
  /*Initalize variables*/
  Chamber largestChamber = anyChamberInHashmap;

  if (largestChamber == null) {
    return null;
  }

  /*Iterate through the hashMap*/
  for (HashMap.Entry<Chamber, ArrayList<Door>> entry: connectionMap.entrySet()) {
    if (entry.getValue().size() > largestChamber.getDoors().size()) {
      largestChamber = entry.getKey();
    }
  }

  /*Return largestChamber*/
  return largestChamber;
}

/**
*This method is used to determine the chamber with the least amount of doors in the connection Hashmap.
*@param anyChamberInHashmap this is a chamber that is passed in that is in the hashMap.
*@return the chamber with the least amount of exits in the hashMap is returned.
*/
public Chamber smallestChamber(Chamber anyChamberInHashmap) {
  /*Initalize variables*/
  Chamber smallestChamber = anyChamberInHashmap;

  if (smallestChamber == null) {
    return null;
  }

  /*Iterate through the hashMap*/
  for (HashMap.Entry<Chamber, ArrayList<Door>> entry: connectionMap.entrySet()) {
    if (entry.getValue().size() < smallestChamber.getDoors().size()) {
      smallestChamber = entry.getKey();
    }
  }

  /*Return largestChamber*/
  return smallestChamber;
}

/**
*This method is used to get a Door from a given key in a Hashmap.
*@param aChamber this is a chamber that is passed in from where a use wants to find a door.
*@return An unsused door is returned from the chamber that was passed in if one exits.
*/
public Door findDoor(Chamber aChamber) {
  /*Create a temporary ArrayList*/
  ArrayList<Door> doors = connectionMap.get(aChamber);

  /*Create a door that needs to be returned*/
  Door doorFound = null;

  /*Search for a door to return*/
  if (doors.size() > 0) {
    for (Door aDoor: doors) {
      if (aDoor.getSpaces().size() < 2) {
        doorFound = aDoor;
        break;
      }
    }
  }

  /*Remove the door from the arraylist of doors in the connection HashMap*/
  if (doorFound != null) {
    doors.remove(doorFound);
    connectionMap.remove(aChamber);
    /*Only if the chamber still has doors availble should it be added back to the hashMap*/
    if (doors.size() > 0) {
      connectionMap.put(aChamber, doors);
    }
  }

  /*Return the door that was found*/
  return doorFound;
}

/**
*This method removes the chamber from the hashmap if it does not have anymore doors.
*@param aChamber this is a chamber that a user wants to remove from the connection hashmap.
*/
public void removeDoor(Chamber aChamber) {
  /*Remove the door from the hashmap*/
  connectionMap.remove(aChamber);
}

/**
*This method is used to make a door to door connection.
*@param doorOne this is a Door object, and the first door in the connection to be made.
*@param doorTwo this is a Door object, and the second door in the connection to be made.
*@param chamberOne this is a Chamber object, and is the chamber that the first door is connected too.
*@param chamberTwo this is a Chamber object, and is the chamber that the second door is connected too.
*/
public void attach(Door doorOne, Door doorTwo, Chamber chamberOne, Chamber chamberTwo) {
  /*Create hashmaps for both of the chambers that are going to be added to the final hashmap*/
  ArrayList<Chamber> doorOneTarget = new ArrayList<Chamber>();
  ArrayList<Chamber> doorTwoTarget = new ArrayList<Chamber>();

  /*Add the doors to the appropriate arrayList*/
  doorOneTarget.add(chamberTwo);
  doorTwoTarget.add(chamberOne);

  /*Add instances of both chambers to the doors*/
  doorOne.setSpacesOne(chamberTwo);
  doorTwo.setSpacesOne(chamberOne);

  /*Put the attached doors into the final hashmap*/
  finalLevel.put(doorOne, doorOneTarget);
  finalLevel.put(doorTwo, doorTwoTarget);

  /*Create a passage to connect the two doors*/
  this.generatePassage();
  aPassage.setPassageId(passageChar);
  passageChar++;
  doorOne.setSpacesOne(aPassage);
  doorTwo.setSpacesOne(aPassage);

}

/**
*This method generates the entire level.
*/
public void createLevel() {
  /*Declare variables / objects*/
  Chamber bigOne = null;
  Chamber smallOne = null;
  Chamber leftOver = null;
  Door bigDoor = null;
  Door smallDoor = null;
  Door aDoor = null;
  Door temp = null;
  int counter;

  /*Get random chamber*/
  Chamber rand = new Chamber();
  for (Chamber aChamber: connectionMap.keySet()) {
    rand = aChamber;
    break;
  }

  bigOne = this.biggestChamber(rand);
  smallOne = this.smallestChamber(rand);

  /*Run throught he connectionMap getting connections and adding them to the finalLevel*/
  while (connectionMap.size() > 1 && bigOne != null && smallOne != null) {
    bigOne = this.biggestChamber(bigOne);
    smallOne = this.smallestChamber(bigOne);


    /*If bigOne and smallOne are the same chamber, change bigOne*/
    if (bigOne == smallOne) {
      for (Chamber aChamber: connectionMap.keySet()) {
         if (bigOne == smallOne) {
           bigOne = aChamber;
         }
      }
    }

    /*Get a door from each of the chamber*/
    bigDoor = findDoor(bigOne);
    smallDoor = findDoor(smallOne);

    /*Attach the two doors*/
    if (smallDoor != null && bigDoor != null) {
      attach(bigDoor, smallDoor, bigOne, smallOne);
    }

  }

  /*Check if there are any left over doors*/
  if (!connectionMap.isEmpty()) {
    /*Get the last chamber*/
    leftOver = new Chamber();
    for (Chamber aChamber: connectionMap.keySet()) {
      leftOver = aChamber;
      break;
    }

    /*Find the door in the left over chamber*/
    aDoor = findDoor(leftOver);
    rand = leftOver;

    /*Get a random chamber that is not the left over chamber*/
    for (Door aDoor1: finalLevel.keySet()) {
      rand = (Chamber) aDoor1.getSpaces().get(0);
      if (rand != leftOver) {
        temp = aDoor1;
        break;
      }
    }

    /*Attach the left over door to one of te doors in the random chamber*/
    if (aDoor != null) {
      attach(aDoor, temp, leftOver, rand);
    }
  }
}

/**
*This method is used to print out the entire level.
*/
public void printLevel() {
  /*Declare variables*/
  ArrayList<Door> doorConnections = new ArrayList<Door>();
  Passage tempPassage = null;
  char doorChar = 'A';
  Chamber checkChamber = null;
  Chamber chamberNext = null;

  /*Print an intro*/
  System.out.println("*********************************************************************************");
  System.out.println("**********************WELCOME TO DUNGEONS AND DRAGONS****************************");
  System.out.println("*********************************************************************************");

  /*This is used to loop through all 5 of the chambers and prints their description*/
  for (int counter = 0; counter < 5; counter++) {
    System.out.println("****************** Chamber " + allChambers.get(counter).getChamberId() + "**********************");
    System.out.println(allChambers.get(counter).getDescription());
    System.out.println("**************************************************");

    /*This loops through all the doors in the chmaber and adds it to an arraylist of doors for that chamber*/
    for (Door aDoor: finalLevel.keySet()) {
      checkChamber = (Chamber) aDoor.getSpaces().get(0);
      if (checkChamber == allChambers.get(counter)) {
        doorConnections.add(aDoor);
      }
    }

    /*This is used to print out each of the doors in the chamber, and the passage that the doors leads too*/
    System.out.println("There are " + doorConnections.size() + " exits.");
    for (Door aDoor: doorConnections) {
      System.out.println("********************Door " + doorChar + "******************");
      System.out.println(aDoor.getDescription());
      tempPassage = (Passage) aDoor.getSpaces().get(2);
      chamberNext = (Chamber) aDoor.getSpaces().get(1);
      System.out.println("This door leads to Passage " + tempPassage.getPassageId() + ".");
      System.out.println("********************************************");
      System.out.println("*****************Passage " + tempPassage.getPassageId() + "******************");
      System.out.println(tempPassage.getDescription());
      System.out.println("This passage leads to Chamber " + chamberNext.getChamberId() + ".");
      System.out.println("********************************************");
      doorChar++;
    }

    /*Reset variables for the next iteration*/
    doorChar = 'A';
    checkChamber = null;
    doorConnections = null;
    doorConnections = new ArrayList<Door>();

  }
}
/**
 * This method will return an arraylist of all the doors in a chamber.
 * @param selectedChamber is the chamber to get all the doors from.
 * @return An arraylist containing all the doors in a Chamber.
 */
public ArrayList<Door> allChamberDoors(Chamber selectedChamber) {
  /*Initialize variables*/
  ArrayList<Door> allChamberDoors = new ArrayList<Door>();
  Chamber checkChamber;

  /*This loops through all the doors in the chmaber and adds it to an arraylist of doors for that chamber*/
  for (Door aDoor: finalLevel.keySet()) {
    checkChamber = (Chamber) aDoor.getSpaces().get(0);
    if (checkChamber == selectedChamber) {
      allChamberDoors.add(aDoor);
    }
  }

  /*Return the doors*/
  return allChamberDoors;
}

/**
 * This method will return an arraylist of all the doors in a Passage.
 * @param selectedPassage is the Passage to get all the doors from.
 * @return An arraylist containing all the doors in a Passage.
 */
public ArrayList<Door> allPassageDoors(Passage selectedPassage) {
  /*Initialize variables*/
  ArrayList<Door> allPassageDoors = new ArrayList<Door>();
  Passage checkPassage;

  /*This loops through all the doors in the level and adds it to an arraylist of doors for that passage*/
  for (Door aDoor: finalLevel.keySet()) {
    checkPassage = (Passage) aDoor.getSpaces().get(2);
    if (checkPassage == selectedPassage) {
      allPassageDoors.add(aDoor);
    }
  }

  /*Return the doors*/
  return allPassageDoors;
}

/**
*This method is used to generate passages for the door connections.
*/
public void generatePassage() {
  /*Declare Variables*/
  int numOfPassageSections = 0;
  aPassage = new Passage();
  PassageSection aPassageSection = new PassageSection();
  int randomRoll;

  do {

    /*Roll a number between 1 and 20*/
    randomRoll = this.rollD20();

    /*Generate a passage using the table*/
    if ((randomRoll == 1 || randomRoll == 2) && (numOfPassageSections < 1)) {

      /*Increment number of passages*/
      numOfPassageSections++;

      /*Create a new passageSection*/
      aPassageSection = new PassageSection("passage goes straight for 10 ft");

      /*Add the passagesection to the list of passagesections in the passage*/
      aPassage.addPassageSection(aPassageSection);

    } else if ((randomRoll > 2 && randomRoll < 6) && (numOfPassageSections > 0)) {

      /*Increment number of passages*/
      numOfPassageSections++;

      /*Create a new passageSection*/
      aPassageSection = new PassageSection("passage ends in Door to a Chamber");

      /*Add the passagesection to the list of passagesections in the passage*/
      aPassage.addPassageSection(aPassageSection);

    } else if (randomRoll > 5 && randomRoll < 8 && numOfPassageSections < 1) {

      /*Increment number of passages*/
      numOfPassageSections++;

      /*Create a new passageSection*/
      aPassageSection = new PassageSection("archway (door) to right (main passage continues straight for 10 ft)");

      /*Add the passagesection to the list of passagesections in the passage*/
      aPassage.addPassageSection(aPassageSection);

    } else if (randomRoll > 7 && randomRoll < 10 && numOfPassageSections < 1) {

      /*Increment number of passages*/
      numOfPassageSections++;

      /*Create a new passageSection*/
      aPassageSection = new PassageSection("archway (door) to left (main passage continues straight for 10 ft)");

      /*Add the passagesection to the list of passagesections in the passage*/
      aPassage.addPassageSection(aPassageSection);

    } else if (randomRoll > 9 && randomRoll < 12 && numOfPassageSections < 1) {
      /*Increment number of passages*/
      numOfPassageSections++;

      /*Create a new passageSection*/
      aPassageSection = new PassageSection("passage turns to left and continues for 10 ft");

      /*Add the passagesection to the list of passagesections in the passage*/
      aPassage.addPassageSection(aPassageSection);

    } else if (randomRoll > 11 && randomRoll < 14 && numOfPassageSections < 1) {

      /*Increment number of passages*/
      numOfPassageSections++;

      /*Create a new passageSection*/
      aPassageSection = new PassageSection("passage turns to right and continues for 10 ft");

      /*Add the passagesection to the list of passagesections in the passage*/
      aPassage.addPassageSection(aPassageSection);
    } else if (randomRoll > 13 && randomRoll < 17 && numOfPassageSections > 0) {
      /*Increment number of passages*/
      numOfPassageSections++;
      /*Create a new passageSection*/
      aPassageSection = new PassageSection("passage ends in archway (door) to chamber");
      /*Add the passagesection to the list of passagesections in the passage*/
      aPassage.addPassageSection(aPassageSection);

    } else if (randomRoll == 17 && numOfPassageSections < 1) {

      /*Increment number of passages*/
      numOfPassageSections++;

      /*Create a new passageSection*/
      aPassageSection = new PassageSection("Stairs, (passage continues straight for 10 ft)");

      /*Add the passagesection to the list of passagesections in the passage*/
      aPassage.addPassageSection(aPassageSection);

    } else if (randomRoll > 17 && randomRoll < 20) {
      /*Reset everything if a deadend is reached*/
      numOfPassageSections = 0;
      aPassage = new Passage();

    } else if (randomRoll == 20) {

      /*Increment number of passages*/
      numOfPassageSections++;

      /*Create a new passageSection*/
      aPassageSection = new PassageSection("Wandering Monster (passage continues straight for 10 ft)");

      /*Add the passagesection to the list of passagesections in the passage*/
      aPassage.addPassageSection(aPassageSection);

    }

  } while (numOfPassageSections < 2);

}

/**
*This method is used to get a roll from the d20 die.
*@return a roll from on the d20 dice.
*/
private int rollD20() {
  /*Create a D20 object*/
  D20 die = new D20();

  int roll = die.roll();

  return roll;
}

/**
*Used to set passageChar.
*@param letter is a char that you want to set the passageChar too.
*/
private void setPassageChar(char letter) {
  this.passageChar = letter;
}

/**
*Used to set passageChar.
*@return passageChar is returned.
*/
private char getPassageChar() {
  return passageChar;
}

}
