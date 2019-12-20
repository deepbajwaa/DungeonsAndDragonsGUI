package sbajwa;

import dnd.models.ChamberContents;
import dnd.models.ChamberShape;
import dnd.models.Monster;
import dnd.models.Treasure;
import dnd.models.Stairs;
import dnd.models.Trap;
import dnd.models.Exit;
import java.util.ArrayList;
import dnd.models.RegularChamberShape;
import dnd.models.UnusualChamberShape;
import dnd.die.D20;
import dnd.die.Percentile;

/*
 * Name:Simrandeep Bajwa
 * Date: November 17, 2019
 */

public class Chamber extends Space implements java.io.Serializable {

/*Just some objects to hold information about the chamber*/

/**
*An object of the ChamberContents class.
*/
private ChamberContents myContents;
/**
*An object of the ChamberShape class.
*/
private ChamberShape mySize;
/**
*An object of the RegularChamberShape class.
*/
private RegularChamberShape regShape;
/**
*An object of the UnusualChamberShape class.
*/
private UnusualChamberShape unusualShape;
/**
*An arrayList of the doors in the chamber.
*/
private ArrayList<Door> allDoors;
/**
*An arrayList of the monsters in the chamber.
*/
private ArrayList<Monster> allMonsters;
/**
*An arrayList of the treasure in the chamber.
*/
private ArrayList<Treasure> allTreasure;
/**
*An aobject of the Exit class.
*/
private Exit anExit;
/**
 * An arraylist with all the exits in the chambers.
 * */
private ArrayList<Exit> allExits;
/**
*A string containing the chamber Description.
*/
private String finalDescription;
/**
*A char representing the chamber's id.
*/
private char chamberId;
/**
 * This flag is used to indicate if a monster should be generated or not.
 */
private boolean genMonster;
/**
  * This flag is used to indicate if a monster should be generated or not.
  */
private boolean genTreasure;
/**
 * This flag is used to indicate if stairs have already been generated or not.
 */
private boolean noStairs;
/**
 * This flag is used to indicate if a trap has already been generated or not.
 */
private boolean noTrap;

/**
*This constructor creates an object of the Chamber class, and initializes the appropriate variables.
*/
public Chamber() {

 /*Initalize objects*/
 myContents = new ChamberContents();
 this.initializeFlags();

 /*Get Shape Object*/
 mySize = regShape.selectChamberShape(this.rollD20());

 /*Initalize objects*/
 this.initalizeArraylist();

 /*Initalize the chamber shape*/
 mySize.setNumExits(this.rollD20());

 /*Initialize all exits*/
 this.generateAllExits(mySize);

 /*Initilize the chmaber Contents*/
 myContents.chooseContents(this.rollD20());

 /*setDescription*/
 this.setDescription();

}

/**
*This constructor creates a chamber object based on the chamber shape and chamber content passed in, and initializes the appropriate variables.
*@param theShape this is an object of the ChamberContents class.
*@param theContents this is an object of the theShape class.
*/
public Chamber(ChamberShape theShape, ChamberContents theContents) {
 /*Set instance variables with the objects inserted as parameters*/
 this.mySize = theShape;
 this.myContents = theContents;
 this.initializeFlags();

 /*Initialize all exits*/
 this.generateAllExits(theShape);

 /*Initalize objects*/
 this.initalizeArraylist();

 /*setDescription*/
 this.setDescription();
}

/**
 * This method is used to initialize the boolean flags.
 */
private void initializeFlags() {
    /*Set Flags*/
    this.genMonster = true;
    this.genTreasure = true;
    this.noStairs = true;
    this.noTrap = true;
}

/**
*This method is used to initialize the arraylists of the Chamber class.
*/
private void initalizeArraylist() {
  /*Initialize All arraylists*/
  this.allTreasure = new ArrayList<Treasure>();
  this.allMonsters = new ArrayList<Monster>();
  this.allDoors = new ArrayList<Door>();
}

/**
*This is used to set the shape of the chamber.
*@param theShape this is an object of the ChamberContents class.
*/
public void setShape(ChamberShape theShape) {
 this.mySize = theShape;
 this.allDoors = new ArrayList<Door>();
 this.setDescription();
}

/**
*This method is used to get all of the doors in the chamber.
*@return an ArrayList of Doors.
*/
public ArrayList<Door> getDoors() {
 return this.allDoors;
}
/**
*This is used to add a monster to the chamber.
*@param theMonster this is an object of the Monster class.
*/
public void addMonster(Monster theMonster) {
  if (theMonster != null) {
    allMonsters.add(theMonster);
  }
}

/**
*This method returns the arraylist of monsters that are in the chamber.
*@return an ArrayList of Monsters.
*/
public ArrayList<Monster> getMonsters() {
 return this.allMonsters;
}

/**
*This is used to add a treasure to the chamber.
*@param theTreasure this is an object of the Treasure class.
*/
public void addTreasure(Treasure theTreasure) {
  if (theTreasure != null) {
    allTreasure.add(theTreasure);
  }
}
/**
*This method returns the arraylist of Treasure that are in the chamber.
*@return returns the arraylist of Treasure.
*/
public ArrayList<Treasure> getTreasureList() {
 return this.allTreasure;
}

    /**
     *This method returns the arraylist of Monsters that are in the chamber.
     *@return returns the arraylist of Monster.
     */
    public ArrayList<Monster> getMonsterList() {
        return this.allMonsters;
    }

/**
This method sets the description of the whole chamber.
*/
private void setDescription() {
 /*Get the chamber*/
 this.finalDescription = "";
 this.finalDescription = finalDescription.concat(this.generateChamberShape() + this.generateChamberContents());
}
/**
*Gets the description of the Chamber.
@return This method returns a string that contains the description of the whole chamber.
*/
@Override
public String getDescription() {
 /*Return string*/
 this.setDescription();
 return finalDescription;
}

/**
*This is used to add a door to the list of chamber's doors.
*@param newDoor this is an object of the Door class.
*/
@Override
public void setDoor(Door newDoor) {
 //should add a door connection to this room
 this.allDoors.add(newDoor);
}
/**
*This method is used to generate a description for a Treasure object.
*@return A string containing the description of the Treasure in the chamber.
*/
private String generateTreasure() {
 /*Create variables*/
 String treasureDescription;

 /*Create object*/
 Treasure ranTreasure = new Treasure();

 /*Set the treasure description*/
 ranTreasure.chooseTreasure(this.getRollPercentile());
 ranTreasure.setContainer(this.rollD20());

 /*Add Treasure to the array list*/
 this.addTreasure(ranTreasure);

 /*Create the final String*/
 treasureDescription = "The treasure contains: " + ranTreasure.getDescription() + "\n" + "The treasure is contained in a: " + ranTreasure.getContainer() + "\n";

 try {
  treasureDescription = treasureDescription.concat("The treasure is guarded by: " + ranTreasure.getProtection() + "\n");
 } catch (Exception NotProtectedException) {
  treasureDescription = treasureDescription.concat("The treasure is not guarded!" + "\n");
 }

 return treasureDescription;
}

/**
  * This method will generate a random Treasure object.
  */
public void generateATreasure() {
  /*Create object*/
  Treasure ranTreasure = new Treasure();

  /*Set the treasure description*/
  ranTreasure.chooseTreasure(this.getRollPercentile());
  ranTreasure.setContainer(this.rollD20());

  /*Add Treasure to the array list*/
  this.addTreasure(ranTreasure);
}

    /**
     * This method will generate a random Treasure object.
     * @param treasure this is the treasure to be generated.
     */
    public void generateSpecificTreasure(String treasure) {
        /*Create object*/
        Treasure specificTreasure = new Treasure();

        /*Set the treasure description*/
        specificTreasure.chooseTreasure(this.setTreasureType(treasure));
        specificTreasure.setContainer(this.rollD20());

        /*Add Treasure to the array list*/
        this.addTreasure(specificTreasure);
    }

    /**
     * This method returns the integer that correspends to a specific treasure.
     * @param treasure this is the treasure to be generated.
     * @return an integer that corresponds to a specfic treasure.
     */
    private int setTreasureType(String treasure) {
        /*Returns an integer based on the string passed into the method*/
        if (treasure.contains("copper")) {
            return 3;
        } else if (treasure.contains("silver")) {
            return 27;
        } else if (treasure.contains("electrum")) {
            return 52;
        } else if (treasure.contains("gold")) {
            return 67;
        } else if (treasure.contains("platinum")) {
            return 82;
        } else if (treasure.contains("gems")) {
            return 92;
        } else if (treasure.contains("jewellery")) {
            return 96;
        } else {
            return 99;
        }
    }

/**
  * This method will remove the last Treasure from the arraylist.
 * @param treasure this is the treasure to be removed fromt he chamber.
  */
public void removeTreasure(String treasure) {
 /*Remove the last Monster*/
    for (Treasure aTreasure: allTreasure) {
        if (aTreasure.getDescription().contains(treasure)) {
            allTreasure.remove(aTreasure);
            break;
        }
    }
}

/**
 *This method is used to generate a description for a Treasure object that is passed into the method.
 * @param aTreasure is a treasure object that is used to generate a description of a treasure.
 *@return A string containing the description of the Treasure in the chamber.
 */
private String generateTreasureWithParameter(Treasure aTreasure) {
 /*Declare variables*/
 String treasureDescription;

 /*Create the final String*/
 treasureDescription = "The treasure contains: " + aTreasure.getDescription() + "\n" + "The treasure is contained in a: " + aTreasure.getContainer() + "\n";

 try {
   treasureDescription = treasureDescription.concat("The treasure is guarded by: " + aTreasure.getProtection() + "\n");
 } catch (Exception NotProtectedException) {
   treasureDescription = treasureDescription.concat("The treasure is not guarded!" + "\n");
 }

 return treasureDescription;
}

/**
 * This method will generate a string description of all the Treasure in the Chamber.
 * @return a string description of all the Treasure in the chamber.
 */
 private String allTreasureDescription() {
 /*Create a string*/
 String allTreasureDescription = "";

 /*If there are monsters indicate there are monsters*/
 if (allTreasure.size() > 0) {
   allTreasureDescription = allTreasureDescription.concat("*****ALL TREASURES*****\n");
 }

 /*Run through all the monsters in the Treasure Array and create a description*/
 for (Treasure aTreasure: allTreasure) {
   allTreasureDescription = allTreasureDescription.concat(generateTreasureWithParameter(aTreasure));
 }

 /*Return the description*/
 return allTreasureDescription;
}
/**
*This method is used to generate a description for a Monster object.
*@return A string containing the description of the Monster in the chamber.
*/
private String generateMonster() {
 /*Declare variables*/
 String monsterDescription;

 /*Create object*/
 Monster monster = new Monster();

 /*Set the type of monster*/
 monster.setType(this.getRollPercentile());

 /*Add monster to ArrayList*/
 this.addMonster(monster);

 /*Create the final String*/
 monsterDescription = "Type of monster: " + monster.getDescription() + "\n";
 monsterDescription = monsterDescription.concat("Maximum amount: " + monster.getMaxNum() + "\n" + "Minimum amount: " + monster.getMinNum() + "\n");

 /*Return string*/
 return monsterDescription;
}

/**
 *This method is used to generate a description for a Monster object that is passed into the method.
 * @param aMonster is an object of the Monster class that is used to generate a monster description.
 *@return A string containing the description of the Monster in the chamber.
 */
private String generateMonsterWithParameter(Monster aMonster) {
    /*Declare variables*/
    String monsterDescription;

    /*Create the final String*/
    monsterDescription = "Type of monster: " + aMonster.getDescription() + "\n";
    monsterDescription = monsterDescription.concat("Maximum amount: " + aMonster.getMaxNum() + "\n" + "Minimum amount: " + aMonster.getMinNum() + "\n");

    /*Return string*/
    return monsterDescription;
}

/**
 * This method will generate a string description of all the monsters in the Chamber.
 * @return a string description of all the monsters in the chamber.
 */
private String allMonsterDescription() {
 /*Create a string*/
 String allMonstersDescription = "";

 /*If there are monsters indicate there are monsters*/
 if (allMonsters.size() > 0) {
   allMonstersDescription = allMonstersDescription.concat("*****ALL MONSTERS*****\n");
 }

 /*Run through all the monsters in the Treasure Array and create a description*/
 for (Monster aMonster: allMonsters) {
   allMonstersDescription = allMonstersDescription.concat(generateMonsterWithParameter(aMonster));
 }

 /*Return the description*/
 return allMonstersDescription;
}

/**
 * This method will generate a random Monster object.
 */
public void generateAMonster() {
    /*Create object*/
    Monster monster = new Monster();

    /*Set the type of monster*/
    monster.setType(this.getRollPercentile());

    /*Add monster to ArrayList*/
    this.addMonster(monster);
}

    /**
     * This method will generate a random Monster object.
     * @param monsterDescription this is the monster to be generated.
     */
    public void generateSpecificMonster(String monsterDescription) {
        /*Create object*/
        Monster monster = new Monster();

        /*Set the type of monster*/
        monster.setType(this.setMonsterType(monsterDescription));

        /*Add monster to ArrayList*/
        this.addMonster(monster);
    }

    /**
     * This method returns the integer that correspends to a specific monster.
     * @param monster this is the monster to be generated.
     * @return an integer that corresponds to a specfic monster.
     */
    private int setMonsterType(String monster) {
        /*Returns an integer based on the string passed into the method*/
        if (monster.contains("Ant")) {
            return 2;
        } else if (monster.contains("Badger")) {
            return 4;
        } else if (monster.contains("Beetle")) {
            return 10;
        } else if (monster.contains("Demon")) {
            return 15;
        } else if (monster.contains("Dwarf")) {
            return 17;
        } else if (monster.contains("Ear Seeker")) {
            return 18;
        } else if (monster.contains("Elf")) {
            return 19;
        } else if (monster.contains("Gnome")) {
            return 20;
        } else if (monster.contains("Goblin")) {
            return 24;
        } else if (monster.contains("Hafling")) {
            return 28;
        } else if (monster.contains("Hobgoblin")) {
            return 30;
        } else if (monster.contains("Human Bandit")) {
            return 36;
        } else if (monster.contains("Kobold")) {
            return 52;
        } else if (monster.contains("Orc")) {
            return 64;
        } else if (monster.contains("Piercer")) {
            return 68;
        } else if (monster.contains("Rat, giant")) {
            return 82;
        } else if (monster.contains("Rot grub")) {
            return 85;
        } else if (monster.contains("Shrieker")) {
            return 96;
        } else if (monster.contains("Skeleton")) {
            return 98;
        } else {
            return 100;
        }
    }



/**
 * This method will remove the last Monster from the arraylist.
 * @param monster this is the monster to be removed from the chamber.
 */
public void removeMonster(String monster) {
    /*Remove the last Monster*/
    for (Monster aMonster: allMonsters) {
        if (aMonster.getDescription().contains(monster)) {
            allMonsters.remove(aMonster);
            break;
        }
    }

    if (allMonsters.size() == 0) {
        genMonster = false;
    }
}

/**
*This method is used to generate a description for a Stairs object.
*@return A string containing the description of the Stairs in the chamber.
*/
private String generateStairs() {
 /*Declare variables*/
 String stairsDescription;

 /*Create object*/
 Stairs stairs = new Stairs();

 /*Set the type of stairs*/
 stairs.setType(this.rollD20());

 /*Create the final String*/
 stairsDescription = "Description of stairs: " + stairs.getDescription() + "\n";

 /*Indicate there are stairs now*/
 noStairs = false;

 /*Return stairs*/
 return stairsDescription;
}

/**
*@return A string containing the description of the Trap in the chamber.
*/
private String generateTrap() {
 /*Declare variables*/
 String trapDescription;

 /*Create object*/
 Trap trap = new Trap();

 /*Set the type of trap*/
 trap.chooseTrap(this.rollD20());

 /*Create final string*/
 trapDescription = "Description of trap: " + trap.getDescription() + "\n";

 /*Indicate there is a trap now*/
 noTrap = false;

 /*Return trap*/
 return trapDescription;

}

/**
**This method is used to generate a description for the Chamber's description..
*@return A string containing the description of the Chamber contents.
*/
private String generateChamberContents() {
 /*Declare variables*/
 String chamberContents = "The chamber contains: " + myContents.getDescription() + "\n";

 /*Check the string generated*/
 chamberContents = chamberContents.concat(this.generateChamberContentsString(chamberContents) + "\n" + this.allMonsterDescription() + "\n" + this.allTreasureDescription());

/*Return String*/
return chamberContents;
}

/**
*This method will create the appropriate object if it is in the Chamber's contents.
*@param chamberContents This is a string containing the contents of the chamber. This parameter will be checked to see what needs to be generated for a chamber (i.e. Treasure)
*@return A string containing the description of the Chamber contents.
*/
private String generateChamberContentsString(String chamberContents) {

  /*Declare variables*/
  String chamberContentReturn = "";

 /*Check the string generated*/
 if (chamberContents.indexOf("monster and treasure") != -1 && allTreasure.size() == 0 && allMonsters.size() == 0 && genMonster && genTreasure) {
   /*Generate monster and treasure*/
   this.generateAMonster();
   this.generateATreasure();
 } else if (chamberContents.indexOf("treasure") != -1 && allTreasure.size() == 0 && genTreasure) {
   this.generateATreasure();
 } else if (chamberContents.indexOf("monster") != -1 && allMonsters.size() == 0 && genMonster) {
     this.generateAMonster();
 } else if (chamberContents.indexOf("stairs") != -1 && noStairs) {
   chamberContentReturn = chamberContentReturn.concat(this.generateStairs());
 } else if (chamberContents.indexOf("trap") != -1 && noTrap) {
   chamberContentReturn = chamberContentReturn.concat(this.generateTrap());
 }

/*Return String*/
return chamberContentReturn;
}

/**
*This method is used to generate the Chamber's shape description.
*@return A string containing the description of the chamber shape.
*/
private String generateChamberShape() {
  /*Declare variables*/
  String chamberShapeDescription = "";
  int numOfExits = mySize.getNumExits();

  /*Create objects*/
  Door doors;
  Exit exit = new Exit();

  /*Get chamber shape and dimensions*/
  chamberShapeDescription = chamberShapeDescription.concat("The shape of the chamber is: " + mySize.getShape() + "\n" + this.generateChamberDimensions(mySize));

  /*Print the locations and the directions for all the exits of the chamber*/
  if (numOfExits == 0) {
    chamberShapeDescription = chamberShapeDescription.concat(this.generateSingleChamberExit(exit));
  } else {
    chamberShapeDescription = chamberShapeDescription.concat("The chamber has " + numOfExits + " exits.\n" + this.generateMultipleChamberExit(mySize));
  }

  /*Return string*/
  return chamberShapeDescription;
}

/**
*This method is used to generate a string containing the chamber's dimensions.
*@param aShape This is an object of the ChamberShape class that will be used to get the dimensions of the chamber.
*@return A string containing the dimensions of the chamber.
*/
private String generateChamberDimensions(ChamberShape aShape) {

  /*Declare variables*/
  String dimensions = "";
  int length;
  int width;
  int area;

  /*Get area*/
  area = this.mySize.getArea();

  /*Get Dimensions*/
  try {
    length = mySize.getLength();
    width = mySize.getWidth();
    dimensions = dimensions.concat("The chamber's length is: " + length + " feet.\n" + "The chamber's width is: " + width + " feet.\n");
  } catch (Exception UnusualShapeException) {
    dimensions = dimensions.concat("The chamber is of unusual shape and has no length or width!\n" + "The area of the chamber is: " + area + " square feet.\n");
  }

  /*Return the generated string*/
  return dimensions;
}

/**
*This method is used to generate the description of a chamber's exits if it only has one exit.
*@param aExit This is an object of the Exit class that will be used to get the direction and location of the only exit in the chamber.
*@return A string containing one exit of the chamber.
*/
private String generateSingleChamberExit(Exit aExit) {

  /*Declare variables*/
  String singleExit = "";

  /*Get single exit if no exits exist*/
  singleExit = singleExit.concat("The chamber has " + 1 + " exit.\n" + "Exit is " + aExit.getDirection() + " and is located at " + aExit.getLocation() + ".\n");

  /*Add a door for every door and add it to the list of doors*/
  Door doors = new Door(aExit);

  /*Create a reference to the chamber by passing in the object that the method is being called on*/
  doors.setSpacesOne(this);
  doors.setDoorId('A');
  this.setDoor(doors);

  /*Return the generated string*/
  return singleExit;

}

/**
*This method is used to generate the description of a chamber's exits if it only has multiple exits.
*@param aShape This is an object of the Shape class that will be used to get all the exits in a chamber.
*@return A string containing a description of all the exits in a chamber.
*/
private String generateMultipleChamberExit(ChamberShape aShape) {

  /*Declare variables*/
  String multipleExit = "";
  Door doors;
  char doorId = 'A';

  /*Reset doors in case there are already doors in the passage*/
  allDoors = new ArrayList<Door>();

  /*Get the location and direction for every exit in the chamber*/
  for (Exit aExit: allExits) {
    multipleExit = multipleExit.concat("Exit is " + aExit.getDirection() + " and is located at " + aExit.getLocation() + ".\n");
    /*Add a door for every door and add it to the list of doors*/
    doors = new Door(aExit);

    /*Create a reference to the chamber by passing in the object that the method is being called on*/
    doors.setSpacesOne(this);
    doors.setDoorId(doorId);
    doorId++;
    this.setDoor(doors);
  }

  /*Return the generated string*/
  return multipleExit;

}

/**
 * This function is used to generate all the exits in the Chamber.
 * @param shape is an object of the ChamberShape class that is used to generate all the exits in the chamber.
 */
private void generateAllExits(ChamberShape shape) {
    /*Initialize the arraylist of exits*/
    allExits =  new ArrayList<Exit>();
    int counter;

    /*Create as many exits as required*/
    for (counter = 0; counter < shape.getNumExits(); counter++) {
        allExits.add(new Exit());
    }
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
*This method is used to get a roll from the Percentile die.
*@return A roll between 1 and 100 is returned.
*/
public int getRollPercentile() {
  /*Create a percetile object and return a roll*/
  Percentile die = new Percentile();
  int roll = die.roll();
  return roll;
}

/**
*This method allows the user to set the chamber's ID.
*@param id is a char that is passed as the chamber's id.
*/
public void setChamberId(char id) {
  /*Create a percetile object and return a roll*/
  this.chamberId = id;
}

/**
*This method is used to get the chamber's ID.
*@return A char which represents the chamber's ID.
*/
public char getChamberId() {
  /*Create a percetile object and return a roll*/
  return chamberId;
}


}
