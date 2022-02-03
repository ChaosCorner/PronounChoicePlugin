package xyz.chaoscorner.pronounchoice.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.chaoscorner.pronounchoice.commands.PronounCommandManager;
import xyz.chaoscorner.pronounchoice.types.SubCommand;
import xyz.chaoscorner.pronounchoice.types.models.SubCommandArg;

import java.util.ArrayList;

public class HelpSubCommand extends SubCommand {
    public HelpSubCommand() {
        super("help", "Shows the version and usage of the plugin", new ArrayList<SubCommandArg>(), "pronoun.help");
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
        String usage = buildUsageString(sender);

        if (sender instanceof Player player) {
            player.sendMessage(usage);
        } else {
            Bukkit.getLogger().info(usage);
        }
    }

    private String buildUsageString(CommandSender sender) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ChatColor.GREEN);
        stringBuilder.append("======= PronounChoice Usage =======\n");
        for (SubCommand subCommand : PronounCommandManager.subCommands.values()) {
            if (subCommand.requiredPermission().isBlank() || sender.hasPermission(subCommand.requiredPermission())) {
                stringBuilder.append("/pronoun ");
                stringBuilder.append(subCommand.name());
                stringBuilder.append(" ");
                for (SubCommandArg arg : subCommand.subCommandArgs()) {
                    if (subCommand.requiredPermission().isBlank() || sender.hasPermission(subCommand.requiredPermission())) {
                        stringBuilder.append(arg.required() ? "<" : "[");
                        stringBuilder.append(arg.name());
                        stringBuilder.append(arg.required() ? "> " : "] ");
                    }
                }
                stringBuilder.append("- ");
                stringBuilder.append(subCommand.description());
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }
}
