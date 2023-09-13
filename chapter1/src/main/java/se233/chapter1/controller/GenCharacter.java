package se233.chapter1.controller;

import se233.chapter1.model.Character.BasedCharacter;
import se233.chapter1.model.Character.BattleMageCharacter;
import se233.chapter1.model.Character.MagicalCharacter;
import se233.chapter1.model.Character.PhysicalCharacter;

import java.util.Random;

public class GenCharacter {
    public static BasedCharacter setUpCharacter() {
        BasedCharacter character;
        Random rand=new Random();
        int type=rand.nextInt(3)+1;
        int basedDef=rand.nextInt(50)+1;
        int basedRes=rand.nextInt(50)+1;
        if (type == 1) {
            character = new MagicalCharacter("MagicChar1", "assets/wizard.png", basedDef, basedRes);
        } else if (type == 2) {
            character = new PhysicalCharacter("PhysicalChar1", "assets/knight.png", basedRes, basedRes);
        } else {
            character = new BattleMageCharacter("BattleMageChar1", "assets/battlemage.png", basedDef, basedRes);
        }

        character.unequipAll();

        return character;
    }
}
