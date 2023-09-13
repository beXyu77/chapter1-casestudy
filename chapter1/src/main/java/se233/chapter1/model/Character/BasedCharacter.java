package se233.chapter1.model.Character;

import se233.chapter1.model.DamageType;
import se233.chapter1.model.item.Armor;
import se233.chapter1.model.item.Weapon;

public class BasedCharacter {
    protected String name,imgpath;
    protected DamageType type;
    protected Integer fullHp,basedPow,basedDef,basedRes;
    protected Integer hp,power,defense,resistance;
    protected Weapon weapon;
    protected Armor armor;
    public String getName() {return name; }
    public String getImagepath() {return imgpath; }
    public Integer getHp() {return hp; }
    public Integer getFullHp() {return fullHp; }
    public Integer getPower() {return power; }
    public Integer getDefense() {return defense; }
    public Integer getResistance() {return resistance; }
    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
        this.power = this.basedPow + weapon.getPower();
    }
    public void equipArmor(Armor armor) {
        this.armor = armor;
        this.defense = this.basedDef + armor.getDefense();
        this.resistance = this.basedRes + armor.getResistance();
    }

    public boolean canEquipWeapon(Weapon weapon) {
        // Check if the weapon class matches the character's class
        return weapon.getDamageType() == type;
    }

    public boolean canEquipArmor(Armor armor) {
        // Check if the character is a BattleMage, in which case armor cannot be equipped
        if (this instanceof BattleMageCharacter) {
            return false;
        }
        // Check if the armor class matches the character's class
        return armor.getDamageType() == type;
    }

    public void unequipAll() {
        weapon = null;
        armor = null;
        power = basedPow;
        defense = basedDef;
        resistance = basedRes;
    }

    @Override
    public String toString() {return name; }
    public DamageType getType() {
        return type;
    }
}

