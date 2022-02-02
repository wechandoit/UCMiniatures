package net.miyukichan.commands;

import net.miyukichan.Main;
import net.miyukichan.configuration.MiniatureMobConfiguration;
import net.miyukichan.consts.Constants;
import net.miyukichan.entityai.ZombieMobBaseEntity;
import net.miyukichan.mob.MobMachine;
import net.miyukichan.mob.Part;
import net.miyukichan.mob.PartType;
import net.miyukichan.utils.ItemBuilder;
import net.miyukichan.utils.UMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

/**
 * A class holding static functions in order to implement the commands from {@link MainCommand}.
 */
public final class Commands {

    /**
     * Command to spawn a mob from the configuration file settings.
     * This command using the nameID of the mob.
     *
     * @param nameID        The name ID of the miniature mob.
     * @param spawnLocation The spawn location of the mob.
     * @param commandSender The command sender of this command.
     * @return Boolean value if the mob has spawned
     */
    public static boolean spawnMob(String nameID, int amount, Location spawnLocation, CommandSender commandSender) {
        MiniatureMobConfiguration mobConfiguration = Main.getInstance().getConfigurationManager().getMobConfigurationByID(nameID);

        if (mobConfiguration == null) {
            commandSender.sendMessage(Constants.Command.MOB_CONFIGUCATION_NOT_FOUND.replace("%mob%", nameID));
            return false;
        }

        Player p = (Player) commandSender;
        MobMachine mobMachine = Main.getInstance().getMobsManager().buildMiniatureMob(mobConfiguration);
        mobMachine.spawn(p.getLocation());
        commandSender.sendMessage(Constants.Command.SPAWN_MOB_SUCCESS.replace("%mob%", nameID));

        return true;
    }

    //just a dev test command
    public static void testCommand(Location location) {
        MobMachine mobMachine = getBearMob();
        mobMachine.spawn(location);
    }

    public static MobMachine getTigerMob() {
        MobMachine mobMachine = Main.getInstance().getMobsManager().buildMiniatureMob("tiger", new ZombieMobBaseEntity());
        ItemStack headSkull = ItemBuilder.getSkullFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmM0MjYzODc0NDkyMmI1ZmNmNjJjZDliZjI3ZWVhYjkxYjJlNzJkNmM3MGU4NmNjNWFhMzg4Mzk5M2U5ZDg0In19fQ==");
        ItemStack bodySkull = ItemBuilder.getSkullFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWY0NTdkZWFjMTZkYzNkZmQ1NDdkOGYyZGY5YzA4NzM1Nzc3YmZlNTNiYzg4NDNhMGE2ODFiMmJkMDEyZTY3MyJ9fX0=");
        ItemStack legSkull = ItemBuilder.getSkullFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWY0NTdkZWFjMTZkYzNkZmQ1NDdkOGYyZGY5YzA4NzM1Nzc3YmZlNTNiYzg4NDNhMGE2ODFiMmJkMDEyZTY3MyJ9fX0=");
        mobMachine.addPart(new Part(PartType.HEAD, headSkull, false), new Vector(0, -1, 0.84));

        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, true), new Vector(0.2, -0.5, -0.15));
        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, true), new Vector(-0.2, -0.5, -0.15));
        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, true), new Vector(.2, -0.5, -0.58));
        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, true), new Vector(-0.2, -0.5, 0.58));

        mobMachine.addPart(new Part(PartType.HEAD, legSkull, true, new EulerAngle(0f, 15f, 0f)), new Vector(-.35, -0.8, -0.03));
        mobMachine.addPart(new Part(PartType.HEAD, legSkull, true, new EulerAngle(0f, -15f, 0f)), new Vector(.35, -0.8, -0.03));
        mobMachine.addPart(new Part(PartType.HEAD, legSkull, true, new EulerAngle(0f, 15f, 0f)), new Vector(.35, -0.8, -0.73));
        mobMachine.addPart(new Part(PartType.HEAD, legSkull, true, new EulerAngle(0f, -15f, 0f)), new Vector(-.35, -0.8, 0.73));
        return mobMachine;
    }

    public static MobMachine getTurtleMob() {
        MobMachine mobMachine = Main.getInstance().getMobsManager().buildMiniatureMob("turtle", new ZombieMobBaseEntity());
        ItemStack headSkull = ItemBuilder.getSkullFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMGE0MDUwZTdhYWNjNDUzOTIwMjY1OGZkYzMzOWRkMTgyZDdlMzIyZjlmYmNjNGQ1Zjk5YjU3MThhIn19fQ==");
        ItemStack bodySkull = ItemBuilder.getSkullFromBase64("eyJ0aW1lc3RhbXAiOjE1ODMwNzYzODg5NDgsInByb2ZpbGVJZCI6IjU3MGIwNWJhMjZmMzRhOGViZmRiODBlY2JjZDdlNjIwIiwicHJvZmlsZU5hbWUiOiJMb3JkU29ubnkiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzVlM2Y2NWU3NmRlZjVlZTJlMzZkMGI0N2I0MTdlZDNlNGU5ZDYxYjkxY2Q5NzVmODUwYTE5MGIzZTNiYjkxZGYifX19===");
        ItemStack legSkull = ItemBuilder.getSkullFromBase64("eyJ0aW1lc3RhbXAiOjE1ODMwNzYzODg5NDgsInByb2ZpbGVJZCI6IjU3MGIwNWJhMjZmMzRhOGViZmRiODBlY2JjZDdlNjIwIiwicHJvZmlsZU5hbWUiOiJMb3JkU29ubnkiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzVlM2Y2NWU3NmRlZjVlZTJlMzZkMGI0N2I0MTdlZDNlNGU5ZDYxYjkxY2Q5NzVmODUwYTE5MGIzZTNiYjkxZGYifX19===");
        ItemStack body = UMaterial.DAYLIGHT_DETECTOR.getItemStack();
        mobMachine.addPart(new Part(PartType.HEAD, headSkull, false), new Vector(0, -1.2, 0.15));

        mobMachine.addPart(new Part(PartType.HEAD, legSkull, true, new EulerAngle(0f, 0f, Math.toRadians(20f))), new Vector(0.35, -0.7, 0));
        mobMachine.addPart(new Part(PartType.HEAD, legSkull, true, new EulerAngle(0f, 0f, -Math.toRadians(20f))), new Vector(-0.35, -0.7, 0));
        mobMachine.addPart(new Part(PartType.HEAD, legSkull, true, new EulerAngle(0f, 0f, -Math.toRadians(20f))), new Vector(-0.35, -0.7, -0.8));
        mobMachine.addPart(new Part(PartType.HEAD, legSkull, true, new EulerAngle(0f, 0f, Math.toRadians(20f))), new Vector(0.35, -0.7, -0.8));

        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, false, new EulerAngle(0f, 0f, Math.toRadians(0))), new Vector(0, -1.4, -0.4));
        mobMachine.addPart(new Part(PartType.HEAD, body, false, new EulerAngle(0f, 0f, Math.toRadians(180f))), new Vector(0, -0.6, -0.4));
        mobMachine.addPart(new Part(PartType.HEAD, body, false, new EulerAngle(0f, 0f, Math.toRadians(180f))), new Vector(0, -0.8, -0.4));

        return mobMachine;
    }

    public static MobMachine getBearMob() {
        MobMachine mobMachine = Main.getInstance().getMobsManager().buildMiniatureMob("bear", new ZombieMobBaseEntity());
        ItemStack headSkull = ItemBuilder.getSkullFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmQ2ZWU2N2MzNTJhZGJmNmVjZWZkODk1YmI4NzdmODg0NjcwZDNjN2JhZDk4YWY0N2JmZDIyYTRlYmFhYTlkIn19fQ==");
        ItemStack bodySkull = ItemBuilder.getSkullFromBase64("eyJ0aW1lc3RhbXAiOjE1ODMwNzc0NzI0MTAsInByb2ZpbGVJZCI6ImQ2MGYzNDczNmExMjQ3YTI5YjgyY2M3MTViMDA0OGRiIiwicHJvZmlsZU5hbWUiOiJCSl9EYW5pZWwiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdiNTdmNWRmMDI3OTIzMzZjZjgyNGQzNzhkNzNmZWE3ZjVhZjU0Y2E3NmZlNzRjNGE1NDk3Mjk5NDhlYzNhNTEifX19==");
        ItemStack legSkull = ItemBuilder.getSkullFromBase64("eyJ0aW1lc3RhbXAiOjE1ODMwNzc2MTE3NDMsInByb2ZpbGVJZCI6IjczODJkZGZiZTQ4NTQ1NWM4MjVmOTAwZjg4ZmQzMmY4IiwicHJvZmlsZU5hbWUiOiJZYU9PUCIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGUwZjgxNzEwM2YwY2RiZWJmZGE0MzZhNzg2MWE2Zjk5ZDAwMWRmODhlZjA0ZWMyZDdhY2NhNGZiN2ExMzEwNSJ9fX0=");

        mobMachine.addPart(new Part(PartType.HEAD, headSkull, false), new Vector(0, -0.9, 0.18));

        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, true), new Vector(0.26,-0.4, -0.26));
        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, true), new Vector(-0.16,-0.4, -0.26));
        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, true), new Vector(-0.16,-0.4, -0.64));
        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, true), new Vector(0.26,-0.4, -0.64));

        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, true), new Vector(0.26,0, -0.26));
        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, true), new Vector(-0.16,0, -0.26));
        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, true), new Vector(-0.16,0, -0.64));
        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, true), new Vector(0.26,0, -0.64));

        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, false), new Vector(0.17,-0.8, -1.1));
        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, false), new Vector(-0.17,-0.8, -1.1));
        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, false), new Vector(-0.17,-1.2, -1.1));
        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, false), new Vector(0.17,-1.2, -1.1));

        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, false), new Vector(0.17,-0.8, -1.7));
        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, false), new Vector(-0.17,-0.8, -1.7));
        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, false), new Vector(-0.17,-1.2, -1.7));
        mobMachine.addPart(new Part(PartType.HEAD, bodySkull, false), new Vector(0.17,-1.2, -1.7));

        mobMachine.addPart(new Part(PartType.HEAD, legSkull, true, new EulerAngle(0f, Math.toRadians(-15f), 0f)), new Vector(0.4, -0.8, -1.7));
        mobMachine.addPart(new Part(PartType.HEAD, legSkull, true, new EulerAngle(0f, Math.toRadians(-15f), 0f)), new Vector(0.3, -0.8, -0.4));
        mobMachine.addPart(new Part(PartType.HEAD, legSkull, true, new EulerAngle(0f, Math.toRadians(15f), 0f)), new Vector(-0.4, -0.8, -1.7));
        mobMachine.addPart(new Part(PartType.HEAD, legSkull, true, new EulerAngle(0f, Math.toRadians(15f), 0f)), new Vector(-0.3, -0.8, -0.4));

        return mobMachine;
    }

    //memory leak testing
    public static void checkList() {
        Bukkit.broadcastMessage(Main.getInstance().getMobsManager().getMobs().toString());
    }

}
