package sbajwa;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class View {
/**
 * Create a gridPane that will be returned to the Main class.
 */
private GridPane selectedView;

/**
 * Create an Arraylist of Nodes to get all the chamber objects.
 */
private ArrayList<Label> tiles;

/**
  * Create an Arraylist with the locations of all the files.
  */
private ArrayList<String> files;

    /**
     * This constructor is used to initialize a View object.
     */
    public View() {
    /*Initialize instance variables*/
    tiles = initializeTiles();
    selectedView = this.createGridPanel();
}
    /**
     * This method is used to create the gridPane by adding all the tiles onto it.
     * @return a GridPane object with all the required tiles.
     */
    private GridPane createGridPanel() {
    /*Initialize display panel*/
    GridPane displayPanel = new GridPane();

    /*Add all the tiles to the gridpane*/
    for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
            displayPanel.add(tiles.get((i * 5) + j), i, j, 1, 1);
        }
    }

    /*Return the newly created gridpane*/
    return displayPanel;
}

    /**
     * This method is used to initialize all the tiles on the gridpane to the floor.
     * @return an arraylist of Node objects containing the tiles that will go on the gridpane.
     */
    private ArrayList<Label> initializeTiles() {
    /*Create an arraylist to be returned*/
    ArrayList<Label> toReturn = new ArrayList<Label>();

    /*Add Nodes to array*/
    for (int i = 0; i < 25; i++) {
        toReturn.add(this.createImage("res/floor.jpg"));
    }

    /*Return the initialized arrayList*/
    return toReturn;
}

    /**
     * This method is used to create a label with an image on it.
     * @param image this is a string containing the directory in which an image is located.
     * @return a Node object with the image attached to it.
     */
    public Label createImage(String image) {
    /*Get image and add to a label*/
    Label aLabel = new Label();

    /*Get the image*/
    try {
      Image floor = new Image(new FileInputStream(image));
      ImageView theImage = new ImageView(floor);

      /*Set the size of the image*/
      theImage.setFitWidth(100);
      theImage.setFitHeight(100);
      aLabel.setGraphic(theImage);
    } catch (FileNotFoundException e) {
      System.out.println("Image could not be loaded!");
    }

    /*Return the created label*/
    return aLabel;
}

    /**
     * This method adds things like treasure to the display if it is in the description of the chamber.
     * @param numOfMonsters is the number of monsters in the passage.
     * @param numOfTreasure is the number of treasures in the passage.
     * @param description this is a string containing the description of the chamber.
     * @param numOfDoors this is the number of doors in the chamber.
     */
    public void setChamber(String description, int numOfDoors, int numOfTreasure, int numOfMonsters) {

    /*Initialize monster counter*/
    int monsterCounter = 10;

    /*Reset Monsters*/
    for (int i = 0; i < 5; i++) {
        tiles.set(monsterCounter, this.createImage("res/floor.jpg"));
        monsterCounter++;
    }
    monsterCounter = 10;

    /*Get new monsters*/
    for (int i = 0; i < numOfMonsters && i < 5; i++) {
        tiles.set(monsterCounter, this.createImage("res/monster.jpg"));
        monsterCounter++;
    }

    /*Initialize treasure counter*/
    int treasureCounter = 5;

    /*Reset Treasure*/
    for (int i = 0; i < 5; i++) {
        tiles.set(treasureCounter, this.createImage("res/floor.jpg"));
        treasureCounter++;
    }
    treasureCounter = 5;

    /*Get new treasure*/
    for (int i = 0; i < numOfTreasure && i < 5; i++) {
         tiles.set(treasureCounter, this.createImage("res/treasure.jpg"));
        treasureCounter++;
    }

    if (description.contains("stairs")) {
        tiles.set(0, this.createImage("res/stairs.jpg"));
    } else {
        tiles.set(0, this.createImage("res/floor.jpg"));
    }

    if (description.contains("trap")) {
        tiles.set(3, this.createImage("res/trap.jpg"));
    } else {
        tiles.set(3, this.createImage("res/floor.jpg"));
    }

    /*Add doors to Chamber View*/
    int doorCounter = 20;

    /*Reset Doors*/
    for (int i = 0; i < 5; i++) {
        tiles.set(doorCounter, this.createImage("res/floor.jpg"));
        doorCounter++;
    }
    doorCounter = 20;

    /*Get new doors*/
    for (int i = 0; i < numOfDoors; i++) {
        tiles.set(doorCounter, this.createImage("res/door.jpg"));
        doorCounter++;
    }

    /*Update grid*/
    selectedView = this.createGridPanel();

}
    /**
     * This method adds things like treasure to the display if it is in the description of the passage.
     * @param numOfMonsters is the number of monsters in the passage.
     * @param numOfTreasure is the number of treasures in the passage.
     * @param description this is a string containing the description of the passage.
     * @param numOfDoors this is the number of doors in the passage.
     */
public void setPassage(String description, int numOfDoors, int numOfTreasure, int numOfMonsters) {
    /*Initialize monster counter*/
    int monsterCounter = 10;

    /*Reset Monsters*/
    for (int i = 0; i < 5; i++) {
        tiles.set(monsterCounter, this.createImage("res/floor.jpg"));
        monsterCounter++;
    }
    monsterCounter = 10;

    /*Get new monsters*/
    for (int i = 0; i < numOfMonsters && i < 5; i++) {
        tiles.set(monsterCounter, this.createImage("res/monster.jpg"));
        monsterCounter++;
    }

    /*Initialize treasure counter*/
    int treasureCounter = 5;

    /*Reset Treasure*/
    for (int i = 0; i < 5; i++) {
        tiles.set(treasureCounter, this.createImage("res/floor.jpg"));
        treasureCounter++;
    }
    treasureCounter = 5;

    /*Get new treasure*/
    for (int i = 0; i < numOfTreasure && i < 5; i++) {
        tiles.set(treasureCounter, this.createImage("res/treasure.jpg"));
        treasureCounter++;
    }

    if (description.contains("stairs")) {
        tiles.set(0, this.createImage("res/stairs.jpg"));
    } else {
        tiles.set(0, this.createImage("res/floor.jpg"));
    }

    if (description.contains("trap")) {
        tiles.set(3, this.createImage("res/trap.jpg"));
    } else {
        tiles.set(3, this.createImage("res/floor.jpg"));
    }

    /*Add doors to Chamber View*/
    int doorCounter = 20;

    /*Reset Doors*/
    for (int i = 0; i < 5; i++) {
        tiles.set(doorCounter, this.createImage("res/floor.jpg"));
        doorCounter++;
    }
    doorCounter = 20;

    /*Get new doors*/
    for (int i = 0; i < numOfDoors; i++) {
        tiles.set(doorCounter, this.createImage("res/door.jpg"));
        doorCounter++;
    }

    /*Update grid*/
    selectedView = this.createGridPanel();

}

    /**
     * This method return the gridpane that has been created in order for the gui to use it.
     * @return a GridPane object with all the tiles on it.
     */
    public GridPane getSelectedView() {
    return this.selectedView;
}

}
