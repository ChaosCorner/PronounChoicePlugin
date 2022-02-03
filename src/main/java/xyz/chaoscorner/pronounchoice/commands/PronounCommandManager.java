package xyz.chaoscorner.pronounchoice.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.chaoscorner.pronounchoice.commands.subcommands.*;
import xyz.chaoscorner.pronounchoice.services.PronounGroupService;
import xyz.chaoscorner.pronounchoice.services.UserPronounService;
import xyz.chaoscorner.pronounchoice.types.SubCommand;
import xyz.chaoscorner.pronounchoice.types.models.SubCommandArg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PronounCommandManager implements TabExecutor {
    public static final Map<String, SubCommand> subCommands = new HashMap<>();

    public PronounCommandManager(PronounGroupService pronounGroupService, UserPronounService userPronounService) {
        subCommands.put("addgroup", new AddGroupSubCommand(pronounGroupService));
        subCommands.put("removegroup", new RemoveGroupSubCommand(pronounGroupService));
        subCommands.put("set", new SetSubCommand(pronounGroupService, userPronounService));
        subCommands.put("remove", new RemoveSubCommand(userPronounService));
        subCommands.put("list", new ListSubCommand());
        subCommands.put("current", new CurrentSubCommand(userPronounService));
        subCommands.put("help", new HelpSubCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        SubCommand command = subCommands.get(args[0]);

        if (args.length > 4 || command == null) {
            sender.sendMessage(String.format("%sCommand not found", ChatColor.RED));
            command = subCommands.get("help");
        }

        command.execute(sender, args);

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }
}
