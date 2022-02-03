package xyz.chaoscorner.pronounchoice.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.chaoscorner.pronounchoice.services.UserPronounService;
import xyz.chaoscorner.pronounchoice.types.SubCommand;
import xyz.chaoscorner.pronounchoice.types.exceptions.UserNoPronounException;
import xyz.chaoscorner.pronounchoice.types.models.PronounGroup;
import xyz.chaoscorner.pronounchoice.types.models.SubCommandArg;

import java.util.Arrays;

public class RemoveSubCommand extends SubCommand {
    private final UserPronounService userPronounService;

    public RemoveSubCommand(UserPronounService userPronounService) {
        super("remove", "Removes your pronouns", Arrays.asList(
                new SubCommandArg("player", false, "pronoun.remove.others")
        ), "pronoun.remove.self");

        this.userPronounService = userPronounService;
    }

    @Override
    public String isExecutable(CommandSender sender, String[] args) {
        if (!sender.hasPermission(requiredPermission())) {
            return "You do not have the required permissions to run this";
        }

        if (args.length > 2) {
            return "You provided an incorrect amount of arguments";
        }

        if (args.length == 2) {
            if (!sender.hasPermission(subCommandArgs().get(0).requiredPermission())) {
                return "You do not have the required permissions to run this";
            }

            if (Bukkit.getPlayerExact(args[1]) == null) {
                return "Player not found or not online";
            }
        } else if (!(sender instanceof Player)) {
            return "You provided an incorrect amount of arguments";
        }

        return "";
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

        try {
            userPronounService.removeUserPronounGroup(target);
        } catch (UserNoPronounException ex) {
            sender.sendMessage(String.format("%sYou cannot do that as the the target already has no pronouns.", ChatColor.RED));
            return;
        }

        String message = String.format("%sPronouns removed successfully", ChatColor.GREEN);
        if (sender instanceof Player) {
            sender.sendMessage(message);
        } else {
            Bukkit.getLogger().info(message);
        }
    }
}
