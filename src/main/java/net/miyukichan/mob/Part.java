package net.miyukichan.mob;

import lombok.Getter;
import lombok.Setter;
import net.miyukichan.utils.EntityUtil;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

/**
 * Part. Holding information about the mob's parts such as {@link PartType}, size, armor stand's parts poses, armor stand and etc.
 * You can add parts to the mob machine here as well.
 */
public class Part {

    @Getter
    private ArmorStand armorstand;
    @Getter
    @Setter
    private PartType partType;
    private ItemStack itemStack;
    @Getter
    @Setter
    private boolean small;
    @Getter
    @Setter
    private boolean spawned;
    @Getter
    @Setter
    private EulerAngle headPose;
    @Getter
    @Setter
    private EulerAngle rightHandPose;
    @Getter
    @Setter
    private EulerAngle leftHandPose;

    /**
     * Creating a part without any information on it.
     */
    public Part() {
    }

    /**
     * Clone the other part into new object.
     *
     * @param part The part you wish to clone.
     */
    public Part(Part part) {
        this.armorstand = part.getArmorstand();
        this.partType = part.getPartType();
        this.itemStack = part.getItemStack();
        this.small = part.isSmall();
        this.headPose = part.getHeadPose();
        this.rightHandPose = part.getRightHandPose();
        this.leftHandPose = part.getLeftHandPose();
    }

    /**
     * Creating part with default pose.
     *
     * @param partType  The part type, contains the item position.
     * @param itemStack The item of the part.
     * @param small     The size of the part.
     */
    public Part(PartType partType, ItemStack itemStack, boolean small) {
        this(partType, itemStack, small, null, null, null);
    }

    /**
     * Creating part with custom head pose.
     *
     * @param partType  The part type, contains the item position.
     * @param itemStack The item of the part.
     * @param small     The size of the part.
     * @param headPose  The position of the head.
     */
    public Part(PartType partType, ItemStack itemStack, boolean small, EulerAngle headPose) {
        this(partType, itemStack, small, headPose, null, null);
    }

    /**
     * Creating part with custom head and right hand pose.
     *
     * @param partType      The part type, contains the item position.
     * @param itemStack     The item of the part.
     * @param small         The size of the part.
     * @param headPose      The position of the head.
     * @param rightHandPose The position of the right hand.
     */
    public Part(PartType partType, ItemStack itemStack, boolean small, EulerAngle headPose, EulerAngle rightHandPose) {
        this(partType, itemStack, small, headPose, rightHandPose, null);
    }

    /**
     * Creating part with custom head, right hand and left hand pose.
     *
     * @param partType      The part type, contains the item position.
     * @param itemStack     The item of the part.
     * @param small         The size of the part.
     * @param headPose      The position of the head.
     * @param rightHandPose The position of the right hand.
     * @param leftHandPose  The position of the left hand.
     */
    public Part(PartType partType, ItemStack itemStack, boolean small, EulerAngle headPose, EulerAngle rightHandPose, EulerAngle leftHandPose) {
        this.itemStack = itemStack;
        this.small = small;
        this.partType = partType;
        this.headPose = headPose;
        this.rightHandPose = rightHandPose;
        this.leftHandPose = leftHandPose;
    }

    public ArmorStand getArmorstand() {
        return armorstand;
    }

    public void setArmorstand(ArmorStand armorstand) {
        this.armorstand = armorstand;
    }

    public PartType getPartType() {
        return partType;
    }

    public void setPartType(PartType partType) {
        this.partType = partType;
    }

    public boolean isSmall() {
        return small;
    }

    public void setSmall(boolean small) {
        this.small = small;
    }

    public boolean isSpawned() {
        return spawned;
    }

    public void setSpawned(boolean spawned) {
        this.spawned = spawned;
    }

    public EulerAngle getHeadPose() {
        return headPose;
    }

    public void setHeadPose(EulerAngle headPose) {
        this.headPose = headPose;
    }

    public EulerAngle getRightHandPose() {
        return rightHandPose;
    }

    public void setRightHandPose(EulerAngle rightHandPose) {
        this.rightHandPose = rightHandPose;
    }

    public EulerAngle getLeftHandPose() {
        return leftHandPose;
    }

    public void setLeftHandPose(EulerAngle leftHandPose) {
        this.leftHandPose = leftHandPose;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Spawning the part in the specified location
     *
     * @param spawnLocation The spawn location of the part.
     * @return If the part has spawned.
     */
    public boolean spawnPart(Location spawnLocation) {
        if (armorstand != null) return false;
        this.armorstand = EntityUtil.spawnCustomArmorStand(spawnLocation, small);

        switch (partType) {
            case HEAD:
                armorstand.setHelmet(itemStack);
                if (headPose != null) armorstand.setHeadPose(headPose);
                break;
            case LEFT_HAND:
                armorstand.getEquipment().setItemInOffHand(itemStack);
                if (leftHandPose != null)
                    armorstand.setLeftArmPose(leftHandPose);
                break;
            case RIGHT_HAND:
                armorstand.getEquipment().setItemInMainHand(itemStack);
                if (rightHandPose != null)
                    armorstand.setRightArmPose(rightHandPose);
                break;
        }

        return true;
    }
}
