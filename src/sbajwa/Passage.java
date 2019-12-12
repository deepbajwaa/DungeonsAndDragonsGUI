package sbajwa;

import dnd.die.D20;
import dnd.die.Percentile;
import dnd.models.Monster;
import dnd.models.Treasure;

import java.util.ArrayList;

/*
 * Name:Simrandeep Bajwa
 * Date: October 25, 2019
 * Student Number: 1040216
 * Email: sbajwa05@uoguelph.ca
 */

public class Passage extends Space implements java.io.Serializable {
  //these instance variables are suggestions only
  //you can change them if you wish.

  /**
  *This arraylist is used to hold all the passage section in a certain passage.
  */
  private ArrayList<PassageSection> thePassage;
  /**
  *This arraylist is used to hold all the doors in a certain passage.
  */
  private ArrayList<Door> doorList;
  /**
  *This string holds to description of passage.
  */
  private String passageDescription;
  /**
  *A char representing the chamber's id.
  */
  private char passageId;
  /**
   * An arraylist to hold all the monsters in the passage.
   */
  private ArrayList<Monster> allMonsters;
  /**
   * An arraylist to hold all the treasure in the passage.
   */
  private ArrayList<Treasure> allTreasure;

/**
*This constructor is used to initialize the Passage class's instance variables.
*/
public Passage() {
  /*Initalize objects*/
  thePassage = new ArrayList<PassageSection>();
  doorList = new ArrayList<Door>();
  allMonsters = new ArrayList<Monster>();
  allTreasure = new ArrayList<Treasure>();
}

/**
*This method is used to get all the doors in the passage.
*@return An arrayList of all the doors in the passage.
*/
public ArrayList<Door> getDoors() {
//gets all of the doors in the entire passage
  return doorList;
}
/**
*This method gets a door from a specific passageSection.
*@param i is the passage section specified.
*@return A door in passage section i.
*/
public Door getDoor(int i) {
  //returns the door in section 'i'. If there is no door, returns null
  if (i >= thePassage.size()) {
    return null;
  } else {
    return thePassage.get(i).getDoor();
  }
}
/**
*This method allows a monster to be added to a certain section.
*@param theMonster this is an object of the Monster class.
*@param i is the passage section specified.
*/
public void addMonster(Monster theMonster, int i) {
  // adds a monster to section 'i' of the passage (index)
  thePassage.get(i).setMonster(theMonster);
}

/**
 * This monster adds all the monsters from the passage sections into one arraylist with all the monsters.
 */
public void addAllMonsters() {
  /*Run through all the passages adding all of the Monsters in the arraylist of monsters*/
  for (PassageSection temp: thePassage) {
    if (temp.isMonster()) {
      allMonsters.add(temp.getMonster());
    }
  }
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
  allMonsters.add(monster);
}

    /**
     * This method will generate a random Monster object.
     * @param monsterDescription this is the description of the monster to be generated.
     */
    public void generateSpecificMonster(String monsterDescription) {
        /*Create object*/
        Monster monster = new Monster();

        /*Set the type of monster*/
        monster.setType(this.setMonsterType(monsterDescription));

        /*Add monster to ArrayList*/
        allMonsters.add(monster);
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
 * @param monster this is the monster to be removed from the passage.
 */
public void removeMonster(String monster) {
  /*Remove the last Monster*/
  for (Monster aMonster: allMonsters) {
    if (aMonster.getDescription().contains(monster)) {
      allMonsters.remove(aMonster);
      break;
    }
  }
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
  allTreasure.add(ranTreasure);
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
    allTreasure.add(specificTreasure);
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
 *This method is used to generate a description for a Treasure object that is passed into the method.
 * @param aTreasure is a Treasure object that is passed to generate the description of the treasure.
 *@return A string containing the description of the Treasure in the passage.
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
 * This method will remove the last Treasure from the arraylist.
 * @param treasure this is the treasure to be removed from the passage.
 */
public void removeTreasure(String treasure) {
  /*Remove the last Monster*/
  for (Treasure aTreasureObject: allTreasure) {
    if (aTreasureObject.getDescription().contains(treasure)) {
      allTreasure.remove(aTreasureObject);
      break;
    }
  }
}

/**
 *This method is used to get a roll from the Percentile die.
 *@return A roll between 1 and 100 is returned.
 */
public int getRollPercentile() {
  /*Create a percentile object and return a roll*/
  Percentile die = new Percentile();
  int roll = die.roll();
  return roll;
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
 * This method is used to add a monster to the passage.
 * @param aMonster This is a monster to be added to arraylist of all the monsters in the passage.
 */
public void addMonsterAll(Monster aMonster) {
  /* Add a monster to all the monsters */
  allMonsters.add(aMonster);
}

/**
*This method will get a monster from a specific passageSection.
*@param i is the passage section specified.
*@return A monster in passage section i.
*/
public Monster getMonster(int i) {
  //returns Monster door in section 'i'. If there is no Monster, returns null
  if (i >= thePassage.size()) {
    return null;
  } else {
    return thePassage.get(i).getMonster();
  }
}

/**
 *This method is used to generate a description for a Monster object that is passed into the method.
 * @param aMonster is a monster object that is passed to generate a description from.
 *@return A string containing the description of the Monster in the passage.
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
*This method is used to add a passageSection to the passage.
*@param toAdd is the passage section to be added.
*/
public void addPassageSection(PassageSection toAdd) {
  //adds the passage section to the passageway
  this.thePassage.add(toAdd);
  this.addPassageSectionDoor(toAdd);
}

/**
*This method will check if a passageSection has doors, and if so it will add  its door to the arrayList of doors in the passage.
*@param toAdd is the passage section to be added.
*/
private void addPassageSectionDoor(PassageSection toAdd) {
  if (toAdd.isDoor()) {
    this.doorList.add(toAdd.getDoor());
  }
}

/**
*This method is used to add a door to the passage.
*@param newDoor is the door to be attached to the passage.
*/
@Override
public void setDoor(Door newDoor) {
  //should add a door connection to the current Passage Section this is where youre gonna have to use the hashmap
  doorList.add(newDoor);
}

/**
*This method is used to get the description of the entire passage.
*@return A string description of the passage.
*/
@Override
public String getDescription() {
  /*Concatenate passageSections together, and return the final string.*/
  passageDescription = "";

  if (allMonsters.size() == 0) {
    this.addAllMonsters();
  }

  /*Generate Passage Description*/
  for (PassageSection ex: thePassage) {
    passageDescription = passageDescription.concat(ex.getDescription() + "\n");
  }
  this.addMonsterDescription();
  this.addTreasureDescription();

  return passageDescription;
}

/**
 * This method is used to add the description of all the Monsters to the passage.
 */
private void addMonsterDescription() {
  /*Add the description of all the monsters*/
  if (allMonsters.size() > 0) {
    passageDescription = passageDescription.concat("******************MONSTERS******************\n");
  }
  for (Monster aMonster: allMonsters) {
    passageDescription = passageDescription.concat(this.generateMonsterWithParameter(aMonster));
  }
}

/**
 * This method is used to add the description of all the Monsters to the passage.
 */
private void addTreasureDescription() {
  /*Add the description of all the monsters*/
  if (allTreasure.size() > 0) {
    passageDescription = passageDescription.concat("******************TREASURE******************\n");
  }
  for (Treasure aTreasure: allTreasure) {
    passageDescription = passageDescription.concat(this.generateTreasureWithParameter(aTreasure));
  }
}

/**
*This method allows the user to set the Passage's ID.
*@param id is a char that is passed as the passage's id.
*/
public void setPassageId(char id) {
  /*Create a percetile object and return a roll*/
  this.passageId = id;
}

/**
*This method is used to get the passage's id.
*@return A char which represents the Passage's ID.
*/
public char getPassageId() {
  /*Create a percetile object and return a roll*/
  return passageId;
}

  /**
   * This method returns all the treasure in the passage.
   * @return all the treasure in the passage.
   */
  public ArrayList<Treasure> getTreasure() {
  return this.allTreasure;
}

  /**
   * This method returns all the monsters in the passage.
   * @return all the monster in the passage.
   */
  public ArrayList<Monster> getMonster() {
    return this.allMonsters;
  }

}
