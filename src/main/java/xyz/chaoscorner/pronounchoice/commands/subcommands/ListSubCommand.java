package xyz.chaoscorner.pronounchoice.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.chaoscorner.pronounchoice.PronounChoice;
import xyz.chaoscorner.pronounchoice.types.SubCommand;
import xyz.chaoscorner.pronounchoice.types.models.PronounGroup;
import xyz.chaoscorner.pronounchoice.types.models.SubCommandArg;

import java.util.ArrayList;

public class ListSubCommand extends SubCommand {
    public ListSubCommand() {
        super("list", "Lists available pronouns", new ArrayList<SubCommandArg>(), "pronoun.list");
    }

    @Override
    public String isExecutable(CommandSender sender, String[] args) {
        if (!requiredPermission().isBlank() && !sender.hasPermission(requiredPermission())) {
            return "You do not have the required permissions to run this";
        }

        return "";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(ChatColor.GREEN);
        sb.append("The following pronouns are available: ");
        for (PronounGroup pronounGroup : PronounChoice.pronounGroups) {
            sb.append(pronounGroup.color());
            sb.append(pronounGroup.name());
            sb.append(String.format("(%s)", pronounGroup.description()));
            sb.append(ChatColor.GREEN);
            sb.append(" | ");
        }

        String message = sb.toString();

        if (sender instanceof Player player) {
            player.sendMessage(message);
        } else {
            Bukkit.getLogger().info(message);
        }
    }
}
