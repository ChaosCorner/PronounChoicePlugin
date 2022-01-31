package xyz.chaoscorner.pronounchoice.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.chaoscorner.pronounchoice.commands.subcommands.*;
import xyz.chaoscorner.pronounchoice.util.LuckPermsDataAccess;
import xyz.chaoscorner.pronounchoice.util.types.SubCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PronounCommandManager implements TabExecutor {
    private LuckPermsDataAccess luckPermsDA;
    private Map<String, SubCommand> subCommands = new HashMap<>();

    public PronounCommandManager(LuckPermsDataAccess luckPermsDA) {
        subCommands.put("addgroup", new AddGroupSubCommand());
        subCommands.put("removegroup", new RemoveGroupSubCommand());
        subCommands.put("set", new SetSubCommand());
        subCommands.put("remove", new RemoveSubCommand());
        subCommands.put("list", new ListSubCommand());
        subCommands.put("current", new CurrentSubCommand());
        subCommands.put("help", new HelpSubCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }
}
