package bwapi;

public enum DamageType {
    Independent(0),
    Explosive(1),
    Concussive(2),
    Normal(3),
    Ignore_Armor(4),
    None(5),
    Unknown(6);

    public final int id;

    DamageType(int id) {
        this.id = id;
    }
}