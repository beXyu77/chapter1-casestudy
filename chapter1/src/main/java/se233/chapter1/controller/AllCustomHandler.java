package se233.chapter1.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import se233.chapter1.Launcher;
import se233.chapter1.model.Character.BasedCharacter;
import se233.chapter1.model.item.Armor;
import se233.chapter1.model.item.BasedEquipment;
import se233.chapter1.model.item.Weapon;
import se233.chapter1.view.InventoryPane;

import java.util.ArrayList;
import java.util.Objects;

public class AllCustomHandler {

    private static BasedEquipment draggedItem; // Track the dragged item

    public static BasedEquipment getDraggedItem() {
        return draggedItem;
    }
    public static class GenCharacterHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Launcher.setMainCharacter(GenCharacter.setUpCharacter());
            Launcher.refreshPane();
        }
    }

    public static void onDragDetected(MouseEvent event, BasedEquipment equipment,ImageView imgView) {
        Dragboard db = imgView.startDragAndDrop(TransferMode.ANY);
        db.setDragView(imgView.getImage());
        ClipboardContent content = new ClipboardContent();
        content.put(equipment.DATA_FORMAT, equipment);
        db.setContent(content);
        event.consume();
    }

    public static void onDragOver(DragEvent event, String type) {
        Dragboard dragboard = event.getDragboard();
        BasedEquipment retrievedEquipment = (BasedEquipment) dragboard.getContent(BasedEquipment.DATA_FORMAT);

        public static void onDragOver(DragEvent event, String type) {
            // ...
            BasedEquipment retrievedEquipment = (BasedEquipment) dragboard.getContent(BasedEquipment.DATA_FORMAT);

            if (dragboard.hasContent(BasedEquipment.DATA_FORMAT)) {
                // Check if the character can equip the weapon or armor based on its class
                if (retrievedEquipment instanceof Weapon) {
                    if (Launcher.getMainCharacter().canEquipWeapon((Weapon) retrievedEquipment)) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                } else if (retrievedEquipment instanceof Armor) {
                    if (Launcher.getMainCharacter().canEquipArmor((Armor) retrievedEquipment)) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                }
            }
        }
    }


    public static void onDragDropped(DragEvent event, Label lbl, StackPane imgGroup) {
        boolean dragComplete = false;
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedEquipment> allEquipments = Launcher.getAllEquipments();
        if (dragboard.hasContent(BasedEquipment.DATA_FORMAT)) {
            BasedEquipment retrievedEquipment = (BasedEquipment)dragboard.getContent(BasedEquipment.DATA_FORMAT);
            BasedCharacter character = Launcher.getMainCharacter();
            if (retrievedEquipment.getClass().getSimpleName().equals("Weapon")) {
                Weapon equippedWeapon = (Weapon) Launcher.getEquippedWeapon();
                if (Launcher.getEquippedWeapon() != null)
                    allEquipments.add(Launcher.getEquippedWeapon());
                Launcher.setEquippedWeapon((Weapon) retrievedEquipment);
                character.equipWeapon((Weapon) retrievedEquipment);
            } else if (retrievedEquipment.getClass().getSimpleName().equals("Armor")) {
                Armor equippedArmor = (Armor) Launcher.getEquippedArmor();
                if (equippedArmor != null) {
                    allEquipments.add(equippedArmor);
                }
                Launcher.setEquippedArmor((Armor) retrievedEquipment);
                character.equipArmor((Armor) retrievedEquipment);
            }
            Launcher.setMainCharacter(character);
            Launcher.setAllEquipments(allEquipments);
            Launcher.refreshPane();
            ImageView imgView = new ImageView();
            if (imgGroup.getChildren().size()!=1) {
                imgGroup.getChildren().remove(1);
                Launcher.refreshPane();
            }
            lbl.setText(retrievedEquipment.getClass().getSimpleName() + ":\n" + retrievedEquipment.getName());
            imgView.setImage(new Image(Launcher.class.getResource(retrievedEquipment.getImagepath()).toString()));
            imgGroup.getChildren().add(imgView);
            dragComplete = true;
        }
        event.setDropCompleted(dragComplete);
        if (!dragComplete) {
            BasedEquipment retrievedEquipment = (BasedEquipment) dragboard.getContent(BasedEquipment.DATA_FORMAT);
            if (retrievedEquipment != null) {
                InventoryPane inventoryPane = Launcher.getInventoryPane();
                inventoryPane.addItem(retrievedEquipment);

                // Regenerate the character
                Launcher.setMainCharacter(GenCharacter.setUpCharacter());
                Launcher.refreshPane();
            }
        }

    }
    public static void onEquipDone(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedEquipment> allEquipments = Launcher.getAllEquipments();
        BasedEquipment retrievedEquipment = (BasedEquipment)dragboard.getContent(BasedEquipment.DATA_FORMAT);
        int pos = -1;
        for (int i = 0; i < allEquipments.size(); i++) {
            if (allEquipments.get(i).getName().equals(retrievedEquipment.getName())) {
                pos = i;
            }
        }
        if (pos != -1) {
            allEquipments.remove(pos);
        }
        Launcher.setAllEquipments(allEquipments);
        Launcher.refreshPane();
    }
}
