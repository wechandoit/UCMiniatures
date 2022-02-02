package net.miyukichan.commands;

import net.miyukichan.consts.Constants;
import net.miyukichan.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The main command, handling subcommands and arguments.
 */
public class MainCommand implements CommandExecutor {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatUtils.chat(Constants.Command.NOT_PLAYER_ERROR));
            return true;
        }

        Player p = (Player) commandSender;

        String subCommand;

        if (args.length == 0) {
            p.sendMessage(ChatUtils.chat(Constants.Command.MAIN_COMMAND_USAGE));
            return true;
        }

        /* /mm spawnmob <mobname> <amount> */
        subCommand = args[0];

        if (subCommand.equals("spawnmob")) {
            String nameID = args[1];

            int amount = 0;

            try {
                amount = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                p.sendMessage(ChatUtils.chat(Constants.Command.NOT_A_NUMBER));
                return true;
            }

            Commands.spawnMob(nameID, amount, p.getLocation().add(0, 1, 0), commandSender);

        } else if (subCommand.equals("test3")) {
            Commands.testCommand(p.getLocation().add(0, 1, 0));
            commandSender.sendMessage(ChatUtils.chat(Constants.Command.SPAWN_MOB_SUCCESS.replace("%mob%", "TestMob")));
            //Commands.spawnDuck(p.getLocation().add(0,1,0));
        } else if (subCommand.equals("test2")) {
            Commands.checkList();
            //Commands.spawnDuck(p.getLocation().add(0,1,0));
        } else {
            p.sendMessage(ChatUtils.chat(Constants.Command.MAIN_COMMAND_USAGE));
        }

        return false;
    }
}
