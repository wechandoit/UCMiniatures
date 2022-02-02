package net.miyukichan.consts;

import org.bukkit.ChatColor;

/**
 * A class holding all the String constants for ease of use, simple classification and modularity.
 */
public final class Constants {

    public static class General {
        public static final String PLUGIN_NAME = "UCMiniatures";
        public static final String COMMAND_SHORTCUT = "UCMiniatures";
    }

    public static class Command {
        public static final String NOT_PLAYER_ERROR = "&cYou must be a player to use this command.";
        public static final String MAIN_COMMAND_USAGE = String.format("&cUSAGE:%s /%s <cmd>\n" + "\n" + "%sSupported Command:%s\n&a/" + General.PLUGIN_NAME + "&r - Set the current location as the hub.\n" + "/" + General.COMMAND_SHORTCUT + " spawnmob <mobname> <amount>&r - Spawn a custom monster\n", "&r", General.PLUGIN_NAME, "&l", "&r");

        public static final String NOT_A_NUMBER = "&aPlease enter a valid number!";
        public static final String MOB_CONFIGUCATION_NOT_FOUND = "&cMob configuration name &e%mob% &ccould not be found.";
        public static final String SPAWN_MOB_SUCCESS = "&a%mob% sucessfully spawned!";
    }

    // Preventing class from being initialized.
    private Constants() {
    }
}
