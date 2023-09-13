package se233.chapter1.model.item;

public class Armor extends BasedEquipment {
    private int defense,resistance;
    public Armor(String name,int defense,int resistance,String imgpath, String damageType) {
        this.name=name;
        this.imgpath=imgpath;
        this.defense=defense;
        this.resistance=resistance;
        this.damageType = damageType;
    }
    public int getDefense() {return defense; }
    public DamageType getDamageType() {
        return damageType;
    }
    public void setDefense(int defense) {this.defense=defense; }
    public int getResistance() {return resistance; }
    public void setResistant(int resistance) {this.resistance=resistance; }
    @Override
    public String toString() {return name; }
}
