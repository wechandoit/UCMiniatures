package net.miyukichan.listeners;

import net.miyukichan.Main;
import net.miyukichan.mob.MobMachine;
import net.miyukichan.utils.ChatUtils;
import net.miyukichan.utils.ItemBuilder;
import net.miyukichan.utils.UMaterial;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * A listener responsible for the mob machine.
 */
public class MachineListeners implements Listener {

    /**
     * Removing mob machine from Lists.
     */
    @EventHandler
    public void MachineDeath(EntityDeathEvent e) {
        MobMachine mobMachine = Main.getInstance().getMobsManager().getMachineByEntity(e.getEntity());
        if (mobMachine == null) return;
        e.getDrops().clear();
        if (mobMachine.getName().equalsIgnoreCase("tiger"))
            e.getDrops().add(ItemBuilder.getSkullFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmM0MjYzODc0NDkyMmI1ZmNmNjJjZDliZjI3ZWVhYjkxYjJlNzJkNmM3MGU4NmNjNWFhMzg4Mzk5M2U5ZDg0In19fQ=="));
        if (mobMachine.getName().equalsIgnoreCase("turtle")) {
            e.getDrops().add(ItemBuilder.getItemStack(UMaterial.TALL_GRASS.getItemStack(), ChatUtils.chat("&aSeagrass")));
        }
        Main.getInstance().getMobsManager().remove(mobMachine);
    }
}
