package xyz.chaoscorner.pronounchoice.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.chaoscorner.pronounchoice.services.UserPronounService;
import xyz.chaoscorner.pronounchoice.types.SubCommand;
import xyz.chaoscorner.pronounchoice.types.models.PronounGroup;
import xyz.chaoscorner.pronounchoice.types.models.SubCommandArg;
import xyz.chaoscorner.pronounchoice.util.Helpers;

import java.util.Arrays;

public class CurrentSubCommand extends SubCommand {
    private final UserPronounService userPronounService;

    public CurrentSubCommand(UserPronounService userPronounService) {
        super("current", "Lists your current pronouns", Arrays.asList(
                new SubCommandArg("player", false, "pronoun.current.others")
        ), "pronoun.current.self");

        this.userPronounService = userPronounService;
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

        Player target = args.length == 2 ? Bukkit.getPlayerExact(args[1]) : (Player) sender;
        String ownerDescriber = args.length == 2 ? String.format("%s's", target.getName()) : "Your";

        PronounGroup current = userPronounService.getUserPronounGroup(target);
        ChatColor pronounColor = current == null ? ChatColor.WHITE : current.color();
        String pronounDescription = current ==  null ? "N/A" : current.description();

        String message = String.format("%s%s pronouns are currently set to %s%s", ChatColor.GREEN, ownerDescriber, pronounColor, pronounDescription);
        if (sender instanceof Player) {
            sender.sendMessage(message);
        } else {
            Bukkit.getLogger().info(message);
        }
    }

    @Override
    public String isExecutable(CommandSender sender, String[] args) {
        if (!(requiredPermission().isBlank() || sender.hasPermission(requiredPermission()))) {
            return "You do not have the required permissions to run this";
        }

        if (args.length > 2 || !(sender instanceof Player || args.length == 2)) {
            return "You provided an incorrect amount of arguments";
        }

        if (args.length == 2) {
            if (!(Helpers.stringIsNullOrEmpty(args[1]) || sender.hasPermission(subCommandArgs().get(0).requiredPermission()))) {
                return "You do not have the required permissions to run this";
            }

            if (!Helpers.stringIsNullOrEmpty(args[1]) && Bukkit.getPlayerExact(args[1]) == null) {
                return "Player not found or not online";
            }
        }

        return "";
    }
}
