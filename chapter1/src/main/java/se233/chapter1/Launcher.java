package se233.chapter1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import se233.chapter1.controller.GenCharacter;
import se233.chapter1.controller.GenItemList;
import se233.chapter1.controller.AllCustomHandler;
import se233.chapter1.model.Character.BasedCharacter;
import se233.chapter1.model.item.Armor;
import se233.chapter1.model.item.BasedEquipment;
import se233.chapter1.model.item.Weapon;
import se233.chapter1.view.CharacterPane;
import se233.chapter1.view.EquipPane;
import se233.chapter1.view.InventoryPane;

import java.util.ArrayList;

public class Launcher extends Application {
    private static Scene mainScene;
    private static BasedCharacter mainCharacter=null;
    private static ArrayList<BasedEquipment> allEquipments=null;
    private static Weapon equippedWeapon=null;
    private static Armor equippedArmor=null;
    private static CharacterPane characterPane=null;
    private static EquipPane equipPane=null;
    private static InventoryPane inventoryPane=null;

    public static void setInventoryPane(InventoryPane pane) {
        inventoryPane = pane;
    }

    public static InventoryPane getInventoryPane() {
        return inventoryPane;
    }

    public static void setAllEquipments(ArrayList<BasedEquipment> allEquipments) {
        Launcher.allEquipments = allEquipments;
    }

    public static void setEquippedWeapon(Weapon equippedWeapon) {
        Launcher.equippedWeapon = equippedWeapon;
    }

    public static void setEquippedArmor(Armor equippedArmor) {
        Launcher.equippedArmor = equippedArmor;
    }

    public static ArrayList<BasedEquipment> getAllEquipments() {
        return allEquipments;
    }

    public static BasedEquipment getEquippedWeapon() {
        return equippedWeapon;
    }

    public static BasedEquipment getEquippedArmor() {
        return equippedArmor;
    }

    @Override
    public void start(Stage primaryStage)throws Exception{
        primaryStage.setTitle("Intro to RPG");
        primaryStage.setResizable(false);
        primaryStage.show();
        mainCharacter= GenCharacter.setUpCharacter();
        allEquipments= GenItemList.setUpItemList();
        Pane mainPane=getMainPane();
        mainScene=new Scene(mainPane);
        primaryStage.setScene(mainScene);
    }
    public Pane getMainPane() {
        BorderPane mainPane = new BorderPane();
        characterPane = new CharacterPane();
        equipPane = new EquipPane();
        inventoryPane = new InventoryPane();
        refreshPane();
        mainPane.setCenter(characterPane);
        mainPane.setLeft(equipPane);
        mainPane.setBottom(inventoryPane);

        // Set the inventoryPane in the Launcher class
        Launcher.setInventoryPane(inventoryPane);

        return mainPane;
    }
    public static void refreshPane() {
        characterPane.drawPane(mainCharacter);
        equipPane.drawPane(equippedWeapon,equippedArmor);
        inventoryPane.drawPane(allEquipments);
    }
    public static BasedCharacter getMainCharacter() {return mainCharacter; }
    public static void setMainCharacter(BasedCharacter mainCharacter) {
        Launcher.mainCharacter=mainCharacter;
    }
    public static void main(String[]args) {
        launch(args);
    }



    public static BasedEquipment getDraggedItem() {
        return AllCustomHandler.getDraggedItem();
    }

    // Add this method to add a dropped item back to the inventory list
    public static void addItemToInventory(BasedEquipment item) {
        if (item != null) {
            allEquipments.add(item);
            refreshPane();
        }
    }
}
