package net.miyukichan.mob;

import it.unimi.dsi.fastutil.objects.ObjectBigArrayBigList;
import lombok.Getter;
import net.miyukichan.Main;
import net.miyukichan.configuration.UCMiniaturesConfiguration;
import net.miyukichan.entityai.MobsBase;
import net.miyukichan.entityai.ZombieMobBaseEntity;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * The mobs manager, manage all the miniature mobs, create and remove {@link Main}.
 */
public class MobsManager {

    public ObjectBigArrayBigList<MobMachine> getMobs() {
        return mobs;
    }

    @Getter
    private final ObjectBigArrayBigList<MobMachine> mobs = new ObjectBigArrayBigList<MobMachine>();

    /**
     * Creating a miniature mob.
     *
     * @param name    the name of the mob that will be displayed on the name tag.
     * @param baseMob the base of the mob, entity.
     * @return The Mob Machine.
     */
    public MobMachine buildMiniatureMob(String name, MobsBase baseMob) {
        MobMachine mobMachine = new MobMachine(name, baseMob);
        mobs.add(mobMachine);
        return mobMachine;
    }

    /**
     * Creating a miniature mob.
     *
     * @param mobConfiguration The mob configuration from the file.
     * @return The Mob Machine.
     */
    public MobMachine buildMiniatureMob(UCMiniaturesConfiguration mobConfiguration) {
        MobsBase mobsBase = new ZombieMobBaseEntity();
        MobMachine mobMachine = new MobMachine(mobConfiguration.getNameID() + "", mobsBase);
        for (Part part : mobConfiguration.getParts()) {
            mobMachine.addPart(new Part(part), Main.getInstance().getConfigurationManager().getOffsetFromConfiguration(mobConfiguration.getNameID()));
        }

        mobMachine.setDamage(mobConfiguration.getDamage());
        mobMachine.setHealth(mobConfiguration.getHealth());

        mobs.add(mobMachine);
        return mobMachine;
    }

    /**
     * Removing all the miniature mobs from the world.
     */
    public void removeAll() {
        //Created in order to avoid ConcurrentModificationException.
        List<MobMachine> cloneList = new ArrayList<MobMachine>();
        cloneList.addAll(mobs);
        for (MobMachine mobMachine : cloneList) {
            remove(mobMachine);
        }
    }

    /**
     * Removing a specific miniature mob.
     *
     * @param mobMachine The miniature mob.
     */
    public void remove(MobMachine mobMachine) {
        if (!(mobMachine.getBaseMob().getEntity() == null || mobMachine.getBaseMob().getEntity().isDead()))
            mobMachine.getBaseMob().getEntity().remove();

        for (Part part : mobMachine.getParts().keySet()) {
            part.getArmorstand().remove();
        }
        if (mobMachine.getNameTag() != null) mobMachine.getNameTag().remove();
        mobs.remove(mobMachine);
    }

    /**
     * Get {@link MobMachine} by {@link LivingEntity}.
     *
     * @param matchMob
     * @return
     */
    public MobMachine getMachineByEntity(LivingEntity matchMob) {
        for (MobMachine mb : mobs) {
            if (mb.getBaseMob().getEntity().equals(matchMob)) {
                return mb;
            }
        }
        return null;
    }

}
