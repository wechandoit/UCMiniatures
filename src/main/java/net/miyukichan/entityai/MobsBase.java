package net.miyukichan.entityai;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * An abstract class representing a mob's base
 */
public abstract class MobsBase {

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    @Getter
    public Entity entity;

    /**
     * Get the entity type.
     *
     * @return The entity type.
     */
    public abstract EntityType getEntityType();

    /**
     * Spawning the vanilla entity with invisible potion.
     *
     * @param location The spawning location
     * @return The entity.
     */
    public Entity spawnEntity(Location location) {
        Entity mob = location.getWorld().spawnEntity(location, getEntityType());

        if (mob instanceof LivingEntity)
            ((LivingEntity) mob).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));

        mob.setSilent(true);
        if (mob instanceof Ageable) {
            ((Ageable) mob).setAdult();
        }
        this.entity = mob;
        return mob;
    }


}
