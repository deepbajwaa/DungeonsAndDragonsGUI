package sbajwa;

import javafx.scene.control.ChoiceBox;
import java.util.ArrayList;
import java.util.Collections;
import dnd.models.Treasure;
import dnd.models.Monster;

/*
 * Name:Simrandeep Bajwa
 * Date: November 17, 2019
 */

public class Controller implements java.io.Serializable {

    /**
     * This is an object of the Level class to generate the level to be displayed.
     */
    private Level aLevel;

    /**
     * This arraylist keeps track of the current doors being used by the gui.
     */
    private ArrayList<Door> currentDoors;
    /**
     * This is an arraylist of all the chambers in the level.
     */
    private ArrayList<Chamber> allChambers;
    /**
     * This is an arraylist of all the passages in the level.
     */
    private ArrayList<Passage> allPassages;

    /**
     * This constructor is used to initalize the controller by generating the level.
     */
    public Controller() {
        aLevel = new Level();
        aLevel.createLevel();
        aLevel.reattachDoors();
        allChambers = aLevel.getAllChambers();
        allPassages = aLevel.getAllPassages();
    }

    /**
     * This method is used to get an arraylist of string with all the chambers in the level.
     * @return an arraylist of strings with the names of all the chambers in the level.
     */
    public ArrayList<String> getAllChambers() {
        /*Declare and initiallize arrayLists*/
        ArrayList<String> allChamberStrings = new ArrayList<String>();

        /*Run through the chambers to get a final arraylist of chamber strings*/
        for (Chamber aChamber: allChambers) {
            allChamberStrings.add("Chamber " + aChamber.getChamberId());
        }

        /*Return the list of chambers*/
        return allChamberStrings;
    }

    /**
     * This method is used to get an arraylist of string with all the passages in the level.
     * @return an arraylist of strings with the names of all the passages in the level.
     */
    public ArrayList<String> getAllPassages() {
        ArrayList<String> allPassageStrings = new ArrayList<String>();

        /*Run through the passages to get a final arraylist of passage strings*/
        for (Passage aPassage: allPassages) {
            allPassageStrings.add("Passage " + aPassage.getPassageId());
        }
        /*Sort the Passages*/
        Collections.sort(allPassageStrings);

        /*Return the list of passages*/
        return allPassageStrings;
    }

    /**
     * This method is used to get an arraylist of string with all the treasure in a passage or chamber.
     * @param option this is the chamber/passage from whcih treasure needs to be retrieved from.
     * @return an arraylist of strings with the names of all the treasure in the passage or chamber.
     */
    public ArrayList<String> getAllTreasure(String option) {
        ArrayList<String> allTreasure = new ArrayList<String>();
        ArrayList<Treasure> treasureList = new ArrayList<Treasure>();
        Chamber aChamber = null;
        Passage aPassage = null;
        String check;

        if (option.contains("Chamber")) {
            aChamber = this.findChamber(option);
            if (aChamber != null) {
                treasureList = aChamber.getTreasureList();
            }
        } else if (option.contains("Passage")) {
            aPassage = this.findPassage(option);
            if (aPassage != null) {
                treasureList = aPassage.getTreasure();
            }
        }

        /*Get an arraylist of all treasure in the passage/chamber*/
        allTreasure = this.getAllTreasureStrings(treasureList);

        /*Return the list of passages*/
        return allTreasure;
    }


    /**
     * This method is used to get an arraylist of string with all the treasure in a passage or chamber.
     * @param option this is the chamber/passage from which the monster list needs to be retreived from.
     * @return an arraylist of strings with the names of all the treasure in the passage or chamber.
     */
    public ArrayList<String> getAllMonsters(String option) {
        ArrayList<String> allMonster = new ArrayList<String>();
        ArrayList<Monster> monsterList = new ArrayList<Monster>();
        Chamber aChamber = null;
        Passage aPassage = null;
        String check;

        if (option.contains("Chamber")) {
            aChamber = this.findChamber(option);
            if (aChamber != null) {
                monsterList = aChamber.getMonsterList();
            }
        } else if (option.contains("Passage")) {
            aPassage = this.findPassage(option);
            if (aPassage != null) {
                monsterList = aPassage.getMonster();
            }
        }

        /*Get an arraylist of all treasure in the passage/chamber*/
        allMonster = this.getAllMonsterStrings(monsterList);

        /*Return the list of passages*/
        return allMonster;
    }

    /**
     * This method is used to get a specific chamber from all the chambers based on the string passed to the method.
     * @param option This is the chamber that needs to be retreived.
     * @return the chamber which was indicated.
     */
    private Chamber findChamber(String option) {
        /*Declare variables*/
        String match;

        /*Run through the chambers to find the correct chamber*/
        for (Chamber temp: allChambers) {
            match = " " + temp.getChamberId();
            if (option.contains(match)) {
                return temp;
            }
        }

        /*Return null if not found*/
        return null;
    }

    /**
     * This method is used to get a specific passage from all the passages based on the string passed to the method.
     * @param option This is the passage that needs to be retreived.
     * @return the passage which was indicated.
     */
    private Passage findPassage(String option) {
        /*Declare variables*/
        String match;

        /*Run through the chambers to find the correct chamber*/
        for (Passage temp: allPassages) {
            match = " " + temp.getPassageId();
            if (option.contains(match)) {
                return temp;
            }
        }

        /*Return null if not found*/
        return null;
    }

    /**
     * This method is used to get a string list of all the treasure in a chamber/passage.
     * @param treasure this is the arraylist of treasure objects.
     * @return a string arraylist containing all the treasure.
     */
    private ArrayList<String> getAllTreasureStrings(ArrayList<Treasure> treasure) {
        /*Declare variables*/
        ArrayList<String> allStringTreasure = new ArrayList<String>();

        /*Run through all the treasure and get their description*/
        for (Treasure aTreasure: treasure) {
            allStringTreasure.add(aTreasure.getDescription());
        }

        /*Return all treasure strings*/
        return allStringTreasure;
    }

    /**
     * This method is used to get a string list of all the monsters in a chamber/passage.
     * @param monsters this is the arraylist of Monster objects.
     * @return a string arraylist containing all the monsters.
     */
    private ArrayList<String> getAllMonsterStrings(ArrayList<Monster> monsters) {
        /*Declare variables*/
        ArrayList<String> allStringMonster = new ArrayList<String>();

        /*Run through all the treasure and get their description*/
        for (Monster aMonster: monsters) {
            allStringMonster.add(aMonster.getDescription());
        }

        /*Return all treasure strings*/
        return allStringMonster;
    }

    /**
     * This method is used to get the description of a specified chamber.
     * @param chamber is a string passed containing the chamber that gui wants the description of.
     * @return a string containing the description of the chamber.
     */
    public String getChamberDescription(String chamber) {
        /*Get all the chambers from the */
        String check;

        /*Run through all chambers to get Description*/
        for (Chamber temp: allChambers) {
            check = " " + temp.getChamberId();
            if (chamber.contains(check)) {
                return "CHAMBER " + temp.getChamberId() + "\n" + temp.getDescription();
            }
        }

        /*If nothing is found return null*/
        return null;
    }

    /**
     * This method is used to get the description of a specified passage.
     * @param passage is a string passed containing the chamber that gui wants the description of.
     * @return a string containing the description of the passage.
     */
    public String getPassageDescription(String passage) {
        /*Get all the chambers from the level*/
        String check;

        /*Run through all chambers to get Description*/
        for (Passage temp: allPassages) {
            check = " " + temp.getPassageId();
            if (passage.contains(check)) {
                return "PASSAGE " + temp.getPassageId() + "\n" + temp.getDescription();
            }
        }

        /*If nothing is found return null*/
        return null;
    }

    /**
     * This method gets an arraylist of strings with all the doors in the passage or chamber.
     * @param option is the chamber or passage to get the doors from.
     * @return an arraylist of strings containing all the doors in a passage or chamber.
     */
    public ArrayList<String> getAllDoors(String option) {
        /*Get all the chambers from the level*/

        /*Create any additional variables needed*/
        ArrayList<String> allDoors = new ArrayList<String>();

        if (option.contains("Chamber")) {
            /*Get an arraylist of string with all the chamber doors*/
            allDoors = this.getListOfChambersDoors(option);
        } else if (option.contains("Passage")) {
            /*Get an arraylist of string with */
            allDoors = this.getListOfPassageDoors(option);
        }

        /*Return the arrayList of Doors*/
        return allDoors;
    }

    /**
     * This method gets all the doors in a given chamber.
     * @param option this is the specified chamber.
     * @return an arraylist of strings with all the doors in the chamber.
     */
    public ArrayList<String> getListOfChambersDoors(String option) {
        /*Declare variables*/
        ArrayList<String> listOfChamberDoors = new ArrayList<String>();
        String check;

        /*Get list of Chambers*/
        for (Chamber temp: allChambers) {
            check = " " + temp.getChamberId();
            if (option.contains(check)) {
                currentDoors = aLevel.allChamberDoors(temp);
                for (Door aDoor: currentDoors) {
                    listOfChamberDoors.add("Door " + aDoor.getDoorId() + "\n");
                }
                break;
            }
        }

        /*Sort the doors*/
        Collections.sort(listOfChamberDoors);

        /*Return list of doors*/
        return listOfChamberDoors;
    }

    /**
     * This method gets all the doors in a given passage.
     * @param option this is the specified passage.
     * @return an arraylist of strings with all the doors in the passage.
     */
    public ArrayList<String> getListOfPassageDoors(String option) {
        /*Declare variables*/
        ArrayList<String> listOfPassageDoors = new ArrayList<String>();
        String check;
        char passageDoor = 'A';

        /*Get list of Chambers*/
        for (Passage temp: allPassages) {
            check = " " + temp.getPassageId();
            if (option.contains(check)) {
                currentDoors = aLevel.allPassageDoors(temp);
                for (Door aDoor: currentDoors) {
                    aDoor.setDoorId(passageDoor);
                    listOfPassageDoors.add("Door " + aDoor.getDoorId() + "\n");
                    passageDoor++;
                }
                break;
            }
        }

        /*Sort the doors*/
        Collections.sort(listOfPassageDoors);

        /*Return list of doors*/
        return listOfPassageDoors;
    }

    /**
     *This method gets the description of a given door from a chamber or passage.
     * @param aChoiceBox this is a choicebox object containing all the doors in the selected chamber or passage.
     * @param option this is a string that specifies if the door is from a chamber or passage.
     * @return a string containing the description of the door.
     */
    public String doorDescription(ChoiceBox<String> aChoiceBox, String option) {
        /*Declare variables*/
        String doorSelection;
        String doorDescription = "************DOOR DESCRIPTION***********\n";
        String match;
        Chamber aChamber;

        /*Get the door the user selected*/
        doorSelection = aChoiceBox.getValue();

        /*Find the door from the current selection of doors and return its description*/
        if (option.contains("Chamber")) {
            doorDescription = doorSelection.concat(this.chamberDoorDescription(doorSelection));
        } else {
            doorDescription = doorDescription.concat(this.passageDoorDescription(doorSelection));
        }
        return doorDescription;
    }

    /**
     * This method gets the description of a given chamber's door.
     * @param doorSelection is the door for which the description is needed.
     * @return a string containing the description of a given door.
     */
    public String chamberDoorDescription(String doorSelection) {
        /*Declare variables*/
        String chamberDoorDescription = "";
        String match;
        Passage aPassage;

        /*Get chamber door description*/
        for (Door aDoor: currentDoors) {
            match = " " + aDoor.getDoorId();
            if (doorSelection.contains(match)) {
                chamberDoorDescription = chamberDoorDescription.concat(aDoor.getDescription());
                if (aDoor.getSpaces().size() > 2) {
                    aPassage = (Passage) aDoor.getSpaces().get(2);
                    chamberDoorDescription = chamberDoorDescription.concat("This door leads to Passage " + aPassage.getPassageId() + ".");
                }
                break;
            }
        }

        /*Return the string*/
        return chamberDoorDescription;
    }

    /**
     * This method gets the description of a given passage's door.
     * @param doorSelection is the door for which the description is needed.
     * @return a string containing the description of a given door.
     */
    public String passageDoorDescription(String doorSelection) {
        /*Declare variables*/
        String passageDoorDescription = "";
        String match;
        Chamber aChamber;

        /*Get chamber door description*/
        for (Door aDoor: currentDoors) {
            match = " " + aDoor.getDoorId();
            if (doorSelection.contains(match)) {
                passageDoorDescription = passageDoorDescription.concat(aDoor.getDescription());
                if (aDoor.getSpaces().size() > 2) {
                    aChamber = (Chamber) aDoor.getSpaces().get(1);
                    passageDoorDescription = passageDoorDescription.concat("This door leads to Passage " + aChamber.getChamberId() + ".");
                }
                break;
            }
        }

        /*Return the string*/
        return passageDoorDescription;
    }

    /**
     * This method adds a monster to a chamber or passage.
     * @param monster this the monster to be added to chamber or passage.
     * @param object is a string containing what chamber or passage that monster needs to be added too.
     */
    public void addMonsterToObject(String object, String monster) {
        /*Call the appropriate method*/
        if (object.contains("Chamber")) {
            aLevel.addMonsterToChamber(object, monster);
        } else {
            aLevel.addMonsterToPassage(object, monster);
        }
    }

    /**
     * This method removes a monster from a chamber or passage.
     * @param monster this is the monster to be removed from the chamber or passage.
     * @param object is a string containing what chamber or passage that monster needs to be removed from.
     */
    public void removeMonsterFromObject(String object, String monster) {
        /*Call the appropriate method*/
        if (object.contains("Chamber")) {
            aLevel.removeMonsterFromChamber(object, monster);
        } else {
            aLevel.removeMonsterFromPassage(object, monster);
        }
    }

    /**
     * This method adds treasure to a chamber or passage.
     * @param treasure this is the treasure to be added to the chamber/passage.
     * @param object is a string containing what chamber or passage that treasure needs to be added too.
     */
    public void addTreasureToObject(String object, String treasure) {
        /*Call the appropriate method*/
        if (object.contains("Chamber")) {
            aLevel.addTreasureToChamber(object, treasure);
        } else {
            aLevel.addTreasureToPassage(object, treasure);
        }
    }

    /**
     * This method removes treasure from a chamber or passage.
     * @param treasure this is the treasure to be removed from the chamber/passage.
     * @param object is a string containing what chamber or passage that treasure needs to be added too.
     */
    public void removeTreasureFromObject(String object, String treasure) {
        /*Call the appropriate method*/
        if (object.contains("Chamber")) {
            aLevel.removeTreasureFromChamber(object, treasure);
        } else {
            aLevel.removeTreasureFromPassage(object, treasure);
        }
    }

    /**
     * This method is used to retrieved the level object.
     * @return the level object generated by the controller class.
     */
    public Level getLevel() {
        return this.aLevel;
    }

    /**
     * This method is used to set the level object to a new level that might be loaded from a saved file.
     * @param newLevel is a level object to be initialized as the new level.
     */
    public void setLevel(Level newLevel) {
        this.aLevel = newLevel;
    }

}
