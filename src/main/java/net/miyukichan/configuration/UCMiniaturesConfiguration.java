package net.miyukichan.configuration;

import lombok.Getter;
import lombok.Setter;
import net.miyukichan.mob.Part;

import java.util.ArrayList;
import java.util.List;

/**
 * A miniature mob configuration. Holding information about mob configuration.
 * Used to load mobs from the configuration files.
 */
public class UCMiniaturesConfiguration {

    public String getNameID() {
        return nameID;
    }

    public void setNameID(String nameID) {
        this.nameID = nameID;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public boolean isSmall() {
        return small;
    }

    public void setSmall(boolean small) {
        this.small = small;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Getter
    private String nameID;
    @Getter
    private List<Part> parts;
    @Getter
    @Setter
    boolean small;
    @Getter
    @Setter
    private int health;
    @Getter
    @Setter
    private String displayName;
    @Getter
    @Setter
    private int damage;

    /**
     * Create miniature mob configuration.
     *
     * @param nameID The string ID of the mob, the section name of each miniature mob.
     */
    UCMiniaturesConfiguration(String nameID) {
        this.nameID = nameID;
        this.parts = new ArrayList<Part>();
    }

    /**
     * Adding part to the parts List.
     *
     * @param part The part (armor stand)
     */
    void addPart(Part part) {
        this.parts.add(part);
    }

}

