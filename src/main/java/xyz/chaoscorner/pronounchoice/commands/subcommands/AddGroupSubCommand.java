package xyz.chaoscorner.pronounchoice.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.chaoscorner.pronounchoice.services.PronounGroupService;
import xyz.chaoscorner.pronounchoice.types.*;
import xyz.chaoscorner.pronounchoice.types.exceptions.LuckPermsGroupPresentException;
import xyz.chaoscorner.pronounchoice.types.models.PronounGroup;
import xyz.chaoscorner.pronounchoice.types.models.SubCommandArg;
import xyz.chaoscorner.pronounchoice.util.Helpers;

import java.util.Arrays;
import java.util.logging.Level;

public class AddGroupSubCommand extends SubCommand {
    private PronounGroupService pronounGroupService;

    public AddGroupSubCommand(PronounGroupService pronounGroupService) {
        super("addgroup", "Adds a pronoun set to LuckPerms", Arrays.asList(
                new SubCommandArg("name", true, ""),
                new SubCommandArg("description", true, ""),
                new SubCommandArg("color", false, "")
        ), "pronoun.addgroup");

        this.pronounGroupService = pronounGroupService;
    }

    @Override
    public void execute(CommandSender sender, String ...args) {
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
            pronounGroupService.createPronounGroup(args[1], args[2], args.length == 4 ? ChatColor.valueOf(args[3].trim()) : ChatColor.WHITE);
        } catch (LuckPermsGroupPresentException ex) {
            sender.sendMessage(String.format("%sThere was an issue with your request.", ChatColor.RED));
            Bukkit.getLogger().log(Level.WARNING, "Command no work", ex);
        }
    }

    @Override
    public String isExecutable(CommandSender sender, String[] args) {
        if (!(requiredPermission().isBlank() || sender.hasPermission(requiredPermission()))) {
            return "You do not have the required permissions to run this";
        }

        if (args.length < 3 || args.length > 4) {
            return "You provided an incorrect amount of arguments";
        }

        if (Helpers.anyStringIsNullOrEmpty(args[1], args[2])) {
            return "You didn't provide all the needed arguments";
        }

        if (!Helpers.stringIsNullOrEmpty(args[3])) {
            try {
                ChatColor color = ChatColor.valueOf(args[3].trim());

                if (!color.isColor()) {
                    return "You provided an invalid color";
                }
            } catch (IllegalArgumentException ex) {
                return "You provided an invalid color";
            }
        }

        return "";
    }
}
