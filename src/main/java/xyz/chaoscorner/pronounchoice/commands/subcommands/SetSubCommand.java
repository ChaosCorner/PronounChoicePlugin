package xyz.chaoscorner.pronounchoice.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.chaoscorner.pronounchoice.services.PronounGroupService;
import xyz.chaoscorner.pronounchoice.services.UserPronounService;
import xyz.chaoscorner.pronounchoice.types.SubCommand;
import xyz.chaoscorner.pronounchoice.types.exceptions.LuckPermsGroupNotPresentException;
import xyz.chaoscorner.pronounchoice.types.models.PronounGroup;
import xyz.chaoscorner.pronounchoice.types.models.SubCommandArg;

import java.util.List;

public class SetSubCommand extends SubCommand {
    private final PronounGroupService pronounGroupService;
    private final UserPronounService userPronounService;

    public SetSubCommand(PronounGroupService pronounGroupService, UserPronounService userPronounService) {
        super("set", "Sets your pronouns", List.of(
                new SubCommandArg("name", true, ""),
                new SubCommandArg("player", false, "pronoun.set.others")
        ), "pronoun.set.self");

        this.pronounGroupService = pronounGroupService;
        this.userPronounService = userPronounService;
    }

    @Override
    public String isExecutable(CommandSender sender, String[] args) {
        if (!sender.hasPermission(requiredPermission())) {
            return "You do not have the required permissions to run this";
        }

        if (args.length < 2 || args.length > 3) {
            return "You provided an incorrect amount of arguments";
        }

        if (args.length == 3) {
            if (!sender.hasPermission(subCommandArgs().get(1).requiredPermission())) {
                return "You do not have the required permissions to run this";
            }

            if (Bukkit.getPlayerExact(args[2]) == null) {
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

        PronounGroup pronounGroup = pronounGroupService.getPronounGroupByName(args[1]);

        if (pronounGroup == null) {
            sender.sendMessage(String.format("%sCannot find given pronouns", ChatColor.RED));
            return;
        }

        Player target = args.length == 3 ? Bukkit.getPlayerExact(args[2]) : (Player) sender;

        try {
            userPronounService.setUserPronounGroup(target, pronounGroup);
        } catch (LuckPermsGroupNotPresentException ex) {
            sender.sendMessage(String.format("%sCannot find given pronouns", ChatColor.RED));
            return;
        }

        String message = String.format("%sPronouns set successfully", ChatColor.GREEN);
        if (sender instanceof Player) {
            sender.sendMessage(message);
        } else {
            Bukkit.getLogger().info(message);
        }
    }
}
