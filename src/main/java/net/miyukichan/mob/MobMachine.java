package net.miyukichan.mob;

import lombok.Getter;
import lombok.Setter;
import net.miyukichan.Main;
import net.miyukichan.entityai.MobsBase;
import net.miyukichan.utils.EntityUtil;
import net.miyukichan.utils.MathUtils;
import org.bukkit.Location;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

/**
 * A mob machine. Holding information about the mob machine such as name, parts, hasSpawned and etc.
 * You can add parts to the mob machine here as well.
 *
 * @see Part
 */
public class MobMachine {

    private final double mobHeight = -0.85;
    @Getter
    private String name;
    @Getter
    private MobsBase baseMob;
    @Getter
    private ArmorStand nameTag;
    @Getter
    private boolean spawned;
    @Getter
    @Setter
    private Map<Part, Vector> parts = new HashMap<Part, Vector>();
    @Getter
    @Setter
    private int health;
    @Getter
    @Setter
    private int damage;

    public MobMachine(String name, MobsBase baseMob) {
        this.name = name;
        this.baseMob = baseMob;
        this.health = 10;
        this.damage = 3;
    }

    public String getName() {
        return name;
    }

    public MobsBase getBaseMob() {
        return baseMob;
    }

    public void setBaseMob(MobsBase baseMob) {
        this.baseMob = baseMob;
    }

    public ArmorStand getNameTag() {
        return nameTag;
    }

    public void setNameTag(ArmorStand nameTag) {
        this.nameTag = nameTag;
    }

    public boolean isSpawned() {
        return spawned;
    }

    public void setSpawned(boolean spawned) {
        this.spawned = spawned;
    }

    public Map<Part, Vector> getParts() {
        return parts;
    }

    public void setParts(Map<Part, Vector> parts) {
        this.parts = parts;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getMobHeight() {
        return mobHeight;
    }

    /**
     * Adding part to the mob machine using offset.
     *
     * @param part   The part that will be added.
     * @param offset The location of the part relevant to the Zombie base.
     */
    public void addPart(Part part, Vector offset) {
        this.parts.put(part, offset.add(new Vector(0, mobHeight, 0)));
    }

    /**
     * Spawning the mob machine with all the parts.
     * Calculate parts spinning via entity's yaw.
     *
     * @param spawnLocation The spawn location of the mob machine.
     * @return The entity of the mob machine.
     */
    public LivingEntity spawn(Location spawnLocation) {
        final LivingEntity mob = (LivingEntity) getBaseMob().spawnEntity(spawnLocation);

        if (mob instanceof Attributable) {
            ((Attributable) mob).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(this.health);
            //((Attributable) mob).getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(this.damage);
        }

        this.nameTag = EntityUtil.spawnCustomArmorStand(mob.getEyeLocation().clone().add(0, mobHeight, 0), false, this.name);

        for (Part p : parts.keySet()) {
            p.spawnPart(mob.getLocation().clone().add(parts.get(p)));
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                if ((!mob.isValid()) || mob.isDead() || mob == null) {
                    Main.getInstance().getMobsManager().remove(MobMachine.this);
                    cancel();
                }

                Location loc = mob.getEyeLocation();
                loc.setPitch(0.0F);
                loc.setYaw(loc.getYaw());
                Vector v1 = loc.getDirection().normalize().multiply(-0.09D);
                v1.setY(0);
                loc.add(v1);

                for (Part mp : parts.keySet()) {
                    Vector offset = parts.get(mp);

                    //rotate parts around the mob while mob is rotating.
                    Vector v = new Vector(offset.getX(), 0, offset.getZ());
                    v = MathUtils.rotateAroundAxisY(v, -loc.getYaw() * (Math.PI / 180));
                    v.setY(offset.getY());
                    loc.add(v);

                    mp.getArmorstand().teleport(loc);

                    loc.subtract(v);
                }
                nameTag.teleport(mob.getEyeLocation().add(0, mobHeight, 0));
            }
        }.runTaskTimer(Main.getInstance(), 0, 1);

        this.spawned = true;

        return mob;
    }
}