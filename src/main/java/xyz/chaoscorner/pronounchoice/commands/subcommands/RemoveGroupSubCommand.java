package xyz.chaoscorner.pronounchoice.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.chaoscorner.pronounchoice.services.PronounGroupService;
import xyz.chaoscorner.pronounchoice.types.SubCommand;
import xyz.chaoscorner.pronounchoice.types.exceptions.LuckPermsGroupNotPresentException;
import xyz.chaoscorner.pronounchoice.types.models.PronounGroup;
import xyz.chaoscorner.pronounchoice.types.models.SubCommandArg;

import java.util.Arrays;
import java.util.logging.Level;

public class RemoveGroupSubCommand extends SubCommand {
    private final PronounGroupService pronounGroupService;

    public RemoveGroupSubCommand(PronounGroupService pronounGroupService) {
        super("removegroup", "Removes a pronoun group from LuckPerms", Arrays.asList(
                new SubCommandArg("name", true, "")
        ), "pronoun.removegroup");

        this.pronounGroupService = pronounGroupService;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        String executableStatus = isExecutable(sender, args);

        if (!executableStatus.isBlank()) {
            String message = String.format("%sCommand error: %s", ChatColor.RED, executableStatus);
            if (sender instanceof Player player) {
                sender.sendMessage(message);
            } else {
                Bukkit.getLogger().warning(message);
            }
            new HelpSubCommand().execute(sender, args);
            return;
        }

        try {
            PronounGroup pg = pronounGroupService.getPronounGroupByName(args[1]);
            pronounGroupService.deletePronounGroup(pg);
        } catch (LuckPermsGroupNotPresentException ex) {
            sender.sendMessage(String.format("%sThere was an issue with your request.", ChatColor.RED));
            Bukkit.getLogger().log(Level.WARNING, "Command no work", ex);
        }
    }

    @Override
    public String isExecutable(CommandSender sender, String[] args) {
        if (!sender.hasPermission(requiredPermission())) {
            return "You do not have the required permissions to run this";
        }

        if (args.length != 2) {
            return "You didn't provide all the needed arguments";
        }

        return "";
    }
}
