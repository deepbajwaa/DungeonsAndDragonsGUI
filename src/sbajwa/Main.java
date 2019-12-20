package sbajwa;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/*
 * Name:Simrandeep Bajwa
 * Date: November 17, 2019
 */

public class Main extends Application implements java.io.Serializable {

    /**
     * This is the layout used to display the main gui.
     */
    private BorderPane root;
    /**
     * This is an object of the controller used to call the controller class methods.
     */
    private Controller aController;
    /**
     * This is the menubar to add a file save and load option.
     */
    private MenuBar aMenuBar;
    /**
     * This is a object of Text to display the chamber or passage description.
     */
    private Text descriptionOfObject;
    /**
     * This is used to allow a user to select a door.
     */
    private ChoiceBox<String> aChoiceBox;
    /**
     * This is used to display all the passages and chambers.
     */
    private ListView<String> aListView;
    /**
     * This is used to display all the treasure in the passage/chamber.
     */
    private ListView<String> allTreasure;
    /**
     * This is used to allow a user to add a certain monster.
     */
    private ChoiceBox<String> allAddMonster;
    /**
     * This is used to allow a user to add a certain treasure.
     */
    private ChoiceBox<String> allAddTreasure;

    /**
     * This is used to display all the treasure in the passage/chamber.
     */
    private ListView<String> allMonster;
    /**
     * This is used to add a scene.
     */
    private Stage mainStage;
    /**
     * This is used to add all the gui components to the display.
     */
    private Scene aScene;

    /**
     * This is used to keep track of the chamber or passage that is currently selected.
     */
    private ObservableList<String> selectedOptions;
    /**
     * This is used to get a view of the chamber or passage.
     */
    private View aView;
    /**
     * This is used to keep track of the current view of a passage or door.
     */
    private GridPane displayView;
    /**
     * This is the HBox containing all the listviews to edit the chamber/passage.
     */
    private HBox listViewHBox;
    /**
     * This is a stage to display the graphic for a selected passage/chamber.
     */
    private Stage displayViewStage;
    /**
     * This is a stage to display the edit menu for a selected passage/chamber.
     */
    private Stage editStage;
    /**
     * This is a stage to display the door description.
     */
    private Stage doorStage;

    /**
     *This method is used to start up the gui.
     * @param primaryStage used to initialize the gui.
     * @throws Exception if something goes wrong.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Initialize instance variables*/
        mainStage = primaryStage;
        aView = new View();
        this.initializeGuiComponents();

        /*Initialize Main Gui*/
        root = createBorderPane();

        /*Add elements to borderPane*/
        this.setGuiElements();

        /*Create the Scene*/
        aScene = new Scene(root, 800, 600);
        aScene.getStylesheets().add("/res/stylesheet.css");

        /*Create the Stage and Show it*/
        this.showGui();
    }

    /**
     * This method is used to initialize the gui's main components.
     */
    private void initializeGuiComponents() {
        /*Initialize objects*/
        aController = new Controller();
        aMenuBar = createMenuBar();
        descriptionOfObject = createTextDescription("Welcome to Dungeons and Dragons!\nSelect a Passage or Chamber, and its description " + "will appear here!");
        aChoiceBox = createChoiceBox();
        aListView = createListView();
        displayView = new GridPane();
        displayViewStage = new Stage();
        editStage = new Stage();
        doorStage = new Stage();
    }

    /**
     * This method is used to add all the gui components to the gui.
     */
    private void setGuiElements() {
        /*Add components to borderpane*/
        root.setTop(aMenuBar);
        root.setCenter(descriptionOfObject);
        root.setLeft(aListView);
        root.setRight(aChoiceBox);
        root.setBottom(createBottom());
    }

    /**
     * This method is used to display the gui to the user.
     */
    private void showGui() {
        /*Add the scene and show the gui*/
        mainStage.setTitle("Dungeons & Dragons");
        mainStage.setScene(aScene);
        mainStage.show();
    }
    /**
     *
     * @return a BorderPane object that has been initialized correctly.
     */
    private BorderPane createBorderPane() {
        /*Create a border pane*/
        BorderPane aBorderPane = new BorderPane();
        aBorderPane.setPrefSize(700, 600);

        /*Return created borderPane*/
        return aBorderPane;
    }

    /**
     * This method is used to create a button.
     * @param buttonLabel is a string that is entered which will be the name of the button.
     * @return the created Button object.
     */
    private Button createAButton(String buttonLabel) {
        /*Create and initialize the button to be returned*/
        Button aButton = new Button();
        aButton.setText(buttonLabel);

        return aButton;
    }

    /**
     * This method is used to create the bottom portion of the gui, with all the needed button and functionality.
     * @return an Hbox object with all the buttons on the gui.
     */
    private HBox createBottom() {
        /*Create a Hbox*/
        HBox aHbox = new HBox();

        /*Create the edit button with an action listener*/
        Button edit = createAButton("Edit");
        Button selectDoor = createAButton("Select Door");

        /*Create action listener*/
        this.addButtonFunctionality(edit, selectDoor);

        /*Add button to hBox*/
        aHbox.getChildren().addAll(edit, selectDoor);

        /*Use Css sheet to update hbox*/
        aHbox.getStylesheets().add("/res/stylesheet.css");
        aHbox.setId("selectionHBox");

        /*Return the hBox*/
        return aHbox;
    }

    /**
     * This method is used to add action listeners to the buttons on the gui.
     * @param edit this is the edit button on the gui.
     * @param selectDoor this is the button to select a door on the gui.
     */
    private void addButtonFunctionality(Button edit, Button selectDoor) {
        /*Create action listener*/
        edit.setOnAction(e -> {
            if (aListView.getSelectionModel().getSelectedItems().size() > 0 && aChoiceBox.getItems().size() > 0) {
                this.editMenu();
            }
        });

        aListView.getSelectionModel().selectedItemProperty().addListener(e -> {
            if (aListView.getSelectionModel().getSelectedItems().size() > 0) {
                displayViewStage.hide();
                doorStage.hide();
                editStage.hide();
                this.showSelectedDescription();
                this.displayView();
            }
        });

        selectDoor.setOnAction(e -> {
            if ((aListView.getSelectionModel().getSelectedItems().size() > 0) && (aChoiceBox.getValue() != null)) {
                this.doorPopup();
            }
        });

    }

    /**
     * This method is used to create a popup with the description of the selected description.
     */
    private void doorPopup() {
        /*Create a Window for the edit Menu*/
        BorderPane doorPopup = new BorderPane();
        doorStage = new Stage();
        Scene doorScene;

        /*Create Text for Door Description*/
        Text doorDescription = new Text(aController.doorDescription(aChoiceBox, selectedOptions.get(0)));

        /*Add Text to doorPane*/
        doorPopup.setCenter(doorDescription);

        /*Create size for editMenu*/
        doorPopup.setPrefSize(300, 300);

        /*Style door pop up*/
        doorPopup.getStylesheets().add("/res/stylesheet.css");
        doorPopup.setId("doorDescription");

        /*Create the scene and add it to the stage*/
        doorScene = new Scene(doorPopup);
        this.showStage(doorStage, doorScene, "Door Description");
    }

    /**
     * This method will show the gui.
     * @param insertStage This is the stage that a scene is to be added too.
     * @param insertScene This is a scene object to be added to the stage.
     * @param insertNameOfStage This is the name of the stage.
     */
    private void showStage(Stage insertStage, Scene insertScene, String insertNameOfStage) {
        /*Set and show Stage*/
        insertStage.setScene(insertScene);
        insertStage.setTitle(insertNameOfStage);
        insertStage.show();
    }

    /**
     * This method is used to create an editMenu for adding/removing monsters and treasure to and from a passage or chamber.
     */
    private void editMenu() {
        /*Create a Window for the edit Menu*/
        VBox allEditComponents = new VBox();
        HBox editMenu = this.allEditButtons();
        HBox allViews = this.allViews();
        editStage = new Stage();

        /*Add components to VBox*/
        allEditComponents.getChildren().addAll(allViews, editMenu);

        /*Create size for editMenu*/
        editMenu.setPrefSize(500, 50);

        /*Create the scene and add it to the stage*/
        Scene editScene = new Scene(allEditComponents);
        this.showStage(editStage, editScene, "Edit Menu");
    }

    /**
     * This method is used to create the bottom of the edit menu.
     * @return an HBox with all the buttons needed to edit monsters and treasure.
     */
    private HBox allEditButtons() {
        /*Declare variables*/
        HBox editBottom = new HBox();
        editBottom.getStylesheets().add("/res/stylesheet.css");
        editBottom.setId("editMenu");

        /*Create Buttons*/
        Button monsterAdd = new Button("Add Monster");
        Button monsterRemove = new Button("Remove Monster");
        Button treasureAdd = new Button("Add Treasure");
        Button treasureRemove = new Button("Remove Treasure");

        /*Create action listeners for all buttons*/
        this.addEditActionListeners(monsterAdd, monsterRemove, treasureAdd, treasureRemove);

        /*Add Buttons to HBox*/
        editBottom.getChildren().addAll(treasureAdd, treasureRemove, monsterAdd, monsterRemove);

        /*Create size for editMenu*/
        editBottom.setPrefSize(500, 50);

        /*Return HBox*/
        return editBottom;
    }

    /*This method is used to get all the needed list views for the edit menu*/
    private HBox allViews() {
        /*Create HBox with listViews*/
        listViewHBox = new HBox();
        allTreasure = createListViewTreasure();
        allMonster = createMonsterListView();
        allAddTreasure = createChoiceBox();
        allAddMonster = createChoiceBox();
        allAddMonster.getItems().addAll("Ant, giant", "Badger", "Beetle, fire", "Demon, manes", "Dwarf", "Ear Seeker", "Elf", "Gnome", "Goblin", "Hafling", "Hobgoblin", "Human Bandit", "Kobold", "Orc", "Piercer", "Rat, gaint", "Rot grub", "Shreiker", "Skeleton", "Zombie");
        allAddTreasure.getItems().addAll("1000 copper pieces/level", "1000 silver pieces", "750 electrum pieces/level", "250 gold pieces/level", "100 platinum pieces/level", "1-4 gems/level", "1 piece jewellery/level", "1 magic item (roll on Magic table))");

        /*Add List view to HBox*/
        listViewHBox.getChildren().addAll(allAddTreasure, allTreasure, allAddMonster, allMonster);

        /*Return HBox*/
        return listViewHBox;
    }

    /**
     *This method is used to create a listview with all the treasure, in the chamber/passage.
     * @return a ListView of all treasure in the chamber/passage.
     */
    private ListView<String> createListViewTreasure() {
        /*Create a list view*/
        ListView<String> someList = new ListView<String>();

        /*Get an ArrayList of all the Chambers*/
        ArrayList<String> allTreasureInObject = aController.getAllTreasure(selectedOptions.get(0));

        /*Add all the chambers to the listview*/
        for (String treasure: allTreasureInObject) {
            someList.getItems().add(treasure);
        }

        /*Return the final list*/
        return someList;
    }

    private ListView<String> createMonsterListView() {
        /*Create a list view*/
        ListView<String> someList = new ListView<String>();

        /*Get an ArrayList of all the Chambers*/
        ArrayList<String> allMonstersInObject = aController.getAllMonsters(selectedOptions.get(0));

        /*Add all the chambers to the listview*/
        for (String aMonster: allMonstersInObject) {
            someList.getItems().add(aMonster);
        }

        /*Return the final list*/
        return someList;
    }

    /**
     * This method is used to add functionality to all the buttons in the edit Menu.
     * @param monsterAdd this is the button used to add monsters.
     * @param monsterRemove this is the button used to remove monsters.
     * @param treasureAdd this is the button used to add treasure.
     * @param treasureRemove this is the button used to remove treasure.
     */
    private void addEditActionListeners(Button monsterAdd, Button monsterRemove, Button treasureAdd, Button treasureRemove) {
        /*Add action listeners to the buttons passed in*/
        monsterAdd.setOnAction(e -> {
            this.addMonsterToObject();
            this.updateMonsterTreasureList();
            this.updateView();
        });
        monsterRemove.setOnAction(e -> {
            this.removeMonsterFromObject();
            this.updateMonsterTreasureList();
            this.updateView();
        });
        treasureAdd.setOnAction(e -> {
            this.addTreasureToObject();
            this.updateMonsterTreasureList();
            this.updateView();
        });
        treasureRemove.setOnAction(e -> {
            this.removeTreasureFromObject();
            this.updateMonsterTreasureList();
            this.updateView();
        });
    }

    private void updateMonsterTreasureList() {
        /*Update Edit Hbox*/
        listViewHBox.getChildren().remove(allMonster);
        listViewHBox.getChildren().remove(allAddMonster);
        listViewHBox.getChildren().remove(allAddTreasure);
        listViewHBox.getChildren().remove(allTreasure);
        allTreasure = this.createListViewTreasure();
        allMonster = this.createMonsterListView();
        listViewHBox.getChildren().addAll(allAddTreasure, allTreasure, allAddMonster, allMonster);
    }

    /**
     * This method is used to add a monster to a chamber or passage depending on what the user chooses.
     */
    private void addMonsterToObject() {
        /*Declare variables*/
        String option;

        /*Get the first selected option*/
        if ((selectedOptions.size() > 0) && (allAddMonster.getValue() != null)) {
            option = selectedOptions.get(0);
            aController.addMonsterToObject(option, allAddMonster.getValue());
            if (option.contains("Chamber")) {
                updateTextDescription(aController.getChamberDescription(option));
            } else {
                updateTextDescription(aController.getPassageDescription(option));
            }
        }
    }

    /**
     * This method is used to remove a monster from a chamber or passage depending on what the user selects.
     */
    private void removeMonsterFromObject() {
        /*Declare variables*/
        String option;

        /*Get the first selected option*/
        if (selectedOptions.size() > 0 && allMonster.getSelectionModel().getSelectedItem() != null) {
            option = selectedOptions.get(0);
            aController.removeMonsterFromObject(option, allMonster.getSelectionModel().getSelectedItem());
            if (option.contains("Chamber")) {
                updateTextDescription(aController.getChamberDescription(option));
            } else {
                updateTextDescription(aController.getPassageDescription(option));
            }
        }
    }

    /**
     * This method is used to add treasure to a chamber or passage.
     */
    private void addTreasureToObject() {
        /*Declare variables*/
        String option;

        /*Get the first selected option*/
        if (selectedOptions.size() > 0 && allAddTreasure.getValue() != null) {
            option = selectedOptions.get(0);
            aController.addTreasureToObject(option, allAddTreasure.getValue());
            if (option.contains("Chamber")) {
                updateTextDescription(aController.getChamberDescription(option));
            } else {
                updateTextDescription(aController.getPassageDescription(option));
            }
        }
    }

    /**
     * This method is used to remove treasure from a chamber or passage.
     */
    private void removeTreasureFromObject() {
        /*Declare variables*/
        String option;
        String tresure;

        /*Get the first selected option*/
        if (selectedOptions.size() > 0 && allTreasure.getSelectionModel().getSelectedItem() != null) {
            option = selectedOptions.get(0);
            aController.removeTreasureFromObject(option, allTreasure.getSelectionModel().getSelectedItem());
            if (option.contains("Chamber")) {
                updateTextDescription(aController.getChamberDescription(option));
            } else {
                updateTextDescription(aController.getPassageDescription(option));
            }
        }
    }

    /**
     * This method is used update the selected option's description, and list of doors.
     */
    private void showSelectedDescription() {
        /*Declare variables*/
        String option = null;

        /*Get the selected options*/
        selectedOptions = aListView.getSelectionModel().getSelectedItems();

        /*Get the first selected option*/
        if (selectedOptions.size() > 0) {
            option = selectedOptions.get(0);

            /*Call the appropriate method*/
            if (option.contains("Chamber")) {
                updateTextDescription(aController.getChamberDescription(option));
                //add the doors to the choicebox
                updateChoiceBox(aController.getAllDoors(option));
            } else {
                updateTextDescription(aController.getPassageDescription(option));
                //add the doors to the choicebox
                updateChoiceBox(aController.getAllDoors(option));
            }
        } else {
            System.out.println("You have either selected nothing, or have selected more than one item!");
        }

    }

    /**
     * This method is used to create a menubar to save and load files.
     * @return a menubar that is to be added to the gui.
     */
    private MenuBar createMenuBar() {
        /*Create a Menu bar*/
        MenuBar newMenuBar = new MenuBar();

        /*Create a Menu*/
        Menu file = new Menu("File:");

        /*Create menu items and add them to the menu*/
        MenuItem save = new MenuItem("Save File");
        MenuItem load = new MenuItem("Load File");
        file.getItems().addAll(save, load);
        newMenuBar.getMenus().add(file);

        /*Add actionlistners*/
        save.setOnAction(e -> this.savefile());
        load.setOnAction(e -> this.loadFile());

        /*Return Menu bar*/
        return newMenuBar;
    }

    /**
     * This method is used to save the current program.
     */
    private void savefile() {
        /*Let the user choose where they want to save the file*/
        File aFile = this.fileMenu(1);

        /*Save the current state*/
        if (aFile != null) {
            try {
                FileOutputStream fileSave = new FileOutputStream(aFile + ".ser");
                ObjectOutputStream out = new ObjectOutputStream(fileSave);
                out.writeObject(aController);
                out.writeObject(aController.getLevel());
                out.close();
                fileSave.close();
            } catch (IOException e) {
                System.out.println("Could not save!");
            }
        }

    }

    /**
     * This method is used to load a selected file from the user.
     */
    private void loadFile() {
        /*Call the file Menu*/
        File aFile = this.fileMenu(0);

        if (aFile != null) {
            try {
                FileInputStream fileIn = new FileInputStream(aFile);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                aController = (Controller) in.readObject();
                aController.setLevel((Level) in.readObject());
                in.close();
                fileIn.close();
                this.updateGUI();
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("Could not load file.");
            }
        }
    }

    /**
     * This method is used to create a popup to allow the user to save and load a file.
     * @param option this is an integer passed to the method, a 1 is passed to allow the user to save a file, otherwise a menu will appear to load a previously saved file.
     * @return a file of where the user wants to load or save a file to or from.
     */
    private File fileMenu(int option) {
        /*Declare variables*/
        JFileChooser fileChooser = new JFileChooser();
        File chosenFile = null;
        int aValue;

        /*Limit it so that only serializable files are saved*/
        FileNameExtensionFilter filter = new FileNameExtensionFilter("SER file only", "ser");
        fileChooser.addChoosableFileFilter(filter);

        /*Limit to current directory*/
        File workingDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setCurrentDirectory(workingDirectory);

        /*Open file chooser*/
        if (option == 1) {
            aValue = fileChooser.showSaveDialog(null);

            /*If the user saves a file get the file name*/
            if (aValue == JFileChooser.APPROVE_OPTION) {
                chosenFile = fileChooser.getSelectedFile();
            }
        } else {
            aValue = fileChooser.showOpenDialog(null);

            /*If the user has choosen a file, get the file*/
            if (aValue == JFileChooser.APPROVE_OPTION) {
                chosenFile = fileChooser.getSelectedFile();
            }
        }

        /*Return the file chosen*/
        return chosenFile;
    }


    /**
     * This method is used to update the gui after the user has loaded a file.
     */
    private void updateGUI() {
        /*Update Gui*/
        aListView = this.createListView();
        root.setLeft(aListView);
    }

    /**
     * This method is used to return a Text object with the string passed in the parameter.
     * @param description a string that is used to initialize the Text object.
     * @return a Text object initialized with the string that was passed to the method.
     */
    private Text createTextDescription(String description) {
        /*Create and return text*/
        Text someText = new Text(description);
        return someText;
    }

    /**
     * This method is used to update the description that is currently on the gui.
     * @param description is a string that the gui will be updated with.
     */
    private void updateTextDescription(String description) {
        /*Create a new updated Textbox and add it to the Main Gui*/
        Text update = createTextDescription(description);
        HBox aHboxDescription = new HBox();
        aHboxDescription.getChildren().add(update);
        aHboxDescription.getStylesheets().add("/res/stylesheet.css");
        aHboxDescription.setId("textUpdate");
        root.setCenter(aHboxDescription);
    }

    /**
     * This method is used to create a new choicebox.
     * @return an initialized choicebox object.
     */
    private ChoiceBox<String> createChoiceBox() {
        ChoiceBox<String> someChoiceBox = new ChoiceBox<String>();
        return someChoiceBox;
    }

    /**
     * This method is used to update the choicebox with all the doors in the currently selected passage or chamber.
     * @param allDoors this is an arraylist of strings with all the doors in the currently selected passage or chamber.
     */
    private void updateChoiceBox(ArrayList<String> allDoors) {
        /*Reinitialize the choiceBox*/
        aChoiceBox = createChoiceBox();

        /*Run through the array of all the doors to add all the doors to the choice box*/
        for (String door: allDoors) {
           aChoiceBox.getItems().add(door);
        }
        /*Set the updated choicebox*/
        root.setRight(aChoiceBox);
    }

    /**
     *This method is used to create a listview with all the passages, and chambers in the level.
     * @return a ListView of all the chambers and passages in the level that is generated.
     */
    private ListView<String> createListView() {
        /*Create a list view*/
        ListView<String> someList = new ListView<String>();

        /*Get an ArrayList of all the Chambers*/
        ArrayList<String> allChambers = aController.getAllChambers();
        ArrayList<String> allPassages = aController.getAllPassages();

        /*Add all the chambers to the listview*/
        for (String aChamber: allChambers) {
            someList.getItems().add(aChamber);
        }
        /*Add all the passages to the listview*/
        for (String aPassage: allPassages) {
            someList.getItems().add(aPassage);
        }

        /*Add color to listview*/
        someList.getStylesheets().add("/res/stylesheet.css");
        someList.setId("listView");
        /*Return the final list*/
        return someList;
    }

    /**
     * This method is used to show the display of the selected chamber or passage.
     */
    private void displayView() {
        /*Create a gui*/
        this.setDisplayView();
        displayView = aView.getSelectedView();
        displayViewStage = new Stage();
        Scene displayScene;

        /*Create the scene and add it to the stage*/
        displayScene = new Scene(displayView, 500, 500);
        this.showStage(displayViewStage, displayScene, "Chamber/Passage View");

    }

    /**
     * This method is used to update the gui.
     */
    private void updateView() {
        /*Create a gui*/
        this.setDisplayView();
        displayView = aView.getSelectedView();
        Scene displayScene;

        /*Create the scene and add it to the stage*/
        displayScene = new Scene(displayView, 500, 500);
        displayViewStage.setScene(displayScene);
    }

    /**
     * This method is used to set the display of the chamber or passage.
     */
    private void setDisplayView() {
        /*Determine if the selected option is a chamber or a passage.*/
        /*Declare variables*/
        String option = null;

        /*Get the selected options*/
        selectedOptions = aListView.getSelectionModel().getSelectedItems();

        /*Get the first selected option*/
        if (selectedOptions.size() > 0) {
            option = selectedOptions.get(0);

            /*Call the appropriate method*/
            if (option.contains("Chamber")) {
                aView.setChamber(aController.getChamberDescription(option), aController.getAllDoors(option).size(), aController.getAllTreasure(option).size(), aController.getAllMonsters(option).size());
            } else if (option.contains("Passage")) {
                aView.setPassage(aController.getPassageDescription(option), aController.getAllDoors(option).size(), aController.getAllTreasure(option).size(), aController.getAllMonsters(option).size());
            }
        }
    }

        /**
         * This method is used to launch the gui.
         * @param args this is a string array that is passed in.
         */
    public static void main(String[] args) {
        Application.launch(args);

    }
}
